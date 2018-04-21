/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.servlet.agendamento;

import br.com.lasalle.classes.Agendamento;
import br.com.lasalle.classes.Cliente;
import br.com.lasalle.servlet.medico.*;
import br.com.lasalle.classes.Especialidade;
import br.com.lasalle.classes.Medico;
import br.com.lasalle.classes.Pessoa;
import br.com.lasalle.jdbc.AgendamentoDAO;
import br.com.lasalle.jdbc.ClienteDAO;
import br.com.lasalle.jdbc.EspecialidadeDAO;
import br.com.lasalle.jdbc.MedicoDAO;
import br.com.lasalle.jdbc.PessoaDAO;
import java.io.IOException;
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
@WebServlet("/agendamento-save")
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
        Agendamento data = null;
        AgendamentoDAO dao;
        
        if (null != id){
            try {
                dao = new AgendamentoDAO();
                data = dao.getSingle(Long.parseLong(id));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        List<Cliente> clientes = null;
        try {            
            ClienteDAO clienteDao = new ClienteDAO();
            clientes = clienteDao.getAll();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Medico> medicos = null;
        try {            
            MedicoDAO medicoDao = new MedicoDAO();
            medicos = medicoDao.getAll();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        request.setAttribute("data", data);
        request.setAttribute("cliente-data", clientes);
        request.setAttribute("medico-data", medicos);
        
        request.setAttribute("contentPath", "./agendamento/save.jsp");
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
        
        AgendamentoDAO agendamentoDao = null;
        try {
            agendamentoDao = new AgendamentoDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // TODO: Validação Server-side de horário, independente se é uma inserçao
        // ou edição, o horário deve estar em between do médico.

        String id = request.getParameter("id");
        
        long insertedIdAgendamento = 0;
        boolean resultOperation = false;
        
        if (id.length() < 1) {
            Agendamento agendamento = new Agendamento(request);
            
            try {
                insertedIdAgendamento = agendamentoDao.insert(agendamento);
            } catch (SQLException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }


            
            resultOperation = insertedIdAgendamento > 0;
        } else {
            Agendamento agendamento = null;
            try {
                agendamento = agendamentoDao.getSingle(Long.parseLong(id));
                
                agendamento.mapRequest(request);

                resultOperation = agendamentoDao.update(agendamento);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        if (resultOperation) {
            response.sendRedirect("agendamento");
            return;
        }
        
        processRequest(request, response);
    }
}