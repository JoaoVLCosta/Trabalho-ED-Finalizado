package loja_backoffice.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import loja_backoffice.model.Produto;
import loja_backoffice.model.Compra;
import loja_backoffice.model.HashTable;
import loja_backoffice.model.Lista;
import loja_backoffice.model.Tipo;
import loja_backoffice.view.HistoricoView;
import loja_backoffice.view.MenuFView;

public class HistoricoController implements ListSelectionListener{

	private static HistoricoView hv;
	
	private String nome_cliente;
	
	private static Lista<Compra> historicoCliente = new Lista<Compra>();
	
	static HashTable hashCompras = new HashTable(historicoCliente);
	
	//CONSTRUTOR
	public HistoricoController(String nome_cliente) {
		this.nome_cliente = nome_cliente;
		hv = new HistoricoView();
		hv.setVisible(true);
		popularListaCompras();
		
		hv.getJTable().getSelectionModel().addListSelectionListener(this);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			String codigo = hv.getJTable().getValueAt(hv.getJTable().getSelectedRow(), 0).toString();
			exibirCompra(codigo);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void exibirCompra(String codigo) throws NumberFormatException, Exception {
		
		Compra compra = hashCompras.getCompra(Integer.valueOf(codigo));
		
		hv.getAreaTexto().setText("Cliente: " + compra.getCliente() + " - Código de Compra: " +
				compra.getCodigo_Compra() + " - TOTAL: R$" + compra.getTotal() + "\n\n");
		
		Lista<Produto> listaP = compra.getComprados();
		
		int tamanho = listaP.size();
		
		for(int i = 0; i < tamanho; i++) {
			hv.getAreaTexto().setText(hv.getAreaTexto().getText() + listaP.get(i).toString("exibir") + "\n");
		}
		
	}

	private void popularListaCompras() {
		try {
			
			historicoCliente.flush();
			
			Lista<String> compras = new Lista<String>();
			
			ArquivoController ac = new ArquivoController();
			
			compras = ac.buscarTodos("Compras.csv");
			int tamanho = compras.size();
			
			for(int i = 0; i < tamanho; i++) {
				String texto = compras.get(i);
				if(!texto.isEmpty() || texto != null) {
					Compra compra = new Compra(texto);
					historicoCliente.addFirst(compra);
				}
			}
			
			hashCompras = new HashTable(historicoCliente);
			
			int tamanhoHash = hashCompras.getTamanho();
			
			for(int i = 0; i < tamanhoHash; i++) {
				Compra retorno = hashCompras.getCompra(i);
				if(retorno.getCliente() != null && !retorno.getCliente().isEmpty() && retorno.getCliente().equals(nome_cliente)) {
					String[] vet = {retorno.getCodigo_Compra(), retorno.getCliente(), retorno.getTotal()};
					hv.getTabela().addRow(vet);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}
