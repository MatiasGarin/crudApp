package graficos;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1797331175626741883L;
	JPanel panelJPanel;
	/* Tabla */
	private JScrollPane tablaJScroll;
	private JTable tablaJTable;
	private JLabel tablaJLabel;
	/* Insercion */
	private JLabel idJLabel; 
	private JTextField idJTextField;
	private JLabel nombreJLabel; 
	private JTextField nombreJTextField;
	private JLabel direccionJLabel; 
	private JTextField direccionJTextField;
	private JLabel telefonoJLabel; 
	private JTextField telefonoJTextField;
	private JButton enviarJButton;
	
	public Ventana() {
		panelJPanel = new JPanel();
		tablaJLabel = new JLabel("Clientes");
		tablaJTable = new JTable();
		idJLabel = new JLabel("Id: ");
		idJTextField = new JTextField();
		nombreJLabel = new JLabel("Nombre: ");
		nombreJTextField = new JTextField();
		direccionJLabel = new JLabel("Dirección: ");
		direccionJTextField = new JTextField();
		telefonoJLabel = new JLabel("Telefono: ");
		telefonoJTextField = new JTextField();
		enviarJButton = new JButton("Buscar");
		iniciarTabla();
		tablaJScroll = new JScrollPane(tablaJTable);
		inicializar();
	}
	
	public void inicializar() {
		setSize(800, 400);
		setTitle("CRUD Aplication");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		panelJPanel.setLayout(null);
		panelJPanel.setBounds(10, 10, 700, 500);
		
		/* Tabla */
		tablaJLabel.setBounds(100, 20, 50, 10);
		tablaJScroll.setBounds(100, 50, 570, 200);
		panelJPanel.add(tablaJLabel);
		panelJPanel.add(tablaJScroll,BorderLayout.CENTER);
		
		/* Insercion datos */
		idJLabel.setBounds(135, 270, 80, 20);
		idJTextField.setBounds(160, 270, 100, 20);
		panelJPanel.add(idJLabel);
		panelJPanel.add(idJTextField);
		
		nombreJLabel.setBounds(100, 300, 80, 20);
		nombreJTextField.setBounds(160, 300, 100, 20);
		panelJPanel.add(nombreJLabel);
		panelJPanel.add(nombreJTextField);
		
		direccionJLabel.setBounds(300, 270, 80, 20);
		direccionJTextField.setBounds(370, 270, 100, 20);
		panelJPanel.add(direccionJLabel);
		panelJPanel.add(direccionJTextField);
		
		telefonoJLabel.setBounds(305, 300, 80, 20);
		telefonoJTextField.setBounds(370, 300, 100, 20);
		panelJPanel.add(telefonoJLabel);
		panelJPanel.add(telefonoJTextField);
		
		
		enviarJButton.setBounds(520,285,100,20);
		panelJPanel.add(enviarJButton);
		
		getContentPane().add(panelJPanel);

	}

	private void iniciarTabla() {
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("Id");
		modelo.addColumn("Nombre");
		modelo.addColumn("Direccion");
		modelo.addColumn("Telefono");
		
		tablaJTable = new JTable(modelo);
	}
}
