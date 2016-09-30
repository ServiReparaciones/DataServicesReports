/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataServices.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author luis
 */
public class ConnectionMysql {
    
    private Connection conexion;
    private String Driver_Class;
    private String URL;
    private String Username;
    private String Password;

    public ConnectionMysql() {
        this.Driver_Class = "com.mysql.jdbc.Driver";
        this.URL = "jdbc:mysql://localhost:3306/dbServices";
        this.Username = "dbServicesUsers";
        this.Password = "05019536.180411";
        this.conexion = conectar();
    }

    public Connection conectar() {
        try {
            Class.forName(this.getDriver_Class()).newInstance();

            String BaseDeDatos = this.getURL();
            System.out.println(BaseDeDatos);

            conexion = DriverManager.getConnection(BaseDeDatos, this.getUsername(), this.getPassword());
            if (conexion != null) {
                System.out.println("Conexion exitosa!");
            } else {
                System.out.println("Conexion fallida!");
            }
            conexion.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            //log.level.info("-> "+e.getMessage());
        } catch (InstantiationException ex) {
            //log.level.info("-> "+ex.getMessage());
        } catch (IllegalAccessException ex) {
            //log.level.info("-> "+ex.getMessage());
        }

        return conexion;
    }

    public boolean ejecutar(String sql) {
        try {
            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql);
            getConexion().commit();
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void CloseConnection() throws SQLException {
        //log.level.info("Se cerro la conexion ");
        conexion.close();
    }

    public ResultSet consultar(String sql) {
        ResultSet resultado = null;
        try {
            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
        } catch (Exception e) {
           // log.level.info("-> " + e.toString());
        }
        return resultado;
    }

    /**
     * @return the Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @return the URL
     */
    public String getURL() {
        return URL;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * @return the Driver_Class
     */
    public String getDriver_Class() {
        return Driver_Class;
    }
    
}
