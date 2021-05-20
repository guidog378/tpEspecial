package informacionCompartida;

public class InfoClienteAtendido implements Informable {

	private int dni;
	
	public InfoClienteAtendido() {
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	@Override
	public int getIdOperacion() {
		return 2;
	}

}
