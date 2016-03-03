<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/test.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/shop_cart.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript">

</script>
</head>
<body>
<div class="shop_web_bg minwidthdiv">
    <div class="shop_body_bg_a">
        <div class="shop_body_bg_b">
            <div class="warp_980">
                <div class="shop_page cl">
                    <div class="mycart">
                        <h2 class="mycart_h2">订单</h2>
                        <%-- <c:if test="${error!=null }">
                        <c:out value="${error}" />
                        </c:if> --%>
                        <table border="0" cellspacing="0" cellpadding="0" class="mycart_tab" >
                            <tr>
                                <th class="pro_title">商品名称</th>
                                <th>商品类型</th>
                                <th>单价（人民币）</th>
                                <th>数量</th>
                                <th>总价（人民币）</th>
                                <th>操作</th>
                            </tr>
                            <tbody id="cartsList">
                            <c:forEach var="var" items="${model.itemsList}" varStatus="status">
                                <tr cid="${var.key}">
                                    <td class="pro_title">${var.itemName }</td>
                                    <td class="category_name">${var.itemCategory }</td>
                                    <td><span class="renminbi d_renminbi" rel="${var.currency}">${var.price}元</span>
                                        <span class="coupon d_coupon" rel="${var.coupon}">${var.currentCoupon}</sapn></td>
                                    <td>
                                        <a title="数量 - 1" class="count_cut" href="javascript:void(0)">数量 - 1</a>
                                        <input type="text" maxlength="2" value="${var.buyCount}" name="pro_count" class="buycommoditynum"style="width: 34px;padding: 2px;">
                                        <a title="数量 + 1" class="count_plus" href="javascript:void(0)">数量 + 1</a>
                                    </td>
                                    <td><span class="t_renminbi">${var.price*var.buyCount}元</span><span class="t_coupon"></span></td>
                                    <td><span class="delete_tr" style="cursor: pointer">删除</span></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="cart_ext cl">
                            <div class="cart_ext_c">商品件数：<b id="cartcnum">${model.totalCount}</b>件
                                共计：<b id="cartctotalnum">${fn:length(model.itemsList)}</b>个商品</div>
                            <div class="cart_ext_b">总价：<span class="b_coupon"><b id="finalPoint"></b></span>
                                <span class="b_renminbi"><b id="finalPrice">${model.totalCurrency}</b>元</span></div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

