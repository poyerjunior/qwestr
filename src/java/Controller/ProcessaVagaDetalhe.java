/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Candidato;
import Model.Candidatura;
import Model.CandidaturaDAO;
import Model.Empresa;
import Model.Vaga;
import Model.VagaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ProcessaVagaDetalhe", urlPatterns = {"/ProcessaVagaDetalhe"})
public class ProcessaVagaDetalhe extends HttpServlet {

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
        
        String tipoServlet = request.getParameter("tipoServlet");
        
        if ("GETBYID".equals(tipoServlet)) {
            int id = Integer.parseInt(request.getParameter("id"));

            VagaDAO VagaDAO = new VagaDAO();
            Vaga Vaga = new Vaga();

            try {
                Vaga = VagaDAO.getById(id);

                request.setAttribute("vaga", Vaga);
                request.setAttribute("vagacategoria", Vaga.getVagaCategoria());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/vagadetalhe.jsp");
                rd.forward(request, response);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProcessaVagaDetalhe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if ("SETCANDIDATURA".equals(tipoServlet)) {
            int idVaga = Integer.parseInt(request.getParameter("idVaga"));
            CandidaturaDAO CandidaturaDAO = new CandidaturaDAO();
            Candidatura Candidatura = new Candidatura();
            
            Candidatura.setAprovacao(false);
            Candidatura.setCandidato(Candidato);
            Candidatura.setDate(Date.valueOf(LocalDate.MAX));
            Candidatura.setIdVaga(idVaga);
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
