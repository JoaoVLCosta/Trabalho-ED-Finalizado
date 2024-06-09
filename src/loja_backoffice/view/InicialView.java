package loja_backoffice.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class InicialView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public InicialView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente.setBounds(37, 104, 144, 62);
		contentPane.add(btnCliente);
		
		JButton btnFuncionario = new JButton("Funcionário");
		btnFuncionario.setBounds(250, 104, 144, 62);
		contentPane.add(btnFuncionario);
		
		JLabel lblNewLabel = new JLabel("Qual tipo de usuário quer acessar?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(65, 37, 371, 33);
		contentPane.add(lblNewLabel);
		
		
		btnCliente.addActionListener(this);
		btnFuncionario.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();
		
		if(comando.equals("Cliente")) {
			MenuCView mcv = new MenuCView();
			mcv.setVisible(true);
			this.dispose();
		}
		
		if(comando.equals("Funcionário")) {
			MenuFView mfv = new MenuFView();
			mfv.setVisible(true);
			this.dispose();
		}
	}
}
