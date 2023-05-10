package edu.fpdual.web.fpdualweb.manager;

import edu.fpdual.web.fpdualweb.dto.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public interface UserManager {


    /**
     *
     * @param connection conexion con la base de datos
     * @return Devuelve un Set de los usuarios registrados
     */
    Set<Usuario> findAllUsuario(Connection connection) throws SQLException, ClassNotFoundException;

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
    boolean insertUser(Connection con, String nick, String nombre, String passwd, String apellido, String telefono, String email)  throws SQLException, ClassNotFoundException;

    /**
     *
     * @param con conexion con la base de datos
     * @param nick nick del usuario
     * @return devuelve la contraseña que corresponde a ese usuario
     */
    String passwordFromNick(Connection con, String nick) throws SQLException;

    /**
     *
     * @param con conexion con la base de datos
     * @param nick nick del usuario
     * @return devuele una instancia del usuario con el nick recibido
     */
    Usuario userFromNick(Connection con, String nick) throws SQLException;

    /**
     *
     * @param con conexion con la base de datos
     * @param nick nick del usuario
     * @return devuelve true si el usuario existe
     */
    boolean existeNick(Connection con, String nick) throws SQLException;
}
