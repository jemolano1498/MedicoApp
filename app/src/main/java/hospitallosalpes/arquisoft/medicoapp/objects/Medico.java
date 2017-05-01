package hospitallosalpes.arquisoft.medicoapp.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by JuanEsteban on 26/04/2017.
 */
public class Medico implements Parcelable, Iusuario{

    private String id;
    private String nombre;
    private String nivel;
    private ArrayList<String> idPacientes;
    private int edad;
    private String fechaNacimiento;
    private String direccion;
    private int telefono;
    private int docIdentidad;
    private String tipo;
    private String usuario;
    private String contrasenia;

    public Medico(){

    }

    protected Medico(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        nivel = in.readString();
        idPacientes = in.createStringArrayList();
        edad = in.readInt();
        fechaNacimiento = in.readString();
        direccion = in.readString();
        telefono = in.readInt();
        docIdentidad = in.readInt();
        tipo = in.readString();
        usuario = in.readString();
        contrasenia = in.readString();
    }

    public static final Creator<Medico> CREATOR = new Creator<Medico>() {
        @Override
        public Medico createFromParcel(Parcel in) {
            return new Medico(in);
        }

        @Override
        public Medico[] newArray(int size) {
            return new Medico[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public ArrayList<String> getIdPacientes() {
        return idPacientes;
    }

    public void setIdPacientes(ArrayList<String> idPacientes) {
        this.idPacientes = idPacientes;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(int docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(nivel);
        dest.writeStringList(idPacientes);
        dest.writeInt(edad);
        dest.writeString(fechaNacimiento);
        dest.writeString(direccion);
        dest.writeInt(telefono);
        dest.writeInt(docIdentidad);
        dest.writeString(tipo);
        dest.writeString(usuario);
        dest.writeString(contrasenia);
    }
}
