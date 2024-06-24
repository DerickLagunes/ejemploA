package mx.edu.utez.pruebaa.dao;

import mx.edu.utez.pruebaa.model.Usuario;
import mx.edu.utez.pruebaa.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    //Primera parte de modificar usuario
    public Usuario getOne(int id){
        Usuario usuario = new Usuario();
        String query = "select * from Usuario where id = ?;";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setContra(rs.getString("contra"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    //Insert para un nuevo usuario
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

    //Read pero para TODOS
    public ArrayList<Usuario> getAll(){
        ArrayList<Usuario> lista = new ArrayList<>();
        String query = "select * from usuario";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setContra(rs.getString("contra"));
                u.setCorreo(rs.getString("correo"));
                u.setEstado(rs.getBoolean("estado"));
                lista.add(u);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public boolean update(Usuario u){
        boolean flag = false;
        String query = "update usuario set nombre = ?, contra = ?, correo = ? where id = ?";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getContra());
            ps.setString(3,u.getCorreo());
            ps.setInt(4,u.getId());
            if(ps.executeUpdate()>0){
                flag = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

}
