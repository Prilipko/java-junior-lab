<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 21.05.2015
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MVC Mock View</title>
</head>
<body>
<b>MVC Mock View</b>
<br/>requestAttribute.str = ${requestAttribute.str}
<br/>requestAttribute.map["key-0"] = ${requestAttribute.map["key-0"]}
<br/>requestAttribute.mockEntityB.str = ${requestAttribute.mockEntityB.str}
<br/>sessionAttribute.array[1] = ${sessionAttribute.array[1]}
<hr/>
<jsp:useBean id="pageBean" scope="page" class="mvc.MockEntityB"></jsp:useBean>
<br/>pageBean.str = ${pageBean.str}
<hr/>
<br/>(pageBean.intValue0 gt -10) and (pageBean.intValue1 lt 10) =
${(pageBean.intValue0 gt -10) and (pageBean.intValue1 lt 10)}
<hr/>
<br/>test = ${test}
</body>
</html>
