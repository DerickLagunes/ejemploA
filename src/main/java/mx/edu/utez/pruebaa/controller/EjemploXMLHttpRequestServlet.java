package mx.edu.utez.pruebaa.controller;

import com.google.gson.Gson;
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

@WebServlet(name="EjemploXMLHttpRequestServlet",value = "/ejemploxml")
public class EjemploXMLHttpRequestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Usuario> lista = new ArrayList<>();

        UsuarioDao dao = new UsuarioDao();
        lista = dao.getAll();
        Gson gson = new Gson();
        String json = gson.toJson(lista);

        resp.setContentType("text/json");
        resp.getWriter().write(json);

    }
}
