package br.com.conexao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class InstrucaoSql {

	private String usuario;
	private String senha;
	private String servidor;
	
	public InstrucaoSql() {
		Properties properties = new Properties();
		FileInputStream arquivoDePropriedades = null;
		try {
			arquivoDePropriedades = new FileInputStream(new File(this.getClass().getResource("").getPath()+"banco.properties"));
			properties.load(arquivoDePropriedades);
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}
		this.servidor = properties.getProperty("url");
		this.usuario = properties.getProperty("usuario");
		this.senha = properties.getProperty("senha");
	}

	public String selectEstrutura(String query) {

		try {
			OAD dao = new OAD(servidor, usuario, senha);

			ResultSet rs = dao.executaSQLcomRs(query);// Executa a consulta
			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount();

			String tableName = rsmd.getTableName(1);
			String texto = "";
			System.out.print("\n---------------------------------");
			System.out.print("\n    Tabela: " + tableName + ".\n");
			System.out.print("---------------------------------");

			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					String colName = rsmd.getColumnName(i);
					String s = rs.getString(i);
					texto += colName + ": " + s + "\n";
				}
				texto += "--------------------------------------------------\n";

			}

			rs.close();
			return texto;

		} catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public String selectColuna(String query, String nomeColuna) {
		String a = "";
		OAD dao = new OAD(servidor, usuario, senha);
		try {
			ResultSet rs = dao.executaSQLcomRs(query);
			if (rs.next()) {
				a = rs.getString(nomeColuna);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public List<String> selectVariosDaColuna(String query, String nomeColuna) {
		List<String> a = new ArrayList();
		OAD dao = new OAD(servidor, usuario, senha);
		try {
			ResultSet rs = dao.executaSQLcomRs(query);
			while (rs.next()) {
				a.add(rs.getString(nomeColuna));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public void update(String query) {
		String usuario = this.usuario;
		String senha = this.senha;

		OAD dao = new OAD(servidor, usuario, senha);
		System.out.println(query);
		try {
			System.out.println("linhas atualizadas: " + dao.executaSQL(query));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
