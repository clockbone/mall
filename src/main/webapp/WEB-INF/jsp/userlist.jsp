<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user.js"></script>
    <title></title>
</head>
<body>
this user list page...
<script>
   /* $(".tr").delegate("a", "click", function(){
        alert("cick")
    });

    $("#tbody").delegate(".click","click",function(){
        console.log("tbody")
    });

    $("#tbody").delegate("td", "click", function(){ $(this).toggleClass("hover");})*/

    $(".click").live("click",function(){
        var type = $(this).data("typetag")
        console.log(type)
        switch (type){
            case 'insert':insert();break;
            case 'update':update();break;
            case 'del':del();break;
            case 'clear':clear();break;
        }
    })
    function insert(){
        if($("#userName").val()==""){
            return;
        }
        if($("#userPassword").val()=="")return
        if($("#userNickName").val()=="")return
        if($("#userAddress").val()=="")return
        if($("#userPhone").val()=="")return
        var data = $("#addUser").serialize()
        $.ajax({
            url:'add',
            data:data,
            type:'GET'
        }).done(function(data){
            alert("success!");
            location.reload();
        })

    }
    function update(){

    }
    function del(){

    }
    function clear(){
        $("#userName").val("")
        $("#userPassword").val("")
        $("#userNickName").val("")
        $("#userAddress").val("")
        $("#userPhone").val("")
    }



</script>

<div class="index_tab" style="">
   <table border="1">
       <tbody id="tbody">
     <%--  <c:forEach var="item" items="${list}" varStatus="status" begin="5">
           <li idtag="${item.value.itemId}" stag="${item.value.status}" daytag="${item.value.availableTime}"
               <c:if test="${item.value.status==2}">style='cursor: pointer;'</c:if>>
               <c:if test='${not empty  item.value.statusDesc}'>${item.value.statusDesc}</c:if>
               <c:if test='${empty item.value.statusDesc}'>未领取</c:if>
           </li>
       </c:forEach>--%>
     <tr>
         <td>userId</td>
         <td>username</td>
         <td>pwd</td>
         <td>nickname</td>
         <td>add</td>
         <td>phone</td>
         <td><a>操作</a></td>
         <td></td>
         <td><a></a></td>
     </tr>
       <c:forEach items="${userList}" var="item">
         <tr class="tr">
                  <td>${item.userId}</td>
                  <td>${item.userName}</td>
                  <td>${item.userPassword}</td>
                  <td>${item.userNickName}</td>
                  <td>${item.userAddress}</td>
                  <td>${item.userPhone}</td>
             <td ><a class="click" style="cursor:pointer" data-typetag="update">修改</a></td>
             <td><a  class="click" style="cursor:pointer" data-typetag="det">删除</a></td>
         </tr>
     </c:forEach>
     <%--${userList}--%>
       </tbody>
   </table>
</div>
<form id="addUser" name="addUser">
<div style="align-content:center">
    username:&nbsp
    <input type="text" name="userName" id="userName"><br>
    pwd:&nbsp;&nbsp;&nbsp
    <input type="text" name="userPassword" id="userPassword"><br>
    nick name:
    <input type="text" name="userNickName" id="userNickName"><br>
    address:&nbsp;&nbsp
    <input type="text" name="userAddress" id="userAddress"><br>
    user phone:
    <input type="text" name="userPhone" id="userPhone"><br>
    <a class="click" style="cursor:pointer" data-typetag="insert">新增</a>
    <a class="click" style="cursor:pointer" data-typetag="clear">clear</a>
</div>
</form>



</body>
</html>

