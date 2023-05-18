package edu.fpdual.web.fpdualweb.controller;

import edu.fpdual.web.fpdualweb.dto.Usuario;
import edu.fpdual.web.fpdualweb.connector.MySQLConnector;
import edu.fpdual.web.fpdualweb.manager.UserManager;
import edu.fpdual.web.fpdualweb.manager.impl.UserManagerImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * Controlador para la base de datos:
 * Crea la conexion y llama al manejador de la base de datos
 */
@Path("/users")
public class UserController {

    private static final UserManager userManager = new UserManagerImpl();
    private static Connection con;

    /**
     *
     * @return Devuelve todos los usuarios registrados
     */
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() throws SQLException, ClassNotFoundException {
        con = new MySQLConnector().getMySQLConnection();
        Set<Usuario> usuarios = userManager.findAllUsuario(con);

        return Response.ok().entity(usuarios).build();
    }

    /**
     *
     * @param nick nombre de usuario a buscar
     * @return devuelve la contraseña de ese nick
     */
    @POST
    @Path("/getPass/")
    public Response getPass(String nick) throws SQLException, ClassNotFoundException {
        con = new MySQLConnector().getMySQLConnection();
        if (userManager.existeNick(con, nick)) {
            Usuario userCompleted = userManager.userFromNick(con, nick);
            return Response.status(201).entity(userCompleted.getPassword()).build();
        }
        else{
            return null;
        }
    }

    /**
     *
     * @param nick nombre de usuario a buscar
     * @return devuelve una instancia de la clase Usuario que corresponde a ese nick
     */
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/userFromNick/")
    public Response userFromNick(String nick) throws SQLException, ClassNotFoundException {
        con = new MySQLConnector().getMySQLConnection();
        if (userManager.existeNick(con, nick)) {
            Usuario userCompleted = userManager.userFromNick(con, nick);
            return Response.status(201).entity(userCompleted).build();
        }
        else {
            return null;
        }
    }

    /**
     *
     * @return devuelve true si ese nick existe
     */
    @GET
    @Path("/existe/{nick}")
    public Response existeNick(@PathParam("nick") String nick) throws SQLException, ClassNotFoundException {
        con = new MySQLConnector().getMySQLConnection();
        if (!userManager.existeNick(con, nick)){
            return Response.ok().entity(false).build();
        } else {
            return Response.ok().entity(true).build();
        }
    }

    /**
     *
     * @param usuario usuario nuevo par ainsertar
     * @return true si se inserto el nuevo usuario
     */
    @POST
    @Path("/insert/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean insertUser(Usuario usuario) throws SQLException, ClassNotFoundException {
        con = new MySQLConnector().getMySQLConnection();
        return userManager.insertUser(con,usuario.getNick(), usuario.getNombre(), usuario.getPassword(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail());
    }

    /**
     *
     * @param usuario usuario al que se le sumaran puntos
     * @param p numero de puntos
     * @return devuelve true si se sumaron los puntos correctamente
     */
    @POST
    @Path("/sumaPuntos/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean sumaPuntos(Usuario usuario, @HeaderParam("puntos") int p) throws SQLException, ClassNotFoundException {
        con = new MySQLConnector().getMySQLConnection();
        return userManager.sumaPuntos(con, usuario.getNick(), p);
    }
}
