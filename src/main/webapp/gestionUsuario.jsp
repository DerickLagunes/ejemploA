<%@ page import="mx.edu.utez.pruebaa.dao.UsuarioDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mx.edu.utez.pruebaa.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Estado</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>
        </thead>
        <tbody>
            <%  // necesitamos acceder a la base de datos y obtener
                // TODOS los usuarios
                UsuarioDao dao = new UsuarioDao();
                ArrayList<Usuario> lista = dao.getAll();
                for(Usuario u : lista){//Por cada usuario de la lista %>
                <tr>
                    <td><%=u.getId()%></td>
                    <td><%=u.getNombre()%></td>
                    <td><%=u.getCorreo()%></td>
                    <td><%=u.isEstado() ? "Habilitado":"Deshabilitado"%></td>
                    <td><a href="sign_in?id=<%=u.getId()%>">Actualizar</a></td>
                    <td><a>Eliminar</a></td>
                </tr>
                <% } %>
        </tbody>
    </table>
</body>
</html>
