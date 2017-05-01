package hospitallosalpes.arquisoft.medicoapp.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JuanEsteban on 29/04/2017.
 */
public class Consejo implements Parcelable {
    protected Consejo(Parcel in) {
        fecha = in.readString();
        tipo = in.readString();
        descripcion = in.readString();
        idDoctor = in.readString();
    }

    public static final Creator<Consejo> CREATOR = new Creator<Consejo>() {
        @Override
        public Consejo createFromParcel(Parcel in) {
            return new Consejo(in);
        }

        @Override
        public Consejo[] newArray(int size) {
            return new Consejo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fecha);
        dest.writeString(tipo);
        dest.writeString(descripcion);
        dest.writeString(idDoctor);
    }

    public enum TipoAlerta {
        DIETA, ACTIVIDADFISICA, TOMAMEDICAMENTO,ASISTENCIACITAMEDICA
    }

    private String fecha;
    private String tipo;
    private String descripcion;
    private String idDoctor;

    public Consejo (){

    }


    public Consejo( String fecha, String descripcion, String idDoctor, String tipo) {
        super();
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.idDoctor = idDoctor;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



}
