package ControladorEmpleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ModeloPaqueteInfo.FactoryPaquete;
import ModeloSocket.Emisor;
import Ventanas.VentanaEmpleado;

public class ControllerEmpleado implements ActionListener{
    private VentanaEmpleado window;
    
    public ControllerEmpleado(VentanaEmpleado ventana) {
    	this.window = ventana;
    }
	
	public void actionPerformed(ActionEvent evento) {
		switch(evento.getActionCommand()) {
		     case "avanceCliente":Emisor.getInstance().setPaqueteAEnviar(FactoryPaquete.getInstance(1));
		    	                  break;
		     case "inicioAtencion":System.out.println("Estoy aca 2.");
		    	                   break;
		     case "finAtencion":System.out.println("Estoy aca 3.");
		    	                break;
	    }		
	}

}
