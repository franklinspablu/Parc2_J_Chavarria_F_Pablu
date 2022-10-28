package com.example.parc2_j_chavarria_f_pablu.Helpers;

public class Usuarios {
// CARPINTERIA HERMANO!!!
    String nombre;
    String cedula;
    String contrasena;
    int tipo;

    public Usuarios(String nombre,String cedula, String contrasena ,int tipo){
        this.nombre = nombre;
        this.cedula= cedula;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
