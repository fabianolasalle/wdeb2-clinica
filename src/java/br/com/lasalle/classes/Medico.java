package br.com.lasalle.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author 201760192
 */
public class Medico {
    public Long id;
    public Long idPessoa;
    public String cpf;
    public String crm;
    public Long idEspecialidade;
    public String horarioInicial;
    public String horarioFinal;
    public String rg;

    public Medico(ResultSet rs) throws SQLException
    {
        this.id = rs.getLong("id");
        this.idPessoa = rs.getLong("id_pessoa");
        this.cpf = rs.getString("cpf");
        this.crm = rs.getString("crm");
        this.idEspecialidade = rs.getLong("id_especialidade");
        this.horarioInicial = rs.getString("horario_inicial");
        this.horarioFinal = rs.getString("horaro_final");
        this.rg = rs.getString("rg");
    }
    
    public Medico()
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Long getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Long idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    
    
}
