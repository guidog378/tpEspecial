package modelo;

import comunicacion.Conexion;
import informacionCompartida.InfoClientesEspera;
import informacionCompartida.InfoPeticion;
import ventana.VentanaEmpleado;

public class EscuchaNuevosClientes implements Runnable {

	private Conexion conexion;
	private VentanaEmpleado window;
	
	public EscuchaNuevosClientes() {
		this.conexion = new Conexion();
	}
	
	@Override
	public void run() {
		try {
			while(true) {
			    this.conexion.enviarPaquete(new InfoPeticion());
			    InfoClientesEspera clientesEspera = (InfoClientesEspera)this.conexion.recibirPaquete();
			    this.window.getModeloEspera().setDnis(clientesEspera.getDnis());
			    this.window.getTablaClientesEsperando().repaint();
			    Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setVentana(VentanaEmpleado ventana) {
		this.window = ventana;
	}

}
