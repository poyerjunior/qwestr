/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pcstr
 */
public class CandidatoDAO {

    private String stmtInsert = "insert into candidato(nome, cpf, curriculo, email, senha) values(?,?,?,?,?);";
    private String stmtUpdate = "update candidato set nome=?, cpf=?, curriculo=?, email=?, senha=?, nota=? where id=?";
    private String stmtSelect = "select * from candidato";
    private String stmtSelectById = "select * from candidato where id =?";
    private String stmtDelete = "delete from candidato where id = ?";
    private String stmtVerfLogin = "select * from candidato where email =? and senha=?";

    public void insert(Candidato Candidato) {
        Connection con = null;
        PreparedStatement stmt = null;
        int idObjeto = 0;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInsert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, Candidato.getNome());
            stmt.setString(2, Candidato.getCpf());
            stmt.setString(3, Candidato.getCurriculo());
            stmt.setString(4, Candidato.getEmail());
            stmt.setString(5, Candidato.getSenha());

            stmt.execute();
            //Seta o id 
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                idObjeto = rs.getInt(1);
            }

            Candidato.setId(idObjeto);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }

    public void update(Candidato Candidato) {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        try {

            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtUpdate);

            stmt.setString(1, Candidato.getNome());
            stmt.setString(2, Candidato.getCpf());
            stmt.setString(3, Candidato.getCurriculo());
            stmt.setString(4, Candidato.getEmail());
            stmt.setString(5, Candidato.getSenha());
            stmt.setDouble(6, Candidato.getNota());
            stmt.setDouble(7, Candidato.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }

    public List<Candidato> getLista() throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelect);
            rs = stmt.executeQuery();
            List<Candidato> lstCandidato = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Candidato Candidato = new Candidato();

                Candidato.setId(rs.getInt("id"));
                Candidato.setNome(rs.getString("nome"));
                Candidato.setCpf(rs.getString("cpf"));
                Candidato.setCurriculo(rs.getString("curriculo"));
                Candidato.setEmail(rs.getString("email"));
                Candidato.setSenha(rs.getString("senha"));
                Candidato.setNota(rs.getDouble("nota"));

                lstCandidato.add(Candidato);
            }
            return lstCandidato;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            };
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

    public Candidato getById(int id) throws ClassNotFoundException {
        Candidato Candidato = new Candidato();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectById, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Candidato.setId(rs.getInt("id"));
                Candidato.setNome(rs.getString("nome"));
                Candidato.setCpf(rs.getString("cpf"));
                Candidato.setCurriculo(rs.getString("curriculo"));
                Candidato.setEmail(rs.getString("email"));
                Candidato.setSenha(rs.getString("senha"));
                Candidato.setNota(rs.getDouble("nota"));
            }
            return Candidato;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }

    public void delete(int id) {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        try {

            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtDelete);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }

    public Candidato verificaLogin(String email, String senha) throws ClassNotFoundException {
        Candidato candidato = new Candidato();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtVerfLogin, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            while (rs.next()) {
                candidato.setId(rs.getInt("id"));
                candidato.setNome(rs.getString("nome"));
                candidato.setCpf(rs.getString("cpf"));
                candidato.setCurriculo(rs.getString("cpf"));
                candidato.setEmail(rs.getString("email"));
                candidato.setSenha(rs.getString("senha"));
            }
            return candidato;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }
}
