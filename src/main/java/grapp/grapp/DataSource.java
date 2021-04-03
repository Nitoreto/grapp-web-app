package grapp.grapp;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

        private static HikariConfig config = new HikariConfig();
        private static HikariDataSource ds;
    
        static {
            /*try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }*/
            config.setJdbcUrl( "postgres://fqdunfercvmtrb:4893ba593d036a518f11634deae9224233e95c7f1e9e37bb2f446805dceb3a29@ec2-52-50-171-4.eu-west-1.compute.amazonaws.com:5432/dduetcch1mnm33" );
            //config.setUsername( "fqdunfercvmtrb" );
            //config.setPassword( "4893ba593d036a518f11634deae9224233e95c7f1e9e37bb2f446805dceb3a29" );
            
            config.addDataSourceProperty( "cachePrepStmts" , "true" );
            config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
            config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
            ds = new HikariDataSource( config );
        }
    
        private DataSource() {}
    
        public static Connection getConnection() throws SQLException {
            
            return ds.getConnection();
        }
    }
