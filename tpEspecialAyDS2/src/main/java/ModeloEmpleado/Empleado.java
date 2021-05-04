package ModeloEmpleado;

import java.util.ArrayList;
import java.util.Date;

public class Empleado {
	  private static Empleado empleado = null;
      private int box;
      //Nuevos agregar al modelo de dominio.
      private ArrayList<String> clientesEsperando;
      private ArrayList<Date> timposAtencion;
      
      private Empleado() {
      }
      
      public static Empleado getInstance() {
    	  if(Empleado.empleado == null)
    		  Empleado.empleado = new Empleado();
    	  return Empleado.empleado;
      }

	public int getBox() {
		return box;
	}

	public void setBox(int box) {
		this.box = box;
	}

	public ArrayList<String> getClientesEsperando() {
		return clientesEsperando;
	}

	public void setClientesEsperando(ArrayList<String> clientesEsperando) {
		this.clientesEsperando = clientesEsperando;
	}

	public ArrayList<Date> getTimposAtencion() {
		return timposAtencion;
	}

	public void setTimposAtencion(ArrayList<Date> timposAtencion) {
		this.timposAtencion = timposAtencion;
	}
	
}
