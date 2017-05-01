package hospitallosalpes.arquisoft.medicoapp.objects;

/**
 * Created by JuanEsteban on 29/04/2017.
 */
public class Reporte {
    private String id;
    private String fecha;
    private String descripcion;
    private String idDoctor;
    private String tipo;

    public Reporte (){

    }


    public Reporte(String id, String fecha, String descripcion, String idDoctor, String tipo) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.idDoctor = idDoctor;
        this.tipo=tipo;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public void setId(String id) {
        this.id = id;
    }

}
