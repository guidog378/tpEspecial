package modeloInfo;

import java.util.Date;

public class InfoInicioAtencion implements Informable {
	
	private Date fechaYHora;
	
	public InfoInicioAtencion() {
		this.fechaYHora = new Date();
	}
	
	@Override
	public int getIdOperacion() {
		return 4;
	}

	public Date getFecha() {
		return fechaYHora;
	}

}
