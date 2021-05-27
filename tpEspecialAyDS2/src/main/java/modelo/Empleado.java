package modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import comunicacion.Conexion;
import informacionCompartida.FactoryInformable;
import informacionCompartida.InfoCliente;
import informacionCompartida.Informable;
import informacionCompartida.InfoBoxDisponible;
import informacionCompartida.InfoTiempoAtencion;

public class Empleado {
	private static Empleado empleado = null;
    private int box;
    //Nuevos agregar al modelo de dominio.
    private ArrayList<TiempoAtencion> tiemposDeAtencion; 
    private Conexion conexion;
      
    private Empleado() {
        this.tiemposDeAtencion = new ArrayList<TiempoAtencion>();
        this.conexion = new Conexion();
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
		InfoTiempoAtencion paquete = (InfoTiempoAtencion)FactoryInformable.getInstance(2);
		paquete.setTiempoAtencion(time);
		paquete.setBox(this.box);
		this.enviarPaquete(paquete);
	}
	
	public TiempoAtencion ultimoTiempo() {
		return this.tiemposDeAtencion.get(this.tiemposDeAtencion.size()-1);
	}
	
	public int avanceCliente(){
		InfoBoxDisponible paqueteEnviado =(InfoBoxDisponible)FactoryInformable.getInstance(1);
		paqueteEnviado.setBox(this.box);
		this.enviarPaquete(paqueteEnviado);
		InfoCliente cliente =(InfoCliente) this.recibirPaquete();
		return cliente.getDni();
	}
	
	private void enviarPaquete(Informable paquete) {
		this.conexion.enviarPaquete(paquete);
	}
	
	private Informable recibirPaquete() {
		return this.conexion.recibirPaquete();
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
