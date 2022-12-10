package uia.com.inventarios;

public class Volumen extends InfoUIA {

    private String largo;
    private String ancho;
    private String alto;

    public Volumen (){ super();}

    public Volumen(String largo, String ancho, String alto){
        this.largo=largo;
        this.ancho=ancho;
        this.alto=alto;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }
}
