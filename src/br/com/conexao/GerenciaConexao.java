package br.com.conexao;

import java.sql.*;

/**
 * Classe para gerenciar conexao <br />
 * Permitindo conectar, desconectar e executar comandos no banco
 */
public class GerenciaConexao {

	private String user;
	private String pass;
	private String url;

	public GerenciaConexao(String url, String usuario, String senha) {
		this.url = url;
		this.user = usuario;
		this.pass = senha;
	}

	public void fechaConexao(Connection con) throws Exception {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			StringBuilder mensagem = new StringBuilder(
					"Nao foi possivel finalizar conexao.");
			mensagem.append("\nMotivo: ").append(ex);
			throw new Exception(mensagem.toString());
		}
	}

	public void fechaConexao(Connection con, PreparedStatement ps)
			throws Exception {
		try {
			if (ps != null) {
				ps.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			StringBuilder mensagem = new StringBuilder(
					"Nao foi possivel finalizar conexao.");
			mensagem.append("\nMotivo: ").append(ex);
			throw new Exception(mensagem.toString());
		}

	}

	public void fechaConexao(Connection con, PreparedStatement ps, ResultSet rs)
			throws Exception {
		if (rs != null) {
			rs.close();
		}

		if (ps != null) {
			ps.close();
		}

		if (con != null) {
			con.close();
		}
	}

	public void fechaConexao(Connection con, Statement st, ResultSet rs)
			throws Exception {
		if (rs != null) {
			rs.close();
		}

		if (st != null) {
			st.close();
		}

		if (con != null) {
			con.close();
		}
	}

	public Connection pegaConexao() throws Exception {
	    Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, user, pass);
	}
}
