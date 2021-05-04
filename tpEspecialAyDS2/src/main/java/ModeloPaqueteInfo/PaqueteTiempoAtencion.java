package ModeloPaqueteInfo;

import java.io.Serializable;
import java.util.Date;

public class PaqueteTiempoAtencion implements Paquete,Serializable{
    private final int idDispositivo = 1;
    private final int idOperacion = 2;
    private Date tiempoAtencion;
	
    public Date getTiempoAtencion() {
		return tiempoAtencion;
	}
    
	public void setTiempoAtencion(Date tiempoAtencion) {
		this.tiempoAtencion = tiempoAtencion;
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
