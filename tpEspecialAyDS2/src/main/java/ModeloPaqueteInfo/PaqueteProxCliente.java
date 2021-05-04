package ModeloPaqueteInfo;

import java.io.Serializable;

import ModeloEmpleado.Empleado;

public class PaqueteProxCliente implements Paquete,Serializable{
    private final int idDispositivo = 1;
    private final int idOperacion = 1;
    private final int box = Empleado.getInstance().getBox();
	
    @Override
    public int getIdDispositivo() {
		return idDispositivo;
	}
	
    @Override
    public int getIdOperacion() {
		return idOperacion;
	}

	public int getBox() {
		return box;
	} 
}
