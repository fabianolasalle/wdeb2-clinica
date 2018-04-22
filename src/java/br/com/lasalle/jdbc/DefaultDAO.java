/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author fabiano
 */
public class DefaultDAO {
    private Connection connection;
    
    public DefaultDAO() throws ClassNotFoundException
    {
        ConnectionSingleton conn = new ConnectionSingleton();
        this.connection = conn.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
