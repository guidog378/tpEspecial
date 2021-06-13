package modeloInfo;

public class FactoryInformable {
     
	public static Informable getInstance(int tipo) {
    	 Informable paquete = null;
    	 switch(tipo) {
    	     case 1:paquete = new InfoBoxDisponible();
    	            break;
    	     case 2:paquete = new InfoTiempoAtencion();
    	            break;
    	     case 3:paquete = new InfoPeticion();
    	            break;
    	     case 4:paquete = new InfoInicioAtencion();
    	            break;
    	 }
		 return paquete;
     }
	
}
