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

    private String stmtInsert = "insert into vaga(nome, descricao, prova, VagaCategoria_id, Empresa_id) values(?,?,?,?,?);";
    private String stmtUpdate = "update vaga set nome=?, descricao=?, prova=?, VagaCategoria_id=?, Empresa_id=? where id=?";
    private String stmtSelect = "select * from vaga where Empresa_id = ?";
    private String stmtSelectTop = "SELECT vaga.*, VagaCategoria.nome as vagacategorianome FROM vaga\n"
            + "INNER JOIN VagaCategoria on VagaCategoria.id = vaga.VagaCategoria_id\n"
            + "WHERE vaga.nome like ? OR descricao like ? OR VagaCategoria.nome like ? Order By vaga.nome ASC LIMIT ?";
    private String stmtSelectById = "select * from vaga where id =?";
    private String stmtDelete = "delete from vaga where id = ?";
    private String stmtSelectByCandidato = "select vaga.* from vaga "
            + "inner join candidatura on vaga.id = candidatura.Vaga_id where candidatura.Candidato_id = ?";

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
            stmt.setInt(6, Vaga.getId());
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

    public List<Vaga> getListaTop(String p1, int qtd) throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectTop);
            stmt.setString(1, "%" + p1 + "%");
            stmt.setString(2, "%" + p1 + "%");
            stmt.setString(3, "%" + p1 + "%");
            stmt.setInt(4, qtd);
            rs = stmt.executeQuery();
            List<Vaga> lstVaga = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Vaga Vaga = new Vaga();

                Vaga.setId(rs.getInt("id"));
                Vaga.setNome(rs.getString("nome"));
                Vaga.setDescricao(rs.getString("descricao"));
                Vaga.setProva(rs.getBoolean("prova"));
                VagaCategoriaDAO vcDAO = new VagaCategoriaDAO();
                EmpresaDAO eDAO = new EmpresaDAO();
                try {
                    Vaga.setVagaCategoria(vcDAO.getById(rs.getInt("VagaCategoria_id")));
                    Vaga.setEmpresa(eDAO.getById(rs.getInt("Empresa_id")));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                CandidaturaDAO cDAO = new CandidaturaDAO();
                Vaga.setLstcandidatura(cDAO.getListaByVaga(rs.getInt("id")));
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

    public List<Vaga> getLista(int idEmpresa) throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelect);
            stmt.setInt(1, idEmpresa);
            rs = stmt.executeQuery();
            List<Vaga> lstVaga = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Vaga Vaga = new Vaga();

                Vaga.setId(rs.getInt("id"));
                Vaga.setNome(rs.getString("nome"));
                Vaga.setDescricao(rs.getString("descricao"));
                Vaga.setProva(rs.getBoolean("prova"));
                VagaCategoriaDAO vcDAO = new VagaCategoriaDAO();
                EmpresaDAO eDAO = new EmpresaDAO();
                try {
                    Vaga.setVagaCategoria(vcDAO.getById(rs.getInt("VagaCategoria_id")));
                    Vaga.setEmpresa(eDAO.getById(rs.getInt("Empresa_id")));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                CandidaturaDAO cDAO = new CandidaturaDAO();
                Vaga.setLstcandidatura(cDAO.getListaByVaga(rs.getInt("id")));
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

    public List<Vaga> getListaByCandidato(int idCandidato) throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectByCandidato);
            stmt.setInt(1, idCandidato);
            rs = stmt.executeQuery();
            List<Vaga> lstVaga = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Vaga Vaga = new Vaga();

                Vaga.setId(rs.getInt("id"));
                Vaga.setNome(rs.getString("nome"));
                Vaga.setDescricao(rs.getString("descricao"));
                Vaga.setProva(rs.getBoolean("prova"));
                VagaCategoriaDAO vcDAO = new VagaCategoriaDAO();
                EmpresaDAO eDAO = new EmpresaDAO();
                try {
                    Vaga.setVagaCategoria(vcDAO.getById(rs.getInt("VagaCategoria_id")));
                    Vaga.setEmpresa(eDAO.getById(rs.getInt("Empresa_id")));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                CandidaturaDAO cDAO = new CandidaturaDAO();
                Vaga.setLstcandidatura(cDAO.getListaByCandidato(idCandidato));
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

    public Vaga getById(int id, boolean trazercandidatura) throws ClassNotFoundException {
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
                Vaga.setDescricao(rs.getString("descricao"));
                Vaga.setProva(rs.getBoolean("prova"));
                VagaCategoriaDAO vcDAO = new VagaCategoriaDAO();
                EmpresaDAO eDAO = new EmpresaDAO();
                try {
                    Vaga.setVagaCategoria(vcDAO.getById(rs.getInt("VagaCategoria_id")));
                    Vaga.setEmpresa(eDAO.getById(rs.getInt("Empresa_id")));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (trazercandidatura) {
                    CandidaturaDAO cDAO = new CandidaturaDAO();
                    Vaga.setLstcandidatura(cDAO.getListaByVaga(rs.getInt("id")));
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
