package ModeloPaqueteInfo;

import java.io.Serializable;

import ModeloEmpleado.Empleado;

public class PaqueteProxCliente implements Paquete,Serializable{
    private final int idDispositivo = 1;
    private final int idOperacion = 1;
    private int box;
	
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
	
	public void setBox(int box) {
		this.box = box;
	}
}
