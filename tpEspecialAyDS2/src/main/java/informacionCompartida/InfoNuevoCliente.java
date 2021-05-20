package informacionCompartida;

import java.io.Serializable;

public class InfoNuevoCliente implements Informable {
    private final int idOperacion = 1;
    private int dni;

    public InfoNuevoCliente(int dni){
        this.dni=dni;
    }

    @Override
    public int getIdOperacion() {
        return idOperacion;
    }
    
	public int getDni() {
		return dni;
	}
}
