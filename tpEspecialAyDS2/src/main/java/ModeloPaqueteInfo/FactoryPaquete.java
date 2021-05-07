package ModeloPaqueteInfo;

public class FactoryPaquete {
     public static IPaquete getInstance(int tipo) {
    	 IPaquete paquete;
    	 if(tipo == 1)
    		 paquete = new PaqueteAvanceCliente();
    	 else
    		 paquete = new PaqueteTiempoAtencion();
		return paquete;
     }
}
