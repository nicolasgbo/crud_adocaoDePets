/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Especie;
import model.bean.Pet;

/**
 *
 * @author nicol
 */

public class PetDAO {
    //CREATE - Para Criar novos pets no banco de dados
    public boolean create(Pet pet) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            //Criando a query para inserir um novo pet no banco de dados
            String sql = "INSERT INTO Pet (nome, idade, status, idUsuario, idEspecie) VALUES (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            
            // Substituindo os ?  pelos valores do objeto Pet
            stmt.setString(1, pet.getNomePet());
            stmt.setInt(2, pet.getIdadePet());
            stmt.setString(3, pet.getStatusPet());
            stmt.setInt(4, pet.getIdUsuarioDono());
            stmt.setInt(5, pet.getIdEspecie());
            
            //Executando a query criada
            stmt.executeUpdate();
            
            //Retornando true caso o cadastro der certo
            return true;
        } catch (SQLException ex) {
            //Caso der errado, vai ser lancado erro
            System.err.println("Erro ao cadastrar pet: " + ex);
            ex.printStackTrace();
            //Retornando false se tiver algum erro
            return false;
        } finally {
            //Independente do resultado, ele vai fechar a conexao depois de tudo
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    //READ - Para Listar todos os pets e solicitar a adocao dos pets
    public ArrayList<Pet> listarPets() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pet> pets = new ArrayList<>();
        
        try {
            //Criando a query para consultar os pets disponiveis
            String sql = "SELECT p.idPet, p.nome, p.idade, p.status, p.idUsuario, " +
                     "e.nome AS nomeEspecie, p.idEspecie " +
                     "FROM Pet p " +
                     "INNER JOIN Especie e ON p.idEspecie = e.idEspecie " +
                     "WHERE p.status = 'disponivel' " +
                     "ORDER BY p.nome";
            
            //Executando a query criada para buscar pets disponiveis
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            //Criando um loop para passar por todos os pets no banco de dados
            while (rs.next()) {
                Pet pet = new Pet();
                pet.setIdPet(rs. getInt("idPet"));
                pet.setNomePet(rs.getString("nome"));
                pet.setIdadePet(rs.getInt("idade"));
                pet.setStatusPet(rs.getString("status"));
                pet.setIdUsuarioDono(rs.getInt("idUsuario"));
                pet.setIdEspecie(rs.getInt("idEspecie"));

                //Criando um objeto Especie, para facilitar a exibicao 
                Especie especie = new Especie();
                especie.setIdEspecie(rs.getInt("idEspecie"));
                especie.setNomeEspecie(rs.getString("nomeEspecie"));

                pets.add(pet);
            }
        } catch (SQLException ex) {
            //Se der erro, ele vai lancar o erro pro usuario
            System.err.println("Erro ao listar pets: " + ex);
            ex.printStackTrace();
        } finally {
            //Independente do resultado, ele vai fechar a conexao
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return pets;
    }
    
    //READ - Para gerenciar as solicitacoes de adocao dos pets
    
    //UPDATE - Atualizar informacoes sobre os pets
    
    //DELETE - Deletar um pet do banco de dados
}
