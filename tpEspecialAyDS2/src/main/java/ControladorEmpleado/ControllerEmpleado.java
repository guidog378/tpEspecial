package ControladorEmpleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ModeloEmpleado.Empleado;
import ModeloPaqueteInfo.FactoryPaquete;
import ModeloSocket.Emisor;
import ModeloUtil.Cronometro;
import Ventanas.VentanaEmpleado;

public class ControllerEmpleado implements ActionListener{
    private VentanaEmpleado window;
    
    public ControllerEmpleado(VentanaEmpleado ventana) {
    	this.window = ventana;
    }
	
	public void actionPerformed(ActionEvent evento) {
		switch(evento.getActionCommand()) {
		     case "avanceCliente":if(!Empleado.getInstance().isColaVacia()) {
		    	                      this.window.getInicioAtencion().setEnabled(true);
		    	                      Empleado.getInstance().avanceCliente();       
		                          }else {
		                        	  JOptionPane.showMessageDialog(this.window, "No hay clientes esperando.");
		                          }
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
