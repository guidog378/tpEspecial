package modeloSocket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import modeloPaqueteInfo.IPaquete;

public class Emisor {
	private static Emisor speaker = null;
	
	private Emisor() {
	}

	public void enviarPaquete(IPaquete paquete) {
		try {
		    Socket socket = new Socket("localhost",9696);
			OutputStream fr = socket.getOutputStream();
		    ObjectOutputStream ofr = new ObjectOutputStream(fr);
		    ofr.writeObject(paquete);
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
