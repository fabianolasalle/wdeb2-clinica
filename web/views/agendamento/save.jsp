<%@page import="br.com.lasalle.classes.Agendamento"%>
<%@page import="br.com.lasalle.classes.Medico"%>
<%@page import="br.com.lasalle.classes.Cliente"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getAttribute("cliente-data"); %>
<% ArrayList<Medico> medicos = (ArrayList<Medico>) request.getAttribute("medico-data"); %>
<% Agendamento agendamento = (Agendamento) request.getAttribute("data"); %>

<% String errorMedico = null; %>
<% if (null != request.getAttribute("error-time")) { %>
<%  errorMedico = request.getAttribute("error-time").toString(); %>
<% } %>
<div clas="row">
    <div class="col">
        <form method="POST" action="agendamento-save">
            <div class="form-group">
              <label for="id_cliente">Cliente</label>
              <select class="form-control" name="id_cliente" id="id_cliente" required>
                  <% if (null != clientes) { %>
                    <% for (Cliente cliente : clientes) { %>
                    <% 
                    String selected = "";
                    if (null != agendamento && agendamento.getIdCliente() == cliente.getId()) 
                        selected = "selected";
                    %>

                    <option value="<%=cliente.getId()%>" <%=selected%>><%=cliente.getPessoa().getNome()%> - <%=cliente.getPessoa().getCpfHtml()%></option>
                    <% } %>
                  <% } %>
              </select>
            </div>
            
            <div class="form-group">
              <label for="id_medico">Médico</label>
              <select class="form-control" name="id_medico" id="id_medico" required>
                  <% if (null != medicos) { %>
                    <% for (Medico medico : medicos) { %>
                    <% 
                    String selected = "";
                    if (null != agendamento && agendamento.getIdMedico() == medico.getId()) 
                        selected = "selected";
                    %>
                    <option value="<%=medico.getId()%>" <%=selected%>><%=medico.getPessoa().getNome()%> - <%=medico.getEspecialidade().getDescricao()%>: Das <%=medico.getHorarioInicialHtml()%> às <%=medico.getHorarioFinalHtml()%></option>
                    <% } %>
                  <% } %>
              </select>
            </div>
              
            <div class="form-group">
              <label for="nome">Data Consulta</label>
              <input type="text" class="form-control" name="data_consulta" id="data_consulta" placeholder="dd/mm/yyyy hh:mi " value="<%= (null == agendamento) ? "" : agendamento.getDataConsultaHtml()%>" required>
              <% if (null != errorMedico) { %>
              <p class="alert alert-danger mt-3"><%= errorMedico %></p>
              <% } %>
            </div>

            <input type="hidden" name="id" value="<%= (null == agendamento) ? "" : agendamento.getId()%>">            
            <button type="submit" class="btn btn-success">Salvar</button>
        </form>
    </div>
</div>
            
<script>
    $(document).ready(function(){
        $("#data_consulta").mask('00/00/0000 00:00');
    });
</script>