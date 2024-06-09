package loja_backoffice.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuFView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MenuFView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrar.setBounds(37, 104, 144, 62);
		contentPane.add(btnCadastrar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(250, 104, 144, 62);
		contentPane.add(btnBuscar);
		
		JLabel lblNewLabel = new JLabel("Qual tipo de atividade quer executar?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(55, 39, 371, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnVoltar = new JButton("voltar");
		btnVoltar.setBounds(10, 10, 85, 21);
		contentPane.add(btnVoltar);
		
		btnCadastrar.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnVoltar.addActionListener(this);;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals("voltar")) {
			InicialView iv = new InicialView();
			iv.setVisible(true);
			this.dispose();
		}
		
		if(comando.equals("Buscar")) {
			BuscaView bv = new BuscaView();
			bv.setVisible(true);
			this.dispose();
		}
		
		if(comando.equals("Cadastrar")) {
			CadastrosView bd = new CadastrosView();
			bd.setVisible(true);
			this.dispose();
		}
	}
}
