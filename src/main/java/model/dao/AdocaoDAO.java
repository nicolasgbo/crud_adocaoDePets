/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author nicol
 */
public class AdocaoDAO {
    //Metodo para aprovar uma adocao (faz toda a transacao de uma vez so)
    public boolean aprovarAdocao(int idPet, int idUsuario) {
        //Iniciando a conexao com o BD
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null; 
        
        try {
            //Iniciando uma transacao, ou ele executa tudo, ou nao executa nada
            con.setAutoCommit(false); //false, decido quando confirmar
            
            //Criando a query para inserir um usuario 
            String sqlAdocao = "INSERT INTO Adocao (idUsuario, idPet, dataAdocao) VALUES (?, ?, CURDATE())";
            stmt = con.prepareStatement(sqlAdocao);
            //Recebendo os valores fornecidos pelo usuario
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idPet);
            //Executando e fechando a query
            stmt.executeUpdate();
            stmt.close();
            
            //Criando a query para passar o status do pet de "disponivel" para "adotado"
            String sqlPet = "UPDATE Pet SET status = 'adotado' WHERE idPet = ?";
            stmt = con.prepareStatement(sqlPet);
            stmt.setInt(1, idPet);
            //Executando e fechando a query
            stmt.executeUpdate();
            stmt.close();
            
            //Removendo todos os interesses registrados pelo pet
            String sqlInteresse = "DELETE FROM Interesse WHERE idPet = ?";
            stmt = con. prepareStatement(sqlInteresse);
            //Passando o id do pet que foi adotado
            stmt.setInt(1, idPet);
            //Executando a query criada
            stmt.executeUpdate();
            
            //Confirmando a transacao
            con.commit();
            
            //Lancando mensagem de sucesso
            System.out.println("Adoção aprovada com sucesso!");
            
            //Se tudo der certo, vai retornar true
            return true;
        } catch (SQLException ex) {
            //Tratando o erro. Se der erro em alguma transacao, ele desfaz tudo
            try {
                if (con != null) {
                    con.rollback(); //rollback para desfazer tudo
                    //Fechando a conexao com o bd
                    Conexao.fecharConexao(con, stmt);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            //Lancando o erro
            System.out.println("Erro ao aprovar adoção: " + ex.getMessage());
            return false;
        
        } finally {
            //Voltando o autoCommit para true e fechando conexao
            try {
                if (con != null) {
                    con.setAutoCommit(true); //Voltando ao modo normal
                }
            } catch (SQLException e) {
                e. printStackTrace();
            }
            //Independente do resultado, ele vai fechar a conexao
            Conexao.fecharConexao(con, stmt);
        }
    }
}
