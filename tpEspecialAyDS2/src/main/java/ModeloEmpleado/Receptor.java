package ModeloEmpleado;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ModeloPaqueteInfo.Paquete;

public class Receptor implements Runnable{
	private boolean andando;
	private static Receptor receptor = null;
	
	private Receptor() {
		this.andando = true;
	}
	
	@Override
	public void run() {
		try {
			ServerSocket sv = new ServerSocket(9999);
			while(isAndandoVentana()) {
				Socket socket = sv.accept();
				ObjectInputStream fe = new ObjectInputStream(socket.getInputStream());
				Paquete paquete = (Paquete)fe.readObject();
				switch(paquete.getIdOperacion()) {
				     //Aca adentro deberia obtener los paquetes desde el servidor que le paso al empleado.
				}
				fe.close();
			}
			sv.close();
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
