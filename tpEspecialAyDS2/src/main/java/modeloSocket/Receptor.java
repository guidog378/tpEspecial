package modeloSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import modeloEmpleado.Empleado;
import modeloPaqueteInfo.IPaquete;
import modeloPaqueteInfo.PaqueteNuevoCliente;

public class Receptor implements Runnable{
	private static Receptor receptor = null;
	
	@Override
	public void run() {
		try {
			ServerSocket sv = new ServerSocket(9998);
			while(true) {	
				Socket socket = sv.accept();
				ObjectInputStream fe= new ObjectInputStream(socket.getInputStream()); 
				IPaquete paquete = (IPaquete)fe.readObject();	
				switch(paquete.getIdOperacion()) {
				     case 1:PaqueteNuevoCliente nuevoCliente = (PaqueteNuevoCliente)paquete;
				            System.out.println("Estoy aca2."+nuevoCliente.getDni());
				            Empleado.getInstance().agregarCliente(nuevoCliente.getDni());
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
}
