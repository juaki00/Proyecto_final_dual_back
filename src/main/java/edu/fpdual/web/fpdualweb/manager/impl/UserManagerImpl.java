package edu.fpdual.web.fpdualweb.manager.impl;

import edu.fpdual.web.fpdualweb.dto.Usuario;
import edu.fpdual.web.fpdualweb.manager.UserManager;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserManagerImpl implements UserManager {

    /**
     *
     * @param connection conexion con la base de datos
     * @return Devuelve un Set de los usuarios registrados
     */
    @Override
    public Set<Usuario> findAllUsuario(Connection connection){

        try(Statement stm = connection.createStatement()) {

            ResultSet result = stm.executeQuery("SELECT * FROM usuarios");

            Set<Usuario> UsuarioSet = new HashSet<>();
//            result.beforeFirst();
            while (result.next()) {
                Usuario usuario = new Usuario(result);
                UsuarioSet.add(usuario);
            }

            return UsuarioSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     *
     * @param con conexion con la base de datos
     * @param nick nick del usuario
     * @return devuelve la contraseña que corresponde a ese usuario
     */
    @Override
    public String passwordFromNick(Connection con, String nick) throws SQLException {
        try (PreparedStatement prepstm = con.prepareStatement("SELECT passwd FROM usuarios WHERE nick = ?")) {

            prepstm.setString(1, nick);

            ResultSet result = prepstm.executeQuery();


            if (result.next()) {
                return result.getString(1);
            } else {
                return null;
            }

        }
    }

        /**
     *
     * @param con conexion con la base de datos
     * @param nick nick del usuario
     * @return devuele una instancia del usuario con el nick recibido
     */
    @Override
    public Usuario userFromNick(Connection con, String nick) throws SQLException {
        try(PreparedStatement prepstm = con.prepareStatement("SELECT * FROM usuarios WHERE nick = ?")) {

            prepstm.setString(1, nick);

            ResultSet result = prepstm.executeQuery();

            Usuario usuario = null;
            if (result.next()) {
                usuario = new Usuario(result);
            }

            return usuario;
        }
    }

    /**
     *
     * @param con conexion con la base de datos
     * @param nick nick del usuario
     * @return devuelve true si el usuario existe
     */
    @Override
    public boolean existeNick(Connection con, String nick) throws SQLException {
        try(PreparedStatement prepstm = con.prepareStatement("SELECT * FROM usuarios WHERE nick = ?")) {

            prepstm.setString(1, nick);

            ResultSet result = prepstm.executeQuery();

            Usuario usuario = null;
            if (result.next()) {
                usuario = new Usuario(result);
            }

            return usuario!=null;
        }
    }
    /**
     *
     * @param con conexion con la base de datos
     * @param nick nick del usuario al que se le sumaran puntos
     * @param puntos numero de puntos
     * @return devuelve true si se sumaron los puntos correctamente
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public Boolean sumaPuntos(Connection con, String nick, int puntos) {
        try (PreparedStatement prepstm = con.prepareStatement("UPDATE usuarios SET puntos = puntos + ? WHERE nick = ?")) {

            prepstm.setInt(1, puntos);
            prepstm.setString(2, nick);

            prepstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     *
     * @param con conexion con la base de datos
     * @param nick nick de usuario
     * @param nombre nombre
     * @param passwd contraseña
     * @param apellido apellido
     * @param telefono telefono
     * @param email correo electronico
     * @return devuelve true si el usuario ha sido insertado correctamente
     */
    @Override
    public boolean insertUser(Connection con, String nick, String nombre, String passwd, String apellido, String telefono, String email) {
        try(PreparedStatement prepstm = con.prepareStatement("INSERT INTO usuarios VALUES (?,?,?,?,?,?,?)")) {

            prepstm.setString(1, nick);
            prepstm.setString(2, nombre);
            prepstm.setString(3, passwd);
            prepstm.setString(4, apellido);
            prepstm.setString(5, telefono);
            prepstm.setString(6, email);
            prepstm.setInt(7, 0);

            prepstm.executeUpdate();
        }
        catch (SQLException e){
            return false;
        }
        return true;
    }

}
