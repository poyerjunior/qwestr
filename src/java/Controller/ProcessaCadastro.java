/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Candidato;
import Model.CandidatoDAO;
import Model.Empresa;
import Model.EmpresaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pcstr
 */
@WebServlet(name = "ProcessaCadastro", urlPatterns = {"/ProcessaCadastro"})
public class ProcessaCadastro extends HttpServlet {

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
        
        String tipo = request.getParameter("grouptipo");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String caminho = request.getParameter("caminho");
        String nomefile = request.getParameter("nomefile");
        String cnpj = request.getParameter("cnpj");
        
        if("0".equals(tipo)){
            CandidatoDAO CandidatoDAO = new CandidatoDAO();
            Candidato Candidato = new Candidato();
            
            try {
                Candidato = CandidatoDAO.verificaLogin(email, senha);
                
                if(Candidato.getId() == 0){
                    Candidato.setNome(nome);
                    Candidato.setEmail(email);
                    Candidato.setSenha(senha);
                    Candidato.setCpf(cpf);
                    Candidato.setCurriculo(caminho);
                    CandidatoDAO.insert(Candidato);
                }else{
                    out.println("ERRO_USUARIO_CADASTRADO");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProcessaCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if("1".equals(tipo)){
            EmpresaDAO EmpresaDAO = new EmpresaDAO();
            Empresa Empresa = new Empresa();
            
            try {
                Empresa = EmpresaDAO.verificaLogin(email, senha);
                
                if(Empresa.getId() == 0){
                    Empresa.setNome(nome);
                    Empresa.setEmail(email);
                    Empresa.setSenha(senha);
                    Empresa.setCnpj(cnpj);
                    EmpresaDAO.insert(Empresa);
                }else{
                    out.println("ERRO_USUARIO_CADASTRADO");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProcessaCadastro.class.getName()).log(Level.SEVERE, null, ex);
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
