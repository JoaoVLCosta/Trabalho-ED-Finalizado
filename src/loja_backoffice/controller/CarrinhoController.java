package loja_backoffice.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import loja_backoffice.model.Produto;
import loja_backoffice.view.CheckoutView;
import loja_backoffice.model.Estoque;
import loja_backoffice.model.Fila;
import loja_backoffice.model.Lista;
import loja_backoffice.model.Pilha;

public class CarrinhoController implements ActionListener {
	
	private static String nome_cliente;
	
	Pilha<Produto> compra = new Pilha<Produto>();
	
	Pilha<Produto> carrinho = new Pilha<Produto>();
	
	Fila<Produto> fila_de_compras = new Fila<Produto>();
	
	private ArquivoController ac = new ArquivoController();
	private BuscaController bc = new BuscaController();
	private static Estoque estoque;
	
	private JComboBox<String> cbxTipo;

	private DefaultTableModel tabelaProduto;
	private DefaultTableModel tabelaCarrinho;
	
	private JTable tableProduto;
	private JTable tableCarrinho;
	
	private JLabel lblMensagem;
	
	public CarrinhoController(JComboBox<String> cbxTipo, DefaultTableModel tabelaProduto, DefaultTableModel tabelaCarrinho, JLabel lblMensagem, 
			JTable tableProduto, JTable tableCarrinho
			, String nome_cliente) throws Exception {
		
		CarrinhoController.nome_cliente = nome_cliente;
		
		this.cbxTipo = cbxTipo;

		this.tabelaProduto = tabelaProduto;
		this.tabelaCarrinho = tabelaCarrinho;
		
		this.tableProduto = tableProduto;
		this.tableCarrinho = tableCarrinho;
		
		this.lblMensagem = lblMensagem;
		
		CarrinhoController.estoque = new Estoque(bc.getListaProduto());
		
		bc.popularTipoComboBox(cbxTipo);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		try {
			
			if(comando.equals("comboBoxChanged")) {
				encontrarProdutos();
			}
			
			if(comando.equals("Adicionar")) {
				if(tableProduto.getSelectedRow() >= 0) {
					lblMensagem.setText("");
					adicionar();
				} else {
					lblMensagem.setText("Selecione um Produto da Tabela acima");
				}
			}
			
			if(comando.equals("Remover")) {
				remover();
			}
			
			if(comando.equals("Checkout")){
				checkout();
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void encontrarProdutos() throws Exception {
		
			Produto produto = new Produto();
		
			String texto = cbxTipo.getSelectedItem().toString();
			
			if(texto.equals("Selecionar Opção")) {
				
			} else {
				String[] vet = texto.split(" - ");
				
				produto.setCodigo(vet[0]);
				produto.setTipo(vet[1]);
				
				Lista<Produto> listaHash = estoque.getLista(produto);
				
				int tamanho = listaHash.size();
				
				tabelaProduto.setRowCount(0);
				
				for (int i = 0; i < tamanho; i++) {
					tabelaProduto.addRow(listaHash.get(i).toString().split(";"));
				}
			}
		

	}

	private void adicionar() throws Exception {
		
		int linha = tableProduto.getSelectedRow();
		String atributos = "";
		
		for(int x = 0; x < 5; x++) {
			atributos = atributos + tableProduto.getModel().getValueAt(linha, x).toString() + ";";
		}
		
		Pilha<String> pilha = new Pilha<String>();
		
		lerCarrinho(pilha, 0);
		
		int tamanhoFila = pilha.size();
		
		tabelaCarrinho.setRowCount(0);
		
		tabelaCarrinho.addRow(atributos.split(";"));
		
		for(int z  = 0;z < tamanhoFila; z++) {
			tabelaCarrinho.addRow(pilha.pop().split(";"));
		}
		
		Produto produto = new Produto();
		produto.setAtributos(atributos);
		
		carrinho.push(produto);
		
	}
	
	private void remover() throws Exception {
		Pilha<String> pilha = new Pilha<String>();
		
		lerCarrinho(pilha, 1);
			
		int tamanhoFila = pilha.size();
			
		tabelaCarrinho.setRowCount(0);
			
		for(int z  = 0;z < tamanhoFila; z++) {
			tabelaCarrinho.addRow(pilha.pop().split(";"));
		}
		
		if(!carrinho.isEmpty()) {
			carrinho.pop();
		}
	}
	
	
	private void lerCarrinho(Pilha<String> pilha, int fim) {
		
		int tamanhoCarrinho = tableCarrinho.getRowCount();
		
		if(tamanhoCarrinho > 0) {
			String texto = "";
			for(int y = tamanhoCarrinho - 1; y >= fim; y--) {
				for(int x = 0; x < 5; x++) {
					texto = texto + tableCarrinho.getModel().getValueAt(y, x).toString() + ";";
				}
				pilha.push(texto);
				texto = "";
			}
	}
			
	}
	
	private void checkout() throws Exception {
		if(carrinho.isEmpty()) {
			lblMensagem.setText("Primeiro Insira Itens no Carrinho");
		} else {
			CheckoutView cov = new CheckoutView(this, nome_cliente);
			cov.setVisible(true);
			
			while(!carrinho.isEmpty()) {
				Produto produto = new Produto();
				produto = carrinho.pop();
				fila_de_compras.insert(produto);
				compra.push(produto);
			}
			
		}
	}
	
	public void iniciar(DefaultTableModel tabelaCaixa, JButton btnFila) throws Exception {
		tabelaCaixa.addRow(fila_de_compras.remove().toString().split(";"));
		btnFila.setText("Próximo");
	}
	
	public void proximoLista(DefaultTableModel tabelaVisualizar, DefaultTableModel tabelaCaixa, JButton btnFila, JButton btnFinalizar) throws Exception {
		
		if(fila_de_compras.isEmpty()) {
			btnFila.setVisible(false);
			btnFinalizar.setVisible(true);
			String[] vet = {tabelaCaixa.getValueAt(0, 1).toString(), tabelaCaixa.getValueAt(0, 3).toString(), tabelaCaixa.getValueAt(0, 4).toString()};
			tabelaCaixa.setRowCount(0);
			tabelaVisualizar.addRow(vet);
		} else {
			
			String[] vet = {tabelaCaixa.getValueAt(0, 1).toString(), tabelaCaixa.getValueAt(0, 3).toString(), tabelaCaixa.getValueAt(0, 4).toString()};
			
			tabelaVisualizar.addRow(vet);
			
			tabelaCaixa.setRowCount(0);
			
			tabelaCaixa.addRow(fila_de_compras.remove().toString().split(";"));
			
			
		}
	}
	
	private float total = 0;
	
	public void somaTotal(JTextArea taTotal, DefaultTableModel tabelaVisualizar) {
		
		int tamanho = tabelaVisualizar.getRowCount();
		total = 0;
		for(int i = 0; i < tamanho; i++) {
			total = total + Float.valueOf(tabelaVisualizar.getValueAt(i, 1).toString());
		}
		
		taTotal.setText("R$" + total);
	}
	
	public void finalizar() throws Exception {
		String codigo = ac.registrarCodigos("ID_Compra.csv");
		String dados = codigo + ";" + nome_cliente + ";" + total + ";";
		while(!compra.isEmpty()) {
			ac.cadastro("Compras.csv", dados + compra.pop().toString());
		}
	}
}
