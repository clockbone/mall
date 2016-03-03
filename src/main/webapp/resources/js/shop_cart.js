$(function(){
    shopCart.init()
    //减少一个商品
    $(".count_cut").click(function(){
        var key = $(this).parent().parent().attr("cid")
        var $buycommoditynum = $(this).siblings(".buycommoditynum")
        var num = $buycommoditynum.attr("value");
        if(num>1){
           num--;
            var index = shopCart.getItemIndex(key)
            console.log(index)
            if(index==-1){
                return false;
            }
            var item={};
            item.sku=key;
            item.quantity=num;
            shopCart.updateQuantity(item)
            $buycommoditynum.val(num)
        }else{
            return false;
        }
    })
    //增加一个商品
    $(".count_plus").click(function(){
        var key = $(this).parent().parent().attr("cid")
        var $buycommoditynum = $(this).siblings(".buycommoditynum")
        var num = $buycommoditynum.attr("value");
        if(num<99){
            num++;
            var index = shopCart.getItemIndex(key)
            if(index==-1){
                return false;
            }
            var item={};
            item.sku=key;
            item.quantity=num;
            shopCart.updateQuantity(item)
            $buycommoditynum.val(num)
        }else{
            return false;
        }
    })
    //删除一个商品
    $(".delete_tr").click(function(){
        var $delete_tr = $(this).parent().parent()
        console.log("delete")
        console.log($delete_tr)
        if(!$delete_tr.attr("cid"))return;
        if($delete_tr.attr("cid")!="undefined"){
            if(window.confirm('你确定要把选中商品从购物车删除吗？')){
                $delete_tr.remove();
                shopCart.delItem($delete_tr.attr("cid"));
                //base_fun.check_total();
               // window.location.reload();
            }
        }


    });
})