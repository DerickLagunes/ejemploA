<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <form method="post" action="login">
        <label>Nombre: </label>
        <input type="text" name="nombre" required>
        <br>
        <label>Contra: </label>
        <input type="password" name="contra" required>
        <br>
        <input type="submit" value="iniciar sesión">
        <%
            HttpSession sesion = request.getSession();
            String mensaje = (String) sesion.getAttribute("mensaje");
            if(mensaje != null){ %>
                <p style="color: red"><%=mensaje%></p>
            <% } %>


    </form>
    <a href="registroUsuario.jsp">Registrarme</a>
</body>
</html>