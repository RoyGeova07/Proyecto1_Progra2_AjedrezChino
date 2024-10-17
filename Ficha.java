/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

/**
 *
 * @author royum
 */
public abstract class Ficha {
    
    protected String nombre;
    protected String Imagen;
    protected Bando color; // enum de bando color

    public Ficha(String nombre, String Imagen, Bando color) {
        this.nombre = nombre;
        this.Imagen = Imagen;
        this.color = color;
    }
    
    public abstract boolean MovimientoValidoPiezaElefante(int Frente, int Atras, int Izquierda, int Derecha );
    
    public abstract boolean MovimientoValidoPiezaGeneral(int Frente, int Atras, int Izquierda, int Derecha );
    
    public abstract boolean MovimientoValidoPiezaOficial(int Frente, int Atras, int Izquierda, int Derecha );
    
    public abstract boolean MovimientoValidoPiezaCarro(int Frente, int Atras, int Izquierda, int Derecha );
    
    public abstract boolean MovimientoValidoPiezaCallon(int Frente, int Atras, int Izquierda, int Derecha );
    
    public abstract boolean MovimientoValidoPiezaSoldado(int Frente, int Atras, int Izquierda, int Derecha );       
    

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return Imagen;
    }

    public Bando getColor() {
        return color;
    }
    
    
    
    
    
}
