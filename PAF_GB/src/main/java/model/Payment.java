package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	private Connection connect()
	 {
	 
		Connection con = null;
	
	try{
	    Class.forName("com.mysql.jdbc.Driver");

	    //Provide the correct details: DBServer/DBName, username, password
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_new", "root", "");
	    System.out.print("Successfully connected");
		}
		catch (Exception e)
		{
		 e.printStackTrace();		
		 }
	     return con;
	 	 } 
	
	public String insertPayment(String Name, String ProductName, String ProductId, String Amount, String PaymentType, String CardNo)
	 {
	
		String output = "";
	 
		try
		{
	 
			Connection con = connect();
	
			if (con == null)
				{
				return "Error while connecting to the database for inserting.";
				}
	 
			// create a prepared statement
	
			String query = " INSERT INTO payment(Id, Name, ProductName, ProductId, Amount, PaymentType, CardNo) VALUES (?, ?, ?, ?, ?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, Name);
	        preparedStmt.setString(3, ProductName);
	        preparedStmt.setString(4, ProductId);
	        preparedStmt.setString(5, Amount);
	        preparedStmt.setString(6, PaymentType);
	        preparedStmt.setString(7, CardNo);
	        // execute the statement
	
	        preparedStmt.execute();
	        con.close();
	        
	        String newPayment = readPayment();
	        output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
	
		}
		catch (Exception e)
		{
	 
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment.\"}"; 
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String readPayment(){
	 
		String output = "";
	 
		try{
	
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 
			// Prepare the html table to be displayed
	
			output = "<table border='1'> <tr><th>Id</th><th>Name</th><th>ProductName</th>" +
	                "<th>ProductId</th>" +
	                "<th>Amount</th>" +
	                "<th>PaymentType</th><th>CardNo</th><th>Update</th><th>Remove</th></tr>";

	 
			String query = "select * from payment";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	        	
	            String Id= Integer.toString(rs.getInt("Id"));
	        	String Name = rs.getString("Name");
	            String ProductName = rs.getString("ProductName");
	            String ProductId = rs.getString("ProductId");
	            String Amount = rs.getString("Amount");
	            String PaymentType = rs.getString("PaymentType");
	            String CardNo = rs.getString("CardNo");
	            
	            // Add into the html table
	
	            output +=  "<tr><td><input id='hidPaymentIdUpdate' name='hidItemIDUpdate'type='hidden' value='" + Id
	            		 + "'>" + Name + "</td>"; 
	            output += "<td>" + ProductName + "</td>";
	            output += "<td>" + ProductId + "</td>";
	            output += "<td>" + Amount + "</td>";
	            output += "<td>" + PaymentType + "</td>";
	            output += "<td>" + CardNo + "</td>";
	
	            // buttons
	           output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
	        		   + "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-paymentid='"
	        		   + Id + "'>" + "</td></tr>"; 
	  
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading the payments.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String updatePayment(String Id ,String Name ,String ProductName, String ProductId ,String Amount  ,String PaymentType, String CardNo){
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for updating.";
				}
	 
			// create a prepared statement
	        String query = "UPDATE payment SET Name=? , ProductName=?, ProductId=?, Amount=?, PaymentType=?, CardNo=? WHERE Id=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	        // binding values
	        preparedStmt.setString(1, Name);
	        preparedStmt.setString(2, ProductName);
	        preparedStmt.setString(3, ProductId);
	        preparedStmt.setString(4, Amount);
	        preparedStmt.setString(5, PaymentType);
	        preparedStmt.setString(6, CardNo);
	        preparedStmt.setInt(7, Integer.parseInt(Id));
	
	        // execute the statement
	        preparedStmt.execute();
	        con.close();
	        
	        String newPayment = readPayment();
	        output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}"; 
	 
		}catch (Exception e){
	 
			output = "{\"status\":\"error\", \"data\":\"Error while updating the payment.\"}"; 
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String deletePayment(String Id){
	 
		String output = "";
	
		try {
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	
			 // create a prepared statement
	         String query = "DELETE FROM payment WHERE Id=?";
	         PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	         // binding values
	         preparedStmt.setInt(1, Integer.parseInt(Id));
	 
	         // execute the statement
	         preparedStmt.execute();
	         con.close();
	         
	         String newPayment = readPayment();
	         output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}"; 
	
		}catch (Exception e){
			
	 
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}"; 
	        System.err.println(e.getMessage());
	
		}
	
		return output;
	 } 
	
}
