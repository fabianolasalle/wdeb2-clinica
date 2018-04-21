/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.servlet.agendamento;

import br.com.lasalle.servlet.medico.*;
import br.com.lasalle.classes.Medico;
import br.com.lasalle.jdbc.AgendamentoDAO;
import br.com.lasalle.jdbc.MedicoDAO;
import br.com.lasalle.jdbc.PessoaDAO;
import java.io.IOException;
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
@WebServlet("/agendamento-remove")
public class RemoveServlet extends HttpServlet {

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
        
        AgendamentoDAO agendamentoDao = null;
        try {
            agendamentoDao = new AgendamentoDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String id = request.getParameter("id");
        boolean resultOperation = false;
        if (id.length() >= 1) {
            try {
                resultOperation = agendamentoDao.remove(Long.parseLong(id));
            } catch (SQLException ex) {
                Logger.getLogger(RemoveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (resultOperation) {
            response.sendRedirect("agendamento");
            return;
        }
    }
}
