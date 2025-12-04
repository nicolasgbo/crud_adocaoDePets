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
    import model.bean.Usuario;

    /**
     *
     * @author nicol
     */

    public class UsuarioDAO {
        //CREATE - Usando CREATE para criar um novo usuario no banco de dados

        //READ -  Para buscar Email/Senha para fazer o login do usuario
        public Usuario read(String email, String senha){
            Connection con = Conexao.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                //Criando a query para buscar o usuario pelo email e senha
                String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";
                stmt = con.prepareStatement(sql);

                //Substituindo os ? para os valores das variaveis 
                stmt.setString(1, email);
                stmt.setString(2, senha);

                //Executando a query
                rs = stmt.executeQuery();

                 //Se for encontrado um usuario no banco de dados 
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario. setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setEmailUsuario(rs.getString("email"));
                    usuario.setSenhaUsuario(rs.getString("senha"));
                    usuario.setTipoUsuario(rs.getString("tipo"));

                    return usuario; //Ele vai retornar um usuario
                }
            } catch (SQLException ex) {
                //Caso der erro, ele vai lancar o erro 
                System.err.println("Erro ao buscar usuário: " + ex);
                ex.printStackTrace();
            } finally {
                //Independente de tudo, ele vai fechar a conexao depois de tudo
                Conexao.fecharConexao(con, stmt, rs);
            }

            return null; //Se nao encontrar, retorna null       
        }
    }
