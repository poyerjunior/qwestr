/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.VagaCategoria;
import Model.VagaCategoriaDAO;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

/**
 *
 * @author pcstr
 */
@WebServlet(name = "ProcessaCadVagaCategoria", urlPatterns = {"/ProcessaCadVagaCategoria"})
public class ProcessaCadVagaCategoria extends HttpServlet {

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

        List<VagaCategoria> lstVagaCategoria = new ArrayList();
        VagaCategoriaDAO VagaCategoriaDAO = new VagaCategoriaDAO();
        VagaCategoria VagaCategoria = new VagaCategoria();
        Gson gson = new Gson();
        JsonObject jsonRetorno = new JsonObject();

        String tipoServlet = request.getParameter("tipoServlet");
        if ("GETLIST".equals(tipoServlet)) {

            try {
                lstVagaCategoria = VagaCategoriaDAO.getLista();

                String data = gson.toJson(lstVagaCategoria);

                jsonRetorno.add("data", new JsonParser().parse(data).getAsJsonArray());
                out.println(jsonRetorno);

            } catch (SQLException ex) {
                Logger.getLogger(ProcessaCadVagaCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //SE FOR UMA INSERÇÃO OU EDIÇÃO CAI AQUI
        if ("INSERT".equals(tipoServlet)) {
            //INDEPENDETE SE FOR EDIÇÃO O0U ISNERÇÃO, SALVA OS DADOS NAS VARIAVEIS E MONTA O OBJETO
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");

            VagaCategoria.setId(id);
            VagaCategoria.setNome(nome);
            //SE FOR INSERT, INSERE O OBJETO, SE FOR UPDATE, ALTERA O OBJ
            if (id == 0) {
                VagaCategoriaDAO.insert(VagaCategoria);
            } else {
                VagaCategoriaDAO.update(VagaCategoria);
            }
        }

        if ("GETBYID".equals(tipoServlet)) {

            int id = Integer.parseInt(request.getParameter("id"));

            try {
                VagaCategoria = VagaCategoriaDAO.getById(id);
                String data = gson.toJson(VagaCategoria);
                out.println(data);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProcessaCadVagaCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ("DELETE".equals(tipoServlet)) {
            int id = Integer.parseInt(request.getParameter("id"));

            VagaCategoriaDAO.delete(id);
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
