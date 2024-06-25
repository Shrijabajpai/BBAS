package bbas.dbinfo;
import java.sql.*;

public class DBconnection {

   private static Connection con=null; 
	
	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //it is used to create the obj
			//factory methods-> to create obj of a class
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bbas_db","root","root");
			//jdbc:mysql->is a sub protocol  ,  localhost->is the name of IP address of the machine where DB has been installed
	        //3306->is the port number on which MySQL listen the request  ,  sms_db->DBname
			//connection string or connection URL 
			// 1st root->userid in MySQL
			// 2nd root->password
		}
		catch(ClassNotFoundException|SQLException cse)
		{
			cse.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		Connection con=openConnection();
		System.out.println(con);
	}
}
