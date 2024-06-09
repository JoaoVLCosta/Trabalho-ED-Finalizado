package loja_backoffice.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import loja_backoffice.model.DadoArquivo;
import loja_backoffice.model.Lista;

public class ArquivoController {
	
	private static final String CAMINHO = System.getProperty("user.home") + File.separator + "DadosLoja";

	public ArquivoController() {
		super();
	}
	
	public String registrarCodigos(String nome_arquivo) throws Exception {
		
		Lista<String> lista = buscarTodos(nome_arquivo);
		
		int tamanho = lista.size();
		
		cadastro(nome_arquivo, ""+tamanho);
		
		return ""+tamanho;
	}
	
	public void cadastro(String nome_arquivo, String objetoInfo) throws IOException {

		File diretorio = new File(CAMINHO);
		
		if(!diretorio.exists()) {
			diretorio.mkdir();
		}
		
		File arquivo = new File(CAMINHO, nome_arquivo);
		
		boolean existeArquivo = false;
		
		if(arquivo.exists()) {
			existeArquivo = true;
		}
		
		registrarCadastro(arquivo, objetoInfo+"\r\n", existeArquivo);
		
	}
	
	public Lista<String> buscarTodos(String nome_arquivo) throws Exception{
		
		Lista<String> lista = new Lista<String>();
		File arquivo = new File(CAMINHO, nome_arquivo);
		
		if(arquivo.exists() && arquivo.isFile()) {
			
			FileInputStream fis = new FileInputStream(arquivo);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			
			String linha = buffer.readLine();
			
			lista.addFirst(linha);
			
			linha = buffer.readLine();
			
			while(linha != null) {
				lista.addLast(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return lista;
	}
	
	public void excluir(String nome_arquivo, String id, int indice) throws Exception {
		
		Lista<String> listaInicial = buscarTodos(nome_arquivo);
		
		Lista<String> listaFinal = new Lista<String>();
		
		String linha = "";
		
		int tamanho = listaInicial.size();
		
		for(int i = 0; i < tamanho; i++) {
			
			linha = listaInicial.get(i);
			
			String[] vet = linha.split(";");
			
			if(!vet[indice].equals(id)) {
				listaFinal.addFirst(linha);
			}
			
		}
		
		tamanho = listaFinal.size();
		
		String texto = "";
		
		for(int i = 0; i < tamanho; i++) {
			texto = listaFinal.get(i) + "\r\n" + texto;
		}
		
		File arquivo = new File(CAMINHO, nome_arquivo);
		
		registrarCadastro(arquivo, texto, false);
	}
	
	//-------------------------------------MÉTODO DE CADASTRO DIRETO---------------------------------------------//
	private void registrarCadastro(File arquivo, String objetoInfo, boolean preservar_arquivo) throws IOException {
		
		FileWriter abrirArquivo = new FileWriter(arquivo, preservar_arquivo);
		
		PrintWriter escreverArquivo = new PrintWriter(abrirArquivo);
		
		escreverArquivo.write(objetoInfo);
		
		escreverArquivo.flush();
		escreverArquivo.close();
		abrirArquivo.close();
	}
	//--------------------------------------------------------------------------------------------------------------//
}
