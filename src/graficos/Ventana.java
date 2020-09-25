package graficos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cliente.Cliente;
import conexion.Conexion;
import conexion.DAOCliente;

public class Ventana extends JFrame {

	private static final long serialVersionUID = -1797331175626741883L;

	JPanel panelJPanel;
	Conexion c;
	DAOCliente dao;
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
	private JButton actualizarJButton;
	private JButton modificarJButton;
	private JButton eliminarJButton;
	//
	DefaultTableModel modelo;

	public Ventana(Conexion conexion) {
		panelJPanel = new JPanel();
		c = conexion;
		dao = new DAOCliente(conexion.getConexion());
		
		idJLabel = new JLabel("Id: ");
		idJTextField = new JTextField();
		nombreJLabel = new JLabel("Nombre: ");
		nombreJTextField = new JTextField();
		direccionJLabel = new JLabel("Dirección: ");
		direccionJTextField = new JTextField();
		telefonoJLabel = new JLabel("Telefono: ");
		telefonoJTextField = new JTextField();
		
		enviarJButton = new JButton("Agregar");
		actualizarJButton = new JButton("Actualizar");
		modificarJButton = new JButton("Modificar");
		eliminarJButton = new JButton("Eliminar");

		tablaJLabel = new JLabel("Base de Datos Clientes");
		tablaJTable = new JTable();

		tablaJScroll = new JScrollPane(tablaJTable);
		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override 
		    public boolean isCellEditable(int row, int column) { 
		     return false; 
		    } 
		};
		modelo.addColumn("Id");
		modelo.addColumn("Nombre");
		modelo.addColumn("Direccion");
		modelo.addColumn("Telefono");
		
		actualizarTabla();
		inicializar();
	}

	public void inicializar() {
		setSize(700, 380);
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		panelJPanel.setLayout(null);
		panelJPanel.setBounds(10, 10, 790, 390);

		/* Tabla */
		tablaJLabel.setBounds(50, 20, 150, 10);
		tablaJScroll.setBounds(50, 50, 570, 200);
		panelJPanel.add(tablaJLabel);
		panelJPanel.add(tablaJScroll, BorderLayout.CENTER);

		/* Insercion datos */
		idJLabel.setBounds(45, 270, 80, 20);
		idJTextField.setBounds(110, 270, 100, 20);
		panelJPanel.add(idJLabel);
		panelJPanel.add(idJTextField);

		nombreJLabel.setBounds(220, 270, 80, 20);
		nombreJTextField.setBounds(290, 270, 100, 20);
		panelJPanel.add(nombreJLabel);
		panelJPanel.add(nombreJTextField);

		direccionJLabel.setBounds(45, 300, 80, 20);
		direccionJTextField.setBounds(110, 300, 100, 20);
		panelJPanel.add(direccionJLabel);
		panelJPanel.add(direccionJTextField);

		telefonoJLabel.setBounds(220, 300, 80, 20);
		telefonoJTextField.setBounds(290, 300, 100, 20);
		panelJPanel.add(telefonoJLabel);
		panelJPanel.add(telefonoJTextField);

		enviarJButton.setBounds(415, 270, 100, 20);
		panelJPanel.add(enviarJButton);

		modificarJButton.setBounds(415, 300, 100, 20);
		panelJPanel.add(modificarJButton);

		eliminarJButton.setBounds(525, 270, 100, 20);
		panelJPanel.add(eliminarJButton);
		
		actualizarJButton.setBounds(525, 300, 100, 20);
		panelJPanel.add(actualizarJButton);

		getContentPane().add(panelJPanel);

		enviarJButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente(Integer.parseInt(idJTextField.getText()), nombreJTextField.getText(),
						direccionJTextField.getText(), Integer.parseInt(telefonoJTextField.getText()));

				if(dao.insertar(cliente) == true) {
					JOptionPane.showMessageDialog(panelJPanel, "Insertado correctamente");
				}else {
					JOptionPane.showMessageDialog(panelJPanel, "Fallo al insertar");
				}
				
				actualizarTabla();
				limpiarCampos();

			}
		});

		actualizarJButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
				
			}
		});
		
		modificarJButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente(Integer.parseInt(idJTextField.getText()), nombreJTextField.getText(),
						direccionJTextField.getText(), Integer.parseInt(telefonoJTextField.getText()));

				if(dao.modificar(cliente) == true) {
					JOptionPane.showMessageDialog(panelJPanel, "Modificado correctamente");
				}else {
					JOptionPane.showMessageDialog(panelJPanel, "Fallo al modificar");
				}
				
				actualizarTabla();
				limpiarCampos();
			}
		});
		
		eliminarJButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente(Integer.parseInt(idJTextField.getText()), "",
						"", 0);

				if(dao.eliminar(cliente) == true) {
					JOptionPane.showMessageDialog(panelJPanel, "Eliminado correctamente");
				}else {
					JOptionPane.showMessageDialog(panelJPanel, "Fallo al eliminar");
				}
				
				actualizarTabla();
				limpiarCampos();

			}
		});

	}

	private void limpiarCampos() {
		idJTextField.setText("");
		nombreJTextField.setText("");
		direccionJTextField.setText("");
		telefonoJTextField.setText("");
	}
	
	private void actualizarTabla() {

		if (modelo.getRowCount() > 0) {
		    for (int i = modelo.getRowCount() - 1; i > -1; i--) {
		        modelo.removeRow(i);
		    }
		}
		
		dao.recargarClientes(modelo);
		tablaJTable = new JTable(modelo);
		tablaJScroll = new JScrollPane(tablaJTable);
		
		
	}

}
