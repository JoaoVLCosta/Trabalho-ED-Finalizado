package loja_backoffice.model;

public class Compra implements DadoArquivo {
	
	private String nome_cliente;
	private String codigo_Compra;
	private String total;
	
	private Lista<Produto> comprados = new Lista<Produto>();
	
	private String id;
	private int indice;
	
	@Override
	public int hashCode() {
		return Integer.valueOf(codigo_Compra);
	}
	
	@Override
	public String toString() {
		return nome_cliente + ";" + codigo_Compra+ ";" + total + ";";
	}
	
	public Compra() {
		
	}
	
	public Compra(String atributos) {
		
		String[] vetorAtributos = atributos.split(";");
		
		setCodigo_Compra(vetorAtributos[0]);
		setCliente(vetorAtributos[1]);
		setTotal(vetorAtributos[2]);
		
		Produto produto = new Produto();
		
		produto.setTipo(vetorAtributos[3]);
		produto.setNome(vetorAtributos[4]);
		produto.setCodigo(vetorAtributos[5]);
		produto.setValor(vetorAtributos[6]);
		produto.setQuantidade(vetorAtributos[7]);
		
		setProduto(produto);
		
		setIdentificador(getCliente());
		setINDEX(1);
	}
	
	public void setProduto(Produto produto){
		comprados.addFirst(produto);
	}
	
	public Lista<Produto> getComprados(){
		return comprados;
	}
	
	public Produto getProduto(int posicao) throws Exception {
		return comprados.get(posicao);
	}
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getCliente() {
		return nome_cliente;
	}
	public void setCliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
	public String getCodigo_Compra() {
		return codigo_Compra;
	}
	public void setCodigo_Compra(String codigo_Compra) {
		this.codigo_Compra = codigo_Compra;
	}

	public void setIdentificador(String id) {
		this.id = id;
	}


	public void setINDEX(int indice) {
		this.indice = indice;
	}
	
	
	@Override
	public void setAtributos(String atributos) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String toString(String separador) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getIdentificador() {
		return id;
	}


	@Override
	public int getINDEX() {
		return indice;
	}


	@Override
	public void setIdentificador() {
		// TODO Auto-generated method stub
		
	}
	
}
