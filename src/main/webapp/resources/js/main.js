//click事件不生效，原来 要在最外面添加$(function(){}),。。。。。。。
//加载完页面后，绑定事件，否则不生效。。。。。。
var cartUrl="/cart"
$(function(){
    $("#divclick").click(function(){
        alert("test")
    })
    shopCart.init();
    $(".gem_list").delegate("li","click",function(){
        _addToCart_index($(this).find("a").attr("id"),1,true);}
    )

})

function _addToCart_index(key,count,goCart){
    //cart.addToCartAjax(key,count,goCart);
    console.log(key)
    console.log(count)
    var item = {};
    item.sku = key;
    item.quantity = count;
    //console.log(item)
    if(shopCart.getTotalCount(key) < 99 ){
        shopCart.addItem(item);
    }else{
        window.location=cartUrl;
    }

};