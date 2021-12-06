package CountBank;

import java.io.Serializable;

public class Articulo implements Serializable {
    private String Nombre,Descripcion;
    private double Precio;
    private int img;
    public Articulo(String Nombre,String Descripcion,double precio,int img){
        this.Nombre=Nombre;
        this.Descripcion=Descripcion;
        this.Precio=precio;
        this.img=img;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public double getPrecio() {
        return Precio;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getDescripcion() {
            return Descripcion;
    }
}
