package componentesVista;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import modeloInfo.InfoCliente;


public class tablaCE implements TableModel {
	private ArrayList<InfoCliente> clientes;
	
	private Class[] types;
	private String[] columnName;
	
	public tablaCE() {
		this.clientes = new ArrayList<InfoCliente>();
		this.types = new Class[2];
		types[0] = String.class;
		types[1] = String.class;
		this.columnName = new String[2];
		columnName[0] = "DNI Cliente";
		columnName[1] = "Nombre Cliente";
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
		return this.clientes.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Object auxObj = null;
		if(!this.clientes.isEmpty() && fila<this.clientes.size()) {
			InfoCliente cliente = this.clientes.get(fila);
			if(columna == 0)
		        auxObj = Integer.toString(cliente.getDni());
			else
				auxObj = cliente.getNombre();
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
	
	public void setClientes(ArrayList<InfoCliente> clientes) {
		this.clientes = clientes;
	}

}

