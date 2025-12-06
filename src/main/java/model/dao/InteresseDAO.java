    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.bean.Interesse;

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
        
        try {
            //Criando a query para inserir um novo interesse no banco de dados do usuário
            String sql = "INSERT INTO Interesse (idPet, idUsuario, dataInteresse) VALUES (?, ?, CURDATE())";
            stmt = con.preparedStatement(sql);
            
            //Substituindo os valores ? pelos valores enviados pelo usuario
            stmt.setInt(1, interesse.getIdPet());
            stmt.setInt(2, interesse.getIdUsuario());
            
            //Executando a query
            stmt.executeUpdate();
            
            //Exibindo mensagem caso de certo
            System.out.printIn("Solicitação registrada com sucesso!");
                    
            //Caso der certo ele vai retornar true
                 
        } catch (SQLException ex) {
            
        }
    }
}
