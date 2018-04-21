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
 * @author 201760192
 */
public class Cliente {
    
    public Long id;
    public Long idPessoa;
    public Pessoa pessoa;

    public Cliente(ResultSet rs) throws SQLException
    {
        this.id = rs.getLong("id");
        this.idPessoa = rs.getLong("id_pessoa");
    }
    
    public Cliente(HttpServletRequest request)
    {            
    }
    
    public void mapRequest(HttpServletRequest request)
    {
        this.idPessoa = Long.parseLong(request.getParameter("id_pessoa"));
    }
        
    public Cliente()
    {
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    
}
