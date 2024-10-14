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
public class User {
    
    public String nombre;
    public String contrasena;
    public int puntos;
    public boolean Activo;
    public Calendar FechaIngreso;

    public User(String nombre, String contrasena,int puntos,Calendar FechaIngreso) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.puntos = puntos;
        this.Activo = true;
        this.FechaIngreso = FechaIngreso;
    }

    public Calendar getFechaIngreso() {
        return FechaIngreso;
    }
    

    
    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setActivo(boolean Activo) {
        this.Activo = Activo;
    }
    
    
    
    public String getFormattedFechaIngreso() {
        int year = FechaIngreso.get(Calendar.YEAR);
        int month = FechaIngreso.get(Calendar.MONTH)+1; // Enero es 0
        int day = FechaIngreso.get(Calendar.DAY_OF_MONTH);
        return day + "/" + month + "/" + year;
    }
}
