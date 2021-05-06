package ModeloEmpleado;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import ModeloPaqueteInfo.FactoryPaquete;
import ModeloPaqueteInfo.Paquete;
import ModeloPaqueteInfo.PaqueteAvance;
import ModeloPaqueteInfo.PaqueteTiempoAtencion;
import ModeloSocket.Emisor;
import ModeloUtil.Cronometro;
import ModeloUtil.TiempoAtencion;

public class Empleado {
	private static Empleado empleado = null;
    private int box;
    //Nuevos agregar al modelo de dominio.
    private Queue<String> clientesEsperando;
    private ArrayList<TiempoAtencion> timposAtencion;
      
    private Empleado() {
    	this.clientesEsperando = new LinkedList<String>();
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
		paquete.setBox(this.box);
		this.enviarPaquete(paquete);
		this.agregarTiempoAtencion(time);
	}
	
	public void avanceCliente(){
		PaqueteAvance paquete =(PaqueteAvance)FactoryPaquete.getInstance(1);
		paquete.setDni(this.clientesEsperando.remove());
		paquete.setBox(this.box);
		this.enviarPaquete(paquete);
	}
	
	private void agregarTiempoAtencion(TiempoAtencion time) {
		this.timposAtencion.add(time);
	}
	
	private void enviarPaquete(Paquete paquete) {
		Emisor.getInstance().setPaqueteAEnviar(paquete);
	}
	
	public boolean isColaVacia() {
		return this.clientesEsperando.isEmpty();
	}
	
	public void agregarCliente(String cliente) {
		this.clientesEsperando.add(cliente);
	}
}
