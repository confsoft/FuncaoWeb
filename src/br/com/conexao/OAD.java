// Create on 14/05/2011

package br.com.conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Paulo.Mariano
 */
public class OAD {

    Connection con = null;
    Statement stmt = null;
    GerenciaConexao manager = null;

    public OAD(String url, String usuario, String senha){
    	this.manager = new GerenciaConexao(url, usuario, senha);
    }
    
    public void conecta() throws Exception{
        this.con = manager.pegaConexao();
        this.stmt = con.createStatement();
    }

    public void desconecta() throws Exception{
        manager.fechaConexao(this.con,stmt,null);
    }

    public void desconecta(ResultSet rs) throws Exception{
        manager.fechaConexao(this.con, stmt, rs);
    }

    public ResultSet executaSQLcomRs(String sql) throws Exception{
        //instacia objeto para captura das inf do banco
        ResultSet rs = null;

        //cria com a conexao a declaracao
        this.conecta();
        //com a declaracao executa a string sql no banco preenchendo o objeto de resultado
        rs = this.stmt.executeQuery(sql);

        //retorna o objeto do resultado
        return rs;
    }
    
    public int executaSQL(String sql) throws Exception{
            //para executar um comando SQL INSERT ou UPDATE
            //apenas conectar e usar declaracao no banco
            this.conecta();

            //usando a declaracao e capturando quantas linhas foram afetadas se for > 0 houve mudança no banco
            int resultado = stmt.executeUpdate(sql);

            this.desconecta();

            //return resultado;
            if(resultado <= 0)
                throw new Exception("Nao alterou nenhum registro.");
            else
                return resultado;
    }

}
