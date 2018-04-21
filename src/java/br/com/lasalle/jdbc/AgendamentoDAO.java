/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.jdbc;

import br.com.lasalle.classes.Agendamento;
import br.com.lasalle.classes.Medico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabiano
 */
public class AgendamentoDAO extends DefaultDAO {
    
    public String tablename = "agendamento";
    
    public AgendamentoDAO() throws ClassNotFoundException{
        super();
    }
    
    public List<Agendamento> getAll() throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM agendamento";
        List<Agendamento> data = new ArrayList<Agendamento>();
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        
        while (resultSet.next()) {
            Agendamento entity = new Agendamento(resultSet);
            
            ClienteDAO clienteDao = new ClienteDAO();
            entity.setCliente(clienteDao.getSingle(entity.getIdCliente()));
            
            MedicoDAO medicoDao = new MedicoDAO();
            entity.setMedico(medicoDao.getSingle(entity.getIdMedico()));

            data.add(entity);
        }
        resultSet.close();
        stmt.close();
        
        return data;
    }
    
    public Agendamento getSingle(Long id) throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM " + this.tablename + " WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet resultSet = stmt.executeQuery();
        Agendamento entity = null;
        
        while (resultSet.next()) {
            entity = new Agendamento(resultSet);
            
            ClienteDAO clienteDao = new ClienteDAO();
            entity.setCliente(clienteDao.getSingle(entity.getIdCliente()));
            
            MedicoDAO medicoDao = new MedicoDAO();
            entity.setMedico(medicoDao.getSingle(entity.getIdMedico()));
        }
        
        return entity;
    }
    
    public boolean update(Agendamento agendamento) throws SQLException
    {
        String sql = "UPDATE " + this.tablename + " SET "
                + "id_cliente = ?, "
                + "id_medico= ?, "
                + "data_consulta = ? "
                + "WHERE id = ?";
        
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setLong(1, agendamento.getIdCliente());
        stmt.setLong(2, agendamento.getIdMedico());
        stmt.setTimestamp(3, agendamento.getDataConsulta());
        stmt.setLong(4, agendamento.getId());
        
        int result = stmt.executeUpdate();
        stmt.close();
        
        return result == 1;
    }
    
    public long insert(Agendamento agendamento) throws SQLException
    {
        String sql = "INSERT INTO " + this.tablename + " (id_cliente, id_medico, data_consulta) "
                + "VALUES (?, ?, ?)";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, agendamento.getIdCliente());
        stmt.setLong(2, agendamento.getIdMedico());
        stmt.setTimestamp(3, agendamento.getDataConsulta());
        
        int result = stmt.executeUpdate();
        
        long insertedIdResult = 0l;
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            insertedIdResult = rs.getInt(1);
        }
        stmt.close();
        
        return insertedIdResult;
    }
    
    public boolean remove(long id) throws SQLException
    {
        String sql = "DELETE FROM " + this.tablename + " WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setLong(1, id);
        int result = stmt.executeUpdate();
        stmt.close();
        
        return result == 1;
    }
}
