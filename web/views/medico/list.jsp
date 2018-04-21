<%@page import="br.com.lasalle.classes.Medico"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Medico> medicos = (ArrayList)request.getAttribute("listData"); %>
<div class="row mb-3">
    <div class="col">
        <a href="medico-save" class="btn btn-success"><i class="fas fa-plus-square"></i> Adicionar</a>
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
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
            <% for (Medico medico : medicos) { %>
                <tr>
                    <td><%=medico.getId()%></td>
                    <td><%=medico.getPessoa().getNome()%></td>
                    <td><%=medico.getCrm()%></td>
                    <td><%=medico.getEspecialidade().getDescricao()%></td>
                    <td>
                        <a href="medico-save?id=<%=medico.getId()%>"><button class="btn btn-sm btn-primary"><i class="fas fa-edit"></i></button></a>
                        <a href="medico-remove?id=<%=medico.getId()%>"><button class="btn btn-sm btn-danger"><i class="fas fa-times"></i></button></a>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>