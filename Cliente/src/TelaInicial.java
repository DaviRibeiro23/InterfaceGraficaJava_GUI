import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Model.Cliente;
import Repository.ClienteRepository;

public class TelaInicial extends JFrame implements ActionListener {

	JFrame frame = new JFrame("Cadastro de Cliente");

	JPanel painel = new JPanel();

	JLabel lTitulo = new JLabel();
	JLabel lCodigo = new JLabel();
	JLabel lNome = new JLabel();
	JLabel lEstado = new JLabel();
	JLabel lCidade = new JLabel();
	JLabel lBairro = new JLabel();
	JLabel lLogradouro = new JLabel();
	JLabel lComplemento = new JLabel();

	JTextArea tCodigo = new JTextArea();
	JTextArea tNome = new JTextArea();
	JTextArea tEstado = new JTextArea();
	JTextArea tCidade = new JTextArea();
	JTextArea tBairro = new JTextArea();
	JTextArea tLogradouro = new JTextArea();
	JTextArea tComplemento = new JTextArea();

	JButton bNovo = new JButton();
	JButton bSalvar = new JButton();
	JButton bApagar = new JButton();
	JButton bConsultar = new JButton();

	Cliente cliente = new Cliente();
	ClienteRepository repository = new ClienteRepository();

	JTable tabela = new JTable();
	DefaultTableModel model = new DefaultTableModel();

	public void telaCadastro() {

		frame.setSize(800, 500);

		painel.setSize(800, 500);

		painel.setLayout(null);

		lTitulo.setText(" Cadastro de Cliente ");
		lTitulo.setBounds(130, 20, 150, 20);

		lCodigo.setText(" Código :");
		lCodigo.setBounds(15, 50, 50, 10);
		tCodigo.setBounds(110, 50, 150, 15);

		lNome.setText(" Nome :");
		lNome.setBounds(15, 75, 50, 10);
		tNome.setBounds(110, 75, 300, 15);

		lEstado.setText("Estado :");
		lEstado.setBounds(15, 100, 50, 10);
		tEstado.setBounds(110, 100, 200, 15);

		lCidade.setText("Cidade :");
		lCidade.setBounds(15, 125, 50, 10);
		tCidade.setBounds(110, 125, 300, 15);

		lBairro.setText("Bairro :");
		lBairro.setBounds(15, 150, 50, 10);
		tBairro.setBounds(110, 150, 300, 15);

		lLogradouro.setText("Logradouro :");
		lLogradouro.setBounds(15, 175, 90, 10);
		tLogradouro.setBounds(110, 175, 300, 15);

		lComplemento.setText("Complemento :");
		lComplemento.setBounds(15, 200, 150, 15);
		tComplemento.setBounds(110, 200, 300, 15);

		frame.setVisible(true);

		frame.add(painel);
		painel.add(lTitulo);
		painel.add(lCodigo);
		painel.add(lNome);
		painel.add(lEstado);
		painel.add(lCidade);
		painel.add(lBairro);
		painel.add(lLogradouro);
		painel.add(lComplemento);

		painel.add(tCodigo);
		painel.add(tNome);
		painel.add(tEstado);
		painel.add(tCidade);
		painel.add(tBairro);
		painel.add(tLogradouro);
		painel.add(tComplemento);

		painel.add(bNovo);
		painel.add(bSalvar);
		painel.add(bApagar);
		painel.add(bConsultar);
		painel.add(tabela);
		
		tabela.setBounds(20, 280, 600, 180);

		bNovo.setText("Novo");
		bNovo.setBounds(120, 230, 80, 30);

		bSalvar.setText("Salvar");
		bSalvar.setBounds(220, 230, 80, 30);
		bSalvar.addActionListener(this);

		bApagar.setText("Apagar");
		bApagar.setBounds(320, 230, 80, 30);

		bConsultar.setText("Consultar");
		bConsultar.setBounds(300, 40, 90, 25);
		bConsultar.addActionListener(new consultaAction());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cliente.setCodigo(Integer.parseInt(tCodigo.getText()));
		cliente.setNome(tNome.getText());
		cliente.setEstado(tEstado.getText());
		cliente.setCidade(tCidade.getText());
		cliente.setBairro(tBairro.getText());
		cliente.setLogradouro(tLogradouro.getText());
		cliente.setComplemento(tComplemento.getText());

		System.out.println(cliente.getCodigo());
		System.out.println(cliente.getNome());
		System.out.println(cliente.getEstado());
		System.out.println(cliente.getCidade());
		System.out.println(cliente.getBairro());
		System.out.println(cliente.getLogradouro());
		System.out.println(cliente.getComplemento());

		repository.insere(cliente);

		tCodigo.setText(null);
		tNome.setText(null);
		tEstado.setText(null);
		tCidade.setText(null);
		tBairro.setText(null);
		tLogradouro.setText(null);
		tComplemento.setText(null);

		new consultaAction().actionPerformed(e);
		try {
			repository.consulta();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public class consultaAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			while (tabela.getModel().getRowCount() > 0) {
				((DefaultTableModel) tabela.getModel()).setRowCount(0);
				((DefaultTableModel) tabela.getModel()).setColumnCount(0);
			}
			ArrayList<Cliente> clientes = new ArrayList<>();
			try {
				clientes = repository.consulta();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			model.addColumn("Código");
			model.addColumn("Nome");
			model.addColumn("Estado");
			model.addColumn("Cidade");
			model.addColumn("Bairro");
			model.addColumn("Logradouro");
			model.addColumn("Complemento");

			model.addRow(new Object[] { "Codigo", "Nome", "CPF", "Estado", "Cidade", "Bairro", "Logradouro",
					"Complemento" });

			for (int i = 0; i < clientes.size(); i++) {
				cliente = clientes.get(i);
				model.addRow(new Object[] { cliente.getCodigo(), cliente.getNome(), cliente.getEstado(),
						cliente.getCidade(), cliente.getBairro(), cliente.getLogradouro(),
						cliente.getComplemento() });
			}
			tabela.setModel(model);

		}
	}
}