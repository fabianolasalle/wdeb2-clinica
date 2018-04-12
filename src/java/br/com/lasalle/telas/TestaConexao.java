/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.telas;

import br.com.lasalle.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author danie
 */
public class TestaConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
         Connection connection = new ConnectionFactory().getConnection();
          System.out.println("Conexão aberta!");
          connection.close();
    }
    
}
