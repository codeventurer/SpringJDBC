package jdbcdemo;

import jdbcdemo.Condominium;
import dao.JDBCDAOImpl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Makeda
 */

public class JDBCDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        ApplicationContext context = new ClassPathXmlApplicationContext ("spring.xml");
        JDBCDAOImpl dao = context.getBean("JDBCDAOImpl", JDBCDAOImpl.class);
        dao.insertCondo();
        //System.out.println(dao.getCondoCount());
        // System.out.println(dao.getCondoLocation());
        //System.out.println(dao.getCondo("VA"));
        System.out.println(dao.getAllCondos());
        dao.updateCondo("Detroit", "MI", "VA");
        dao.deleteCondo("25193 Asher Drive");
        
        
        
        
        
        
        
        
        
        
        
        
        // System.out.println(dao.getAllCondoInfo());       

    }      
     
    
}
