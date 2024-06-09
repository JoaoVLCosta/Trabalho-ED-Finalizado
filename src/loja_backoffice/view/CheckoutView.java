package loja_backoffice.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import loja_backoffice.controller.CarrinhoController;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class CheckoutView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private CarrinhoController cartCont;
	
	private JTable tableCaixa;
	private JTable tableVisualizar;
	
	private JButton btnFila;
	private JButton btnFinalizar;
	
	private String nome_cliente;
	
	private JTextArea taTotal;
	
	private DefaultTableModel tabelaCaixa;
	private DefaultTableModel tabelaVisualizar;
	
	public CheckoutView(CarrinhoController cartCont, String nome_cliente) {
		this.nome_cliente = nome_cliente;
		this.cartCont = cartCont;
		
		setTitle("Checkout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		tbCheckout.addTab("Caixa", null, tabCheckout, "Finalizar Compra");
		tabCheckout.setLayout(null);

		JScrollPane scrollPaneCheckout = new JScrollPane();
		scrollPaneCheckout.setBounds(10, 253, 450, 133);
		tabCheckout.add(scrollPaneCheckout);

		//Tabela
		JTable tableVisualizar = new JTable();
		DefaultTableModel tabelaVisualizar = new DefaultTableModel();
		scrollPaneCheckout.setViewportView(tableVisualizar);
		tableVisualizar.setModel(tabelaVisualizar);
		tabelaVisualizar.setColumnIdentifiers(new String[] {"Nome", "Valor", "Quantidade" });

		JButton btnFila = new JButton("Iniciar");
		btnFila.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFila.setBounds(228, 112, 120, 29);
		tabCheckout.add(btnFila);
		
		JButton btnFinalizar = new JButton("Finalizar Compra");
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFinalizar.setBounds(190, 162, 202, 40);
		tabCheckout.add(btnFinalizar);
		//DEIXANDO BOTÃO INVISIVELs
		btnFinalizar.setVisible(false);
		
		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 22, 581, 40);
		tabCheckout.add(scrollPaneProduto);

		JLabel lblCheck = new JLabel("NOTA");
		lblCheck.setBounds(265, 234, 102, 14);
		tabCheckout.add(lblCheck);
		
		JTable tableCaixa = new JTable();
		DefaultTableModel tabelaCaixa = new DefaultTableModel();
		scrollPaneProduto.setViewportView(tableCaixa);
		tableCaixa.setModel(tabelaCaixa);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(510, 260, 46, 14);
		tabCheckout.add(lblTotal);
		
		JTextArea taTotal = new JTextArea();
		taTotal.setBounds(490, 291, 70, 22);
		tabCheckout.add(taTotal);
		tabelaCaixa.setColumnIdentifiers(new String[] { "Tipo", "Nome", "ID Produto", "Valor", "Quantidade" });
		
		this.tableCaixa = tableCaixa;
		this.tableVisualizar = tableVisualizar;
		this.tabelaCaixa = tabelaCaixa;
		this.tabelaVisualizar = tabelaVisualizar;
		
		this.btnFila = btnFila;
		this.btnFinalizar = btnFinalizar;
		
		this.taTotal = taTotal;
		
		//BOTÃO
		btnFila.addActionListener(this);
		btnFinalizar.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		try {
		if(comando.equals("Iniciar")){
			cartCont.iniciar(tabelaCaixa, btnFila);
		}
		
		if(comando.equals("Próximo")){
			cartCont.proximoLista(tabelaVisualizar, tabelaCaixa, btnFila, btnFinalizar);
			cartCont.somaTotal(taTotal, tabelaVisualizar);
		}
		
		if(comando.equals("Finalizar Compra")){
			cartCont.finalizar();
			JOptionPane.showMessageDialog(null,"Compra Finalizada");
			CarrinhoView cv = new CarrinhoView(nome_cliente);
			cv.setVisible(true);
			this.dispose();
		}
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
