package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTable;

import controladorEmpleado.ControllerEmpleado;
import modeloComponentes.tablaCE;
import modeloComponentes.tablaTiempoAtencion;
import modeloSocket.Receptor;

import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class VentanaEmpleado extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton avanceCliente;
	private JButton inicioAtencion;
	private JButton finAtencion;
	private JTextField box;
	private JButton aceptar;
	private tablaTiempoAtencion modeloTabla;
	private JTable tablaClientesEsperando;
	private tablaCE modeloEspera;
	private JLabel promedio;

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
					receptor.setVentana(window);
					Thread miThreadReceptor = new Thread(receptor);
					miThreadReceptor.start();
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
		setBounds(100, 100, 475, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		panel.add(panel_1);
		
		avanceCliente = new JButton("Avance cliente");
		avanceCliente.setName("avanceCliente");
		avanceCliente.setActionCommand("avanceCliente");
		avanceCliente.setEnabled(false);
		panel_1.add(avanceCliente);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		inicioAtencion = new JButton("Iniciar atencion");
		inicioAtencion.setName("inicioAtencion");
		inicioAtencion.setActionCommand("inicioAtencion");
		inicioAtencion.setEnabled(false);
		panel_2.add(inicioAtencion);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		finAtencion = new JButton("Finalizar atencion");
		finAtencion.setName("finAtencion");
		finAtencion.setActionCommand("finAtencion");
		finAtencion.setEnabled(false);
		panel_3.add(finAtencion);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.add(tabbedPane, BorderLayout.CENTER);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Clientes en espera", null, layeredPane, null);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		layeredPane.add(scrollPane_1, BorderLayout.CENTER);
		
		modeloEspera = new tablaCE();
		tablaClientesEsperando = new JTable(modeloEspera);
		scrollPane_1.setViewportView(tablaClientesEsperando);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setViewportView(tablaClientesEsperando);
		scrollPane_1.setColumnHeaderView(tablaClientesEsperando.getTableHeader());
		tablaClientesEsperando.setFillsViewportHeight(true);	
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Tiempos de atencion", null, layeredPane_1, null);
		layeredPane_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		layeredPane_1.add(panel_4, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Tiempo promedio:");
		panel_4.add(lblNewLabel);
		
		promedio = new JLabel("0");
		panel_4.add(promedio);
		
		JScrollPane scrollPane = new JScrollPane();
		layeredPane_1.add(scrollPane, BorderLayout.CENTER);
		
		modeloTabla = new tablaTiempoAtencion();
		table = new JTable(modeloTabla);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(table);
		scrollPane.setColumnHeaderView(table.getTableHeader());
		table.setFillsViewportHeight(true);	
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_6, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Box:");
		panel_6.add(lblNewLabel_1);
		
		box = new JTextField();
		panel_6.add(box);
		box.setColumns(10);
		
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand("aceptarBox");
		panel_6.add(aceptar);
	}
	
	public JButton getAvanceCliente() {
		return avanceCliente;
	}
	

	public JButton getInicioAtencion() {
		return inicioAtencion;
	}

	public JButton getFinAtencion() {
		return finAtencion;
	}

	public JTextField getBox() {
		return box;
	}
	
	public JButton getAceptar() {
		return aceptar;
	}

	public tablaTiempoAtencion getModeloTabla() {
		return modeloTabla;
	}

	public JTable getTable() {
		return table;
	}

	public tablaCE getModeloEspera() {
		return modeloEspera;
	}

	public JLabel getPromedio() {
		return promedio;
	}

	public JTable getTablaClientesEsperando() {
		return tablaClientesEsperando;
	}

	private void setActionListener(ControllerEmpleado c) {
		this.avanceCliente.addActionListener(c);
		this.finAtencion.addActionListener(c);
		this.inicioAtencion.addActionListener(c);
		this.aceptar.addActionListener(c);
	}
}
