package loja_backoffice.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import loja_backoffice.controller.BuscaController;
import loja_backoffice.controller.CarrinhoController;

public class CarrinhoView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nome_cliente;
	
	public CarrinhoView(String nome_cliente) {
		this.nome_cliente = nome_cliente;
		componentesTela();
	}

	public void componentesTela(){
		setTitle("Carrinho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tbCarrinho = new JTabbedPane(JTabbedPane.TOP);
		tbCarrinho.setBounds(10, 10, 606, 423);
		contentPane.add(tbCarrinho);

		JPanel tabCarrinho = new JPanel();
		tbCarrinho.addTab("Produto", null, tabCarrinho, "Busca de Produtos");
		tabCarrinho.setLayout(null);

		JScrollPane scrollPaneCarrinho = new JScrollPane();
		scrollPaneCarrinho.setBounds(10, 253, 581, 133);
		tabCarrinho.add(scrollPaneCarrinho);

		//Tabela de busca Produtos
		JTable tableCarrinho = new JTable();
		DefaultTableModel tabelaCarrinho = new DefaultTableModel();
		scrollPaneCarrinho.setViewportView(tableCarrinho);
		tableCarrinho.setModel(tabelaCarrinho);
		tabelaCarrinho.setColumnIdentifiers(new String[] { "Tipo", "Nome", "ID Produto", "Valor", "Quantidade" });


		JLabel lblProdutoTipo = new JLabel("Tipo");
		lblProdutoTipo.setBounds(105, 6, 42, 29);
		tabCarrinho.add(lblProdutoTipo);


		JComboBox<String> cbxTipo = new JComboBox<String>();
		cbxTipo.setBounds(152, 6, 439, 29);
		tabCarrinho.add(cbxTipo);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemover.setBounds(10, 213, 120, 29);
		tabCarrinho.add(btnRemover);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdicionar.setBounds(10, 173, 120, 29);
		tabCarrinho.add(btnAdicionar);

		JButton btnVoltar = new JButton("voltar");
		btnVoltar.setBounds(10, 10, 85, 21);
		tabCarrinho.add(btnVoltar);
		
		JButton btnVisualizar = new JButton("Checkout");
		btnVisualizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVisualizar.setBounds(389, 184, 202, 40);
		tabCarrinho.add(btnVisualizar);
		
		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 63, 581, 110);
		tabCarrinho.add(scrollPaneProduto);

		JLabel lblCarrinho = new JLabel("CARRINHO");
		lblCarrinho.setBounds(253, 235, 102, 14);
		tabCarrinho.add(lblCarrinho);
		
		JTable tableProduto = new JTable();
		DefaultTableModel tabelaProduto = new DefaultTableModel();
		scrollPaneProduto.setViewportView(tableProduto);
		tableProduto.setModel(tabelaProduto);
		tabelaProduto.setColumnIdentifiers(new String[] { "Tipo", "Nome", "ID Produto", "Valor", "Quantidade" });
		
		JLabel lblVP = new JLabel("Visualizar Produtos");
		lblVP.setBounds(242, 46, 130, 14);
		tabCarrinho.add(lblVP);
		
		JLabel lblMensagem = new JLabel("");
		lblMensagem.setBounds(140, 184, 221, 14);
		tabCarrinho.add(lblMensagem);
		
		try {
			CarrinhoController cc = new CarrinhoController(cbxTipo, tabelaProduto, tabelaCarrinho, lblMensagem
					, tableProduto, tableCarrinho
					, nome_cliente);
			
			btnAdicionar.addActionListener(cc);
			btnRemover.addActionListener(cc);
			btnVisualizar.addActionListener(cc);
			btnVisualizar.addActionListener(this);
			
			btnVoltar.addActionListener(this);
			cbxTipo.addActionListener(cc);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals("Checkout")){
			this.dispose();
		}
		
		if(comando.equals("voltar")){
			MenuCView mcv = new MenuCView();
			mcv.setVisible(true);
			this.dispose();
		}
		
	}
}