package loja_backoffice.model;

public class Cliente implements DadoArquivo {
	
	//Cliente
	private String cpf_cnpj;
	private String nome;
	private String classificacao; 

	//Contato
	private String contato;	
	private String email;
	
	//Endereço
	private String cep;
	private String logradouro;
	private String numero_de_porta;
	private String complemento;
	
	@Override
	public String toString() {
		return classificacao + ";" + nome +  ";" + cpf_cnpj + ";" + cep + ";" + numero_de_porta + ";" + logradouro
				+ ";" + complemento + ";" + contato + email + ";";
	}
	
	//---------------Métodos Implementados--------------//
	
	@Override
	public void setAtributos(String atributos) {
		String[] vetorAtributos = atributos.split(";");
		
		setClassificacao(vetorAtributos[0]);
		setNome(vetorAtributos[1]);
		setCPF_CNPJ(vetorAtributos[2]);
		setCep(vetorAtributos[3]);
		setLogradouro(vetorAtributos[4]);
		setNumero_de_porta(vetorAtributos[5]);
		setComplemento(vetorAtributos[6]);
		setContato(vetorAtributos[7]);
		
	}

	@Override
	public String toString(String exibir) {
		
		if(exibir.equals("exibir")) {
			return "Classificação: " + getClassificacao() + " - CPF / CNPJ: " + getCPF_CNPJ()
			+ " - Nome: " + getNome() + "\nCEP: " + getCep()
			+ "\nEndereço: " + getLogradouro() + " Nº " + getNumero_de_porta() + " " + getComplemento()
			+ " - Contato:" + getContato();
		} else {
			return getCPF_CNPJ() + " - " + getNome();
		}
		
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
		if((cpf_cnpj == null || cpf_cnpj.isEmpty()) && (nome == null || nome.isEmpty())) {
			id = classificacao;
			indice = 0;
		} else if(cpf_cnpj == null || cpf_cnpj.isEmpty()){
			id = nome;
			indice = 1;
		} else {
			id = cpf_cnpj;
			indice = 2;
		}
	}
	
	//------------------------------------------------//
	
	//---------------Métodos do Objeto--------------//
	
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	
	public String getClassificacao() {
		return classificacao;
		
	}
	public String getCPF_CNPJ() {
		return cpf_cnpj;
	}
	public void setCPF_CNPJ(String cpf) {
		this.cpf_cnpj = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero_de_porta() {
		return numero_de_porta;
	}
	public void setNumero_de_porta(String numero_de_porta) {
		this.numero_de_porta = numero_de_porta;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	//------------------------------------------------//
	
}
