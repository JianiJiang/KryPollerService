package model;

import java.sql.*;

/**
 * Create the table in MySQL default schema
 * @author JianiJiang
 */
public class TableCreation {
    private Connection conn;
    //private final String sql1 = "CREATE SCHEMA 'krypollerservice'";
    private final String sql = "CREATE TABLE TbServices " +
            "(serviceid INT NOT NULL AUTO_INCREMENT, " +
            " service_name VARCHAR(45) NOT NULL, " +
            " url VARCHAR(45) NOT NULL, " +
            " creation_time DATETIME NOT NULL, " +
            " service_status VARCHAR(45) NULL, " +
            " PRIMARY KEY (serviceid))";

    public TableCreation() {
        setupDBConnection();
    }

    private void setupDBConnection(){
        if (this.conn==null){
            DBConnection dbConn = new DBConnection();
            this.conn = dbConn.getConn();
        }
    }

    public void TableSetup(){
        try {
            //check if 'tbservices' table is there
            DatabaseMetaData dbm = this.conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "TbServices", null);
            //table exist, no need to create
            if (tables.next()) return;
            else{
                Statement statement = this.conn.createStatement();
                statement.executeUpdate(this.sql);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
