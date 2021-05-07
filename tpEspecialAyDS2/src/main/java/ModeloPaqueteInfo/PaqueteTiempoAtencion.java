package ModeloPaqueteInfo;

import java.io.Serializable;
import java.util.Date;

import ModeloUtil.TiempoAtencion;

public class PaqueteTiempoAtencion implements IPaquete,Serializable{
    private final int idOperacion = 2;
    private TiempoAtencion tiempoAtencion;
    private int box;
    
	public void setTiempoAtencion(TiempoAtencion tiempoAtencion) {
		this.tiempoAtencion = tiempoAtencion;
	}

	public TiempoAtencion getTiempoAtencion() {
		return tiempoAtencion;
	}
	
	@Override
	public int getIdOperacion() {
		return idOperacion;
	}
	
	public int getBox() {
		return this.box;
	}
	
	public void setBox(int box) {
		this.box = box;
	}
}
