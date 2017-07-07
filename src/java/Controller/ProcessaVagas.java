/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Empresa;
import Model.Vaga;
import Model.VagaCategoria;
import Model.VagaCategoriaDAO;
import Model.VagaDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ProcessaVagas", urlPatterns = {"/ProcessaVagas"})
public class ProcessaVagas extends HttpServlet {

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

        List<Vaga> lstVaga = new ArrayList();
        VagaDAO VagaDAO = new VagaDAO();
        Vaga Vaga = new Vaga();
        VagaCategoriaDAO VagaCategoriaDAO = new VagaCategoriaDAO();
        VagaCategoria VagaCategoria = new VagaCategoria();
        Gson gson = new Gson();
        JsonObject jsonRetorno = new JsonObject();

        String tipoServlet = request.getParameter("tipoServlet");
        if ("GETLIST".equals(tipoServlet)) {
            int qtd = Integer.parseInt(request.getParameter("qtd"));
            String p1 = request.getParameter("p1");
            try {
                lstVaga = VagaDAO.getListaTop(p1, qtd);

                String data = gson.toJson(lstVaga);

                jsonRetorno.add("data", new JsonParser().parse(data).getAsJsonArray());
                out.println(jsonRetorno);

            } catch (SQLException ex) {
                Logger.getLogger(ProcessaCadVaga.class.getName()).log(Level.SEVERE, null, ex);
            }
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
