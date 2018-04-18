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
@WebServlet("/alteraContato")
public class AlteraContatoServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        // buscando os parâmetros no request

        
        String id = request.getParameter("id");
        out.print(out);
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String dataEmTexto = request.getParameter("dataNascimento");

        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setEmail(email);
        contato.setId(Long.parseLong(id));
        ContatoDao dao = new ContatoDao();

        try {
            dao.altera(contato);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlteraContatoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher r = request.getRequestDispatcher("./FrmContato.jsp");
        r.forward(request, response);
  

        /*      Uteis ut = new Uteis();

         //monta um objeto contato
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setEmail(email);
      /*  try {
            //contato.setDataNascimento(ut.formataData(dataEmTexto));
            contato.setDataNascimento(ut.formataData(dataEmTexto));
        } catch (Exception ex) {
            Logger.getLogger(AdicionaContatoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        // salva o contato
        //   ContatoDao dao = new ContatoDao();
        //     dao.altera(contato);
        /*
                  // imprime o nome do contato que foi adicionado
                  out.println("Contato " + contato.getNome() +
                          " adicionado com sucesso"); */
        //   RequestDispatcher r = request.getRequestDispatcher("./FrmContato.jsp");
        // r.forward(request, response);
    }
}
