package uia.com.inventarios;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SolicitudEntregaMaterial.class, name = "SRM")
})

public class ReporteEntregaInventario implements IEntregaInventario
{
    protected NivelInventario inventario;
    protected SolicitudEntregaMaterial sem;

    public ReporteEntregaInventario(IEntregaInventario inventario)
    {
        super();
        this.inventario = (NivelInventario) new NivelInventario();
    }

    public ReporteEntregaInventario() {
        super();
    }


    public void cargaSolicitudEntrega(String nombre)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            sem =  mapper.readValue(new FileInputStream(nombre), SolicitudEntregaMaterial.class );
        }catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.sem.getInventario().print();
    }

    @Override
    public List<InfoItem> busca(int id, String descripcion, String categoria, String cantidad, String idPartida, String idSubpartida, String idCategoria)
    {
        return inventario.busca(id, descripcion, categoria, cantidad, idPartida, idSubpartida, idCategoria);
    }

    @Override
    public void serializa()
    {
    }

    @Override
    public void print() {

    }



    @Override
    public void agrega(String idPartida, String descPartida, String idSubpartida, String descSubpartida, String idCat, String descCat,
                       Lote lote, int minimoNivel)
    {
        InfoItem item = new InfoItem("Item", idPartida, descPartida, descCat,  idPartida, idSubpartida, idCat,
                lote, minimoNivel);
    }




    public void cargaSolicitudEntregaToInventario() {

        for(int i=0; i<this.sem.getInventario().getItems().size(); i++)
        {
            for (Map.Entry<String, InfoItem> partidaHash : this.sem.getInventario().getItems().entrySet())
            {
                InfoItem partida =  partidaHash.getValue();   //partida

                //Nodos subpartida
                for (Map.Entry<String, InfoItem>  subpartidaHash: partida.getItems().entrySet())
                {
                    //Nodos categoria
                    for (Map.Entry<String, InfoItem>  categoriaHash: subpartidaHash.getValue().getItems().entrySet())
                    {
                        categoriaHash.getValue().print();

                        for (Map.Entry<String, InfoItem>  itemFuenteHash: categoriaHash.getValue().getItems().entrySet())
                        {
                            itemFuenteHash.getValue().print();
                            InfoItem nodoFuente = itemFuenteHash.getValue();

                            InfoItem catObjetivo;
                            if((catObjetivo = this.inventario.busca(partidaHash.getKey(), subpartidaHash.getKey(), categoriaHash.getKey())) != null)
                            {
                                for (Map.Entry<String, InfoItem>  nodoObjetivoHash: catObjetivo.getItems().entrySet())
                                {
                                    if(nodoObjetivoHash.getKey().contentEquals(nodoFuente.getId()) && categoriaHash.getValue().getCantidadEntrega()>0)
                                    {
                                        nodoObjetivoHash.getValue().setEstatus("Entrega");
                                        categoriaHash.getValue().setCantidadEntrega(categoriaHash.getValue().getCantidadEntrega()-1);
                                    }
                                    categoriaHash.getValue().print();
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    public void cargaInventario(String nombre)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            inventario =  mapper.readValue(new FileInputStream(nombre), NivelInventario.class );
        }catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.inventario.print();

    }
}
