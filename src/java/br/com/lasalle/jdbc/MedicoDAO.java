/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.jdbc;

import br.com.lasalle.classes.Medico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabiano
 */
public class MedicoDAO extends DefaultDAO {
    public MedicoDAO() throws ClassNotFoundException{
        super();
    }
    
    public List<Medico> getAll() throws SQLException
    {
        String sql = "SELECT * FROM medico";
        List<Medico> data = new ArrayList<Medico>();
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        
        while (resultSet.next()) {
            Medico entity = new Medico(resultSet);
            data.add(entity);
        }
        resultSet.close();
        stmt.close();
        
        return data;
    }
    
    public Medico getSingle(int id) throws SQLException
    {
        String sql = "SELECT * FROM medico WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();
        Medico entity = null;
        
        while (resultSet.next()) {
            entity = new Medico(resultSet);
        }
        
        return entity;
    }
    
    public boolean update(Medico medico) throws SQLException
    {
        String sql = "UPDATE especialidade SET "
                + "cpf = ?, "
                + "crm = ?, "
                + "id_especialidade = ?, "
                + "horario_incial = ?, "
                + "horario_final = ?, "
                + "rg = ? "
                + "WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setString(1, medico.getCpf());
        stmt.setString(2, medico.getCrm());
        stmt.setLong(3, medico.getIdEspecialidade());
        stmt.setString(4, medico.getHorarioInicial());
        stmt.setString(5, medico.getHorarioFinal());
        stmt.setString(6, medico.getRg());
        stmt.setLong(7, medico.getId());
        int result = stmt.executeUpdate();
        stmt.close();
        
        return result == 1;
    }
    
    public boolean insert(Medico medico) throws SQLException
    {
        String sql = "INSERT INTO especialidade (id_pessoa, cpf, crm, id_especialidade, horario_inicial, horario_final, rg) VALUES (?)";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setString(1, especialidade.getDescricao());
        int result = stmt.executeUpdate();
        stmt.close();
        
        return result == 1;
    }
    
    public boolean remove(int id) throws SQLException
    {
        String sql = "DELETE FROM especialidade WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        int result = stmt.executeUpdate();
        stmt.close();
        
        return result == 1;
    }
}
