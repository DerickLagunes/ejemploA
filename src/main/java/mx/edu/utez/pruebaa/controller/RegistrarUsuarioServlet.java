package mx.edu.utez.pruebaa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pruebaa.dao.UsuarioDao;
import mx.edu.utez.pruebaa.model.Usuario;

import java.io.IOException;

@WebServlet(name="RegistrarUsuarioServlet",value = "/sign_in")
public class RegistrarUsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1) obtener la información del usuario
        int id = Integer.parseInt(req.getParameter("id"));
        //Si el id identifica a X usuario necesitamos un método
        //Para obtener su información (DAO)
        UsuarioDao dao = new UsuarioDao();
        Usuario u = dao.getOne(id);

        // 2) llevar la info a un formulario
        HttpSession sesion = req.getSession();
        sesion.setAttribute("usuario", u);
        //Aqui sera donde vamos a editar la información a modificar
        resp.sendRedirect("registroUsuario.jsp");

        // 3) update (se va a hacer en otro servlet)
    }

    //Esto es para inicio de sesión
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario u = new Usuario();
        u.setNombre(req.getParameter("nombre"));
        u.setCorreo(req.getParameter("correo"));
        if(req.getParameter("contra1").equals(req.getParameter("contra2"))){
            u.setContra(req.getParameter("contra1"));
        }else{
            //Mensaje para visar que las contras no son iguales
            resp.sendRedirect("registroUsuario.jsp");
        }
        u.setEstado(true);

        //Debemos mandar a llamar un DAO que me permita insertar
        UsuarioDao dao = new UsuarioDao();

        //Ver si esta haciendo un insert o un update
        if(req.getParameter("operacion") != "") {
            //es un update
            u.setId(Integer.parseInt(req.getParameter("operacion")));
            dao.update(u);
        }else{
            //Es un insert
            dao.insert(u);
        }
        resp.sendRedirect("index.jsp");
    }
}
