/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author royum
 */
public class UserManager implements GuardarPlayers {

    User[] registrarUsuario;
    String[] ArregloDeUsuario;

    public static int contador = 0;
    private static User[] usuarios = new User[50];

    @Override
    public boolean AgregarUsuario(String NombreUsuario, String Contrasena, Calendar FechaIngreso) {

        if (contador >= 50) {
            return false;
        }

        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getNombre().equals(NombreUsuario)) {
                return false;
            }
        }

        usuarios[contador] = new User(NombreUsuario, Contrasena, 0, FechaIngreso);
        contador++;

        return true;

    }

    @Override
    public User IniciarSesion(String nombre, String Contrasena) {
        for (int aceptar = 0; aceptar < contador; aceptar++) {

            if (usuarios[aceptar].getNombre().equals(nombre) && usuarios[aceptar].getContrasena().equals(Contrasena)) {
                return usuarios[aceptar];
            }

        }
        return null;
    }

    @Override
    public boolean ExisteUsuario(String Nombre) {

        for (int exis = 0; exis < contador; exis++) {
            if (usuarios[exis].getNombre().equals(Nombre)) {
                return true;
            }
        }
        return false;

    }

    public User[] getUsuarios() {
        return usuarios;
    }

}
