package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ControladorEmpleado.ControllerEmpleado;
import ModeloSocket.Emisor;
import ModeloSocket.Receptor;

import javax.swing.JScrollPane;

public class VentanaEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JButton avanceCliente;
	private JButton inicioAtencion;
	private JButton finAtencion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpleado window = new VentanaEmpleado();
					ControllerEmpleado controller = new ControllerEmpleado(window);
					Receptor receptor = Receptor.getInstance();
					Emisor emisor = Emisor.getInstance();
					Thread miThreadReceptor = new Thread(receptor);
					miThreadReceptor.start();
					Thread miThreadEmisor = new Thread(emisor);
					miThreadEmisor.start();   
					window.setActionListener(controller);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		panel.add(panel_1);
		
		avanceCliente = new JButton("Avance cliente");
		avanceCliente.setName("avanceCliente");
		avanceCliente.setActionCommand("avanceCliente");
		panel_1.add(avanceCliente);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		inicioAtencion = new JButton("Iniciar atencion");
		inicioAtencion.setName("inicioAtencion");
		inicioAtencion.setActionCommand("inicioAtencion");
		panel_2.add(inicioAtencion);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		finAtencion = new JButton("Finalizar atencion");
		finAtencion.setName("finAtencion");
		finAtencion.setActionCommand("finAtencion");
		panel_3.add(finAtencion);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.add(tabbedPane, BorderLayout.CENTER);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Clientes en espera", null, layeredPane, null);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"DNI1", "DNI2", "DNI3", "DNI4", "DNI5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		layeredPane.add(list, BorderLayout.CENTER);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Tiempos de atencion", null, layeredPane_1, null);
		layeredPane_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		layeredPane_1.add(panel_4, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Tiempo promedio:");
		panel_4.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		panel_4.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		layeredPane_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"40494914", "15:14"},
			},
			new String[] {
				"DNI Cliente", "Tiempo de atencion"
			}
		));
		scrollPane.setViewportView(table);
	}
	
	private void setActionListener(ControllerEmpleado c) {
		this.avanceCliente.addActionListener(c);
		this.finAtencion.addActionListener(c);
		this.inicioAtencion.addActionListener(c);
	}
}
