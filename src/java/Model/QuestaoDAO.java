/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergio.poyer
 */
public class QuestaoDAO {
    private String stmtSelect = "select * from questao where id not in (select Questao_id from Candidato_Questao where Candidato_id = ?);";
    
    public List<Questao> getLista(int idCandidato) throws SQLException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelect);
            stmt.setInt(1, idCandidato);
            rs = stmt.executeQuery();
            List<Questao> lstQuestao = new ArrayList();

            while (rs.next()) {
                // criando o objeto Grupo
                Questao Questao = new Questao();

                Questao.setId(rs.getInt("id"));
                Questao.setEnunciado(rs.getString("enunciado"));
                Questao.setA(rs.getString("a"));
                Questao.setB(rs.getString("b"));
                Questao.setC(rs.getString("c"));
                Questao.setD(rs.getString("d"));
                Questao.setE(rs.getString("e"));
                Questao.setCorreta(rs.getString("correta"));
                
                lstQuestao.add(Questao);
            }
            return lstQuestao;

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
    
}
