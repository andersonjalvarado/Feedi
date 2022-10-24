package edu.puj.feedi.model;

public class Oferta {
    private String precio;
    private String cantidad;
    private String nombre;
    private int image;

    public Oferta(String nombre, String precio, String cantidad, int image) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
