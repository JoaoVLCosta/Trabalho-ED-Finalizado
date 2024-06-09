package loja_backoffice.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loja_backoffice.controller.BuscaController;
import loja_backoffice.controller.CadastroController;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CadastrosView extends JFrame implements ActionListener{

	private JTextField campo;
	private JLabel titulo;
	private JComboBox<String> cbNaturezaCliente;
	private JLabel lblContato;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfTipoNome;
	private JTextField tfTipoDescricao;
	
	private JTextField tfProdutoNome;
	private JTextField tfProdutoValor;
	private JTextField tfProdutoQuantidade;
	private JTextField tfNomeCliente;
	private JTextField tfCPF_CNPJ;
	private JTextField tfContato;
	private JTextField tfEmail;
	private JTextField tfCEP;
	private JTextField tfLogradouro;
	private JTextField tfNumero;
	private JTextField tfComplemento;

	public CadastrosView() {
		setTitle("Cadastros");
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
		
		
		JPanel tabTipoDeProduto = new JPanel();
		tabTipoDeProduto.setToolTipText("");
		tabbedPane.addTab("Tipo de Produto", null, tabTipoDeProduto, "Cadastro de Tipo de Produto");
		tabTipoDeProduto.setLayout(null);
		
		JLabel lblTipoCodigo = new JLabel("Descrição");
		lblTipoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipoCodigo.setBounds(81, 84, 85, 49);
		tabTipoDeProduto.add(lblTipoCodigo);
		
		tfTipoDescricao = new JTextField();
		tfTipoDescricao.setBounds(176, 95, 273, 30);
		tabTipoDeProduto.add(tfTipoDescricao);
		tfTipoDescricao.setColumns(10);
		
		JButton btnTipoCadastrar = new JButton("Cadastrar Tipo");
		btnTipoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTipoCadastrar.setBounds(202, 134, 205, 56);
		tabTipoDeProduto.add(btnTipoCadastrar);
		
		JScrollPane scrollPaneTipoDeProduto = new JScrollPane();
		scrollPaneTipoDeProduto.setBounds(10, 200, 581, 186);
		tabTipoDeProduto.add(scrollPaneTipoDeProduto);
		
		JTextArea taTipoDeProdutoLista = new JTextArea();
		scrollPaneTipoDeProduto.setViewportView(taTipoDeProdutoLista);
		
		JPanel tabProduto = new JPanel();
		tabbedPane.addTab("Produto", null, tabProduto, "Cadastro de Produtos");
		tabProduto.setLayout(null);
		
		JLabel lblProdutoNome = new JLabel("Nome");
		lblProdutoNome.setBounds(58, 83, 42, 35);
		tabProduto.add(lblProdutoNome);
		
		JLabel lblProdutoValor = new JLabel("Valor");
		lblProdutoValor.setBounds(58, 126, 42, 29);
		tabProduto.add(lblProdutoValor);
		
		tfProdutoNome = new JTextField();
		tfProdutoNome.setBounds(134, 87, 188, 27);
		tabProduto.add(tfProdutoNome);
		tfProdutoNome.setColumns(10);
		
		tfProdutoValor = new JTextField();
		tfProdutoValor.setBounds(134, 124, 188, 26);
		tabProduto.add(tfProdutoValor);
		tfProdutoValor.setColumns(10);
		
		JButton btnProdutoCadastrar = new JButton("Cadastrar Produto");
		btnProdutoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnProdutoCadastrar.setBounds(352, 105, 188, 66);
		tabProduto.add(btnProdutoCadastrar);
		
		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 200, 581, 186);
		tabProduto.add(scrollPaneProduto);
		
		JTextArea taProdutoLista = new JTextArea();
		scrollPaneProduto.setViewportView(taProdutoLista);
		
		JLabel lblProdutoTipo = new JLabel("Tipo");
		lblProdutoTipo.setBounds(58, 44, 42, 29);
		tabProduto.add(lblProdutoTipo);
		
		JLabel lblProdutoQuantidade = new JLabel("Quantidade");
		lblProdutoQuantidade.setBounds(30, 161, 94, 29);
		tabProduto.add(lblProdutoQuantidade);
		
		tfProdutoQuantidade = new JTextField();
		tfProdutoQuantidade.setBounds(134, 159, 188, 26);
		tabProduto.add(tfProdutoQuantidade);
		tfProdutoQuantidade.setColumns(10);
		
		
		JComboBox<String> produtoTipoComboBox = new JComboBox<String>();
		produtoTipoComboBox.setBounds(127, 44, 413, 29);
		tabProduto.add(produtoTipoComboBox);
		
		
		JButton btnVoltar_1 = new JButton("voltar");
		btnVoltar_1.setBounds(10, 10, 85, 21);
		tabTipoDeProduto.add(btnVoltar_1);
		

		JButton btnVoltar = new JButton("voltar");
		btnVoltar.setBounds(10, 10, 85, 21);
		tabProduto.add(btnVoltar);
		
		JPanel tabCliente = new JPanel();
		tabCliente.setToolTipText("");
		tabbedPane.addTab("Cliente", null, tabCliente, "Cadastro de Clientes");
		tabCliente.setLayout(null);
		
		tfNomeCliente = new JTextField();
		tfNomeCliente.setBounds(120, 83, 162, 19);
		tabCliente.add(tfNomeCliente);
		tfNomeCliente.setColumns(10);
		
		JLabel lblClienteNome = new JLabel("Nome");
		lblClienteNome.setBounds(51, 83, 45, 19);
		tabCliente.add(lblClienteNome);
		
		JButton btnVoltar_2 = new JButton("voltar");
		btnVoltar_2.setBounds(10, 13, 85, 21);
		tabCliente.add(btnVoltar_2);
		
		JLabel lblCPF_CNPJ = new JLabel("CPF / CNPJ");
		lblCPF_CNPJ.setBounds(27, 115, 69, 13);
		tabCliente.add(lblCPF_CNPJ);
		
		tfCPF_CNPJ = new JTextField();
		tfCPF_CNPJ.setColumns(10);
		tfCPF_CNPJ.setBounds(120, 112, 162, 19);
		tabCliente.add(tfCPF_CNPJ);
		
		JLabel lblContato = new JLabel("Celular");
		lblContato.setBounds(315, 87, 69, 13);
		tabCliente.add(lblContato);
		
		tfContato = new JTextField();
		tfContato.setColumns(10);
		tfContato.setBounds(388, 83, 162, 19);
		tabCliente.add(tfContato);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(388, 112, 162, 19);
		tabCliente.add(tfEmail);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(315, 115, 45, 13);
		tabCliente.add(lblEmail);
		
		JComboBox<String> cbNaturezaCliente = new JComboBox<String>();
		cbNaturezaCliente.setBounds(120, 52, 162, 21);
		tabCliente.add(cbNaturezaCliente);
		
		JLabel lblPessoa = new JLabel("Tipo de Pessoa");
		lblPessoa.setBounds(10, 55, 107, 13);
		tabCliente.add(lblPessoa);
		
		tfCEP = new JTextField();
		tfCEP.setColumns(10);
		tfCEP.setBounds(10, 172, 126, 19);
		tabCliente.add(tfCEP);
		
		JLabel lblCEP = new JLabel("CEP");
		lblCEP.setBounds(62, 155, 45, 13);
		tabCliente.add(lblCEP);
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(198, 155, 69, 13);
		tabCliente.add(lblLogradouro);
		
		tfLogradouro = new JTextField();
		tfLogradouro.setColumns(10);
		tfLogradouro.setBounds(146, 172, 162, 19);
		tabCliente.add(tfLogradouro);
		
		tfNumero = new JTextField();
		tfNumero.setColumns(10);
		tfNumero.setBounds(32, 221, 78, 19);
		tabCliente.add(tfNumero);
		
		JLabel lblNmero = new JLabel("Número");
		lblNmero.setBounds(51, 204, 45, 13);
		tabCliente.add(lblNmero);
		
		tfComplemento = new JTextField();
		tfComplemento.setColumns(10);
		tfComplemento.setBounds(161, 221, 139, 19);
		tabCliente.add(tfComplemento);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(197, 204, 85, 13);
		tabCliente.add(lblComplemento);
		
		JTextArea taClienteLista = new JTextArea();
		taClienteLista.setBounds(10, 265, 579, 131);
		tabCliente.add(taClienteLista);
		
		JButton btnClienteCadastrar = new JButton("Cadastrar Cliente");
		btnClienteCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClienteCadastrar.setBounds(372, 175, 188, 66);
		tabCliente.add(btnClienteCadastrar);
		
		tfTipoNome = new JTextField();
		tfTipoNome.setColumns(10);
		tfTipoNome.setBounds(176, 49, 273, 30);
		tabTipoDeProduto.add(tfTipoNome);
		
		JLabel lblTipoNome = new JLabel("Nome");
		lblTipoNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipoNome.setBounds(81, 38, 85, 49);
		tabTipoDeProduto.add(lblTipoNome);
		
		
		try {
			
			BuscaController bCont = new BuscaController();
			bCont.popularTipoComboBox(produtoTipoComboBox);
			
			cbNaturezaCliente.addItem("Pessoa Fisica");
			cbNaturezaCliente.addItem("Pessoa Jurídica");
			
			cbNaturezaCliente.addActionListener(this);
			
			this.cbNaturezaCliente = cbNaturezaCliente;
			campo = tfEmail;
			titulo = lblEmail;
			campo.setVisible(false);
			titulo.setVisible(false);
			this.lblContato = lblContato;
			
			
		
		CadastroController cCont = new CadastroController(
				tfTipoNome, tfTipoDescricao, tfProdutoNome, tfProdutoValor, tfProdutoQuantidade,
				tfNomeCliente, tfCPF_CNPJ, tfCEP, tfNumero, tfLogradouro, 
				tfComplemento, tfEmail, tfContato,
				cbNaturezaCliente, produtoTipoComboBox, 
				taProdutoLista, taTipoDeProdutoLista, taClienteLista);
		
		btnTipoCadastrar.addActionListener(cCont);
		btnProdutoCadastrar.addActionListener(cCont);
		btnClienteCadastrar.addActionListener(cCont);
		
		btnVoltar.addActionListener(this);
		btnVoltar_1.addActionListener(this);
		btnVoltar_2.addActionListener(this);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals("comboBoxChanged")) {
			atualizarPessoa();
		}
		
		if(comando.equals("voltar")){
			MenuFView mfv = new MenuFView();
			mfv.setVisible(true);
			this.dispose();
		}
		
	}
	
	public void atualizarPessoa() {
		String texto = cbNaturezaCliente.getSelectedItem().toString();
		
		if(texto.equals("Pessoa Jurídica")) {
			campo.setVisible(true);
			titulo.setVisible(true);
			campo.setText("");
			lblContato.setText("Telefone");
		} else { if(texto.equals("Pessoa Fisica")){
			campo.setVisible(false);
			titulo.setVisible(false);
			lblContato.setText("Celular");
		}
	}
	}
}