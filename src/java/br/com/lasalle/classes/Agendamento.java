/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fabiano
 */
public class Agendamento implements ICrudEntity {
    
    public long id;
    public long idCliente;
    public long idMedico;
    public Timestamp dataConsulta;
    public Cliente cliente;
    public Medico medico;
    
    public Agendamento(ResultSet rs) throws SQLException
    {
        this.id = rs.getLong("id");
        this.idCliente = rs.getLong("id_cliente");
        this.idMedico = rs.getLong("id_medico");
        this.dataConsulta = rs.getTimestamp("data_consulta");
    }
    
    public Agendamento(HttpServletRequest request)
    {            
        this.mapRequest(request);
    }
    
    public Agendamento()
    {   
    }
    
    @Override
    public void mapRequest(HttpServletRequest request) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyyy HH:mm").parse(request.getParameter("data_consulta"));
        } catch (ParseException ex) {
            Logger.getLogger(Agendamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        
        this.idCliente = Long.parseLong(request.getParameter("id_cliente"));
        this.idMedico = Long.parseLong(request.getParameter("id_medico"));
        this.dataConsulta = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico = idMedico;
    }

    public Timestamp getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Timestamp dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    
    
}
