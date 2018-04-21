<%@page import="br.com.lasalle.classes.Cliente"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Cliente> clientes = (ArrayList)request.getAttribute("listData"); %>
<div class="row mb-3">
    <div class="col">
        <a href="cliente-save" class="btn btn-success"><i class="fas fa-plus-square"></i> Adicionar</a>
    </div>
</div>

<div class="row">
    <div class="col">
        <table class="table table-striped table-sm">
            <thead>
              <tr>
                <th>#</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
            <% for (Cliente cliente : clientes) { %>
                <tr>
                    <td><%=cliente.getId()%></td>
                    <td><%=cliente.getPessoa().getNome()%></td>
                    <td><%=cliente.getPessoa().getCpfHtml()%></td>
                    <td>
                        <a href="cliente-save?id=<%=cliente.getId()%>"><button class="btn btn-sm btn-primary"><i class="fas fa-edit"></i></button></a>
                        <a href="cliente-remove?id=<%=cliente.getId()%>"><button class="btn btn-sm btn-danger"><i class="fas fa-times"></i></button></a>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>