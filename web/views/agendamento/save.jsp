<%@page import="br.com.lasalle.classes.Agendamento"%>
<%@page import="br.com.lasalle.classes.Medico"%>
<%@page import="br.com.lasalle.classes.Cliente"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getAttribute("cliente-data"); %>
<% ArrayList<Medico> medicos = (ArrayList<Medico>) request.getAttribute("medico-data"); %>
<% Agendamento agendamento = (Agendamento) request.getAttribute("data"); %>
<div clas="row">
    <div class="col">
        <form method="POST" action="agendamento-save">
            <div class="form-group">
              <label for="id_cliente">Cliente</label>
              <select class="form-control" name="id_cliente" id="id_cliente">
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
              <select class="form-control" name="id_medico" id="id_medico">
                  <% if (null != medicos) { %>
                    <% for (Medico medico : medicos) { %>
                    <% 
                    String selected = "";
                    if (null != agendamento && agendamento.getIdMedico() == medico.getId()) 
                        selected = "selected";
                    %>
                    <option value="<%=medico.getId()%>" <%=selected%>><%=medico.getPessoa().getNome()%> - <%=medico.getEspecialidade().getDescricao()%></option>
                    <% } %>
                  <% } %>
              </select>
            </div>
              
            <div class="form-group">
              <label for="nome">Data Consulta</label>
              <input type="text" class="form-control" name="data_consulta" id="data_consulta" placeholder="dd/mm/yyyy hh:mi " value="<%= (null == agendamento) ? "" : agendamento.getDataConsulta()%>">
            </div>

            <input type="hidden" name="id" value="<%= (null == agendamento) ? "" : agendamento.getId()%>">            
            <button type="submit" class="btn btn-success">Salvar</button>
        </form>
    </div>
</div>