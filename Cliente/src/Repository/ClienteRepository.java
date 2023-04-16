package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Conection.Conexao;
import Model.Cliente;

public class ClienteRepository {
	public void insere(Cliente cliente) {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectar();

		try {
			String consulta = "INSERT INTO cliente(codigo,nome,estado,cidade,bairro,logradouro,complemento) "
					+ "VALUES (NEXTVAL('CLIENTE_SEQ'),?,?,?,?,?,?)";

			PreparedStatement stm;
			stm = conn.prepareStatement(consulta);

			stm.setInt(1, cliente.getCodigo());
			stm.setString(2, cliente.getNome());
			stm.setString(3, cliente.getEstado());
			stm.setString(4, cliente.getCidade());
			stm.setString(5, cliente.getBairro());
			stm.setString(6, cliente.getLogradouro());
			stm.setString(7, cliente.getComplemento());
			stm.execute();

		} catch (Exception e) {

		} finally {
			conexao.desconectar(conn);
		}
	}
	public ArrayList<Cliente> consulta() throws SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectar();
		ArrayList<Cliente> clientes = new ArrayList<>();

		String consulta = "SELECT * FROM CLIENTE";

		Statement stm = conn.createStatement();
		ResultSet resultado = stm.executeQuery(consulta);

		try {
			while (resultado.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(resultado.getInt("Codigo"));
				cliente.setNome(resultado.getString("Nome"));
				cliente.setEstado(resultado.getString("Estado"));
				cliente.setCidade(resultado.getString("Cidade"));
				cliente.setBairro(resultado.getString("Bairro"));
				cliente.setLogradouro(resultado.getString("Logradouro"));
				cliente.setComplemento(resultado.getString("Complmento"));
				
				clientes.add(cliente);
				System.out.println(cliente.getNome());
				clientes.add(cliente);
			}

		} catch (Exception e) {
			System.out.println("Não conseguiu consultar a tabela Funcionario");

		} finally {
			conexao.desconectar(conn);
		}

		return clientes;

	}
}
