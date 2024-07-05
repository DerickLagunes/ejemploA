<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="updateContra">
        <label>Ingresa tu nueva contraseña: </label>
        <input type="password" name="contra" required>
        <br>
        <input type="hidden" name="codigo" value="<%=request.getParameter("codigo")%>">
        <input type="submit" value="Cambiar contraseña">
    </form>
</body>
</html>
