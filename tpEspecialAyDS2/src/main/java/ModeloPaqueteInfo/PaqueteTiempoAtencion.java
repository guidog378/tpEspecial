package ModeloPaqueteInfo;

import java.io.Serializable;
import java.util.Date;

import ModeloEmpleado.TiempoAtencion;

public class PaqueteTiempoAtencion implements Paquete,Serializable{
    private final int idDispositivo = 1;
    private final int idOperacion = 2;
    private TiempoAtencion tiempoAtencion;
    
	public void setTiempoAtencion(TiempoAtencion tiempoAtencion) {
		this.tiempoAtencion = tiempoAtencion;
	}

	public TiempoAtencion getTiempoAtencion() {
		return tiempoAtencion;
	}

	@Override
	public int getIdDispositivo() {
		return idDispositivo;
	}
	
	@Override
	public int getIdOperacion() {
		return idOperacion;
	}
}
