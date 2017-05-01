package hospitallosalpes.arquisoft.medicoapp.objects;

/**
 * Created by JuanEsteban on 29/04/2017.
 */
public class Evento {
    private String id;

    public enum TipoEvento{
        FRECUENCIACARDIACA, PRESIONSANGUINEA, NIVELDEESTRES
    }

    public enum TipoNivel{
        VERDE, AMARILLO, ROJO
    }
    private String fecha;

    private int medicion;

    private String GPS;

    private TipoEvento tipo;

    private TipoNivel nivel;


    public Evento(){

    }

    public Evento(String id, String fecha, int medicion, String gPS , TipoEvento tipo, TipoNivel nivel) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.medicion = medicion;
        this.GPS = gPS;
        this.nivel=nivel;
        this.tipo=tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMedicion() {
        return medicion;
    }

    public void setMedicion(int medicion) {
        this.medicion = medicion;
    }

    public String getGPS() {
        return GPS;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }

    public String getId() {

        return id;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public TipoNivel getNivel() {
        return nivel;
    }

    public void setNivel(TipoNivel nivel) {
        this.nivel = nivel;
    }

    public void setId(String id) {
        this.id = id;
    }
}
