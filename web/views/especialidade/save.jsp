<%@page import="br.com.lasalle.classes.Especialidade"%>
<% Especialidade especialidade = (Especialidade) request.getAttribute("data"); %>
<div clas="row">
    <div class="col">
        <form method="POST" action="especialidade-save">
            <div class="form-group">
              <label for="descricao">Descrição</label>
              <input type="text" class="form-control" name="descricao" id="descricao" placeholder="descricao" value="<%= (null == especialidade) ? "" : especialidade.getDescricao()%>"  required>
              <input type="hidden" name="id" value="<%= (null == especialidade) ? "" : especialidade.getId()%>">
            </div>
            <button type="submit" class="btn btn-success">Salvar</button>
        </form>
    </div>
</div>