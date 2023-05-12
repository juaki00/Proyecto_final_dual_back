package edu.fpdual.web.fpdualweb.dto;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  Representacion de un usuario registrado en el sistema
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Usuario {
    private String nick;
    private String password;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private int puntos;


    public Usuario(ResultSet result) throws SQLException {
        setNick(result.getString("nick"));
        setNombre(result.getString("nombre"));
        setPassword(result.getString("passwd"));
        setApellido(result.getString("apellido"));
        setTelefono(result.getString("telefono"));
        setEmail(result.getString("email"));
        setPuntos(result.getInt("puntos"));

    }

}
