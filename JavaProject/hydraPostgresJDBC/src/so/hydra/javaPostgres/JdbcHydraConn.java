package so.hydra.javaPostgres;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class JdbcHydraConn {
	public static void main(String args[]) {
	      Connection c = null;
	      Statement stmt = null;
	      // auto-commit is enabled by default
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	        		 //Note section:------------
	        		 // if you choose all the default configuration during the hydra setup then use the below connection
	        		 .getConnection("jdbc:postgresql://localhost:5432/mydatabase",
	        		            "postgres", "hydra");
	         		// if you are trying to connnect to postgres from different machine then un-comment the below code, and change the Ip and port based on your configuration
		            //.getConnection("jdbc:postgresql://192.168.2.24:5484/mydatabase",
		            //"postgres", "hydra");
	      
	         System.out.println("Opened database successfully");
	      
	         
	         stmt = c.createStatement();
	         String sql = "DROP TABLE IF EXISTS COMPANY;";
             stmt.executeUpdate(sql);
             //c.commit();
	      
		     stmt = c.createStatement();
		         sql = "CREATE TABLE COMPANY " +
		            "(ID INT PRIMARY KEY     NOT NULL," +
		            " NAME           TEXT    NOT NULL, " +
		            " AGE            INT     NOT NULL, " +
		            " ADDRESS        CHAR(50), " +
		            " SALARY         REAL)";
		         stmt.executeUpdate(sql);

		         
		         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
		                 + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
		              stmt.executeUpdate(sql);

		              sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
		                 + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
		              stmt.executeUpdate(sql);

		              sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
		                 + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
		              stmt.executeUpdate(sql);

		              sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
		                 + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
		            
		              System.out.println("Rows Inserted successfully");
		              
		              stmt.executeUpdate(sql);
		              
		              
		              stmt = c.createStatement();
		              ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
		              while ( rs.next() ) {
		                 int id = rs.getInt("id");
		                 String  name = rs.getString("name");
		                 int age  = rs.getInt("age");
		                 String  address = rs.getString("address");
		                 float salary = rs.getFloat("salary");
		                 System.out.println( "ID = " + id );
		                 System.out.println( "NAME = " + name );
		                 System.out.println( "AGE = " + age );
		                 System.out.println( "ADDRESS = " + address );
		                 System.out.println( "SALARY = " + salary );
		                 System.out.println();
		              }
		              System.out.println("Rows Displayed successfully");
		              
		              stmt = c.createStatement();
		              sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
		              stmt.executeUpdate(sql);
		              //c.commit();
		              System.out.println("Rows Updated successfully");
		              
		              stmt = c.createStatement();
		              rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
		              while ( rs.next() ) {
		                 int id = rs.getInt("id");
		                 String  name = rs.getString("name");
		                 int age  = rs.getInt("age");
		                 String  address = rs.getString("address");
		                 float salary = rs.getFloat("salary");
		                 System.out.println( "ID = " + id );
		                 System.out.println( "NAME = " + name );
		                 System.out.println( "AGE = " + age );
		                 System.out.println( "ADDRESS = " + address );
		                 System.out.println( "SALARY = " + salary );
		                 System.out.println();
		              }
		              //c.commit();
		              
		              rs.close();
		              stmt.close();
		              c.close();
		              
		      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Operations Done Successfully!");
	   }
}
