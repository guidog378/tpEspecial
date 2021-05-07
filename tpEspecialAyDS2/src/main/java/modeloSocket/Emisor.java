package modeloSocket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import modeloPaqueteInfo.IPaquete;

public class Emisor{
	private static Emisor speaker = null;
	
	private Emisor() {
	}

	public void enviarPaquete(IPaquete paquete) {
		try {			
			Socket socket = new Socket("192.168.1.40",9999);//Se debe poner la ip del servidor.
		    ObjectOutputStream fr = new ObjectOutputStream(socket.getOutputStream());
		    fr.writeObject(paquete);
		    fr.close();
		} catch (UnknownHostException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public static Emisor getInstance() {
		if(Emisor.speaker == null)
			Emisor.speaker = new Emisor();
		return Emisor.speaker;
	}
}
