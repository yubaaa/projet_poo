package application;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author WINDOWS 10
 */
public class database {
    
    public static Connection connectDb(){
        
        try{
        	 Class.forName("oracle.jdbc.driver.OracleDriver");
            
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "nsmwnn64ever"); // root is the default username while "" or empty is for the pass
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}