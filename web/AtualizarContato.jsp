<%-- 
    Document   : FrmContato.Jsp
    Created on : 12/04/2018, 10:00:54
    Author     : danie
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@page import="br.com.lasalle.classes.Contato"%>
<%@page import="br.com.lasalle.jdbc.ContatoDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script  type="text/javascript"  src="scriptJs.js"></script>
        <title>JSP Page</title>
    </head>
    <body>

          


        <div class="container">
            <h2>Cadastro de Contatos</h2>
            <hr />
            <form action="alteraContato" method="POST">
                
                 <div class="form-group">
                     Nome: <input class="form-control" type="text" name="id" value="<%=request.getParameter("id")%>" /><br />
                </div>
                <div class="form-group">
                    Nome: <input class="form-control" type="text" name="nome" value="<%=request.getParameter("nome")%>"/><br />
                </div>
                <div class="form-group">
                    E-mail: <input class="form-control" type="text" name="email" value="<%=request.getParameter("email")%>" /><br />
                </div>
                <div class="form-group">
                    Endereço: <input  class="form-control "type="text" name="endereco" value="<%=request.getParameter("endereco")%>"/><br />
                </div>
               
                <input class="btn btn-default" type="submit" value="Gravar" />
            </form>


            <%
                ContatoDao dao = new ContatoDao();
                List<Contato> contatos = dao.getLista();

            %>
            <div class="container">
                <h2>Lista de Contatos</h2>
                <table class="table">
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Email</th> 
                        <th>Endereco</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                    <% for (Contato contato : contatos) {
                    %>
                    <tr>
                        <td><%=contato.getId()%></td>
                        <td><%=contato.getNome()%></td>
                        <td><%=contato.getEmail()%></td> 
                        <td> <%=contato.getEndereco()%></td>

                 
                    <form action="?" method="post"> 

                        <td> <a href="AtualizarContato.jsp?id=<%=contato.getId()%>&endereco=<%=contato.getId()%>&nome=<%=contato.getNome()%>&id=<%=contato.getId()%>&email=<%=contato.getEmail()%>&nome<%=contato.getNome()%>">Alterar</a></td>
                        <td>  <a href="removeContato?id=<%=contato.getId()%>&nome=<%=contato.getNome()%>&email=<%=contato.getEmail()%>&endereco=<%=contato.getEndereco()%>">Excluir</a></td> 
                        
                    </form>

                    </tr>

                    <%
                        }
                    %>
                </table>




                </body>
                </html>
