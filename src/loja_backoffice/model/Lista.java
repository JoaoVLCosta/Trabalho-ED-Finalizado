package loja_backoffice.model;
public class Lista<T>{

	private No<T> primeiro;
	
	public Lista() {
		primeiro = null;
	}
	
	//MÉTODOS DE OPERAÇÃO//
	
	public void flush() {
		primeiro = null;
	}
	
	public boolean isEmpty() {
		if(primeiro == null) {
			return true;
		}
		return false;
	}

	public int size() {
		int cont = 0;
		if(primeiro != null) {
			No<T> auxiliar = primeiro;
			while(auxiliar != null) {
				auxiliar = auxiliar.proximo;
				cont++;
			}
		}
		return cont;
	}

	private No<T> getNo(int posicao) throws Exception {
		validar();
		validar(posicao, 1);
		
		No<T> auxiliar = primeiro;
		
		for(int cont = 0; cont < posicao; cont++) {
			auxiliar = auxiliar.proximo;
		}
		
		return auxiliar;
	}

	public T get(int posicao) throws Exception {
		
		No<T> elemento = getNo(posicao);
		
		return elemento.dado;
	}
	
	//------------------//

	//MÉTODOS DE ADIÇÃO//
	
	public void addFirst(T objeto) {
		No<T> elemento = new No<T>();
		elemento.dado = objeto;
		elemento.proximo = primeiro;
		primeiro = elemento;
	}

	public void addLast(T objeto) throws Exception {
		validar();
		
		int tamanho = size();
		
		No<T> elemento = new No<T>();
		elemento.dado = objeto;
		elemento.proximo = null;
		
		No<T> ultimo = getNo(tamanho - 1);
		ultimo.proximo = elemento;
		
	}

	public void add(int posicao, T objeto) throws Exception {
		validar(posicao, 0);
		int tamanho = size();
		
		if(posicao == 0) {
			addFirst(objeto);
		} else if(posicao == tamanho - 1) {
			addLast(objeto);
		} else {
			No<T> elemento = new No<T>();
			elemento.dado = objeto;
			No<T> anterior = getNo(posicao - 1);
			elemento.proximo = anterior.proximo;
			anterior.proximo = elemento;
		}
	}

	//------------------//
	
	//MÉTODOS DE REMOÇÃO//
	
	public void removeFirst() throws Exception {
		validar();
		primeiro = primeiro.proximo;
	}

	public void removeLast() throws Exception {
		validar();
		int tamanho = size();
		No<T> penultimo = getNo(tamanho - 2);
		penultimo.proximo = null;
	}

	public void remove(int posicao) throws Exception {
		validar();
		validar(posicao, 1);
		
		int tamanho = size();
		if(posicao == 0) {
			removeFirst();
		}else if(posicao == tamanho - 1) {
			removeLast();
		}else {
			No<T> anterior = getNo(posicao - 1);
			No<T> alvo = anterior.proximo;
			anterior.proximo = alvo.proximo;
		}
		
	}

	//------------------//
	
	//MÉTODOS DE VALIDAÇÃO//
	
	
	private void validar() throws Exception {
		if(isEmpty()) {
			throw new Exception("Lista Vazia");
		}	
		
	}
	
	private void validar(int posicao, int subtrair) throws Exception {
		int tamanho = size();
		if(posicao < 0 || posicao > tamanho - subtrair) {
			throw new Exception("Posição Inválida");
		}		
	}
	
	//--------------------//
}
