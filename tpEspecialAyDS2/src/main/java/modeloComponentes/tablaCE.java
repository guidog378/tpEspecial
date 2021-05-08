package modeloComponentes;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class tablaCE implements TableModel {
	private ArrayList<Integer> dniClientes;
	
	private Class[] types;
	private String[] columnName;
	
	public tablaCE() {
		this.dniClientes = new ArrayList<Integer>();
		this.types = new Class[1];
		types[0] = java.lang.Integer.class;
		this.columnName = new String[1];
		columnName[0] = "DNI Cliente";
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
		return 1;
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
		if(!this.dniClientes.isEmpty() && fila<this.dniClientes.size()) {
			Integer dni = this.dniClientes.get(fila);
		    auxObj = Integer.toString(dni);	
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
	
	public void agregaDni(int Dni) {
	    System.out.println("Estoy aca.");
		this.dniClientes.add(Dni);
	}

}

