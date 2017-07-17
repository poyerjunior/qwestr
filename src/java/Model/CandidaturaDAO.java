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
 * @author sergio.poyer
 */
public class CandidaturaDAO {

    private String stmtInsert = "insert into candidatura(data, Candidato_id, Vaga_id, CandidaturaStatus_id) values(?,?,?,?);";
    private String stmtUpdate = "update candidatura set data=?, Candidato_id=?, Vaga_id=?, CandidaturaStatus_id=? where id=?";
    private String stmtSelect = "select * from candidatura";
    private String stmtSelectByIdVaga = "select * from candidatura where Vaga_id = ?";
    private String stmtSelectByIdCandidato = "select * from candidatura where Candidato_id = ?";
    private String stmtSelectById = "select * from candidatura where id =?";
    private String stmtDelete = "delete from candidatura where id = ?";

    public void insert(Candidatura Candidatura) {
        Connection con = null;
        PreparedStatement stmt = null;
        int idObjeto = 0;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInsert, Statement.RETURN_GENERATED_KEYS);

            stmt.setDate(1, Candidatura.getDate());
            stmt.setInt(2, Candidatura.getCandidato().getId());
            stmt.setInt(3, Candidatura.getVaga().getId());
            stmt.setInt(4, Candidatura.getCandidaturaStatus().getId());

            stmt.execute();
            //Seta o id 
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                idObjeto = rs.getInt(1);
            }

            Candidatura.setId(idObjeto);

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

    public void update(Candidatura Candidatura) {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        try {

            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtUpdate);

            stmt.setDate(1, Candidatura.getDate());
            stmt.setInt(2, Candidatura.getCandidato().getId());
            stmt.setInt(3, Candidatura.getVaga().getId());
            stmt.setInt(4, Candidatura.getCandidaturaStatus().getId());
            stmt.setInt(5, Candidatura.getId());
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

    public List<Candidatura> getLista() throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelect);
            rs = stmt.executeQuery();
            List<Candidatura> lstCandidatura = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Candidatura Candidatura = new Candidatura();
                CandidatoDAO CandidatoDAO = new CandidatoDAO();
                VagaDAO VagaDAO = new VagaDAO();
                CandidaturaStatusDAO CandidaturaStatusDAO = new CandidaturaStatusDAO();

                Candidatura.setId(rs.getInt("id"));
                Candidatura.setDate(rs.getDate("data"));
                try {
                    Candidatura.setCandidato(CandidatoDAO.getById(rs.getInt("Candidato_id"), false));
                    Candidatura.setVaga(VagaDAO.getById(rs.getInt("Vaga_id"), false));
                    Candidatura.setCandidaturaStatus(CandidaturaStatusDAO.getById(rs.getInt("CandidaturaStatus_id")));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CandidaturaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

                lstCandidatura.add(Candidatura);
            }
            return lstCandidatura;

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

    public List<Candidatura> getListaByCandidato(int id) throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectByIdCandidato, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            List<Candidatura> lstCandidatura = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Candidatura Candidatura = new Candidatura();
                VagaDAO VagaDAO = new VagaDAO();
                CandidatoDAO CandidatoDAO = new CandidatoDAO();
                CandidaturaStatusDAO CandidaturaStatusDAO = new CandidaturaStatusDAO();

                Candidatura.setId(rs.getInt("id"));
                Candidatura.setDate(rs.getDate("data"));
                try {
                    Candidatura.setVaga(VagaDAO.getById(rs.getInt("Vaga_id"), false));
                    Candidatura.setCandidato(CandidatoDAO.getById(rs.getInt("Candidato_id"), false));
                    Candidatura.setCandidaturaStatus(CandidaturaStatusDAO.getById(rs.getInt("CandidaturaStatus_id")));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CandidaturaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                Candidatura.setAprovacao(rs.getBoolean("aprovacao"));

                lstCandidatura.add(Candidatura);
            }
            return lstCandidatura;

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

    public List<Candidatura> getListaByVaga(int id) throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectByIdVaga, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            List<Candidatura> lstCandidatura = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Candidatura Candidatura = new Candidatura();
                CandidatoDAO CandidatoDAO = new CandidatoDAO();
                VagaDAO VagaDAO = new VagaDAO();
                CandidaturaStatusDAO CandidaturaStatusDAO = new CandidaturaStatusDAO();

                Candidatura.setId(rs.getInt("id"));
                Candidatura.setDate(rs.getDate("data"));
                try {
                    Candidatura.setCandidato(CandidatoDAO.getById(rs.getInt("Candidato_id"), false));
                    Candidatura.setVaga(VagaDAO.getById(rs.getInt("Vaga_id"), false));
                    Candidatura.setCandidaturaStatus(CandidaturaStatusDAO.getById(rs.getInt("CandidaturaStatus_id")));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CandidaturaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                Candidatura.setAprovacao(rs.getBoolean("aprovacao"));

                lstCandidatura.add(Candidatura);
            }
            return lstCandidatura;

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

    public Candidatura getById(int id) throws ClassNotFoundException {
        Candidatura Candidatura = new Candidatura();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectById, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                CandidatoDAO CandidatoDAO = new CandidatoDAO();
                VagaDAO VagaDAO = new VagaDAO();
                CandidaturaStatusDAO CandidaturaStatusDAO = new CandidaturaStatusDAO();

                Candidatura.setId(rs.getInt("id"));
                Candidatura.setDate(rs.getDate("data"));
                try {
                    Candidatura.setCandidato(CandidatoDAO.getById(rs.getInt("Candidato_id"), false));
                    Candidatura.setVaga(VagaDAO.getById(rs.getInt("Vaga_id"), false));
                    Candidatura.setCandidaturaStatus(CandidaturaStatusDAO.getById(rs.getInt("CandidaturaStatus_id")));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CandidaturaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                Candidatura.setAprovacao(rs.getBoolean("aprovacao"));
            }
            return Candidatura;
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
