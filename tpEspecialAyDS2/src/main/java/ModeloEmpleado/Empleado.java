package ModeloEmpleado;

import java.util.ArrayList;
import java.util.Date;

import ModeloPaqueteInfo.FactoryPaquete;
import ModeloPaqueteInfo.Paquete;
import ModeloPaqueteInfo.PaqueteTiempoAtencion;
import ModeloSocket.Emisor;

public class Empleado {
	  private static Empleado empleado = null;
      private int box;
      //Nuevos agregar al modelo de dominio.
      private ArrayList<String> clientesEsperando;
      private ArrayList<TiempoAtencion> timposAtencion;
      
      private Empleado() {
    	  this.clientesEsperando = new ArrayList<String>();
    	  this.timposAtencion = new ArrayList<TiempoAtencion>();
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

	public ArrayList<TiempoAtencion> getTimposAtencion() {
		return timposAtencion;
	}

	public void setTimposAtencion(ArrayList<TiempoAtencion> timposAtencion) {
		this.timposAtencion = timposAtencion;
	}
	
	public void iniciarAtencion() {
		Cronometro.getInstance().setAndando(true);
		new Thread(Cronometro.getInstance()).start();
	}
	
	public void finalizarAtencion() {
		Cronometro cronometro = Cronometro.getInstance();
		cronometro.setAndando(false);
		TiempoAtencion time = new TiempoAtencion(cronometro.getHora(),cronometro.getMinutos(),cronometro.getSegundos());
		cronometro.setHora(0);
		cronometro.setMinutos(0);
		cronometro.setSegundos(0);	
		PaqueteTiempoAtencion paquete = (PaqueteTiempoAtencion)FactoryPaquete.getInstance(2);
		paquete.setTiempoAtencion(time);
		this.enviarPaquete(paquete);
		this.agregarTiempoAtencion(time);
	}
	
	public void avanceCliente() {
		this.enviarPaquete(FactoryPaquete.getInstance(1));
	}
	
	private void agregarTiempoAtencion(TiempoAtencion time) {
		this.timposAtencion.add(time);
	}
	
	private void enviarPaquete(Paquete paquete) {
		Emisor.getInstance().setPaqueteAEnviar(paquete);
	}
}
