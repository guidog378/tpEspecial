package ModeloSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ModeloEmpleado.Empleado;
import ModeloPaqueteInfo.IPaquete;
import ModeloPaqueteInfo.PaqueteNuevoCliente;

public class Receptor implements Runnable{
	private boolean andando;
	private static Receptor receptor = null;
	
	private Receptor() {
		this.andando = true;
	}
	
	@Override
	public void run() {
		try {
			Socket socket = new Socket("192.168.1.40",9999);
			while(isAndandoVentana()) {	
				 ObjectInputStream fe = new ObjectInputStream(socket.getInputStream());
				 IPaquete paquete = (IPaquete)fe.readObject();
				 if(paquete != null) {
				     switch(paquete.getIdOperacion()) {
				          case 1:PaqueteNuevoCliente nuevoCliente = (PaqueteNuevoCliente)paquete;
				                 Empleado.getInstance().agregarCliente(nuevoCliente.getDni());
				                 break;
				     }
				 }
				 fe.close();
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean isAndandoVentana() {
		return andando;
	}

	public void setAndando(boolean andando) {
		this.andando = andando;
	}
    
	public static Receptor getInstance() {
		if(Receptor.receptor == null)
			Receptor.receptor = new Receptor();
		return Receptor.receptor;
	}
}
