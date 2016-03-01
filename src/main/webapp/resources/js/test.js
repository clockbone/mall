/***
 * 购物车操作模块
 *
 */
var shopCart = function(window){
    "use strict";
    //全局变量
    // note new new Date("2020-12-23") 在ie下面报错，不支持这样的语法
    var items = [];
    var cartName='TEST_CART';
    var expires = new Date( new Date().getTime()+86400000*7 );
    var debug = true,decimal = 2;
    var basic_domain = "test.clock.com";
    var options = {
        'cartName' : cartName, //cookie的名字
        'expires' : expires, //cookie失效的时间
        'debug' : debug,  //是否打印调试信息
        'decimal' : decimal, //钱的精确到小数点后的位数
        'callback' : undefined,
        'domain' : basic_domain
    };
    //商品类
    /***
     * @name item
     * @example
     item(sku, name, price, quantity)
     * @params {string} sku 商品的标示
     * @params {string} name 商品的名字
     * @param {number} price 商品的价格
     * @param {number} quantity 商品的数量
     */
    function item(sku, quantity){
        this.sku = sku;
        this.quantity = quantity;
    }
    //暴露给外部的接口方法
    return {
        inited : false,
        init: function(option){
            //判断用户是否禁用cookie
            if(!window.navigator.cookieEnabled ){
                alert('浏览器不支持cookie无法使用购物车！,请设置允许设置cookie。');
                return false;
            }
            //从cookie中获取购物车中的数据
            this.inited = true;
            if(option){
                extend(options,option);
            }
            var cookie = getCookie(options.cartName);
            if(typeof cookie === 'undefined'){
                //setCookie(options.cartName,'',{expires:options.expires,domain:options.domain});
                setCookie(options.cartName,'',{domain:options.domain});
                console.log(cookie)
            }else{
                //每个item之间用&分开，item的属性之间用|分割
                var cookie = getCookie(options.cartName);
                if(cookie){
                    var cItems = cookie.split(':');
                    for(var i=0,l=cItems.length-1;i<l;i++){
                        if(cItems[i]){
                            var cItem = cItems[i].split('_');
                            var item = {};
                            item.sku = cItem[0] || '';
                            item.quantity = cItem[1] || '';
                            items.push(item);
                            //以下两行为了清除以前cookie域名为clock.com和7天有效期
                            _delCookie(options.cartName);
                            _saveCookie();//重新存一次

                        }
                    }
                };
            };
        },
        findItem: function(sku){//根据sku标示查找商品
            //如果木有提供sku,则返回所有的item
            if(sku){
                for(var i=0,l=items.length;i<l;i++){
                    var item = items[i];
                    if(item.sku === sku){
                        return item;
                    }
                }
                return undefined;
            }else{
                return items;
            }

        },
        getItemIndex : function(sku){ //获取item在items的数组下标
            for(var i=0,l=items.length;i<l;i++){
                var item = items[i];
                if(item.sku == sku){
                    return i;
                }
            }
            //木有找到返回-1
            return -1;
        },
        addItem: function(item){ //增加一个新商品到购物车
            //添加一个商品
            if(this.findItem(item.sku)){
                if(options.debug){
                    _log('商品已经存在了，修改商品的数量');
                    var index = this.getItemIndex(item.sku);
                    items[index].quantity = parseInt(items[index].quantity) + parseInt(item.quantity);
                }
            }else{
                items.push(item);
            }
            _saveCookie();
            if(typeof(cartUrl)!='undefined'&&cartUrl!=""){
                //todo test
                //window.location=cartUrl;
            }
            return true;
        },
        delItem: function(sku){ //从购物车中删除一个商品
            //删除一个商品
            var index = this.getItemIndex(sku);
            if(index > -1){
                items.splice(index,1);
                _saveCookie();
            }else{
                if(options.debug){
                    _log('商品不存在');
                    return false;
                }
            }
        },
        updateQuantity: function(item){ //更新商品的数量
            //更新一个商品
            var index = this.getItemIndex(item.sku);
            if(index > -1){
                items[index].quantity = item.quantity;
                _saveCookie();
            }else{
                if(options.debug){
                    _log('商品不存在');
                    return false;
                }
            }
        },
        emptyCart: function(){
            //清空数组
            items.length = 0;
            _saveCookie();
        },
        checkout: function(){
            //点击结算后的回调函数
            if(options.callback){
                options.callback();
            }
        },
        getTotalCount: function(sku){
            //获取购物车商品的件数，如果传某个商品的id，那么就返回该商品的数量
            var totalCount = 0;
            if(sku){
                totalCount = (typeof this.findItem(sku) === 'undefined' ? 0 : this.findItem(sku).quantity );
            }else{
                for(var i=0,l=items.length;i<l;i++){
                    totalCount += (parseInt(items[i].quantity) === 'NaN' ? 0 : parseInt(items[i].quantity )) ;
                }
            }
            return totalCount;
        },
        getTotalNum: function(){
            //获取购物车商品种类数量
            var totalNum = items.length || 0;
            return totalNum;
        },
        getTotalPrice : function(sku){
            //获取购物车商品的总价格 ,如果传某个商品的id，那么就返回该商品的总价格
            var totalPrice = 0.0;
            if(sku){
                var num = parseInt((typeof this.findItem(sku) === 'undefined' ? 0 : this.findItem(sku).quantity )),
                    price = parseFloat((typeof this.findItem(sku) === 'undefined' ? 0 : this.findItem(sku).price ));
                num = num=== 'NaN' ? 0 : num;
                price = price === 'NaN' ? 0 : price;
                totalPrice = price * num;
            }else{
                for(var i=0,l=items.length;i<l;i++){
                    totalPrice += (parseFloat(items[i].price ) * parseInt(items[i].quantity));
                }
            }
            return totalPrice.toFixed(options.decimal);
        },
        getCookie : getCookie,
        setCookie : setCookie
    };
    function _delCookie(name){
        var exp = new Date();
        exp.setTime(exp.getTime() - 1000);
        var cval=getCookie(name);
        if(cval!=null) document.cookie= name + "="+cval+"; path=/;domain=clock.com;expires="+exp.toGMTString();
    }
    /**
     * 设置cookie
     * @name setCookie
     * @example
     setCookie(name, value[, options])
     * @params {string} name 需要设置Cookie的键名
     * @params {string} value 需要设置Cookie的值
     * @params {string} [path] cookie路径
     * @params {Date} [expires] cookie过期时间
     */
    function setCookie(name, value, options) {
        options = options || {};
        var expires = options.expires || null;
        var path = options.path || "/";
        var domain = options.domain || document.domain;
        var secure = options.secure || null;
        /**
         document.cookie = name + "=" + escape(value)
         + ((expires) ? "; expires=" + expires.toGMTString() : "")
         + "; path=" + path
         + "; domain=" + domain ;
         + ((secure) ? "; secure" : "");
         */
        //var str = name + "=" + value + ((expires) ? "; expires=" + expires.toGMTString() : "") + "; path=/;domain="+domain+"";
        var str = name + "=" + value + "; path=/;domain="+domain+"";
        document.cookie = str;
        console.log(str)
        console.log( document.cookie )
    };

    /**
     * 获取cookie的值
     * @name getCookie
     * @example
     getCookie(name)
     * @param {string} name 需要获取Cookie的键名
     * @return {string|null} 获取的Cookie值，获取不到时返回null
     */
    function getCookie(name) {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) {
            return decodeURIComponent(arr[2]);
        }
        return undefined;
    };

    //***********************私有方法********************/
    function _saveCookie(){
        var i=0,l=items.length;
        if(l>0){
            var tItems = [];
            var str = "";
            for(;i<l;i++){
                var item = items[i];
                var str_i = item.sku + '_' + item.quantity;
                str += str_i+':'
            };
            //setCookie(options.cartName, str, {expires:options.expires,domain:options.domain});
            console.log("cookie str=")
            console.log(str)
            console.log("options=")
            console.log(options)
            setCookie(options.cartName, str, {domain:options.domain});
        }else{
            //setCookie(options.cartName, '', {expires:options.expires,domain:options.domain});
            setCookie(options.cartName, '', {domain:options.domain});
        }

    };

    //***********************工具方法********************/
    //显示调试信息
    function _log(info){
        if(typeof console != 'undefined'){
            console.log(info);
        }
    };
    //继承属性
    function extend(destination, source) {
        for ( var property in source) {
            destination[property] = source[property];
        }
    };
}(typeof window === 'undifined' ? this: window);



