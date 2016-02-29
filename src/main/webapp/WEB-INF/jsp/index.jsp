
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<html>
  <head>
      <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.8.3.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/test.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    <title></title>
  </head>
  <body>
 this index page...

  <div class="index_tab" style="">
      <span id="divclick">testlcick </span>

      <div class="gem_list">
          <ul>
              <li>test1
                  <a  id="1" style="">
                      <div class="gem_price">10￥</div>
                  </a>

              </li>
              <li>test1
                  <a  id="2">
                      <div class="gem_price">30￥</div>
                  </a>
              </li>
              <li>test1
                  <a id="3">
                      <div class="gem_price">50￥</div>
                  </a>
              </li>
              <li>
                  <a id="4">
                      <div class="gem_price">500￥</div>
                  </a>
              </li>
              <li>
                  <a id="5">
                      <div class="gem_price">1000￥</div>
                  </a>
              </li>
              <li>
                  <a id="6" >
                      <div class="gem_price">5000￥</div>
                  </a>
              </li>
          </ul>
      </div>
  </div>


  <a href="/logout">退出</a>
  </body>
</html>
