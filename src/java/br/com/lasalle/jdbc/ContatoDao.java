/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.jdbc;

import br.com.lasalle.classes.Contato;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class ContatoDao {

    // a conexão com o banco de dados
    private Connection connection;

    public ContatoDao() {

    }

    public void adiciona(Contato contato) throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
        String sql = "insert into contatos "
                + "(nome,email,endereco,dataNascimento )"
                + " values (?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, (Date) contato.getDataNascimento());

            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contato> getLista() throws ClassNotFoundException {

        this.connection = new ConnectionFactory().getConnection();
        String sql = "select	*	from	contatos";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            List<Contato> contatos = new ArrayList<Contato>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
                //    Calendar data = Calendar.getInstance();
                //  data.setTime(rs.getDate("dataNascimento"));
                //contato.setDataNascimento(data.getTime());
                contatos.add(contato);
            }
            rs.close();
            stmt.close();
            return contatos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(Contato contato) throws ClassNotFoundException {
       this.connection = new ConnectionFactory().getConnection();
        String sql = "update	contatos	set	nome=?,	email=?,"
                + "endereco=?	where	id=?";
        try {
             PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setLong(4, contato.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Contato contato) throws ClassNotFoundException, SQLException {

        this.connection = new ConnectionFactory().getConnection();
        String sql = "delete	from	contatos where	id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setLong(1, contato.getId());
                stmt.execute();
                stmt.close();
            
        }

    

    public Contato comunica(Contato contato) {

        return contato;

    }

}
