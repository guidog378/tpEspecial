package ModeloEmpleado;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ModeloPaqueteInfo.Paquete;

public class Emisor implements Runnable {
	private Paquete paqueteAEnviar;
	private boolean andando;
	private static Emisor speaker = null;
	
	private Emisor() {
		this.paqueteAEnviar = null;
		this.andando = true;
	}

	@Override
	public void run() {
		try {			
			while(isAndandoVentana()) {		
				Paquete paquete = this.getPaqueteAEnviar();
				if(paquete != null) {
					Socket socket = new Socket("192.168.1.40",9999);//Se debe poner la ip del servidor.
		        	ObjectOutputStream fr = new ObjectOutputStream(socket.getOutputStream());
				    fr.writeObject(paquete);
				    this.setPaqueteAEnviar(null);
				    fr.close();
				    socket.close();
		        }
			}	
		} catch (UnknownHostException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
	}

	public boolean isAndandoVentana() {
		return andando;
	}

	public void setAndando(boolean andando) {
		this.andando = andando;
	}
	
	public static Emisor getInstance() {
		if(Emisor.speaker == null)
			Emisor.speaker = new Emisor();
		return Emisor.speaker;
	}

	public synchronized Paquete getPaqueteAEnviar() {
		return paqueteAEnviar;
	}

	public synchronized void setPaqueteAEnviar(Paquete paquete) {
		this.paqueteAEnviar = paquete;
	}
	
}
