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

/**
 *
 * @author pcstr
 */
public class EmpresaDAO {

    private String stmtInsert = "insert into empresa(nome, cnpj, email, senha) values(?,?,?,?);";
    private String stmtUpdate = "update empresa set nome=?, cnpj=?, email=?, senha=? where id=?";
    private String stmtSelect = "select * from empresa";
    private String stmtSelectById = "select * from empresa where id =?";
    private String stmtDelete = "delete from empresa where id = ?";
    private String stmtVerfLogin = "select * from empresa where email =? and senha=?";

    public void insert(Empresa Empresa) {
        Connection con = null;
        PreparedStatement stmt = null;
        int idObjeto = 0;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInsert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, Empresa.getNome());
            stmt.setString(2, Empresa.getCnpj());
            stmt.setString(3, Empresa.getEmail());
            stmt.setString(4, Empresa.getSenha());

            stmt.execute();
            //Seta o id 
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                idObjeto = rs.getInt(1);
            }

            Empresa.setId(idObjeto);

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

    public void update(Empresa Empresa) {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        try {

            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtUpdate);

            stmt.setString(1, Empresa.getNome());
            stmt.setString(2, Empresa.getCnpj());
            stmt.setString(3, Empresa.getEmail());
            stmt.setString(4, Empresa.getSenha());
            stmt.setDouble(5, Empresa.getId());

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

    public List<Empresa> getLista() throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelect);
            rs = stmt.executeQuery();
            List<Empresa> lstEmpresa = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Empresa Empresa = new Empresa();

                Empresa.setId(rs.getInt("id"));
                Empresa.setNome(rs.getString("nome"));
                Empresa.setCnpj(rs.getString("cnpj"));
                Empresa.setEmail(rs.getString("email"));
                Empresa.setSenha(rs.getString("senha"));

                lstEmpresa.add(Empresa);
            }
            return lstEmpresa;

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

    public Empresa getById(int id) throws ClassNotFoundException {
        Empresa Empresa = new Empresa();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectById, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Empresa.setId(rs.getInt("id"));
                Empresa.setNome(rs.getString("nome"));
                Empresa.setCnpj(rs.getString("cnpj"));
                Empresa.setEmail(rs.getString("email"));
                Empresa.setSenha(rs.getString("senha"));
            }
            return Empresa;
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

    public Empresa verificaLogin(String email, String senha) throws ClassNotFoundException {
        Empresa Empresa = new Empresa();
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
                Empresa.setId(rs.getInt("id"));
                Empresa.setNome(rs.getString("nome"));
                Empresa.setCnpj(rs.getString("cnpj"));
                Empresa.setEmail(rs.getString("email"));
                Empresa.setSenha(rs.getString("senha"));
            }
            return Empresa;
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
