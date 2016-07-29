import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		
		Connection c = null;
		Statement stmt = null;
		
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:ClientDB.db");
	    } catch ( Exception e ) {
	      System.out.println("errore nel db");
	    }
	    System.out.println("db aperto correttamente");
	   
	    try {
	    stmt = c.createStatement();
	    String sql = "CREATE TABLE ClientsUno " +
	                 "(first_name 		TEXT    NOT NULL," +
	                 " last_name        TEXT    NOT NULL, " + 
	                 " address          TEXT    NOT NULL, " +  
	                 " city         	TEXT)"; 
	    stmt.executeUpdate(sql);
	    stmt.close();
	    c.close();
	  } catch ( Exception e ) {
	    System.out.println("errore nella tabella");
	  }
	  System.out.println("tabella creata");	
		
	    FileReader f;
	    f=new FileReader("Client.txt");

	    BufferedReader b;
	    b=new BufferedReader(f);

	    String s1;
	    String s2;
	    String s3;

	    s1=b.readLine();
	    String[] s11= s1.split(";");
	    //System.out.println(s11[2]);	
	    

	    s2=b.readLine();
	    String[] s22= s1.split(";");
	    
	    s3=b.readLine();
	    String[] s33= s1.split(";");
		
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:ClientDB.db");
	      c.setAutoCommit(false);

	      stmt = c.createStatement();
	      String sql = "INSERT INTO ClientsUno (first_name,last_name,address,city) " +
	                   "VALUES ('" + s11[0] + "','" + s11[1] + "','" + s11[2] + "','" + s11[3] + "');"; 
	      stmt.executeUpdate(sql);
	      
	      sql = "INSERT INTO ClientsUno (first_name,last_name,address,city) " +
                "VALUES ('" + s22[0] + "','" + s22[1] + "','" + s22[2] + "','" + s22[3] + "');";
	      stmt.executeUpdate(sql);

	      sql = "INSERT INTO ClientsUno (first_name,last_name,address,city) " +
                "VALUES ('" + s33[0] + "','" + s33[1] + "','" + s33[2] + "','" + s33[3] + "');";
	      stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.out.println("errore nella compilazione della tabella");
	    }
	    System.out.println("Records creati");
	}

}
