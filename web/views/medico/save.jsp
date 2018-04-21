<%@page import="java.util.ArrayList"%>
<%@page import="br.com.lasalle.classes.Especialidade"%>
<%@page import="java.util.List"%>
<%@page import="br.com.lasalle.classes.Pessoa"%>
<%@page import="br.com.lasalle.classes.Medico"%>
<% Medico medico = (Medico) request.getAttribute("data"); %>
<% Pessoa pessoa = (Pessoa) request.getAttribute("pessoa-data"); %>
<% List<Especialidade> especialidades = (ArrayList<Especialidade>) request.getAttribute("especialidade-data"); %>
<div clas="row">
    <div class="col">
        <form method="POST" action="medico-save">
            <div class="form-group">
              <label for="nome">Nome</label>
              <input type="text" class="form-control" name="nome" id="nome" placeholder="Nome" value="<%= (null == pessoa) ? "" : pessoa.getNome()%>">
            </div>
            <div class="form-group">
              <label for="telefone">Telefone</label>
              <input type="text" class="form-control" name="telefone" id="telefone" placeholder="Telefone" value="<%= (null == pessoa) ? "" : pessoa.getTelefone()%>">
            </div>
            <div class="form-group">
              <label for="endereco">Endereço</label>
              <input type="text" class="form-control" name="endereco" id="endereco" placeholder="Endereço" value="<%= (null == pessoa) ? "" : pessoa.getEndereco()%>">
            </div>
            <div class="form-group">
              <label for="email">E-mail</label>
              <input type="email" class="form-control" name="email" id="email" placeholder="email@dominio.com.br" value="<%= (null == pessoa) ? "" : pessoa.getEmail()%>">
            </div>
            <div class="form-group">
              <label for="cpf">CPF</label>
              <input type="text" class="form-control" name="cpf" id="cpf" placeholder="CPF" maxlength="11" value="<%= (null == pessoa) ? "" : pessoa.getCpfHtml()%>">
            </div>
            <div class="form-group">
              <label for="rg">RG</label>
              <input type="text" class="form-control" name="rg" id="rg" placeholder="RG" maxlength="10" value="<%= (null == pessoa) ? "" : pessoa.getRgHtml()%>">
            </div>
            <div class="form-group">
              <label for="crm">CRM</label>
              <input type="text" class="form-control" name="crm" id="crm" placeholder="CRM" value="<%= (null == medico) ? "" : medico.getCrm()%>">
            </div>
            <div class="form-group">
              <label for="horario_inicio">Horário Inicial</label>
              <input type="text" class="form-control" name="horario_inicio" id="horario_inicio" placeholder="xx:xx" value="<%= (null == medico) ? "" : medico.getHorarioInicialHtml()%>">
            </div>
            <div class="form-group">
              <label for="horario_fim">Horário Final</label>
              <input type="text" class="form-control" name="horario_fim" id="horario_fim" placeholder="xx:xx" value="<%= (null == medico) ? "" : medico.getHorarioFinalHtml()%>">
            </div>
            <div class="form-group">
              <label for="horario_fim">Especialidade</label>
              <select class="form-control" name="id_especialidade" id="id_especialidade">
                  <% if (null != especialidades) { %>
                    <% for (Especialidade especialidade : especialidades) { %>
                    <% 
                    String selected = "";
                    if (null != medico && medico.getIdEspecialidade() == especialidade.getId()) 
                        selected = "selected";
                    %>

                    <option value="<%=especialidade.getId()%>" <%=selected%>><%=especialidade.getDescricao()%></option>
                    <% } %>
                  <% } %>
              </select>
            </div>
            <input type="hidden" name="id" value="<%= (null == medico) ? "" : medico.getId()%>">
            <input type="hidden" name="id_pessoa" value="<%= (null == pessoa) ? "" : pessoa.getId()%>">
            
            <button type="submit" class="btn btn-success">Salvar</button>
        </form>
    </div>
</div>