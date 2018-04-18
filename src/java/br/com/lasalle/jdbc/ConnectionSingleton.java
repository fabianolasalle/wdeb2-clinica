/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author danie
 */
public class ConnectionSingleton {
    
    public Connection connection;
    
    public ConnectionSingleton() throws ClassNotFoundException {
        try {
            if (null == this.connection) {
                Class.forName("com.mysql.jdbc.Driver"); 
                this.connection = DriverManager.getConnection("jdbc:mysql://localhost/webdevt1", "root", "123456");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }   
    
    public Connection getConnection() {
        return this.connection;
    }
}
