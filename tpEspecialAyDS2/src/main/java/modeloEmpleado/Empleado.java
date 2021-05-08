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
    private ArrayList<TiempoAtencion> tiemposDeAtencion;
      
    private Empleado() {
    	this.clientesEsperando = new LinkedList<Integer>();
        this.tiemposDeAtencion = new ArrayList<TiempoAtencion>();
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
	
	public void finalizarAtencion() {
		Cronometro cronometro = Cronometro.getInstance();
		cronometro.setAndando(false);
		TiempoAtencion time = new TiempoAtencion(cronometro.getHora(),cronometro.getMinutos(),cronometro.getSegundos());
		this.tiemposDeAtencion.add(time);
		cronometro.setHora(0);cronometro.setMinutos(0);cronometro.setSegundos(0);	
		PaqueteTiempoAtencion paquete = (PaqueteTiempoAtencion)FactoryPaquete.getInstance(2);
		paquete.setTiempoAtencion(time);
		paquete.setBox(this.box);
		this.enviarPaquete(paquete);
	}
	
	public TiempoAtencion ultimoTiempo() {
		return this.tiemposDeAtencion.get(this.tiemposDeAtencion.size()-1);
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
	
	public TiempoAtencion tiempoPromedio() {
		double horas,min,seg,total;
		int cantTiempos;
		cantTiempos = this.tiemposDeAtencion.size();
		horas=0;min=0;seg=0;
		for(TiempoAtencion tp : this.tiemposDeAtencion) {
			horas+=tp.getHoras();
			min+=tp.getMinutos();
			seg+=tp.getSegundos();
		}
		horas = horas*3600;
		min = min*60;
		total = (horas + min + seg)/cantTiempos;
		horas = Math.floor(total/3600);
		total = total - (horas*3600);
		min = Math.floor(total/60);
		seg = total - (min*60);
		return new TiempoAtencion((int)horas,(int)min,(int)seg);
	}
}
