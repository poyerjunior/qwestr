/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Candidato;
import Model.Candidatura;
import Model.CandidaturaDAO;
import Model.CandidaturaStatusDAO;
import Model.Empresa;
import Model.Vaga;
import Model.VagaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
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

        if (Candidato != null) {
            String tipoServlet = request.getParameter("tipoServlet");

            if ("GETBYID".equals(tipoServlet)) {
                int id = Integer.parseInt(request.getParameter("id"));

                VagaDAO VagaDAO = new VagaDAO();
                Vaga Vaga = new Vaga();

                try {
                    Vaga = VagaDAO.getById(id, true);
                    int isCandidato = 0;
                    for (Candidatura Candidatura : Vaga.getLstcandidatura()) {
                        if ((Candidatura.getCandidato() != null) && (Candidatura.getCandidato().getId() == Candidato.getId())) {
                            isCandidato = 1;
                            request.setAttribute("Candidatura", Candidatura);
                            break;
                        }
                    }

                    request.setAttribute("isCandidato", isCandidato);
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
                CandidaturaStatusDAO CandidaturaStatusDAO = new CandidaturaStatusDAO();
                VagaDAO VagaDAO = new VagaDAO();
                Candidatura Candidatura = new Candidatura();
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                Candidatura.setCandidato(Candidato);
                Candidatura.setDate(date);
                try {
                    Candidatura.setVaga(VagaDAO.getById(idVaga, true));
                    Candidatura.setCandidaturaStatus(CandidaturaStatusDAO.getById(3));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProcessaVagaDetalhe.class.getName()).log(Level.SEVERE, null, ex);
                }
                CandidaturaDAO.insert(Candidatura);
            }

            if ("DELETECANDIDATURA".equals(tipoServlet)) {
                int idCandidatura = Integer.parseInt(request.getParameter("idCandidatura"));
                CandidaturaDAO CandidaturaDAO = new CandidaturaDAO();

                CandidaturaDAO.delete(idCandidatura);
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
