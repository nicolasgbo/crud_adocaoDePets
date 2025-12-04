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
import model.bean.Especie;

/**
 *
 * @author nicol
 */
public class EspecieDAO {
    
    // CREATE - Criar nova espécie
    public void create(Especie especie) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO Especie (nomeEspecie) VALUES (?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, especie.getNomeEspecie());
            
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Espécie cadastrada! Linhas afetadas: " + linhasAfetadas);
            
        } catch (SQLException ex) {
            System.err.println("Erro ao cadastrar espécie: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar espécie: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    // READ - Listar todas as espécies
    public ArrayList<Especie> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Especie> especies = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Especie ORDER BY nomeEspecie");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Especie especie = new Especie();
                especie.setIdEspecie(rs.getInt("idEspecie"));
                especie.setNomeEspecie(rs.getString("nomeEspecie"));
                especies.add(especie);
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro ao listar espécies: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return especies;
    }
    
    // UPDATE - Atualizar espécie
    public void update(Especie especie) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE Especie SET nomeEspecie = ? WHERE idEspecie = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, especie.getNomeEspecie());
            stmt.setInt(2, especie.getIdEspecie());
            
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Espécie atualizada! Linhas afetadas: " + linhasAfetadas);
            
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar espécie: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Erro ao atualizar espécie: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    // DELETE - Excluir espécie
    public void delete(int idEspecie) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String sql = "DELETE FROM Especie WHERE idEspecie = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idEspecie);
            
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Espécie excluída! Linhas afetadas: " + linhasAfetadas);
            
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir espécie: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Erro ao excluir espécie: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
