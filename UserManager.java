/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import java.util.Calendar;

/**
 *
 * @author royum
 */
public class UserManager implements GuardarPlayers {

    User[] registrarUsuario;
    String[] ArregloDeUsuario;
    public static Calendar[] FechaIngreso = new Calendar[50];
    int puntos = 0;
    int contador = 0;
    public static String[] nombres = new String[50];
    public static String[] contrasenas = new String[50];
    public static boolean[] Activas = new boolean[50];

    public UserManager() {
        registrarUsuario = new User[50];

    }

    @Override
    public boolean ConfirmarNombre(String nombre) {
        for (User user : registrarUsuario) {
            if (user != null) {
                if (nombre.equals(user.getNombre())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean ConfirmarContrasena(String nombre, String Contrasena) {
        for (int pase = 0; pase < registrarUsuario.length; pase++) {
            if (registrarUsuario[pase] != null) {
                if (nombre.equals(registrarUsuario[pase].nombre) && Contrasena.equals(registrarUsuario[pase].contrasena)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean AgregarUsuario(String nombre, String Contrasena,Calendar fecha) {
        if (contador >= 50) {
            return false;
        }

        for (int agrega = 0; agrega < contador; agrega++) {
            if (nombres[agrega] != null && nombres[agrega].equals(nombre)) {
                return false;
            }
        }

        nombres[contador] = nombre;
        contrasenas[contador] = Contrasena;
        Activas[contador] = true;
        FechaIngreso[contador] = fecha;
        // aqui creo un objeto User con 0 puntos
        User nuevoUsuario = new User(nombre,Contrasena,0);
        registrarUsuario[contador] = nuevoUsuario;
        contador++;
        return true;
    }

    public String[] ListaDeUsuario(String nombre) {
        int guardar = 0;
        for (int listar = 0; listar < registrarUsuario.length; listar++) {
            if (registrarUsuario[listar] != null && !nombre.equals(registrarUsuario[listar].nombre)) {
                ArregloDeUsuario[guardar] = registrarUsuario[listar].nombre;
                guardar++;
            }
        }
        return ArregloDeUsuario;
    }

    public void Puntos(String nombre) {
        for (User user : registrarUsuario) {
            if (user != null) {
                if (nombre.equals(user.getNombre())) {
                    puntos += 3;
                    user.setPuntos(puntos);
                }
            }
        }
    }

    public Calendar[] getFechaIngreso() {
        return FechaIngreso;
    }

}
