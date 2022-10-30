package com.ventas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://162.241.169.207:3306/";
    private final String DB = "pawlluco_dbsistema";
    private final String USER = "pawlluco_david";
    private final String PASSWORD = "admin";

    public Connection cadena;
    public static Conexion instancia;

    private Conexion() {
        this.cadena = null;
    }

    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL + DB, USER, PASSWORD);
            System.out.println("Conexion buena!");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.cadena;
    }

    public void desconectar() {
        try {
            this.cadena.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public synchronized static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
    
    public static void main(String[]args){
        Conexion c =  new Conexion();
        c.conectar();
    }
}
