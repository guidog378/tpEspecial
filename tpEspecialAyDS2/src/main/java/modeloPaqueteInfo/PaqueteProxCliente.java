package modeloPaqueteInfo;

import java.io.Serializable;

import modeloEmpleado.Empleado;

public class PaqueteProxCliente implements IPaquete{
    private final int idOperacion = 1;
    private int box;
    private int DNIcliente;
	
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

	public int getDNIcliente() {
		return DNIcliente;
	}

	public void setDNIcliente(int DNIcliente) {
		this.DNIcliente = DNIcliente;
	}
}
