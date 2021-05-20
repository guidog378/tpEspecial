package informacionCompartida;

import java.io.Serializable;

import modelo.Empleado;

public class InfoProxCliente implements Informable{
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
