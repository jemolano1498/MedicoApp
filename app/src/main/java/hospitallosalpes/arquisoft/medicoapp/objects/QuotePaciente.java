package hospitallosalpes.arquisoft.medicoapp.objects;

/**
 * Created by JuanEsteban on 26/04/2017.
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotePaciente {

    private String estado;
    private String elemento;
    private Paciente usuario;


    public QuotePaciente() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public Paciente getUsuario() {
        return usuario;
    }

    public void setUsuario(Paciente usuario) {
        this.usuario = usuario;
    }

//    @Override
//    public String toString() {
//        return "Quote{" +
//                "estado='" + estado + '\'' +
//                "elemento='" + elemento + '\'' +
//                ", value=" + usuario +
//                '}';
//    }
}
