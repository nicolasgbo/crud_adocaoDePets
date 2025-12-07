    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Interesse;
import model.bean.Usuario;

/**
 *
 * @author nicol
 */
public class InteresseDAO {
    //CREATE - para registrar interesse de um usuario em um pet
    public boolean registrarInteresse (Interesse interesse) throws SQLException {
        //Iniciando conexao com o bd
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            //Criando a query para inserir um novo interesse no banco de dados do usuário
            String sql = "INSERT INTO Interesse (idPet, idUsuario, dataInteresse) VALUES (?, ?, CURDATE())";
            stmt = con.prepareStatement(sql);
            
            //Substituindo os valores ? pelos valores enviados pelo usuario
            stmt.setInt(1, interesse.getIdPet());
            stmt.setInt(2, interesse.getIdUsuario());
            
            //Executando a query
            stmt.executeUpdate();
            
            //Exibindo mensagem caso de certo
            System.out.println("Solicitação registrada com sucesso!");
                    
            //Caso der certo ele vai retornar true
            return true;           
        } catch (SQLException ex) {
            //Se der erro, ele vai lancar o erro
            System.err.println("Erro ao verificar interesse: " + ex);
            ex.printStackTrace();
            //Retornado false caso de errado 
            return false;
        } finally {
            //Independentemente do r    esultado ele vai fechar as conexoes
            Conexao.fecharConexao(con, stmt, rs);
        }
    }
    
    //READ - Verificando se existem interesses registrados no banco de dados
    public boolean verificarInteresseExistente (int idPet, int idUsuario) {
        //Iniciar a conexao com o bd
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            //Criando a query para consultar se tem interesse cadastrado no bd
            String sql = "SELECT COUNT(*) FROM Interesse WHERE idPet = ? AND idUsuario = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPet);
            stmt.setInt(2, idUsuario);
            
            //Executando a query criada
            rs = stmt.executeQuery();
            
            //Vai retornar true existir interesse no bd
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            //Caso der errado ele vai lancar o erro
            System.out.println("Erro ao verificar interesse: " + ex);
            ex.printStackTrace();
        } finally {
            //Independente de qualquer execucao, depois ele fecha tudo
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        //Caso der errado ele vai retornar false
        return false;
    }
    
    //DELETE - Fazendo a remocao do interesse (quando um usuario recusar a solicitacao de adocao, vai ter que ser removido o interesse da tabela)
    public boolean removerInteresse(int idPet, int idUsuario) {
        //iniciando a conexao com o bd
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        
        try {
            //Criando a query para deletar o interesse do banco de dados
            String sql = "DELETE FROM Interesse WHERE idPet = ? AND idUsuario = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPet);
            stmt.setInt(2, idUsuario);
            
            //Executando a query DELETE criada
            stmt.executeUpdate();
            
            //Inserindo msg informando ao usuario a conclusao da solicitacao
            System.out.println("Solicitação recusada com sucesso!");
            
            //Caso a execucao ocorreu tudo certo, vai retornar true
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao remover a solicitação" + ex);
            ex.printStackTrace();
            //Caso deu algo errado na solicitacao do usuario, vai retornar false
            return false;
        } finally {
            //Independente dos resultados, vai fechar as conexoes
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    //READ - Listar todos os interesses de um pet especifico com os dados do usuario
    public ArrayList<Interesse> listarInteressesPorPet(int idPet) throws SQLException {
        //Criando a conexao e a preparacao com a query
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Interesse> interesses = new ArrayList<>();

        try {
            //Criando a query que vai buscar os interesses do pet com o nome do usuario
            String sql = "SELECT i.idPet, i.idUsuario, i.dataInteresse, u.nome " +
                         "FROM Interesse i " +
                         "INNER JOIN Usuario u ON i.idUsuario = u.idUsuario " +
                         "WHERE i. idPet = ? " +
                         "ORDER BY i.dataInteresse DESC";
            
            //Preparando e executando a query
            stmt = con. prepareStatement(sql);
            stmt.setInt(1, idPet);
            rs = stmt.executeQuery();

            //Percorrendo os resultados exibidos
            while (rs. next()) {
                Interesse interesse = new Interesse();
                interesse.setIdPet(rs.getInt("idPet"));
                interesse.setIdUsuario(rs.getInt("idUsuario"));
                interesse.setDataInteresse(rs.getDate("dataInteresse"). toLocalDate());

                //Criando objeto Usuario com o nome
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario. setNomeUsuario(rs.getString("nome"));
                interesse.setUsuario(usuario);

                interesses.add(interesse);
            }
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }

        return interesses;
    }
}
