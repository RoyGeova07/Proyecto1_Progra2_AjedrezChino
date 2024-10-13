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
    public static String[] nombres = new String[50];
    public static String[] contrasenas = new String[50];
    public static String[] fechaIngreso = new String[50];
    public static int[] puntosUsuario = new int[100]; 

    public static boolean[] Activas = new boolean[50];

  @Override
    public boolean AgregarUsuario(String NombreUsuario, String Contrasena, String FechaIngreso) {
        
        if(contador >= 50){
            return false;
        }
        
        for (int i = 0; i < contador; i++) {
          if(nombres[i] != null && nombres[i].equals(NombreUsuario)){
              return false;
          }
      }
        
        nombres[contador] = NombreUsuario;
        contrasenas[contador] = Contrasena;
        fechaIngreso[contador] =  FechaIngreso;
        puntosUsuario[contador] = 0;
        contador++;
        
        return true;
        
    }
    

    @Override
    public boolean IniciarSesion(String nombre, String Contrasena) {
        for (int aceptar = 0; aceptar < contador; aceptar++) {

            if (nombres[aceptar].equals(nombre) && contrasenas[aceptar].equals(Contrasena)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean ExisteUsuario(String Nombre) {

        for (int exis = 0; exis < contador; exis++) {
            if (nombres[exis].equals(Nombre)) {
                return true;
            }
        }
        return false;

    }

       public void Puntos(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (nombres[i] != null && nombres[i].equals(nombre)) {
                puntosUsuario[i] += 3;
            }
        }
    }


       

}
