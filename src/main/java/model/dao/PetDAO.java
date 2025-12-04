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
import model.bean.Pet;

/**
 *
 * @author nicol
 */
public class PetDAO {
    // CREATE - Criando os Pets no banco de dados
    public void create(Pet pet) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO Pet (nome, idade, status, idUsuario, idEspecie) VALUES (?, ?, ?, ?, ?)";
            //Preparando para executar uma query
            stmt = con.prepareStatement(sql); 
            
            stmt.setString(1, pet.getNomePet());
            stmt.setInt(2, pet.getIdadePet());
            stmt.setString(3, pet.getStatusPet());
            stmt.setInt(4, pet.getIdUsuario());
            stmt.setInt(5, pet.getIdEspecie());
            
            //Executando a query
            stmt.executeUpdate();
        
        //Tratando erros
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar pet: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    // READ (listar todos)
    public ArrayList<Pet> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pet> pets = new ArrayList<>(); //Criando um arraylist para ler todos os Pets do banco de dados

        try {
            stmt = con.prepareStatement("SELECT * FROM Pet");
            rs = stmt.executeQuery();

            //Criando um loop para exibir todos os pets do BD
            while (rs.next()) {
                Pet pet = new Pet();
                pet.setIdPet(rs.getInt("idPet"));
                pet.setNomePet(rs.getString("nome"));
                pet.setIdadePet(rs.getInt("idade"));
                pet.setStatusPet(rs.getString("status"));
                pet.setIdUsuario(rs.getInt("idUsuario"));
                pet.setIdEspecie(rs.getInt("idEspecie"));

                pets.add(pet);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao listar pets: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }

        return pets;
    }

    // UPDATE - Atualizar os dados do PET
    public void update(Pet pet) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE Pet SET nome=?, idade=?, status=?, idEspecie=? WHERE idPet=?";
            stmt = con.prepareStatement(sql);

            stmt.setString(1, pet.getNomePet());
            stmt.setInt(2, pet.getIdadePet());
            stmt.setString(3, pet.getStatusPet());
            stmt.setInt(4, pet.getIdEspecie());
            stmt.setInt(5, pet.getIdPet());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar pet: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    // DELETE
    public void delete(int idPet) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Pet WHERE idPet = ?");
            stmt.setInt(1, idPet);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao excluir pet: " + ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
