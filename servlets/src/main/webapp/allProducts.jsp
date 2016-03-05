<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
  <b>PRODUCTS PAGE</b>

  <ul>
      <c:forEach var="eachProduct" items="${allProducts}">
      <li>
          <a href="/product.do?id=${eachProduct.id}">${eachProduct.name}</a>
      </li>
      </c:forEach>
  </ul>
  <br/>
  <br/><b>BUCKET</b>
  <ul>
      <c:forEach items="${bucket}" var="entry">
          <li>
                  ${entry.key.name}: ${entry.value}
              (<a href="/bucket.do?id=${entry.key.id}&num=5">+5</a>
              <a href="/bucket.do?id=${entry.key.id}&num=1">+1</a>
              <a href="/bucket.do?id=${entry.key.id}&num=-1">-1</a>
              <a href="/bucket.do?id=${entry.key.id}&num=-5">-5</a>)
          </li>
      </c:forEach>
  </ul>
<br/><a href="/index.jsp">main page</a>

</body>
</html>
