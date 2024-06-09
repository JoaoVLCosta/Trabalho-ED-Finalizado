package loja_backoffice.controller;
import loja_backoffice.model.Tipo;
import javax.swing.JTextArea;

import loja_backoffice.model.DadoArquivo;

public class ExcluirController {
	
	ArquivoController ac = new ArquivoController();
	
	public ExcluirController() {
		super();
	}
		
	public <T extends DadoArquivo> void excluirAlvo(T ulitma_busca, String nome_arquivo) throws Exception {
		
		if(ulitma_busca != null) {
				
			ac.excluir(nome_arquivo, ulitma_busca.getIdentificador(), ulitma_busca.getINDEX());
				
		}
			
			
	}
	
	public void colapsarProdutos(Tipo tipo) throws Exception {
		
		ac.excluir("produto.csv", tipo.getNome(), 0);
		
	}
	
	
		
}
