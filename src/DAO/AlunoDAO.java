package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import model.Aluno;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class AlunoDAO {
    private Connection conn;

    public AlunoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Aluno aluno) throws SQLException{
        // esse comando é vulneravel
        //String sql = "select * from aluno where usuario = '"
        //        + aluno.getUsuario() + "' AND senha = '"
        //        + aluno.getSenha() +"'";
        String sql = "select * from aluno where usuario = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,aluno.getUsuario());
        statement.setString(2,aluno.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    public void inserir(Aluno aluno) throws SQLException{
        String sql = "insert into aluno (nome, usuario, senha) values ('"
                + aluno.getNome()    + "', '" 
                + aluno.getUsuario() + "', '" 
                + aluno.getSenha()   + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    public void atualizar(Aluno aluno) throws SQLException{
        String sql = "update aluno set senha = ? where usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, aluno.getSenha());
        statement.setString(2, aluno.getUsuario());
        statement.execute();
        conn.close();
    }
    public void excluir (Aluno aluno) throws SQLException{
        String sql = "delete from aluno where usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, aluno.getUsuario());
        statement.execute();
        conn.close();
    }
}
