/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fabiano
 */
public class Pessoa {
    public Long id;
    public String nome;
    public String telefone;
    public String endereco;
    public String email;

    public Pessoa(ResultSet rs) throws SQLException
    {
        this.id = rs.getLong("id");
        this.nome = rs.getString("nome");
        this.telefone = rs.getString("telefone");
        this.endereco = rs.getString("endereco");
        this.email = rs.getString("email");
    }
    
    public Pessoa(HttpServletRequest request)
    {         
        this.mapRequest(request);
    }
    
    public Pessoa()
    {
        
    }
    
    public void mapRequest(HttpServletRequest request)
    {
        this.nome = request.getParameter("nome");
        this.telefone = request.getParameter("telefone");
        this.endereco = request.getParameter("endereco");
        this.email = request.getParameter("email");
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
