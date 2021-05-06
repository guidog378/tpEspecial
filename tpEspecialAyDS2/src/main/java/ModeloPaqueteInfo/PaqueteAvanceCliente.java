package ModeloPaqueteInfo;

import java.io.Serializable;

import ModeloEmpleado.Empleado;

public class PaqueteAvanceCliente implements Paquete,Serializable{
    private final int idOperacion = 1;
    private int box;
    private String dni;
	
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}
