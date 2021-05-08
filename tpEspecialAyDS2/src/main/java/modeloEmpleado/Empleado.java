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
      
    private Empleado() {
    	this.clientesEsperando = new LinkedList<Integer>();
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
	
	public void iniciarAtencion() {
		Cronometro.getInstance().setAndando(true);
		new Thread(Cronometro.getInstance()).start();
	}
	
	public TiempoAtencion finalizarAtencion() {
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
        return time;
	}
	
	public int avanceCliente(){
		PaqueteProxCliente paquete =(PaqueteProxCliente)FactoryPaquete.getInstance(1);
		int cliente = this.clientesEsperando.remove();
		paquete.setDNIcliente(cliente);
		paquete.setBox(this.box);
		this.enviarPaquete(paquete);
		return cliente;
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

	public Queue<Integer> getClientesEsperando() {
		return clientesEsperando;
	}
}
