

//浮动居中层
var opendiv = window.opendiv = {
    addWrap : function(){
        if(!$(".mask_div").length){
            var $wrap=$("<div class='mask_div'></div>");
            $("body").append($wrap);
        }else{
            $(".mask_div").show();
        }
    },
    removeWrap : function(){
        if($(".mask_div").length){$(".mask_div").hide();}
    },
    closediv : function($obj){
        if($obj.length){
            $obj.remove();
        }
        opendiv.removeWrap()
    },
    show : function(id,options,callback){
        var $obj=opendiv._init(id,options);
        if(options.wrapper){opendiv.addWrap();}
        $obj.css({display : "block"});
        (callback?callback:function(){})($obj);
    },
    _init : function(id,options){
        var dom=opendiv.templates.dom,
            contentReg = /(?:\{)(\w*)(?:\})/g;
        while((matchResult=contentReg.exec(opendiv.templates.dom))!==null && options[matchResult[1]]){
            dom=dom.replace(matchResult[0],options[matchResult[1]])
        };
        $dom=$(dom);
        $dom.attr("id",id).appendTo($(document).find("body"));
        $dom.css({
            width: options.width,
            height: options.height
        });
        $dom.find(".closediv").click(function(){
            opendiv.closediv($dom);
        });
        resizefn();
        $(window).resize(function() {
            resizefn();
        });
        function resizefn() {
            var marginLeft = parseInt(options.width / 2);
            var marginTop = parseInt(options.height / 2);
            var winWidth = parseInt($(window).width() / 2);
            var winHeight = parseInt($(window).height() / 2.2);
            var lefta = winWidth - marginLeft;
            var topa = winHeight - marginTop;
            if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
                scall($dom, topa, lefta);
                $(window).scroll(function() {
                    scall($dom, topa, lefta);
                })
            } else {
                $dom.css({
                    left: lefta,
                    top: topa
                });
            }
        };
        function scall(obj, topc, leftc) {
            $(".mask_div").css({
                top: $(document).scrollTop()
            });
            obj.css({
                left: leftc,
                top: $(document).scrollTop() + topc
            });
        };
        return $dom;
    },
    templates:{
        dom : "<div class='opendiv'><span class='spanclose closediv'></span><div class='openlogincon'>{content}</div></div>"
    }
};
