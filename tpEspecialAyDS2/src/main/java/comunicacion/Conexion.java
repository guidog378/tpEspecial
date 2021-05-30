package comunicacion;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import modeloInfo.InfoServerEmpleado;
import modeloInfo.InfoServerFuncional;
import modeloInfo.Informable;

public class Conexion {
	private Socket socket;
	private String ipServer,ipMonitor;
	private int puertoServer,puertoMonitor;
	
	public Conexion() {
		this.ipMonitor = "localhost";
		this.puertoMonitor = 9090;
		this.obtenerServidor();
	}

	public void enviarPaquete(Informable paqueteEnviar) {
		try {
			this.socket = new Socket(this.ipServer,this.puertoServer);
			OutputStream fr = this.socket.getOutputStream();
		    ObjectOutputStream ofr = new ObjectOutputStream(fr);
		    ofr.writeObject(paqueteEnviar);
		} catch (UnknownHostException e) {
				this.obtenerServidor();
				this.enviarPaquete(paqueteEnviar);
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public Informable recibirPaquete() {
		Informable paquete = null;
		ObjectInputStream fe;
		try {
			InputStream is = this.socket.getInputStream();
			fe = new ObjectInputStream(is);
			paquete = (Informable)fe.readObject();	
		} catch (IOException e) {
			System.out.println("IOException");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
		} 
		return paquete;
	}
	
	private void obtenerServidor() {
        try {
        	InfoServerEmpleado info = new InfoServerEmpleado();
			this.socket = new Socket(this.ipMonitor,this.puertoMonitor);
			ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
			oos.writeObject(info);
			ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
			InfoServerFuncional infoServer = (InfoServerFuncional) ois.readObject();
			this.ipServer = infoServer.getIpServer();
			this.puertoServer = infoServer.getPuertoServer();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
