package loja_backoffice.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class HistoricoView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private DefaultTableModel tabelaVisualizar;
	private JTable tableVisualizar;
	
	private JTextArea taCompra;
	
	public HistoricoView() {
		historicoTela();
	}

	public void historicoTela() {
		setTitle("Histórico de Compras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tbCheckout = new JTabbedPane(JTabbedPane.TOP);
		tbCheckout.setBounds(10, 10, 606, 423);
		contentPane.add(tbCheckout);

		JPanel tabCheckout = new JPanel();
		tbCheckout.addTab("Histórico", null, tabCheckout, "Visualizar Compra");
		tabCheckout.setLayout(null);

		JScrollPane scrollPaneCheckout = new JScrollPane();
		scrollPaneCheckout.setBounds(10, 253, 581, 133);
		tabCheckout.add(scrollPaneCheckout);

		//Tabela
		JTable tableVisualizar = new JTable();
		DefaultTableModel tabelaVisualizar = new DefaultTableModel();
		scrollPaneCheckout.setViewportView(tableVisualizar);
		tableVisualizar.setModel(tabelaVisualizar);
		tabelaVisualizar.setColumnIdentifiers(new String[] {"ID Compra", "Cliente", "Total"});

		JLabel lblCompras = new JLabel("LISTA DE COMPRAS");
		lblCompras.setBounds(245, 234, 130, 14);
		tabCheckout.add(lblCompras);
		DefaultTableModel tabelaCompras = new DefaultTableModel();
		
		JTextArea taCompra = new JTextArea();
		taCompra.setBounds(10, 50, 580, 135);
		tabCheckout.add(taCompra);
		
		JLabel lblViz = new JLabel("VISUALIZAÇÃO");
		lblViz.setBounds(245, 32, 130, 14);
		tabCheckout.add(lblViz);
		
		JScrollPane scrollPaneArea = new JScrollPane(taCompra);
		scrollPaneArea.setBounds(10, 50, 580, 135);
		tabCheckout.add(scrollPaneArea);
		
		JButton btnVoltar = new JButton("voltar");
		btnVoltar.setBounds(10, 11, 89, 23);
		tabCheckout.add(btnVoltar);
		
		this.tabelaVisualizar = tabelaVisualizar;
		this.tableVisualizar = tableVisualizar;
		
		this.taCompra = taCompra;
		
		btnVoltar.addActionListener(this);
		
	}
	
	public DefaultTableModel getTabela() {
		return tabelaVisualizar;
	}
	
	public JTable getJTable() {
		return tableVisualizar;
	}
	
	public JTextArea getAreaTexto() {
		return taCompra;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals("voltar")) {
			this.dispose();
		}
		
	}
}
