/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author julio.gabardo
 */
public class Conexao {

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto", "root", "123qwe@");
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
