package mx.edu.utez.pruebaa.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SendMail {
    final private String de = "@gmail.com";
    final private String pass = "contra";

    public SendMail(){
    }

    //Este método recibe la dirección hacia donde debe ser enviado el correo, el asunto, el mensaje
    //y un archivo "FIle" para adjuntar en el correo
    //Si se desea no enviar archivo o enviar más archivos, sobreescribe el método ;)
    public void sendEmail(String para, String asunto, String mensaje, File archivo){
        //Las siguientes propiedades deben ser establecidas y pueden variar para otros dominos (fuera de gmail):
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.transport.protocol", "smtp");

        Authenticator auth = new Authenticator() {
            //Configuración de la clase Authenticator
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(de, pass);
            }
        };

        //Obtener una sesión de gmail
        Session session = Session.getInstance(props,auth);
        //Intentar hacer el mensaje
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(de));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(asunto);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mensaje, "text/html");

            MimeBodyPart adjunto = new MimeBodyPart();
            adjunto.attachFile(archivo);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(adjunto);

            message.setContent(multipart);

            Transport.send(message);

            //System.out.println("Mensaje enviado!");
        }catch(MessagingException e){ //Error en envio de mensaje
            throw new RuntimeException(e);
        }catch (IOException e){ //Error creando el mensaje
            throw new RuntimeException(e);
        }
    }
}
