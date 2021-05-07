package ModeloPaqueteInfo;

import java.io.Serializable;

public class PaqueteNuevoCliente implements IPaquete,Serializable {
    private final int idOperacion = 1;
    private int dni;

    public PaqueteNuevoCliente(int dni){
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
