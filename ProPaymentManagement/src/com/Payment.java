package com;
import java.sql.*;
public class Payment
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con =
 DriverManager.getConnection(
 "jdbc:mysql://127.0.0.1:3306/paymentpro", "root", "");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }

public String readPayments()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for reading.";
}
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Payment card no</th><th>Payment cvv</th><th>Payment expire date</th>"+ "<th> Payment cardholder name</th><th>Update</th><th>Remove</th></tr>";
String query = "select * from payments";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String paymentID = Integer.toString(rs.getInt("paymentID"));
String paymentCardNo = rs.getString("paymentCardNo");
String paymentCvv = rs.getString("paymentCvv");
String paymentExpireDate = rs.getString("paymentExpireDate");
String paymentCardholderName = rs.getString("paymentCardholderName");
// Add into the html table
output += "<tr><td><input id='hidPaymentIDUpdate'name='hidPaymentIDUpdate'type='hidden' value='" + paymentID
+ "'>" + paymentCardNo + "</td>";
output += "<td>" + paymentCvv + "</td>";
output += "<td>" + paymentExpireDate + "</td>";
output += "<td>" + paymentCardholderName + "</td>";
// buttons
output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-paymentid='"
+ paymentID + "'>" + "</td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the payments.";
System.err.println(e.getMessage());
}
return output;
}


public String insertPayment(String cardno, String cvv, String expiredate, String paymentcardholdername)
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
 String query = " insert into payments(`paymentID`,`paymentCardNo`,`paymentCvv`,`paymentExpireDate`,`paymentCardholderName`)"
 + " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, cardno);
		 preparedStmt.setString(3, cvv);
		 preparedStmt.setString(4, expiredate);
		 preparedStmt.setString(5, paymentcardholdername);
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newPayments = readPayments();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newPayments + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updatePayment(String id, String cardno, String cvv,
		 String expiredate, String paymentcardholdername)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for updating.";
		 }
		 // create a prepared statement
		 String query = "UPDATE payments SET paymentCardNo=?,paymentCvv=?,paymentExpireDate=?,paymentCardholderName=? WHERE paymentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, cardno);
		 preparedStmt.setString(2, cvv);
		 preparedStmt.setString(3, expiredate);
		 preparedStmt.setString(4, paymentcardholdername);
		 preparedStmt.setInt(5, Integer.parseInt(id));
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newPayments = readPayments();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newPayments + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while updating the payment.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deletePayment(String paymentID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for deleting.";
		 }
		 // create a prepared statement
		 String query = "delete from payments where paymentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(paymentID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newPayments = readPayments();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newPayments + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		}