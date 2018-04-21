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
public class Especialidade implements ICrudEntity {
    public Long id;
    public String descricao;

    public Especialidade(ResultSet rs) throws SQLException
    {
        this.id = rs.getLong("id");
        this.descricao = rs.getString("descricao");
    }   

    public Especialidade() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }    

    @Override
    public void mapRequest(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
