package mx.edu.utez.pruebaa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pruebaa.dao.UsuarioDao;
import mx.edu.utez.pruebaa.model.Usuario;

import java.io.IOException;

@WebServlet(name = "UsuarioServlet",value = "/login")
public class UsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Conseguimos la info del formulario
        //donde los inputs se llamen asi:
        String nombre = req.getParameter("nombre");
        String contra = req.getParameter("contra");

        UsuarioDao dao = new UsuarioDao();

        //Si el usuario esta vacio
        Usuario usr = dao.getOne(nombre, contra);
        if(usr.getNombre() == null){
            //Es porque no existe en la base de datos
            System.out.println("El usuario " + nombre + " No existe en la BD");
            resp.sendRedirect("index.html");
        }else{
            //Si existe en la base de datos
            System.out.println("El usuario " + nombre + " Si esta en la BD");
            resp.sendRedirect("usuario.html");
        }


    }

    public void destroy() {

    }

    public void init() throws ServletException {

    }
}
