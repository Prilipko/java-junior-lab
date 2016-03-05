<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 20.05.2015
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<b>PRODUCT PAGE</b>
<br/> id: ${product.id}
<br/> name: ${product.name}
<br/>
<a href="/bucket.do?id=${product.id}&num=5">+5</a>
<a href="/bucket.do?id=${product.id}&num=1">+1</a>
<a href="/bucket.do?id=${product.id}&num=-1">-1</a>
<a href="/bucket.do?id=${product.id}&num=-5">-5</a>
<br/>
<br/><a href="/allProduct.do">All products</a>
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
</body>
</html>
