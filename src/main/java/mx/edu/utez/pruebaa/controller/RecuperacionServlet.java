package mx.edu.utez.pruebaa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pruebaa.dao.UsuarioDao;
import mx.edu.utez.pruebaa.model.Usuario;
import mx.edu.utez.pruebaa.utils.SendMail;
import mx.edu.utez.pruebaa.utils.SimpleRandomStringGenerator;

import java.io.IOException;

@WebServlet(name = "RecuperacionServlet",value = "/solicitudRecuperacion")
public class RecuperacionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1) Checar que el usuario tenga una cuenta en nuestro sistema
        String correo = req.getParameter("correo");
        UsuarioDao dao = new UsuarioDao();
        Usuario u = dao.getOne(correo);
        if(u.isEstado()){
            //Significa que además de que si existe si puede cambiar su contraseña
            //2) Generar un código unico para ese usuario
            String codigo = SimpleRandomStringGenerator.generateRandomString(256);
            //3) Insertar ese código en la base de datos
            dao.insertCodigo(u,codigo);
            //4) Generar un correo electronico donde exista un enlace a un Servlet que maneje el código
            SendMail sm = new SendMail();
            sm.sendEmail(
                    "dericklagunes@utez.edu.mx",
                    "Prueba de envio de mensaje",
                    "<h1>Hola mundo</h1>" +
                            "<p>Este es mi primer correo electronico desde Java</p>" +
                            "<a href=\"solicitudRecuperacion?codigo="+codigo+"\">Click aqui para recuperar la contraseña</a>",
                    null
            );
        }else{
            //No existe o no puede cambiar su contraseña
            req.getSession().setAttribute("mensaje","El usuario no existe en la BD");
            resp.sendRedirect("index.jsp");
        }

    }


    //5) Mandar al usuario a cambiar su contraseña (vista recuperacion.jsp)

}
