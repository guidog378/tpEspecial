package informacionCompartida;

public class FactoryInformable {
     public static Informable getInstance(int tipo) {
    	 Informable paquete;
    	 if(tipo == 1)
    		 paquete = new InfoProxCliente();
    	 else
    		 paquete = new InfoTiempoAtencion();
		return paquete;
     }
}
