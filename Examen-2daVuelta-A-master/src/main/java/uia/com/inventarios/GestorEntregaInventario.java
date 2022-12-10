package uia.com.inventarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorEntregaInventario extends ReporteEntregaInventario
    
{
    List<SolicitudEntregaMaterial> items = new ArrayList<SolicitudEntregaMaterial>();


    public GestorEntregaInventario(IEntregaInventario inventario)
    {
        super(inventario);
    }

    public GestorEntregaInventario(String nombre)
    {
        super();
        //this.gestorApartado = new GestorApartado();
    }

    public GestorEntregaInventario() {

    }


    public void serializa(SolicitudEntregaMaterial newSSM, String nom) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(nom), newSSM);
    }




    public List<SolicitudEntregaMaterial> getItems()
    {
        return this.items;
    }


    public void setItems(List<SolicitudEntregaMaterial> items) {
        this.items = items;
    }


    public SolicitudEntregaMaterial agregaSolicitudSalidaMaterial(String nomArchivo, String solicitante, String categoria) throws IOException {
        String id = nomArchivo+"_"+String.valueOf(this.getItems().size()+1)+".json";
        String existencia = "";
        IEntregaInventario inventario=null;
        SolicitudEntregaMaterial newSSM = new SolicitudEntregaMaterial(super.inventario, id, solicitante, categoria);
        return newSSM;
    }

    @Override
    public void print() {

    }



    public void serializaNivelInventario(String s) throws IOException {
        this.inventario.serializa(s);
    }
}
