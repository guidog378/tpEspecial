package comunicacion;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import informacionCompartida.Informable;
import informacionCompartida.InfoClienteAtendido;
import informacionCompartida.InfoNuevoCliente;
import modelo.Empleado;
import modelo.TiempoAtencion;
import ventana.VentanaEmpleado;

public class Receptor implements Runnable{
	private static Receptor receptor = null;
	private VentanaEmpleado window;
	
	@Override
	public void run() {
		try {
			ServerSocket sv = new ServerSocket(9690);
			while(true) {	
				Socket socket = sv.accept();
				ObjectInputStream fe= new ObjectInputStream(socket.getInputStream()); 
				Informable paquete = (Informable)fe.readObject();	
				switch(paquete.getIdOperacion()) {
				     case 1:InfoNuevoCliente nuevoCliente = (InfoNuevoCliente)paquete;
				            this.window.getModeloEspera().agregaDni(nuevoCliente.getDni());
				            this.window.getTablaClientesEsperando().repaint();
				            break;
				     case 2:InfoClienteAtendido clienteAtendido = (InfoClienteAtendido)paquete;
				            this.window.getModeloEspera().eliminarDNI(clienteAtendido.getDni());
				            this.window.getTablaClientesEsperando().repaint();
				            this.window.getModeloTabla().agregaDni(clienteAtendido.getDni());
				            this.window.getModeloTabla().agregaTiempo(new TiempoAtencion(0,0,0));
				            this.window.getTable().repaint();
				    	    break;
				}
	        }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public static Receptor getInstance() {
		if(Receptor.receptor == null)
			Receptor.receptor = new Receptor();
		return Receptor.receptor;
	}
	
	public void setVentana(VentanaEmpleado window) {
		this.window = window;
	}
}
