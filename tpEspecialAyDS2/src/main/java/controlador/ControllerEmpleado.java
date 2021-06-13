package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Empleado;
import modeloUtil.TiempoAtencion;
import ventana.VentanaEmpleado;

public class ControllerEmpleado implements ActionListener{
    private VentanaEmpleado window;
    
    public ControllerEmpleado(VentanaEmpleado ventana) {
    	this.window = ventana;
    }
	
	public void actionPerformed(ActionEvent evento) {
		switch(evento.getActionCommand()) {
		     case "avanceCliente":Integer clienteAtendido = Empleado.getInstance().avanceCliente();	 
		    	                  if(clienteAtendido != -1) {  
		    	                      this.window.getInicioAtencion().setEnabled(true);
		    				          this.window.getModeloTabla().agregaDni(clienteAtendido);
		    				          this.window.getModeloTabla().agregaTiempo(new TiempoAtencion(0,0,0));
		    				          this.window.getTable().repaint();
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
		                        this.window.getPromedio().setText(Empleado.getInstance().tiempoPromedio().toString());
		    	                this.window.getModeloTabla().agregaTiempo(Empleado.getInstance().ultimoTiempo());
		                        this.window.getTable().repaint();
		                        this.window.getAvanceCliente().setEnabled(true);
		                        this.window.getFinAtencion().setEnabled(false);
		    	                break;
		     case "aceptarBox":try{
		    	                   int box = Integer.valueOf(this.window.getBox().getText());
		    	                   Empleado.getInstance().setBox(box);
		    	                   this.window.getAvanceCliente().setEnabled(true);
		    	                   this.window.getBox().setEditable(false);
		    	                   this.window.getAceptar().setEnabled(false);
		                       }catch(NumberFormatException e) {
		                    	   JOptionPane.showMessageDialog(this.window, "Formato de box no valido,debe ser un numero.");
		                       }
		    	               
	    }
	}

}
