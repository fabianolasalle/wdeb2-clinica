/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.jdbc;

import br.com.lasalle.classes.Cliente;
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
public class ClienteDAO extends DefaultDAO {
    
    public String tablename = "cliente";
    
    public ClienteDAO() throws ClassNotFoundException{
        super();
    }
    
    public List<Cliente> getAll() throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM " + this.tablename;
        List<Cliente> data = new ArrayList<Cliente>();
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        
        while (resultSet.next()) {
            Cliente entity = new Cliente(resultSet);
            
            PessoaDAO pessoaDao = new PessoaDAO();
            entity.setPessoa(pessoaDao.getSingle(entity.getIdPessoa()));
            
            data.add(entity);
        }
        resultSet.close();
        stmt.close();
        
        return data;
    }
    
    public Cliente getSingle(Long id) throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM " + this.tablename + " WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet resultSet = stmt.executeQuery();
        Cliente entity = null;
        
        while (resultSet.next()) {
            entity = new Cliente(resultSet);
            
            PessoaDAO pessoaDao = new PessoaDAO();
            entity.setPessoa(pessoaDao.getSingle(entity.getIdPessoa()));
        }
        
        return entity;
    }
    
    public boolean update(Cliente cliente) throws SQLException
    {
        return true;
    }
    
    public long insert(Cliente cliente) throws SQLException
    {
        String sql = "INSERT INTO " + this.tablename + " (id_pessoa) "
                + "VALUES (?)";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, cliente.getIdPessoa());
        
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
