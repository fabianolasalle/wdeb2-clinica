/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.jdbc;

import br.com.lasalle.classes.Medico;
import br.com.lasalle.classes.Pessoa;
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
public class PessoaDAO extends DefaultDAO {
    
    public String tablename = "pessoa";
    
    public PessoaDAO() throws ClassNotFoundException{
        super();
    }
    
    public List<Pessoa> getAll() throws SQLException
    {
        String sql = "SELECT * FROM " + this.tablename;
        List<Pessoa> data = new ArrayList<Pessoa>();
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        
        while (resultSet.next()) {
            Pessoa entity = new Pessoa(resultSet);
            data.add(entity);
        }
        resultSet.close();
        stmt.close();
        
        return data;
    }
    
    public Pessoa getSingle(Long id) throws SQLException
    {
        String sql = "SELECT * FROM " + this.tablename + " WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet resultSet = stmt.executeQuery();
        Pessoa entity = null;
        
        while (resultSet.next()) {
            entity = new Pessoa(resultSet);
        }
        
        return entity;
    }
    
    public boolean update(Pessoa pessoa) throws SQLException
    {
        String sql = "UPDATE " + this.tablename + " SET "
                + "nome = ?, "
                + "telefone = ?, "
                + "endereco = ?, "
                + "email = ? "
                + "WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getTelefone());
        stmt.setString(3, pessoa.getEndereco());
        stmt.setString(4, pessoa.getEmail());
        stmt.setLong(5, pessoa.getId());
        
        int result = stmt.executeUpdate();
        stmt.close();
        
        return result == 1;
    }
    
    public long insert(Pessoa pessoa) throws SQLException
    {
        String sql = "INSERT INTO " + this.tablename + " (nome, telefone, endereco, email) "
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getTelefone());
        stmt.setString(3, pessoa.getEndereco());
        stmt.setString(4, pessoa.getEmail());
        
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
