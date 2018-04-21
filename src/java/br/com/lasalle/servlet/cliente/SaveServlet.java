/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.servlet.cliente;

import br.com.lasalle.servlet.medico.*;
import br.com.lasalle.servlet.especialidade.*;
import br.com.lasalle.classes.Especialidade;
import br.com.lasalle.classes.Medico;
import br.com.lasalle.classes.Pessoa;
import br.com.lasalle.jdbc.EspecialidadeDAO;
import br.com.lasalle.jdbc.MedicoDAO;
import br.com.lasalle.jdbc.PessoaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet("/cliente-save")
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
        Medico data = null;
        MedicoDAO dao;
        
        if (null != id){
            try {
                dao = new MedicoDAO();
                data = dao.getSingle(Long.parseLong(id));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        List<Especialidade> especialidades = null;
        
        try {            
            EspecialidadeDAO especialidadeDao = new EspecialidadeDAO();
            especialidades = especialidadeDao.getAll();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PessoaDAO pessoaDao = null;
        Pessoa pessoa = null;
        try {
            pessoaDao = new PessoaDAO();
            pessoa = pessoaDao.getSingle(data.getIdPessoa());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                    
        request.setAttribute("data", data);
        request.setAttribute("especialidade-data", especialidades);
        request.setAttribute("pessoa-data", pessoa);
        
        request.setAttribute("contentPath", "./medico/save.jsp");
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
        
        PessoaDAO pessoaDao = null;
        MedicoDAO medicoDao = null;
        try {
            pessoaDao = new PessoaDAO();
            medicoDao = new MedicoDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        String id = request.getParameter("id");
        String id_pessoa = request.getParameter("id_pessoa");
        
        long insertedIdPessoa = 0;
        long insertedIdMedico = 0;
        boolean resultOperation = false;
        
        if (id.length() < 1) {
            Pessoa pessoa = new Pessoa(request);
            
            try {
                insertedIdPessoa = pessoaDao.insert(pessoa);
            } catch (SQLException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (insertedIdPessoa > 0){
                Medico medico = new Medico(request);
                medico.setIdPessoa(insertedIdPessoa);
                try {
                    insertedIdMedico = medicoDao.insert(medico);
                } catch (SQLException ex) {
                    Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            resultOperation = insertedIdMedico > 0;
        } else {
            Pessoa pessoa = null;
            Medico medico = null;
            try {
                pessoa = pessoaDao.getSingle(Long.parseLong(id_pessoa));
                medico = medicoDao.getSingle(Long.parseLong(id));
                
                pessoa.mapRequest(request);
                medico.mapRequest(request);
                
                pessoaDao.update(pessoa);
                resultOperation = medicoDao.update(medico);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        if (resultOperation) {
            response.sendRedirect("medico");
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
