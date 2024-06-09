package loja_backoffice.model;

public class HashTable {
	
	Compra[] compras;
	
    public int getTamanho() {
    	return compras.length;
    }
    
    public HashTable(Lista<Compra> listaCompra) {
    	try {
    		compras = new Compra[listaCompra.size()];
			popularHashTable(listaCompra);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void popularHashTable(Lista<Compra> listaCompras) throws Exception {
    	int tamanho = listaCompras.size();
    	inicializarHashTable();
    	for (int i = 0; i < tamanho; i++){
    		Compra compra = listaCompras.get(i);
    		if(compra == null) {
    			
    		} else {
    			Produto produto = compra.getProduto(0);
    			int hashC = compra.hashCode();
    			compras[compra.hashCode()].setProduto(produto);
    			
    			compras[hashC].setCliente(compra.getCliente());
    			compras[hashC].setCodigo_Compra(compra.getCodigo_Compra());
    			compras[hashC].setTotal(compra.getTotal());
    			
    			compras[hashC].setIdentificador(compra.getCliente());
    			compras[hashC].setINDEX(1);
    		}
    	}
    }
    
    private void inicializarHashTable() {
        int tamanho = compras.length;
        for (int i = 0; i < tamanho; i++) {
            compras[i] = new Compra();
        }

    }
    
    public Compra getCompra(int hash) throws Exception {
    	Compra compraRetorno = compras[hash];
       return compraRetorno;
    }
    
}
