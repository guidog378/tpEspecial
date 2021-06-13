package modeloInfo;

import java.util.Date;
import java.util.GregorianCalendar;

public class InfoCliente implements Informable {
    private final int idOperacion = 1;
    private int dni;
    private String nombre=null;
    private int categoria=-1;
    private Date fechaYHoraRegistro=null;

    public InfoCliente(int dni){
        this.dni=dni;
    }

    @Override
    public int getIdOperacion() {
        return idOperacion;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public Date getFechaYHoraRegistro() {
        return fechaYHoraRegistro;
    }
}
