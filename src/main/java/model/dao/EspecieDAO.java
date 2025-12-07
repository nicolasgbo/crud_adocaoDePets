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

    /**
     *
     * @author nicol
     */

    public class EspecieDAO {
        //READ - Para exibir as especies no combobox 
        public ArrayList<Especie> listarEspecies() {
            Connection con = Conexao.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            ArrayList<Especie> especies = new ArrayList<>();

            try {
                //Criando a query para consultar o banco de dados
                String sql = "SELECT * FROM Especie ORDER BY nome";
                stmt = con.prepareStatement(sql);

                //Executando a query
                rs = stmt.executeQuery();

                while (rs.next()) {
                    Especie especie = new Especie();
                    especie.setIdEspecie(rs.getInt("idEspecie"));
                    especie. setNomeEspecie(rs.getString("nome"));
                    especies.add(especie);
                }

            } catch (SQLException ex) {
                System. err.println("Erro ao listar espécies: " + ex);
                ex.printStackTrace();
            } finally {
                Conexao.fecharConexao(con, stmt, rs);
            }

            return especies;
        }
    }
