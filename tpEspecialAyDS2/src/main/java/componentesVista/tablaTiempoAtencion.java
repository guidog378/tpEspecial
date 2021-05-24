package componentesVista;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import modelo.TiempoAtencion;

public class tablaTiempoAtencion implements TableModel {

	private ArrayList<TiempoAtencion> tiemposDeAtencion;
	private ArrayList<Integer> dniClientes;
	
	private Class[] types;
	private String[] columnName;
	
	public tablaTiempoAtencion() {
		this.tiemposDeAtencion = new ArrayList<TiempoAtencion>();
		this.dniClientes = new ArrayList<Integer>();
		this.types = new Class[2];
		types[0] = TiempoAtencion.class;
		types[1] = java.lang.Integer.class;
		this.columnName = new String[2];
		columnName[0] = "DNI Cliente";
		columnName[1] = "Tiempo de atencion";
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		
	}

	@Override
	public Class<?> getColumnClass(int columna) {
		return this.types[columna];
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int columna) {
		return this.columnName[columna];
	}

	@Override
	public int getRowCount() {
		return this.dniClientes.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Object auxObj = null;
		if(!this.tiemposDeAtencion.isEmpty() && fila<this.tiemposDeAtencion.size()) {
			TiempoAtencion tiempo = this.tiemposDeAtencion.get(fila);
			Integer dni = this.dniClientes.get(fila);
			switch(columna) {
				case 0: auxObj = Integer.toString(dni);
						break;
				case 1:	auxObj = tiempo.toString();
						break;
			}
		}
		return auxObj;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		
	}
	
	public void agregaTiempo(TiempoAtencion tiempo) {
		this.tiemposDeAtencion.add(this.dniClientes.size()-1, tiempo);
	}
	
	public void agregaDni(Integer dni) {
		this.dniClientes.add(dni);
	}

}
