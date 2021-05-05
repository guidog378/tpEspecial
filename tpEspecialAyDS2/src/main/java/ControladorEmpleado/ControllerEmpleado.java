package ControladorEmpleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ModeloEmpleado.Cronometro;
import ModeloEmpleado.Empleado;
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
		     case "avanceCliente":Empleado.getInstance().enviarPaquete(FactoryPaquete.getInstance(1));
		    	                  break;
		     case "inicioAtencion":Empleado.getInstance().iniciarAtencion();
		    	                   break;
		     case "finAtencion":Empleado.getInstance().finalizarAtencion();
		                        //Falta enviar el paquete.
		    	                break;
	    }		
	}

}
