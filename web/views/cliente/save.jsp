<%@page import="br.com.lasalle.classes.Cliente"%>
<%@page import="br.com.lasalle.classes.Pessoa"%>
<% Cliente cliente = (Cliente) request.getAttribute("data"); %>
<% Pessoa pessoa = (Pessoa) request.getAttribute("pessoa-data"); %>
<div clas="row">
    <div class="col">
        <form method="POST" action="cliente-save">
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

            <input type="hidden" name="id" value="<%= (null == cliente) ? "" : cliente.getId()%>">
            <input type="hidden" name="id_pessoa" value="<%= (null == pessoa) ? "" : pessoa.getId()%>">
            
            <button type="submit" class="btn btn-success">Salvar</button>
        </form>
    </div>
</div>