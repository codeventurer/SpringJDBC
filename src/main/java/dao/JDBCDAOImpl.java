package dao;

import jdbcdemo.Condominium;
import jdbcdemo.JDBCDemo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;







import jdbcdemo.Condominium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public class JDBCDAOImpl {
    
	
    private DataSource datasource;
    private JdbcTemplate template = new JdbcTemplate();
    
 /*  public Condominium getCondominium (int id, String address, String city, String state, String zipcode) throws SQLException {
        
        
             Connection conn = null;
             PreparedStatement ps = null;
             String db_url = "jdbc:sqlserver://localhost:1433;database=SPRINGJDBC;username=;password=";
             String db_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
             String db_username = ""; 
        try {
             
              Class.forName(db_driver);              
              conn = DriverManager.getConnection(db_url); 
              conn = getDatasource().getConnection();
              ps = conn.prepareStatement("insert INTO Condominiums values (5, '25600 Quits Pond Court', 'Chantilly', 'VA')");
              System.out.println("loading");
              
              ps = conn.prepareStatement("insert INTO Condominiums values (5, '25600 Quits Pond Court', 'Chantilly', 'VA', '01-01-2016', '12-25-2015')");
              ps = conn.prepareStatement("SELECT * FROM  Condominiums");
              ResultSet rs = ps.executeQuery();
              while (rs.next()) {
                  condo = new Condominium ();
                  condo = new Condominiums(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getString(3));
                  System.out.println(rs.findColumn("City"));
                  System.out.println(rs.getString(2));
                  System.out.println(rs.getDate(5));
                  System.out.println(getCondoLocation());
              }
        } 
        
        catch (Exception ex) {
               Logger.getLogger(JDBCDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
               throw new RuntimeException(ex);
        }
        
        finally {
            
            ps.close();
            conn.close();         
        }
}*/

     public int getCondoCount () {
    	System.out.println("This is the count of all of the rows of Condominiums from getCondoCount method.");
        String sql = "SELECT COUNT (*) FROM Condominiums";
        return template.queryForObject (sql, Integer.class);
        
    } 
    
     public String getCondoLocation () {
         System.out.println("This going to retrieve the address for VA from getCondoLocation method.");
    	 String sql = "SELECT address FROM Condominiums WHERE State = 'VA'";
         return template.queryForObject(sql, String.class);
        
    }
     
    
     public DataSource getDatasource() {
        
        return datasource;
    }

   @Autowired
    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
      this.template = new JdbcTemplate(datasource);
    }  
    
    public Condominium getCondo (String s) {
        
       String sql = "SELECT * FROM Condominiums WHERE STATE = ?";
       System.out.println("Rowmapper jdbc template queryForObject method example from getCondo method.");
       return template.queryForObject(sql, new Object [] {s}, new CondoMapper());
    
    }
    
    public List<Condominium> getAllCondos ( ) {   
    	System.out.println("Rowmapper jdbc template query method example from getAllCondos.");
        String sql = "SELECT * FROM Condominiums";
        return template.query(sql, new CondoMapper());
    }    
  
    public List <Map <String, Object>> getAllCondoInfo () { 
    	System.out.println("Query for List example from getAllCondoInfo");
        String sql = "SELECT * FROM Condominiums WHERE state='MD'";
        return template.queryForList (sql);
    }
     
   
    
    private final class CondoMapper implements RowMapper <Condominium> {

        @Override //The JDBC template calls mapRow once for each row in the result set.
        public Condominium mapRow(ResultSet rs, int rownum) throws SQLException {
            Condominium condo = new Condominium ();
            condo.setAptno(rs.getInt("AptNo"));
            condo.setAddress(rs.getString("address"));
            condo.setCity(rs.getString("city"));
            condo.setState(rs.getString("state"));
            System.out.println("RowMapper example:  " + (Integer) condo.getAptno()  + " " + condo.getAddress().toString() + " " + condo.getCity().toString() + " " + " " + condo.getState().toString());
            return condo;  //This returns an initialized Condominium object each time return condo is called.  There is one condo object created for each row in the result set.
     }
        
  }
     
    public void insertCondo () {
   	 
   	 String sql = "INSERT INTO Condominiums (Aptno, address, city, state, zip, purchaseDate, sellDate) VALUES (2 , '25163 Asher Drive', 'Herndon', 'VA', '20185' , '2002-03-08', '2016-12-08')";
   	 template.update(sql);
    }
    
    public void retrieveCondo (int aptno) {
   	 
   	 String sql = "SELECT * FROM Condominiums WHERE AptNo=?";
   	 template.update(sql, new Object [] {aptno});
  }
    
    public void updateCondo (String newcity, String newstate, String oldstate) {
     	 
     	 String sql = "UPDATE Condominiums SET city=?, state=? WHERE state=?";
     	 template.update(sql, new Object [] {newcity, newstate, oldstate});
     	 
    }
    
    public void deleteCondo (String address) {
    	 
    	 String sql = "DELETE FROM Condominiums WHERE address=?";
    	 template.update(sql, new Object [] {address});
   }

    
}

