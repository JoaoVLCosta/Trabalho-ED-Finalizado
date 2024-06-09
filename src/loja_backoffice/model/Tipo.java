package loja_backoffice.model;

public class Tipo implements DadoArquivo{

	private String codigo;
	private String nome;	
	private String descricao;
	
	@Override
	public String toString() {
		return codigo + ";" + nome + ";" + descricao;
	}
	
	//---------------Métodos Implementados--------------//	
	
	@Override
	public String toString(String separador) {
		
		if(separador.equals("exibir")) {
			return "Código: " + getCodigo() + " - Descrição: " + getNome();
		} else {
			return codigo + " - " + nome;
		}
		
	}
	
	@Override
	public void setAtributos(String atributos){
		
		String[] vetorAtributos = atributos.split(";");
		
		setCodigo(vetorAtributos[0]);
		setNome(vetorAtributos[1]);
		setDescricao(vetorAtributos[2]);
		
	}	
	
	//------------------------------------------------//
	
	//---------------Métodos do Objeto--------------//	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String tipo) {
		this.nome = tipo;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		if(codigo == null || codigo.isEmpty()) {
			id = nome;
			indice = 1;
		} else {
			id = codigo;
			indice = 0;
		}	
	}
	//------------------------------------------------//
	
}
