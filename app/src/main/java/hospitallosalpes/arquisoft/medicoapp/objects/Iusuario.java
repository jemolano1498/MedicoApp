package hospitallosalpes.arquisoft.medicoapp.objects;

/**
 * Created by JuanEsteban on 1/05/2017.
 */
public interface Iusuario {

        public String getId();

        public String getNombre();

        public void setNombre(String nombre);

        public int getEdad();

        public void setEdad(int edad);

        public String getFechaNacimiento();

        public void setFechaNacimiento(String fechaNacimiento);

        public String getDireccion();

        public void setDireccion(String direccion);

        public int getTelefono();

        public void setTelefono(int telefono);

        public int getDocIdentidad();

        public void setDocIdentidad(int docIdentidad);

    }
