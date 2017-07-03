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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 *
 * @author pcstr
 */
@WebServlet(name = "ProcessaCadastro", urlPatterns = {"/ProcessaCadastro"})
@MultipartConfig
public class ProcessaCadastro extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 8000000;
    private int maxMemSize = 4 * 1024;
    private File file;
    private static final String SAVE_DIR = "uploadFiles";
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
        
        
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        Collection<Part> teste = request.getParts();
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);
        }
        

        String tipo = request.getParameter("grouptipo");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String caminho = request.getParameter("caminho");
        String nomefile = request.getParameter("nomefile");
        String cnpj = request.getParameter("cnpj");

        if ("0".equals(tipo)) {
            CandidatoDAO CandidatoDAO = new CandidatoDAO();
            Candidato Candidato = new Candidato();

            try {
                Candidato = CandidatoDAO.verificaLogin(email, senha);

                if (Candidato.getId() == 0) {
                    Candidato.setNome(nome);
                    Candidato.setEmail(email);
                    Candidato.setSenha(senha);
                    Candidato.setCpf(cpf);
                    Candidato.setCurriculo(caminho);
                    CandidatoDAO.insert(Candidato);
                } else {
                    out.println("ERRO_USUARIO_CADASTRADO");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProcessaCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ("1".equals(tipo)) {
            EmpresaDAO EmpresaDAO = new EmpresaDAO();
            Empresa Empresa = new Empresa();

            try {
                Empresa = EmpresaDAO.verificaLogin(email, senha);

                if (Empresa.getId() == 0) {
                    Empresa.setNome(nome);
                    Empresa.setEmail(email);
                    Empresa.setSenha(senha);
                    Empresa.setCnpj(cnpj);
                    EmpresaDAO.insert(Empresa);
                } else {
                    out.println("ERRO_USUARIO_CADASTRADO");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProcessaCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
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
