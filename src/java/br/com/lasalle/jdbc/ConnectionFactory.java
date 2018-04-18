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
public class ConnectionFactory {
        
    public Connection getConnection() throws ClassNotFoundException {
              try {
                  Class.forName("com.mysql.jdbc.Driver"); 
                  return DriverManager.getConnection("jdbc:mysql://localhost/webdevt1", "root", "123456");
              } catch (SQLException e) {
                  throw new RuntimeException(e);
              }
          }
    
}
