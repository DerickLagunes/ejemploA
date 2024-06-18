package mx.edu.utez.pruebaa.dao;

import mx.edu.utez.pruebaa.model.Usuario;
import mx.edu.utez.pruebaa.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    //CRUD para usuario
    //Read para un usuario
    public Usuario getOne(String nombre, String contra){
        Usuario usuario = new Usuario();
        String query = "select * from Usuario where nombre = ? and contra = sha2(?,256);";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,nombre);
            ps.setString(2,contra);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario.setNombre(rs.getString("nombre"));
                usuario.setContra(rs.getString("contra"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    //Encargaco de insertar un nuevo usuario
    public boolean insert(Usuario u){
        boolean flag = false;
        String query = "insert into usuario(nombre,contra,correo) values (?,sha2(?,256),?);";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getContra());
            ps.setString(3,u.getCorreo());
            if(ps.executeUpdate()>0){
                flag = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
}
