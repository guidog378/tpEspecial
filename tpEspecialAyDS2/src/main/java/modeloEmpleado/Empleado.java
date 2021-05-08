package modeloEmpleado;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import modeloPaqueteInfo.FactoryPaquete;
import modeloPaqueteInfo.IPaquete;
import modeloPaqueteInfo.PaqueteProxCliente;
import modeloPaqueteInfo.PaqueteTiempoAtencion;
import modeloSocket.Emisor;
import modeloUtil.Cronometro;
import modeloUtil.TiempoAtencion;

public class Empleado {
	private static Empleado empleado = null;
    private int box;
    //Nuevos agregar al modelo de dominio.
    private Queue<Integer> clientesEsperando;
    private ArrayList<TiempoAtencion> timposAtencion;
      
    private Empleado() {
    	this.clientesEsperando = new LinkedList<Integer>();
    	this.timposAtencion = new ArrayList<TiempoAtencion>();
    	//Para probar.
    	this.clientesEsperando.add(40454948);
    	this.clientesEsperando.add(40454947);
    	this.clientesEsperando.add(40454946);
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
		PaqueteProxCliente paquete =(PaqueteProxCliente)FactoryPaquete.getInstance(1);
		paquete.setDNIcliente(this.clientesEsperando.remove());
		paquete.setBox(this.box);
		this.enviarPaquete(paquete);
	}
	
	private void agregarTiempoAtencion(TiempoAtencion time) {
		this.timposAtencion.add(time);
	}
	
	private void enviarPaquete(IPaquete paquete) {
		Emisor.getInstance().enviarPaquete(paquete);
	}
	
	public boolean isColaVacia() {
		return this.clientesEsperando.isEmpty();
	}
	
	public void agregarCliente(int cliente) {
		this.clientesEsperando.add(cliente);
	}
}
