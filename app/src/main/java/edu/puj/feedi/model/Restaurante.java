package edu.puj.feedi.model;

public class Restaurante {
    private String nombreRestaurante;
    private String ofertas;
    private int image;
    private int image_ubicacion;

    public Restaurante(String nombreRestaurante, String ofertas, int image, int image_ubicacion) {
        this.nombreRestaurante = nombreRestaurante;
        this.ofertas = ofertas;
        this.image = image;
        this.image_ubicacion = image_ubicacion;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getOfertas() {
        return ofertas;
    }

    public void setOfertas(String ofertas) {
        this.ofertas = ofertas;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public int getImage_ubicacion() {
        return image_ubicacion;
    }

    public void setImage_ubicacion(int image_ubicacion) {
        this.image_ubicacion = image_ubicacion;
    }
}
