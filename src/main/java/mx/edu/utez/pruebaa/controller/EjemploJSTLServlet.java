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
import java.util.ArrayList;

@WebServlet(name="EjemploJSTLServlet",value = "/ejemplojstl")
public class EjemploJSTLServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ArrayList<Usuario> lista = new ArrayList<>();

        UsuarioDao dao = new UsuarioDao();
        lista = dao.getAll();

        session.setAttribute("usuarios",lista);
        resp.sendRedirect("ejemploJSTL.jsp");
    }
}
