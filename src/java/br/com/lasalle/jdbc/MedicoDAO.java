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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabiano
 */
public class MedicoDAO extends DefaultDAO {
    
    public String tablename = "medico";
    
    public MedicoDAO() throws ClassNotFoundException{
        super();
    }
    
    public List<Medico> getAll() throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM medico";
        List<Medico> data = new ArrayList<Medico>();
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        
        while (resultSet.next()) {
            Medico entity = new Medico(resultSet);
            
            PessoaDAO pessoaDao = new PessoaDAO();
            entity.setPessoa(pessoaDao.getSingle(entity.getIdPessoa()));
            
            EspecialidadeDAO especialidadeDao = new EspecialidadeDAO();
            entity.setEspecialidade(especialidadeDao.getSingle(entity.getIdEspecialidade()));
            
            data.add(entity);
        }
        resultSet.close();
        stmt.close();
        
        return data;
    }
    
    public Medico getSingle(Long id) throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM " + this.tablename + " WHERE id = ?";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet resultSet = stmt.executeQuery();
        Medico entity = null;
        
        while (resultSet.next()) {
            entity = new Medico(resultSet);
            
            PessoaDAO pessoaDao = new PessoaDAO();
            entity.setPessoa(pessoaDao.getSingle(entity.getIdPessoa()));
            
            EspecialidadeDAO especialidadeDao = new EspecialidadeDAO();
            entity.setEspecialidade(especialidadeDao.getSingle(entity.getIdEspecialidade()));
        }
        
        return entity;
    }
    
    public boolean update(Medico medico) throws SQLException
    {
        String sql = "UPDATE " + this.tablename + " SET "
                + "cpf = ?, "
                + "crm = ?, "
                + "id_especialidade = ?, "
                + "horario_inicial = ?, "
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
    
    public long insert(Medico medico) throws SQLException
    {
        String sql = "INSERT INTO " + this.tablename + " (id_pessoa, cpf, crm, id_especialidade, horario_inicial, horario_final, rg) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = this.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, medico.getIdPessoa());
        stmt.setString(2, medico.getCpf());
        stmt.setString(3, medico.getCrm());
        stmt.setLong(4, medico.getIdEspecialidade());
        stmt.setString(5, medico.getHorarioInicial());
        stmt.setString(6, medico.getHorarioFinal());
        stmt.setString(7, medico.getRg());
        
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
