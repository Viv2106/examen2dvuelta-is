package uia.com.inventarios;

public class CondicionesAlmacenamiento{


    String tipo;
    String temperatura;
    String humedad;


    public CondicionesAlmacenamiento(){}

    public CondicionesAlmacenamiento(String tipo,String temperatura, String humedad){
        this.tipo=tipo;
        this.temperatura=temperatura;
        this.humedad=humedad;

    }





    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }
}
