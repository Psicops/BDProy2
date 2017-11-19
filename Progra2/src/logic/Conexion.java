package logic;

import java.sql.*;

class Conexion {
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    public static final String USERNAME = "dbaproy2";
    public static final String PASSWORD = "1234";
    static Connection cn;
    static Statement st;
    static ResultSet rs;
    
    public static Connection getConexion() throws SQLException{
        cn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return cn;
    }
    
    public static ResultSet getRS(String query) throws SQLException{
        cn = Conexion.getConexion();
        st = cn.createStatement();
        rs = st.executeQuery(query);
        return rs;
    }
    
    public static void setQuery(String query) throws SQLException{
        cn = Conexion.getConexion();
        st = cn.createStatement();
        st.executeQuery(query);
    }
}
