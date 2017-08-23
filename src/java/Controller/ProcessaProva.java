/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Candidato;
import Model.CandidatoQuestao;
import Model.CandidatoQuestaoDAO;
import Model.ProvaDAO;
import Model.Questao;
import Model.QuestaoDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sergio.poyer
 */
@WebServlet(name = "ProcessaProva", urlPatterns = {"/ProcessaProva"})
public class ProcessaProva extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Candidato Candidato = (Candidato) session.getAttribute("candidato");
        Gson gson = new Gson();
        JsonObject jsonRetorno = new JsonObject();

        if (Candidato != null) {

            String tipoServlet = request.getParameter("tipoServlet");
            if ("getProva".equals(tipoServlet)) {
                ProvaDAO pDAO = new ProvaDAO();
                String data = "";
                try {
                    data = gson.toJson(pDAO.getProva(Candidato.getId()));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProcessaProva.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.println(data);
            }
            
            if ("setResposta".equals(tipoServlet)) {
                CandidatoQuestaoDAO cqDAO = new CandidatoQuestaoDAO();
                QuestaoDAO qDAO = new QuestaoDAO();
                CandidatoQuestao cq = new CandidatoQuestao();
                Questao Questao = new Questao();
                
                int idQuestao = Integer.parseInt(request.getParameter("idQuestao"));
                String resposta = request.getParameter("resposta");
                Questao.setId(idQuestao);
                cq.setCandidato(Candidato);
                cq.setResposta(resposta);
                cq.setQuestao(Questao);
                
                cqDAO.insert(cq);
            }
            
            if ("getAcertos".equals(tipoServlet)) {
                QuestaoDAO qDAO = new QuestaoDAO();
                try {
                    out.println(qDAO.getAcertos(Candidato.getId()));
                } catch (SQLException ex) {
                    Logger.getLogger(ProcessaProva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            out.println("ERRO_DESLOGAR");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
