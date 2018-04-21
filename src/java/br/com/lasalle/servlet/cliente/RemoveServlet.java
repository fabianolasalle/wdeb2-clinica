/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.servlet.cliente;

import br.com.lasalle.classes.Cliente;
import br.com.lasalle.jdbc.ClienteDAO;
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
@WebServlet("/cliente-remove")
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
        
        PessoaDAO pessoaDao = null;
        ClienteDAO clienteDao = null;
        try {
            pessoaDao = new PessoaDAO();
            clienteDao = new ClienteDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String id = request.getParameter("id");
        boolean resultOperation = false;
        if (id.length() >= 1) {
            try {
                Cliente cliente = clienteDao.getSingle(Long.parseLong(id));
                resultOperation = clienteDao.remove(Long.parseLong(id));
                pessoaDao.remove(cliente.getIdPessoa());
            } catch (SQLException ex) {
                Logger.getLogger(RemoveServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RemoveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (resultOperation) {
            response.sendRedirect("cliente");
            return;
        }
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
