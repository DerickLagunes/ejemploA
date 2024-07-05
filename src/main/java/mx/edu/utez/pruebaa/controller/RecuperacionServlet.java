package mx.edu.utez.pruebaa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pruebaa.dao.UsuarioDao;
import mx.edu.utez.pruebaa.model.Usuario;
import mx.edu.utez.pruebaa.utils.GmailSender;
import mx.edu.utez.pruebaa.utils.SimpleRandomStringGenerator;

import java.io.IOException;

@WebServlet(name = "RecuperacionServlet",value = "/solicitudRecuperacion")
public class RecuperacionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Checar que el código exista en la BD
        String codigo = req.getParameter("codigo");
        UsuarioDao dao = new UsuarioDao();
        if(dao.existe(codigo)){
            //Si si existe redirigir al usuario a la página de restablecimiento de contra
            resp.sendRedirect("recuperacion.jsp?codigo="+codigo);
        }else{
            //Decirle al usuario que el código no sirve o esta expirado
            req.getSession().setAttribute("mensaje", "El código no sirve o esta expirado");
            resp.sendRedirect("index.jsp");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1) Checar que el usuario tenga una cuenta en nuestro sistema
        String correo = req.getParameter("correo");
        UsuarioDao dao = new UsuarioDao();
        Usuario u = dao.getOne(correo);
        if(u.isEstado()){
            //Significa que además de que si existe si puede cambiar su contraseña
            //2) Generar un código unico para ese usuario
            String codigo = SimpleRandomStringGenerator.generateRandomString(20);
            //3) Insertar ese código en la base de datos
            dao.updateCodigo(u,codigo);
            //4) Generar un correo electronico donde exista un enlace a un Servlet que maneje el código
            try{
                GmailSender gmail = new GmailSender();
                String para = correo;
                String asunto = "Restablecimiento de contraseña";
                //req.getServletContext().getContextPath();
                String mensaje =
                        "<h1>Le enviamos es código para el restablecimiento de su contraseña</h1>\n"+
                        "<a href=\"http://localhost:8080/PruebaA_war_exploded/solicitudRecuperacion?codigo="+codigo+"\">haz click AQUI PARA RESTABLECER TU CONTRASEÑA</a>";
                gmail.sendMail(para,asunto,mensaje);
                req.getSession().setAttribute("mensaje","Favor de revisar tu correo electronico y la carpeta de SPAM");
                resp.sendRedirect("index.jsp");
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            //No existe o no puede cambiar su contraseña
            req.getSession().setAttribute("mensaje","El usuario no existe en la BD");
            resp.sendRedirect("index.jsp");
        }

    }


    //5) Mandar al usuario a cambiar su contraseña (vista recuperacion.jsp)

}
