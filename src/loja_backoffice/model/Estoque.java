package loja_backoffice.model;

public class Estoque {

    private static Lista<Produto>[] estoque = new Lista[10];
    
    public int getTamanho() {
        return estoque.length;
    }
    
    public Estoque(Lista<Produto> listaProduto) {
    	try {
			popularHashTable(listaProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void popularHashTable(Lista<Produto> listaProduto) throws Exception {
    	int tamanho = listaProduto.size();
    	
    	redimensionar(tamanho);
    	inicializarHashTable();
    	
    	for (int i = 0; i < tamanho; i++){
    		addProduto(listaProduto.get(i));
    	}
    }

    public void redimensionar(int tamanhoAdicional) {
        Lista<Produto>[] novoEstoque = new Lista[estoque.length + tamanhoAdicional];
        Lista<Produto>[] antigoEstoque;
        antigoEstoque = estoque;
        estoque = novoEstoque;
        inicializarHashTable();
        
        int tamanho = antigoEstoque.length;
        
        for (int i = 0; i < tamanho; i++) {
            estoque[i] = antigoEstoque[i];
        }
    }

    private void inicializarHashTable() {
        int tamanho = estoque.length;
        for (int i = 0; i < tamanho; i++) {
            estoque[i] = new Lista<Produto>();
        }

    }

    public void addProduto(Produto produto) {
        int posicao = produto.hashCode();
        estoque[posicao].addFirst(produto);
    }

    public Lista<Produto> getLista(Produto produto) throws Exception {
    	Lista<Produto> listaRetorno = estoque[produto.hashCode()];
       return listaRetorno;

    }

}
