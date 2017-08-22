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
public class ProvaDAO {
     private String stmtSelect = "select * from prova;";
     
     public Prova getProva(int idCandidato) throws ClassNotFoundException {
        Prova Prova = new Prova();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelect, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Prova.setId(rs.getInt("id"));
                Prova.setNome(rs.getString("nome"));
                
                QuestaoDAO q = new QuestaoDAO();
                Prova.setLstQuestao(q.getLista(idCandidato));
            }
            return Prova;
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
