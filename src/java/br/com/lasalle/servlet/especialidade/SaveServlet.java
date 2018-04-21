/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.servlet.especialidade;

import br.com.lasalle.classes.Especialidade;
import br.com.lasalle.jdbc.EspecialidadeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fabiano
 */
@WebServlet("/especialidade-save")
public class SaveServlet extends HttpServlet {

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
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("./views/index.jsp");
        dispatcher.forward(request, response);
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
        
        String id = request.getParameter("id");
        Especialidade data = null;
        EspecialidadeDAO dao;
        
        if (null != id){
            try {
                dao = new EspecialidadeDAO();
                data = dao.getSingle(Long.parseLong(id));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        request.setAttribute("data", data);
        request.setAttribute("contentPath", "./especialidade/save.jsp");
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
        
        EspecialidadeDAO dao = null;
        try {
            dao = new EspecialidadeDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        String id = request.getParameter("id");
        long idInserted = 0;
        boolean resultOperation = false;
        if (id.length() < 1) {
            // TODO: Insert
            Especialidade especialidade = new Especialidade();
            especialidade.setDescricao(request.getParameter("descricao"));
            try {
                idInserted = dao.insert(especialidade);
            } catch (SQLException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            resultOperation = idInserted > 0;
        } else {           
            Especialidade especialidade = null;
            try {
                especialidade = dao.getSingle(Integer.parseInt(id));
                especialidade.setDescricao(request.getParameter("descricao"));
                resultOperation = dao.update(especialidade);
            } catch (SQLException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        if (resultOperation) {
            response.sendRedirect("especialidade");
            return;
        }
        
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
