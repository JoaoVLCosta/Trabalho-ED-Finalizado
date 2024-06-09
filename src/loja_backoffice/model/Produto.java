package loja_backoffice.model;

public class Produto implements DadoArquivo {

	private String tipo;
	private String nome;
	private String codigo;
	private float valor;
	private int quantidade;
	
	
	@Override
	public String toString() {
		return tipo + ";" + nome + ";" + codigo + ";" + valor + ";" + quantidade;
	}
	
	//---------------Métodos Implementados--------------//
	
	@Override
	public String toString(String exibir) {
		return 
				"Tipo: " + tipo + " - " +
				"Produto: " + nome+ " - " +
				"Código: " + codigo + " - " +
				"Preço: R$" + valor + " - " +
				"Quantidade: " + quantidade;
	}
	
	@Override
	public void setAtributos(String atributos) {
		
		String[] vetorAtributos = atributos.split(";");
		
		setTipo(vetorAtributos[0]);
		setNome(vetorAtributos[1]);
		setCodigo(vetorAtributos[2]);
		setValor(vetorAtributos[3]);
		setQuantidade(vetorAtributos[4]);
		
	}
	
	@Override
	public int hashCode() {
		return Integer.parseInt((codigo.split("/")[0]));
	}
	
	//------------------------------------------------//
	
	//atributos e métodos pertinentes ao arquivamento//
	
	private String id;
	private int indice;
	
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
		
		if((codigo == null || codigo.isEmpty()) && (nome == null || nome.isEmpty())) {
			id = tipo;
			indice = 0;
		} else if(codigo == null || codigo.isEmpty()){
			id = nome;
			indice = 1;
		} else {
			id = codigo;
			indice = 2;
		}
	}
	
	public void setIdentificador(String id, int indice) {
		this.id = id;
		this.indice = indice;
	}
	
	//------------------------------------------------//
	
	//---------------Métodos do Objeto--------------//
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public void setValor(String valor) {
		this.valor = Float.parseFloat(valor);
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void setQuantidade(String quantidade) {
		this.quantidade = Integer.parseInt(quantidade);
	}
	
	//------------------------------------------------//
}
