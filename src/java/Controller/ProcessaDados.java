/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Empresa;
import Model.EmpresaDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ProcessaDados", urlPatterns = {"/ProcessaDados"})
public class ProcessaDados extends HttpServlet {

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
        Empresa empresa = (Empresa) session.getAttribute("empresa");

        Gson gson = new Gson();
        JsonObject jsonRetorno = new JsonObject();
        String tipoServlet = request.getParameter("tipoServlet");

        if (empresa != null) {

            if ("GETBYIDEMPRESA".equals(tipoServlet)) {

                int id = empresa.getId();
                EmpresaDAO EmpresaDAO = new EmpresaDAO();
                Empresa Empresa = new Empresa();
                try {
                    Empresa = EmpresaDAO.getById(id);
                    String data = gson.toJson(Empresa);
                    out.println(data);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProcessaCadVaga.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if ("UPDATEEMPRESA".equals(tipoServlet)) {
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                String cnpj = request.getParameter("cnpj");
                
                EmpresaDAO EmpresaDAO = new EmpresaDAO();

                empresa.setNome(nome);
                empresa.setEmail(email);
                empresa.setSenha(senha);
                empresa.setCnpj(cnpj);
                EmpresaDAO.update(empresa);
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
