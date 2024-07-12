<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <label>Seleccione usuario: </label>
    <select>
        <option value="" selected disabled>Seleccione...</option>
        <c:forEach items="${usuarios}" var="u">
            <option value="${u.id}">${u.nombre}</option>
        </c:forEach>
    </select>
</body>
</html>
