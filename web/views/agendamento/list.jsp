<%@page import="java.util.ArrayList"%>
<% ArrayList<Agendamento> agendamentos = (ArrayList)request.getAttribute("listData"); %>
<div class="row mb-3">
    <div class="col">
        <a href="agendamento-save" class="btn btn-success"><i class="fas fa-plus-square"></i> Adicionar</a>
    </div>
</div>

<div class="row">
    <div class="col">
        <table class="table table-striped table-sm">
            <thead>
              <tr>
                <th>#</th>
                <th>Cliente</th>
                <th>Médico</th>
                <th>Data</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
            <% for (Agendamento agendamento : agendamentos) { %>
                <tr>
                    <td><%=agendamento.getId()%></td>
                    <td><%=agendamento.getCliente().getPessoa().getNome()%></td>
                    <td><%=agendamento.getMedico().getPessoa().getNome()%></td>
                    <td><%=agendamento.getDataConsulta()%></td>
                    <td>
                        <a href="agendamento-save?id=<%=agendamento.getId()%>"><button class="btn btn-sm btn-primary"><i class="fas fa-edit"></i></button></a>
                        <a href="agendamento-remove?id=<%=agendamento.getId()%>"><button class="btn btn-sm btn-danger"><i class="fas fa-times"></i></button></a>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>