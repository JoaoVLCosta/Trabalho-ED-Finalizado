package loja_backoffice.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loja_backoffice.controller.BuscaController;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class MenuCView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfValidar;
	JLabel lblMsg;

	public MenuCView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Nome de Usuário");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setBounds(139, 64, 183, 33);
		contentPane.add(lblUsuario);
		
		JButton btnVoltar = new JButton("voltar");
		btnVoltar.setBounds(10, 10, 85, 21);
		contentPane.add(btnVoltar);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(157, 165, 124, 33);
		contentPane.add(btnLogin);
		
		//BOTÃO VOLTAR//
		btnVoltar.addActionListener(this);
		
		btnLogin.addActionListener(this);
		
		try {
			BuscaController bc = new BuscaController();
			
			tfValidar = new JTextField();
			tfValidar.setBounds(39, 108, 363, 21);
			contentPane.add(tfValidar);
			tfValidar.setColumns(10);
			
			JLabel lblMsg = new JLabel("");
			lblMsg.setBounds(145, 135, 150, 14);
			contentPane.add(lblMsg);
			
			this.lblMsg = lblMsg;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
		String comando = e.getActionCommand();
		
		BuscaController bc = new BuscaController();
		
		if(comando.equals("voltar")) {
			InicialView iv = new InicialView();
			iv.setVisible(true);
			this.dispose();
		}
		
			
		String validar = bc.busca(bc.getListaCliente(), tfValidar.getText(), 1);
		
		if(comando.equals("Login")) {
			if(!validar.equals("Não Encontrado")) {
				CarrinhoView cv = new CarrinhoView(tfValidar.getText());
				cv.setVisible(true);
				this.dispose();
			} else {
				lblMsg.setText("Usuário Não Cadastrado");
			}
		}
		
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
