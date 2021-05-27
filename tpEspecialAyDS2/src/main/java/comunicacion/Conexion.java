package comunicacion;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import informacionCompartida.InfoClienteAtendido;
import informacionCompartida.InfoCliente;
import informacionCompartida.Informable;
import modelo.TiempoAtencion;
import ventana.VentanaEmpleado;

public class Conexion {
	private Socket socket;
	
	public Conexion() {
	}

	public void enviarPaquete(Informable paqueteEnviar) {
		try {
			this.socket = new Socket("localhost",9696);
			OutputStream fr = this.socket.getOutputStream();
		    ObjectOutputStream ofr = new ObjectOutputStream(fr);
		    ofr.writeObject(paqueteEnviar);
		} catch (UnknownHostException e) {
				e.printStackTrace();
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
			System.out.println(paquete.getIdOperacion());
		} catch (IOException e) {
			System.out.println("IOException");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
		} 
		return paquete;
	}
}
