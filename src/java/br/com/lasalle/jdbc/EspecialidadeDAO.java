/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.jdbc;

import br.com.lasalle.classes.Contato;
import br.com.lasalle.classes.Especialidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabiano
 */
public class EspecialidadeDAO extends DefaultDAO {
    public EspecialidadeDAO() throws ClassNotFoundException{
        super();
    }
    
    public List<Especialidade> getAll() throws SQLException
    {
        String sql = "SELECT * FROM especialidade";
        List<Especialidade> data = new ArrayList<Especialidade>();
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        
        while (resultSet.next()) {
            Especialidade especialidade = new Especialidade(resultSet);
            data.add(especialidade);
        }
        resultSet.close();
        stmt.close();
        
        return data;
    }
    
    public Especialidade getSingle(int id) throws SQLException
    {
        String sql = "SELECT * FROM especialidade WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();
        Especialidade especialidade = null;
        
        while (resultSet.next()) {
            especialidade = new Especialidade(resultSet);
        }
        
        return especialidade;
    }
    
    public boolean update(Especialidade especialidade) throws SQLException
    {
        String sql = "UPDATE especialidade SET descricao = ? WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setString(1, especialidade.getDescricao());
        stmt.setLong(2, especialidade.getId());
        int result = stmt.executeUpdate();
        stmt.close();
        
        return result == 1;
    }
    
    public boolean insert(Especialidade especialidade) throws SQLException
    {
        String sql = "INSERT INTO especialidade (descricao) VALUES (?)";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setString(1, especialidade.getDescricao());
        int result = stmt.executeUpdate();
        stmt.close();
        
        return result == 1;
    }
}
