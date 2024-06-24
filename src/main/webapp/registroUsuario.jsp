<%@ page import="mx.edu.utez.pruebaa.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    HttpSession sesion = request.getSession();
    Usuario u = (Usuario) sesion.getAttribute("usuario");
%>
    <form method="post" action="sign_in">
        <label>Ingrese su Nombre: </label>
        <input type="text" name="nombre" value="<%=u.getNombre()%>">
        <br>
        <label>Ingrese su contraseña: </label>
        <input type="password" name="contra1">
        <br>
        <label>Reingrese su contraseña: </label>
        <input type="password" name="contra2">
        <br>
        <label>Ingrese su correo: </label>
        <input type="email" name="correo" value="<%=u.getCorreo()%>">
        <br>
        <input type="hidden" name="operacion" value="<%=u.getId()%>" >
        <input type="submit" value="Aceptar" />
    </form>
</body>
</html>
