package loja_backoffice.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import loja_backoffice.model.Tipo;
import loja_backoffice.model.Produto;
import loja_backoffice.model.Cliente;
import loja_backoffice.model.DadoArquivo;
import loja_backoffice.model.Estoque;
import loja_backoffice.model.Lista;

public class BuscaController implements ActionListener {

    private static Lista<Tipo> listaTipo = new Lista<Tipo>();
    private static Lista<Produto> listaProduto = new Lista<Produto>();
    private static Lista<Cliente> listaCliente = new Lista<Cliente>();

    private static Tipo ultimaBusca_Tipo;
    private static Produto ultimaBusca_Produto;
    private static Cliente ultimaBusca_Cliente;

    ArquivoController ac = new ArquivoController();
    ExcluirController ec = new ExcluirController();
    static Estoque estoqueModel = new Estoque(listaProduto);

    private JTextField tfProdutoNome;

    private JTextField tfBuscaTipo;
    private JComboBox<String> produtoTipoComboBox;
    private JTextField tfBuscaCliente;

    private DefaultTableModel tabelaTipos;
    private DefaultTableModel tabelaProdutos;
    private DefaultTableModel tabelaClientes;
    
    private JLabel lblMensagemTipo;
    private JLabel lblMensagemProduto;
    private JLabel lblMensagemCliente;

    //----------------------------MÉTODOS CONSTRUTORES---------------------------------//

    public BuscaController() throws Exception {
        listaTipo.flush();
        listaProduto.flush();
        listaCliente.flush();
        popularListas();
    }

    public BuscaController(
            JTextField tfProdutoNome,
            JTextField tfBuscaTipo, JComboBox<String> produtoTipoComboBox, JTextField tfBuscaCliente,
            DefaultTableModel tabelaTipos, DefaultTableModel tabelaProdutos, DefaultTableModel tabelaClientes,
            JLabel lblMensagemTipo, JLabel lblMensagemProduto, JLabel lblMensagemCliente) throws Exception {

        listaTipo.flush();
        listaProduto.flush();
        listaCliente.flush();
        popularListas();

        this.tfProdutoNome = tfProdutoNome;

        this.tfBuscaTipo = tfBuscaTipo;
        this.produtoTipoComboBox = produtoTipoComboBox;
        this.tfBuscaCliente = tfBuscaCliente;

        this.tabelaTipos = tabelaTipos;
        this.tabelaProdutos = tabelaProdutos;
        this.tabelaClientes = tabelaClientes;
        
        this.lblMensagemTipo = lblMensagemTipo;
        this.lblMensagemProduto = lblMensagemProduto;
        this.lblMensagemCliente = lblMensagemCliente;

        iniciarTabelaTipos();
        iniciarTabelaClientes();
    }

    //-------------------------------------------------------------------------------//

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        try {
            //---------------TIPO----------------//
            if (comando.equals("Buscar Tipo")) {
                tabelaTipos.setRowCount(0);
                buscarTipo();
            }

            if (comando.equals("Excluir Tipo")) {
                if (ultimaBusca_Tipo != null) {
                    ec.excluirAlvo(ultimaBusca_Tipo, "tipo.csv");
                    ec.colapsarProdutos(ultimaBusca_Tipo);
                    ultimaBusca_Tipo = null;
                    listaTipo.flush();
                    popularListaTipo();
                    popularTipoComboBox(produtoTipoComboBox);
                }
            }

            //----------------------------------//

            //-------------PRODUTO--------------//
            if (comando.equals("Buscar Produto")) {
            	tabelaProdutos.setRowCount(0);
                buscarProduto();
            }

            if (comando.equals("Excluir Produto")) {
                if (ultimaBusca_Produto != null) {

                    ec.excluirAlvo(ultimaBusca_Produto, "produto.csv");
                    ultimaBusca_Produto = null;
                    listaProduto.flush();
                    popularListaProduto();
                }
            }

            //-------------CLIENTE--------------//
            if (comando.equals("Buscar Cliente")) {
                tabelaClientes.setRowCount(0);
                buscarCliente();
            }

            if (comando.equals("Excluir Cliente")) {
                if (ultimaBusca_Cliente != null) {
                    ec.excluirAlvo(ultimaBusca_Cliente, "cliente.csv");
                    ultimaBusca_Cliente = null;
                    listaCliente.flush();
                    popularListaCliente();
                }
            }
            
            if (comando.equals("Visualizar Compras")) {
            	if (ultimaBusca_Cliente != null) {
            		HistoricoController hc = new HistoricoController(ultimaBusca_Cliente.getNome());
            		ultimaBusca_Cliente = null;
                }
            }

            //----------------------------------//

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void buscarTipo() throws Exception {

        Tipo tipo = new Tipo();

        String texto = tfBuscaTipo.getText();
        
        if (texto.equals("Digite um Tipo")) {

        	lblMensagemTipo.setText("Busca Inválida");
        	
        }else if(texto.isEmpty()){
            iniciarTabelaTipos();
        } else {
        	String retornoBusca = busca(listaTipo, texto, 0);
        	
            if(!retornoBusca.equals("Não Encontrado")){
            	lblMensagemTipo.setText("");
            	tabelaTipos.addRow(retornoBusca.split(";"));
            	tipo.setCodigo(retornoBusca.split(";")[0]);
            	tipo.setNome(retornoBusca.split(";")[1]);
            	tipo.setIdentificador();
            	
            	//ESTÁTICO//
            	ultimaBusca_Tipo = tipo;
            } else {
            	lblMensagemTipo.setText("Não Encontrado");
            }
            
        }
    }
    private void iniciarTabelaTipos() throws Exception {

        int tamanho = listaTipo.size();
        for (int i=0; i < tamanho;i++) {
            tabelaTipos.addRow(listaTipo.get(i).toString().split(";"));
        }

    }

    private void buscarProduto() throws Exception {

        Produto produto = new Produto();

        String texto = produtoTipoComboBox.getSelectedItem().toString();

        if (texto.equals("Selecionar Opção")) {

        	lblMensagemProduto.setText("Busca Inválida");

        } else {
        	
        	lblMensagemProduto.setText("");
        	
            String[] vet = texto.split(" - ");

            produto.setNome(tfProdutoNome.getText());
            produto.setCodigo(vet[0]);
            produto.setTipo(vet[1]);

            Lista<Produto> listaHash = estoqueModel.getLista(produto);
            
            if (produto.getNome() == null || produto.getNome().isEmpty()) {
                //BUSCA POR TIPO//
            	produto.setIdentificador(produto.getTipo(), 0);
            } else {
            	//BUSCA POR NOME//
                produto.setIdentificador(produto.getNome(), 1);
            }

            String retornoBusca = busca(listaHash, produto.getIdentificador(), produto.getINDEX());
            
            String[] item = retornoBusca.split( "\n");
            
            if(retornoBusca.equals("Não Encontrado")) {
            	
            	lblMensagemProduto.setText(retornoBusca);
            	
            } else {
            	
            	for (String s : item) {
            		tabelaProdutos.addRow(s.split(";"));
            		
            	}
            }
            //ESTÁTICO//
            ultimaBusca_Produto = produto;
        }

    }

    private void iniciarTabelaClientes() throws Exception {

        int tamanho = listaCliente.size();
        for (int i=0; i < tamanho;i++) {
            tabelaClientes.addRow(listaCliente.get(i).toString().split(";",6));
        }

    }

    private void buscarCliente() throws Exception {

        Cliente cliente = new Cliente();

        String texto = tfBuscaCliente.getText();

        if (texto.equals("Digite um CPF/CNPJ")) {
        	lblMensagemCliente.setText("Busca Inválida");

        }else if(texto.isEmpty()){
            iniciarTabelaClientes();
        } else {
        	lblMensagemCliente.setText("");
        	
            cliente.setCPF_CNPJ(texto);
            
            String buscaRetorno = busca(listaCliente,texto,2);
            
            if(buscaRetorno.equals("Não Encontrado")) {
            	lblMensagemCliente.setText(buscaRetorno);
            } else {
            	tabelaClientes.addRow(buscaRetorno.split(";",6));
            	cliente.setAtributos(buscaRetorno);
            }

            //ESTÁTICO//
            ultimaBusca_Cliente = cliente;

        }
    }
    
    public <T extends DadoArquivo> String busca(Lista<T> listaGenerica, String identificador, int indice) throws Exception {

        StringBuilder texto = new StringBuilder();

        if (!listaGenerica.isEmpty()) {

            int tamanho = listaGenerica.size();

            for (int i = 0; i < tamanho; i++) {

                String linha = listaGenerica.get(i).toString();

                String[] vet = linha.split(";");

                if (vet[indice].equals(identificador)) {

                    texto.append(listaGenerica.get(i).toString()).append("\r\n");

                }
            }

        }

        if (texto.isEmpty()) {
            texto = new StringBuilder("Não Encontrado");
        }

        return texto.toString();
    }

    public void popularListaTipo() throws Exception {
        Lista<String> lista = ac.buscarTodos("tipo.csv");
        if (!lista.isEmpty() && lista.get(0) != null && !lista.get(0).isEmpty()) {
            int tamanho = lista.size();
            for (int i = 0; i < tamanho; i++) {
                Tipo tipo = new Tipo();
                tipo.setAtributos(lista.get(i));
                listaTipo.addFirst(tipo);
            }
        }
    }

    //---------------MÉTODO QUE POPULA LISTA DE PRODUTO E HASH TABLE---------------//
    public void popularListaProduto() throws Exception {
        listaProduto.flush();
        Lista<String> lista = ac.buscarTodos("produto.csv");
        if (!lista.isEmpty() && lista.get(0) != null && !lista.get(0).isEmpty()) {
            int tamanho = lista.size();
            for (int i = 0; i < tamanho; i++) {
                Produto produto = new Produto();
                produto.setAtributos(lista.get(i));
                listaProduto.addFirst(produto);
            }
        }
        estoqueModel.popularHashTable(listaProduto);
    }
    //----------------------------------------------------------------------------//

    public void popularListaCliente() throws Exception {
        Lista<String> lista = ac.buscarTodos("cliente.csv");
        if (!lista.isEmpty() && lista.get(0) != null && !lista.get(0).isEmpty()) {
            int tamanho = lista.size();
            for (int i = 0; i < tamanho; i++) {
                Cliente cliente = new Cliente();
                cliente.setAtributos(lista.get(i));
                listaCliente.addFirst(cliente);
            }
        }
    }

    //--------------------------MÉTODOS SEMI-AUTOMÁTICOS-------------------------------//

    private void popularListas() throws Exception {
        popularListaTipo();
        popularListaProduto();
        popularListaCliente();
    }

    public void popularTipoComboBox(JComboBox<String> cBox) throws Exception {

        cBox.removeAllItems();

        cBox.addItem("Selecionar Opção");

        int tamanho = listaTipo.size();

        for (int i = 0; i < tamanho; i++) {
            cBox.addItem(listaTipo.get(i).toString(" - "));
        }

        cBox.setSelectedItem("Selecionar Opção");

    }

    public void popularClienteComboBox(JComboBox<String> cBox) throws Exception {

        cBox.removeAllItems();

        cBox.addItem("Selecionar Opção");

        int tamanho = listaCliente.size();

        for (int i = 0; i < tamanho; i++) {
            cBox.addItem(listaCliente.get(i).toString(" - "));
        }

        cBox.setSelectedItem("Selecionar Opção");

    }
    //-------------------------------------------------------------------------------//
    
    public Lista<Tipo> getListaTipo(){
    	return listaTipo;
    }
    public Lista<Produto> getListaProduto(){
    	return listaProduto;
    }
    public Lista<Cliente> getListaCliente(){
    	return listaCliente;
    }
}