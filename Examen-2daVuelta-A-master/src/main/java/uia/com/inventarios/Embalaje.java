package uia.com.inventarios;

import java.io.IOException;

public class Embalaje {

    private String id;
    private String clase;
    private String name;
    private String cantidad;
    private String estatus;

    private Volumen volumen= new Volumen();

    public Embalaje(){

    }

    public Embalaje(String id, String clase, String name, String cantidad, String estatus){
        this.id= id;
        this.clase= clase;
        this.name= name;
        this.cantidad= cantidad;
        this.estatus= estatus;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
