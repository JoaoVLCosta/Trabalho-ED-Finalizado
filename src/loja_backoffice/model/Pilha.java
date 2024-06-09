package loja_backoffice.model;

public class Pilha<T> {
	
	No<T> topo;
	
	public Pilha() {
		topo = null;
	}
	
	public boolean isEmpty() {
		if(topo == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void push(T e) {
		No<T> elemento = new No<T>();
		elemento.dado = e;
		if(isEmpty()) {
			topo = elemento;
		} else {
			elemento.proximo = topo;
			topo = elemento;
		}
	}
	
	public T pop() throws Exception {
		if(isEmpty()) {
			throw new Exception("Não há elementos para desempilhar");
		}
		T dado = topo.dado;
		topo = topo.proximo;
		return dado;
	}
	
	public T top() throws Exception {
		if(isEmpty()) {
			throw new Exception("Não há elementos na pilha");
		}
		return topo.dado;
	}
	
	public int size() {
		int cont = 0;
		if(!isEmpty()) {
			No<T> auxiliar = topo;
			cont = 1;
			while(auxiliar.proximo != null) {
				auxiliar = auxiliar.proximo;
				cont++;
			}
		}
		return cont;
	}
	

}
