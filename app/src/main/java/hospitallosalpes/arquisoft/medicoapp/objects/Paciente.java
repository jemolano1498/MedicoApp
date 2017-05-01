package hospitallosalpes.arquisoft.medicoapp.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by JuanEsteban on 27/04/2017.
 */
public class Paciente implements Parcelable {
    private String id;

    private String nombre;
    private int edad;
    private String fechaNacimiento;
    private String direccion;
    private int telefono;
    private int docIdentidad;
    private double montoPago;
    private String fechaPago;
    private String idDispositivo;
    private ArrayList<Reporte> historiaClinica;
    private ArrayList<Consejo> consejos;
    private ArrayList<Evento> eventos;
    private double fecuenciaMarcapasos;

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

    private String usuario;
    private String contrasenia;

    public Paciente(){

    }



    public Paciente(String id, String nombre, int edad, String fechaNacimiento, String direccion,
                    int telefono, int docIdentidad, double montoPago, String fechaPago, String idDispositivo,
                    ArrayList<Reporte> historiaClinica, ArrayList<Consejo> consejos, ArrayList<Evento> eventos,
                    double fecuenciaMarcapasos,
                    String usuario, String contrasenia) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.docIdentidad = docIdentidad;
        this.montoPago = montoPago;
        this.fechaPago = fechaPago;
        this.idDispositivo = idDispositivo;
        this.historiaClinica = historiaClinica;
        this.consejos = consejos;
        this.eventos = eventos;
        this.fecuenciaMarcapasos = fecuenciaMarcapasos;
    }


    protected Paciente(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        edad = in.readInt();
        fechaNacimiento = in.readString();
        direccion = in.readString();
        telefono = in.readInt();
        docIdentidad = in.readInt();
        montoPago = in.readDouble();
        fechaPago = in.readString();
        idDispositivo = in.readString();
        fecuenciaMarcapasos = in.readDouble();
    }

    public static final Creator<Paciente> CREATOR = new Creator<Paciente>() {
        @Override
        public Paciente createFromParcel(Parcel in) {
            return new Paciente(in);
        }

        @Override
        public Paciente[] newArray(int size) {
            return new Paciente[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public ArrayList<Reporte> getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(ArrayList<Reporte> historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public ArrayList<Consejo> getConsejos() {
        return consejos;
    }

    public void setConsejos(ArrayList<Consejo> consejos) {
        this.consejos = consejos;
    }

    public double getFecuenciaMarcapasos() {
        return fecuenciaMarcapasos;
    }

    public void setFecuenciaMarcapasos(double fecuenciaMarcapasos) {
        this.fecuenciaMarcapasos = fecuenciaMarcapasos;
    }

    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }



    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }



    public void setId(String id) {
        this.id=id;

    }

    public boolean addReporte(Reporte reporte){
        return historiaClinica.add(reporte);
    }

    public boolean addConsejo(Consejo consejo){
        return consejos.add(consejo);
    }


    public boolean addEvento(Evento evento){
        return eventos.add(evento);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeInt(edad);
        dest.writeString(fechaNacimiento);
        dest.writeString(direccion);
        dest.writeInt(telefono);
        dest.writeInt(docIdentidad);
        dest.writeDouble(montoPago);
        dest.writeString(fechaPago);
        dest.writeString(idDispositivo);
        dest.writeDouble(fecuenciaMarcapasos);
    }
}
