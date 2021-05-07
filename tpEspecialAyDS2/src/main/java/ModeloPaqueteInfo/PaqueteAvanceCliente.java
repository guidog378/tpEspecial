package ModeloPaqueteInfo;

import java.io.Serializable;

import ModeloEmpleado.Empleado;

public class PaqueteAvanceCliente implements IPaquete,Serializable{
    private final int idOperacion = 1;
    private int box;
    private int dni;
	
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

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}
}
