package ControladorEmpleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ModeloEmpleado.Empleado;
import ModeloPaqueteInfo.FactoryPaquete;
import ModeloSocket.Emisor;
import Ventanas.VentanaEmpleado;
import modeloUtil.Cronometro;

public class ControllerEmpleado implements ActionListener{
    private VentanaEmpleado window;
    
    public ControllerEmpleado(VentanaEmpleado ventana) {
    	this.window = ventana;
    }
	
	public void actionPerformed(ActionEvent evento) {
		switch(evento.getActionCommand()) {
		     case "avanceCliente":this.window.getInicioAtencion().setEnabled(true);
		    	                  Empleado.getInstance().avanceCliente();
		    	                  break;
		     case "inicioAtencion":Empleado.getInstance().iniciarAtencion();
		                           this.window.getAvanceCliente().setEnabled(false);
		                           this.window.getInicioAtencion().setEnabled(false);
		                           this.window.getFinAtencion().setEnabled(true);
		    	                   break;
		     case "finAtencion":Empleado.getInstance().finalizarAtencion();
		                        this.window.getAvanceCliente().setEnabled(true);
		                        this.window.getFinAtencion().setEnabled(false);
		    	                break;
	    }		
	}

}
