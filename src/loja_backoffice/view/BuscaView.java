package loja_backoffice.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import loja_backoffice.controller.BuscaController;
import loja_backoffice.controller.CadastroController;

public class BuscaView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField tfProdutoNome;

	public BuscaView() {
		componentesTela();

	}

	public void componentesTela(){
		setTitle("Buscas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 606, 423);
		contentPane.add(tabbedPane);
		DefaultTableModel tabelaTipos = new DefaultTableModel();
		tabelaTipos.setColumnIdentifiers(new String[] { "ID Tipo","Nome","Descrição" });

		JPanel tabProduto = new JPanel();
		tabbedPane.addTab("Produto", null, tabProduto, "Busca de Produtos");
		tabProduto.setLayout(null);

		JLabel lblProdutoNome = new JLabel("Nome");
		lblProdutoNome.setBounds(50, 69, 42, 35);
		tabProduto.add(lblProdutoNome);

		tfProdutoNome = new JTextField();
		tfProdutoNome.setBounds(109, 73, 412, 27);
		tabProduto.add(tfProdutoNome);
		tfProdutoNome.setColumns(10);

		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 200, 581, 186);
		tabProduto.add(scrollPaneProduto);

		//Tabela de busca Produtos
		JTable tableBuscaProdutoLista = new JTable();
		DefaultTableModel tabelaProdutos = new DefaultTableModel();
		
		scrollPaneProduto.setViewportView(tableBuscaProdutoLista);
		tableBuscaProdutoLista.setModel(tabelaProdutos);
		tabelaProdutos.setColumnIdentifiers(new String[] { "Tipo", "Nome", "ID Tipo/ID Produto", "Valor", "Quantidade" });


		JLabel lblProdutoTipo = new JLabel("Tipo");
		lblProdutoTipo.setBounds(49, 33, 42, 29);
		tabProduto.add(lblProdutoTipo);


		JComboBox<String> produtoTipoComboBox = new JComboBox<String>();
		produtoTipoComboBox.setBounds(108, 33, 413, 29);
		tabProduto.add(produtoTipoComboBox);
		
		JButton btnExcluirProduto = new JButton("Excluir Produto");
		btnExcluirProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExcluirProduto.setBounds(335, 134, 205, 56);
		tabProduto.add(btnExcluirProduto);

		JButton btnBuscarProduto = new JButton("Buscar Produto");
		btnBuscarProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscarProduto.setBounds(71, 134, 205, 56);
		tabProduto.add(btnBuscarProduto);



		JButton btnVoltar_1 = new JButton("voltar");
		btnVoltar_1.setBounds(10, 10, 85, 21);
		tabProduto.add(btnVoltar_1);

		JPanel tabCliente = new JPanel();
		tabbedPane.addTab("Cliente", null, tabCliente, "Buscar Cliente");
		tabCliente.setLayout(null);


		JButton btnBuscarCliente = new JButton("Buscar Cliente");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscarCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscarCliente.setBounds(35, 135, 135, 55);
		tabCliente.add(btnBuscarCliente);

		JButton btnExcluirCliente = new JButton("Excluir Cliente");
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluirCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExcluirCliente.setBounds(205, 135, 135, 55);
		tabCliente.add(btnExcluirCliente);


		JTextField tfBuscaCliente = new JTextField("Digite um CPF/CNPJ");
		tfBuscaCliente.setEditable(true);
		tfBuscaCliente.setBounds(85, 61, 413, 29);

		tfBuscaCliente.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				tfBuscaCliente.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {

			}
		});
		tabCliente.add(tfBuscaCliente);

		JScrollPane scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setBounds(10, 202, 579, 184);
		tabCliente.add(scrollPaneClientes);

		//Tabela de Busca de Clientes
		JTable tableBuscaClienteLista = new JTable();
		DefaultTableModel tabelaClientes = new DefaultTableModel();
		scrollPaneClientes.setViewportView(tableBuscaClienteLista);
		tableBuscaClienteLista.setModel(tabelaClientes);
		tabelaClientes.setColumnIdentifiers(new String []{"Tipo de Cliente","Nome","CPF/CNPJ","Contato","Email","Endereço"});
		tableBuscaClienteLista.setBounds(10, 202, 579, 184);





		JPanel tabTipoDeProduto = new JPanel();
		tabTipoDeProduto.setToolTipText("");
		tabbedPane.addTab("Tipo", null, tabTipoDeProduto, "Busca de Tipo de Produto");
		tabTipoDeProduto.setLayout(null);
		
		JButton btnBuscarTipo = new JButton("Buscar Tipo");
		btnBuscarTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscarTipo.setBounds(58, 134, 205, 56);
		tabTipoDeProduto.add(btnBuscarTipo);
		
		JScrollPane scrollPaneTipoDeProduto = new JScrollPane();
		scrollPaneTipoDeProduto.setBounds(10, 200, 581, 186);
		tabTipoDeProduto.add(scrollPaneTipoDeProduto);
		
		//Tabela de Busca de Tipos
		
		JTable tableBuscaTipoLista = new JTable();
		
		tableBuscaTipoLista.setModel(tabelaTipos);
		scrollPaneTipoDeProduto.setViewportView(tableBuscaTipoLista);
		
		//TextField Utilizada na Busca de Tipo
		JTextField tfBuscaTipo = new JTextField("Digite o ID de um Tipo");
		tfBuscaTipo.setEditable(true);
		tfBuscaTipo.setBounds(86, 60, 413, 29);
		tfBuscaTipo.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				tfBuscaTipo.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		tabTipoDeProduto.add(tfBuscaTipo);
		
		JButton btnExcluirTipo = new JButton("Excluir Tipo");
		btnExcluirTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExcluirTipo.setBounds(322, 134, 205, 56);
		tabTipoDeProduto.add(btnExcluirTipo);
		
		
		JButton btnVoltar = new JButton("voltar");
		btnVoltar.setBounds(10, 10, 85, 21);
		tabTipoDeProduto.add(btnVoltar);
		
		
		JLabel lblMensagemTipo = new JLabel("");
		lblMensagemTipo.setBounds(250, 100, 174, 14);
		tabTipoDeProduto.add(lblMensagemTipo);

		JButton btnVoltar_2 = new JButton("voltar");
		btnVoltar_2.setBounds(10, 10, 85, 21);
		tabCliente.add(btnVoltar_2);
		
		JLabel lblMensagemCliente = new JLabel("");
		lblMensagemCliente.setBounds(250, 100, 174, 14);
		tabCliente.add(lblMensagemCliente);
		
		JLabel lblMensagemProduto = new JLabel("");
		lblMensagemProduto.setBounds(250, 109, 174, 14);
		tabProduto.add(lblMensagemProduto);

		JButton btnCompras = new JButton("Visualizar Compras");
		btnCompras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCompras.setBounds(380, 135, 200, 55);
		tabCliente.add(btnCompras);
		
		try {

			btnVoltar.addActionListener(this);
			btnVoltar_1.addActionListener(this);
			btnVoltar_2.addActionListener(this);

			BuscaController bCont = new BuscaController(
					tfProdutoNome,
					tfBuscaTipo, produtoTipoComboBox, tfBuscaCliente,
					tabelaTipos, tabelaProdutos, tabelaClientes,
					lblMensagemTipo, lblMensagemProduto, lblMensagemCliente);
			
			
			bCont.popularTipoComboBox(produtoTipoComboBox);
			
			btnBuscarTipo.addActionListener(bCont);
			btnExcluirTipo.addActionListener(bCont);

			btnBuscarProduto.addActionListener(bCont);
			btnExcluirProduto.addActionListener(bCont);

			btnBuscarCliente.addActionListener(bCont);
			btnExcluirCliente.addActionListener(bCont);
			btnCompras.addActionListener(bCont);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals("voltar")){
			MenuFView mfv = new MenuFView();
			mfv.setVisible(true);
			this.dispose();
		}
		
	}
}