/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.servlet;

import br.com.lasalle.classes.Contato;
import br.com.lasalle.classes.Uteis;
import br.com.lasalle.jdbc.ContatoDao;
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
 * @author danie
 */
@WebServlet("/removeContato")
public class removeContatoServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        // buscando os parâmetros no request

        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        
        Contato contato = new Contato();
        contato.setId(Long.parseLong(id));
        contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setEmail(email);
        
        ContatoDao dao = new ContatoDao();
        try {
            dao.remove(contato);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(removeContatoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(removeContatoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher r = request.getRequestDispatcher("./FrmContato.jsp");
        r.forward(request, response);

    }
}
