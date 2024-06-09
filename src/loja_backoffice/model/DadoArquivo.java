package loja_backoffice.model;

public interface DadoArquivo {
	
	void setAtributos(String atributos);
	String toString(String separador);

	String getIdentificador();
	int getINDEX();
	void setIdentificador();
	
}
