# Hackathon_Project
# Hackathon_Project
package hackathon_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/display/")
public class Table {
public static Connection getConnection() throws SQLException {

Connection con=null;
try {
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb?autoReconnect=true","root","Swetha@123");
System.out.println("Success");
}
catch(ClassNotFoundException e) {
e.printStackTrace();
}
return con;
}

public static String getDetails() throws SQLException {
PreparedStatement preparedStatement3=getConnection().prepareStatement("SELECT maximum(CPU_Value),average(CPU_Value) FROM CPU");
ResultSet result2=preparedStatement3.executeQuery();
String maximum="";
String average="";
while(result2.next()) {
maximum=result2.getString(1);
average=result2.getString(2);
}
String s="<table style=\"border: 1px solid black\"><tr><th style=\"border: 1px solid black\">Transaction Name</th><th style=\"border: 1px solid black\">maximum</th><th style=\"border: 1px solid black\">average</th></tr><tr><td style=\"border: 1px solid black\">Sample Trasaction</td><td style=\"border: 1px solid black\">"+maximum+"</td><td style=\"border: 1px solid black\">"+average+"</td></tr></table>";
return s;
}
@Path("/table")
@GET
@Produces(MediaType.TEXT_HTML)
public String display() throws SQLException {
String s=getDetails();
return s;


}
}
