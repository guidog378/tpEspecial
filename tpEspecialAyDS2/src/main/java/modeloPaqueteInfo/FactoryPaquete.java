package modeloPaqueteInfo;

public class FactoryPaquete {
     public static IPaquete getInstance(int tipo) {
    	 IPaquete paquete;
    	 if(tipo == 1)
    		 paquete = new PaqueteProxCliente();
    	 else
    		 paquete = new PaqueteTiempoAtencion();
		return paquete;
     }
}
