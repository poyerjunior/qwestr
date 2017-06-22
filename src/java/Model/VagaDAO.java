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
public class VagaDAO {

    private String stmtInsert = "insert into vaga(nome, descricao, prova, VagaCategoria_id, Empresa_id, Prova_id) values(?,?,?,?,?,?);";
    private String stmtUpdate = "update vaga set nome=?, descricao=?, prova=?, VagaCategoria_id=?, Empresa_id=?, Prova_id=? where id=?";
    private String stmtSelect = "select * from vaga";
    private String stmtSelectById = "select * from vaga where id =?";
    private String stmtDelete = "delete from vaga where id = ?";

    public void insert(Vaga Vaga) {
        Connection con = null;
        PreparedStatement stmt = null;
        int idObjeto = 0;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInsert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, Vaga.getNome());
            stmt.setString(2, Vaga.getDescricao());
            stmt.setBoolean(3, Vaga.isProva());
            stmt.setInt(4, Vaga.getVagaCategoria().getId());
            stmt.setInt(5, Vaga.getEmpresa().getId());
            stmt.setInt(6, Vaga.getProva().getId());

            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                idObjeto = rs.getInt(1);
            }

            Vaga.setId(idObjeto);

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

    public void update(Vaga Vaga) {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        try {

            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtUpdate);

            stmt.setString(1, Vaga.getNome());
            stmt.setString(2, Vaga.getDescricao());
            stmt.setBoolean(3, Vaga.isProva());
            stmt.setInt(4, Vaga.getVagaCategoria().getId());
            stmt.setInt(5, Vaga.getEmpresa().getId());
            stmt.setInt(6, Vaga.getProva().getId());
            stmt.setInt(7, Vaga.getId());
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

    public List<Vaga> getLista() throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelect);
            rs = stmt.executeQuery();
            List<Vaga> lstVaga = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Vaga Vaga = new Vaga();

                Vaga.setId(rs.getInt("id"));
                Vaga.setNome(rs.getString("nome"));
                Vaga.setProva(rs.getBoolean("prova"));
                VagaCategoriaDAO vcDAO = new VagaCategoriaDAO();
                EmpresaDAO eDAO = new EmpresaDAO();
                try {
                    Vaga.setVagaCategoria(vcDAO.getById(rs.getInt("VagaCategoria_id")));
                    Vaga.setEmpresa(eDAO.getById(rs.getInt("Empresa_id")));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

                lstVaga.add(Vaga);
            }
            return lstVaga;

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

    public Vaga getById(int id) throws ClassNotFoundException {
        Vaga Vaga = new Vaga();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectById, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Vaga.setId(rs.getInt("id"));
                Vaga.setNome(rs.getString("nome"));
                Vaga.setProva(rs.getBoolean("prova"));
                VagaCategoriaDAO vcDAO = new VagaCategoriaDAO();
                EmpresaDAO eDAO = new EmpresaDAO();
                try {
                    Vaga.setVagaCategoria(vcDAO.getById(rs.getInt("VagaCategoria_id")));
                    Vaga.setEmpresa(eDAO.getById(rs.getInt("Empresa_id")));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            return Vaga;
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

}
