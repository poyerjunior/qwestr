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

/**
 *
 * @author sergio.poyer
 */
public class CandidatoQuestaoDAO {
    private String stmtInsert = "insert into candidato_questao(Candidato_id, Questao_id, reposta) values(?, ?, ?);";
    
    public void insert(CandidatoQuestao CandidatoQuestao) {
        Connection con = null;
        PreparedStatement stmt = null;
        int idObjeto = 0;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInsert, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, CandidatoQuestao.getCandidato().getId());
            stmt.setInt(2, CandidatoQuestao.getQuestao().getId());
            stmt.setString(3, CandidatoQuestao.getResposta());

            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                idObjeto = rs.getInt(1);
            }

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
