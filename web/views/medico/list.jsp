<%@page import="java.util.ArrayList"%>
<%@page import="br.com.lasalle.classes.Especialidade"%>
<% ArrayList<Especialidade> especialidades = (ArrayList)request.getAttribute("listData"); %>
<div class="row mb-3">
    <div class="col">
        <a href="especialidade-save" class="btn btn-success"><i class="fas fa-plus-square"></i> Adicionar</a>
    </div>
</div>

<div class="row">
    <div class="col">
        <table class="table table-striped table-sm">
            <thead>
              <tr>
                <th>#</th>
                <th>Nome</th>
                <th>CRM</th>
                <th>Especialidade</th>
              </tr>
            </thead>
            <tbody>
            <% for (Especialidade especialidade : especialidades) { %>
                <tr>
                    <td><%=especialidade.getId()%></td>
                    <td><%=especialidade.getDescricao()%></td>
                    <td>
                        <a href="especialidade-save?id=<%=especialidade.getId()%>"><button class="btn btn-sm btn-primary"><i class="fas fa-edit"></i></button></a>
                        <a href="especialidade-remove?id=<%=especialidade.getId()%>"><button class="btn btn-sm btn-danger"><i class="fas fa-times"></i></button></a>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>