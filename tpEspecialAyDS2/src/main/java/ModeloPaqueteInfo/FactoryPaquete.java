package ModeloPaqueteInfo;

public class FactoryPaquete {
     public static Paquete getInstance(int tipo) {
    	 Paquete paquete;
    	 if(tipo == 1)
    		 paquete = new PaqueteProxCliente();
    	 else
    		 paquete = new PaqueteTiempoAtencion();
		return paquete;
     }
}
