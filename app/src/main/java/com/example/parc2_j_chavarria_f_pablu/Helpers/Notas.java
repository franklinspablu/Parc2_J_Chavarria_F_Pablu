package com.example.parc2_j_chavarria_f_pablu.Helpers;

public class Notas {

    String nombreMateria;
    String nota;
    String descipcion;
    int imagenMateria;
    int imagenStatus;

    public Notas(String nombreMateria, String nota, String descipcion, int imagenMateria, int imagenStatus) {
        this.nombreMateria = nombreMateria;
        this.nota = nota;
        this.descipcion = descipcion;
        this.imagenMateria = imagenMateria;
        this.imagenStatus = imagenStatus;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public int getImagenMateria() {
        return imagenMateria;
    }

    public void setImagenMateria(int imagenMateria) {
        this.imagenMateria = imagenMateria;
    }

    public int getImagenStatus() {
        return imagenStatus;
    }

    public void setImagenStatus(int imagenStatus) {
        this.imagenStatus = imagenStatus;
    }
}
