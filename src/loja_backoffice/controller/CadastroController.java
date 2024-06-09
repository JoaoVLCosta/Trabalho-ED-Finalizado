package loja_backoffice.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import loja_backoffice.model.Cliente;
import loja_backoffice.model.Lista;
import loja_backoffice.model.Produto;
import loja_backoffice.model.Tipo;

public class CadastroController implements ActionListener {
	
	ArquivoController ac = new ArquivoController();
	
	private Lista<JTextField> listaTipoTF = new Lista<JTextField>();
	private Lista<JTextField> listaProdutoTF = new Lista<JTextField>();
	private Lista<JTextField> listaClienteTF = new Lista<JTextField>();
	
	private JTextField tfTipoNome;
	private JTextField tfTipoDescricao;
	
	private JTextField tfProdutoNome;
	private JTextField tfProdutoValor;
	private JTextField tfProdutoQuantidade;
	private JComboBox<String> produtoTipoComboBox;
	
	//------------CLIENTE------------//
	private JTextField tfNomeCliente;
	private JTextField tfCPF_CNPJ;

	private JTextField tfCEP;
	private JTextField tfNumero;
	private JTextField tfLogradouro;
	private JTextField tfComplemento;

	private JTextField tfEmail;
	private JTextField tfContato;
	
	private JComboBox<String> cbNaturezaCliente;
	//------------------------------//
	
	private JTextArea taTipoDeProdutoLista;
	private JTextArea taProdutoLista;
	private JTextArea taClienteLista;
	
	public CadastroController(
			JTextField tfTipoNome, JTextField tfTipoDescricao, JTextField tfProdutoNome, JTextField tfProdutoValor, JTextField tfProdutoQuantidade,
			JTextField tfNomeCliente, JTextField tfCPF_CNPJ, JTextField tfCEP, JTextField tfNumero, JTextField tfLogradouro, 
			JTextField tfComplemento, JTextField tfEmail, JTextField tfContato,
			JComboBox<String> cbNaturezaCliente, JComboBox<String> produtoTipoComboBox,
			JTextArea taProdutoLista, JTextArea taTipoDeProdutoLista, JTextArea taClienteLista) throws Exception {
		
		this.tfTipoNome = tfTipoNome;
		this.tfTipoDescricao = tfTipoDescricao;
		listaTipoTF.add(0, tfTipoNome);
		listaTipoTF.add(1, tfTipoDescricao);
		
		this.tfProdutoNome = tfProdutoNome;
		this.tfProdutoValor = tfProdutoValor;
		this.tfProdutoQuantidade = tfProdutoQuantidade;
		listaProdutoTF.add(0, tfProdutoNome);
		listaProdutoTF.add(1, tfProdutoValor);
		listaProdutoTF.add(2, tfProdutoQuantidade);
		
		//------------CLIENTE------------//
		this.tfNomeCliente = tfNomeCliente;
		this.tfCPF_CNPJ = tfCPF_CNPJ;

		this.tfCEP = tfCEP;
		this.tfNumero = tfNumero;
		this.tfLogradouro = tfLogradouro;
		this.tfComplemento = tfComplemento;

		this.tfEmail = tfEmail;
		this.tfContato = tfContato;
		
		this.cbNaturezaCliente = cbNaturezaCliente;
		
		listaClienteTF.add(0, tfNomeCliente);
		listaClienteTF.add(1, tfCPF_CNPJ);
		listaClienteTF.add(2, tfCEP);
		listaClienteTF.add(3, tfLogradouro);
		listaClienteTF.add(4, tfNumero);
		listaClienteTF.add(5, tfComplemento);
		listaClienteTF.add(6, tfContato);
		
		
		this.produtoTipoComboBox = produtoTipoComboBox;
		
		this.taProdutoLista = taProdutoLista;
		this.taTipoDeProdutoLista = taTipoDeProdutoLista;
		this.taClienteLista = taClienteLista;
	}


	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		try {
			if(comando.equals("Cadastrar Tipo")) {
				if(validar(listaTipoTF, taTipoDeProdutoLista)) {
					cadastroTipo();
					BuscaController bCont = new BuscaController();
					bCont.popularTipoComboBox(produtoTipoComboBox);
				}
			}
			
			if(comando.equals("Cadastrar Produto")) {
				if(validar(listaProdutoTF, taProdutoLista)) {
					cadastroProduto();
					BuscaController bCont = new BuscaController();
					bCont.popularListaProduto();
				}
			}
			
			if(comando.equals("Cadastrar Cliente")) {
				if(validar(listaClienteTF, taClienteLista)) {
					cadastroCliente();
				}
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
	private boolean validar(Lista<JTextField> listaTF, JTextArea area) throws Exception {
		
		int tamanho = listaTF.size();
		
		for(int i = 0; i < tamanho; i++) {
			if(listaTF.get(i).getText().isEmpty()) {
				area.setText("ERRO: Campos Obrigatórios Vazios\n");
				return false;
		}
			
		for(int x = 0; x < tamanho; x++) {
			if(listaTF.get(x).getText().indexOf(";") > -1) {
				area.setText("ERRO: Caractere inválido: \" ; \"\n");
				return false;
			}
		}
		
	}
		StringBuilder st = new StringBuilder(tfProdutoValor.getText());
		int posicao = tfProdutoValor.getText().indexOf(",");
		if(posicao > -1){
			st.setCharAt(posicao, '.');
		}
		
		tfProdutoValor.setText(st.toString());
		
		return true;
}

	private void cadastroTipo() throws Exception {
		
		String codigo = ac.registrarCodigos("Tipo_Codigos.csv");
		String texto = codigo + ";" + tfTipoNome.getText() + ";" + tfTipoDescricao.getText() + ";";
		
		Tipo tipo = new Tipo();
		
		tipo.setAtributos(texto);
		ac.cadastro("tipo.csv", tipo.toString());
		
		taTipoDeProdutoLista.setText(taTipoDeProdutoLista.getText() + "Novo Cadastro Inserido - Código: " + tipo.getCodigo()
		+ " - Nome: " + tipo.getNome()
		+ " - Descrição: " + tipo.getDescricao() + "\n");
		
		tfTipoNome.setText("");
		tfTipoDescricao.setText("");
	}
	
	//--------------------------------------------CADASTRO DE PRODUTO---------------------------------------------------//
	
	private void cadastroProduto() throws Exception {
		
		String[] vet = produtoTipoComboBox.getSelectedItem().toString().split(" - ");
		String codigo = vet[0] + "/" + ac.registrarCodigos("Produto_Codigos.csv");
		String texto = vet[1] + ";" + tfProdutoNome.getText() + ";" + codigo + ";" + tfProdutoValor.getText()
		+ ";" + tfProdutoQuantidade.getText() + ";";
		
		Produto produto = new Produto();
		
		produto.setAtributos(texto);
		ac.cadastro("produto.csv", produto.toString());
		
		tfProdutoNome.setText("");
		tfProdutoValor.setText("");
		tfProdutoQuantidade.setText("");
		
		taProdutoLista.setText(taProdutoLista.getText() + "Novo Cadastro Inserido - Tipo: " 
				+ produto.getTipo() + " - Nome: " + produto.getNome() + " - Código: " + produto.getCodigo() + " - Valor: " +
				produto.getValor() + " - Quantidade: " + produto.getQuantidade() + "\n");
	}
	//------------------------------------------------------------------------------------------------------------------//
	
	//--------------------------------------------CADASTRO DE CLIENTE---------------------------------------------------//
	private void cadastroCliente() throws IOException {
		String texto = cbNaturezaCliente.getSelectedItem().toString() + ";" + 
				tfNomeCliente.getText() + ";" + tfCPF_CNPJ.getText() + ";" + 
				tfCEP.getText() + ";" + tfLogradouro.getText() + ";" + tfNumero.getText() + ";" + tfComplemento.getText()
				+ ";" + tfContato.getText() + ";" + tfEmail.getText() + ";";
		
		Cliente cliente = new Cliente();
		
		cliente.setAtributos(texto);
		cliente.setIdentificador();
		
		ac.cadastro("cliente.csv", cliente.toString());
		taClienteLista.setText(cliente.toString("exibir"));
		
		tfNomeCliente.setText("");
		tfCPF_CNPJ.setText("");
		tfCEP.setText("");
		tfNumero.setText("");
		tfLogradouro.setText("");
		tfComplemento.setText("");
		tfEmail.setText("");
		tfContato.setText("");
		
	}
}
