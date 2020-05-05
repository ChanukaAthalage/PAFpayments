package com.Payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
	
Connection con = null;
	
	public PaymentRepo() {
		
	String url = "jdbc:mysql://localhost:3306/paymentdb?serverTimezone=UTC";
	String username = "root";
	String password = "";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url,username,password);
	
	}catch(Exception e) {
		System.out.println(e);
		
	}
}
	
	public String getAllPayments(){
		
		String output = "";
		
		// Prepare the html table to be displayed 
				output = "<table id = table border='1' table width ='100%' ><tr>"
						
						+ "<th>Name</th>"
						+ "<th>CardNo</th>"   
						+ "<th>Cvc</th>"
						+ "<th>Exp</th>"
						+ "<th>AppointmentNo</th>"
						+ "<th>Amount</th>"
						+ "<th>PayDate</th>"
						+ "<th>Email</th>"
						+ "<th>Update</th>"
						+ "<th>Remove</th></tr>"; 
		
//		List<Payments> payment = new ArrayList<>();
		String sql = "select * from paymentdetails";
		
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				//Payments p = new Payments();
				String PaymentID = Integer.toString(rs.getInt(1));
				String Name = rs.getString(2);
				String CardNo = rs.getString(3);
				String Cvc = Integer.toString(rs.getInt(4));
				String Exp = rs.getString(5);
				String AppointmentNo = Integer.toString(rs.getInt(6));
				String Amount = Float.toString(rs.getFloat(7));
				String PayDate = rs.getString(8);
				String Email = rs.getString(9);
				
				//payment.add(p);
				
				// Add into the html table 
				output += "<tr><td><input id='PaymentID'  name='PaymentID'   type='hidden' value='" + PaymentID    + "'>"+
						Name + "</td>";  
				output += "<td>" + CardNo + "</td>";
				output += "<td>" + Cvc + "</td>";
				output += "<td>" + Exp + "</td>";
				output += "<td>" + AppointmentNo+ "</td>";
				output += "<td>" + Amount + "</td>";
				output += "<td>" + PayDate + "</td>";
				output += "<td>" + Email + "</td>";
				
				
				
				output += "<td><input name='btnUpdate'     type='button' value='Update'   class='btnUpdate btn btn-secondary'></td>" 
				+ "<td><input name='btnRemove' type='button' value='Remove'   class='btnRemove btn btn-danger'    data-paymentid='"  
						+ PaymentID + "'>" + "</td></tr>";
			}
			
			// Complete the html table
						
						output += "</table>"; 
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		//return payment;
		return output;
	}
	
//	public Payments getPayment(int PaymentID) {
//		
//		String sql = "select PaymentID,Name,CardNo,AppointmentID,Amount,PayDate,Email from paymentdetails where PaymentID ="+PaymentID;
//		Payments p = new Payments();
//		
//		try {
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(sql);
//			if(rs.next()) {
//				
//				p.setPaymentID(rs.getInt(1));
//				p.setName(rs.getString(2));
//				p.setCardNo(rs.getString(3));
//				p.setAppointmentID(rs.getInt(4));
//				p.setAmount(rs.getFloat(5));
//				p.setPayDate(rs.getString(6));
//				p.setEmail(rs.getString(7));
//				
//	
//			}
//			
//		}catch(Exception e) {
//			
//			System.out.println(e);
//		}
//		return p;
//		
//	}
//	
public String createPayments(String Name,String CardNo,String Cvc,String Exp,String AppointmentNo,String Amount,String PayDate,String Email ) {
		
		String output = "";
		String sql = "insert into paymentdetails values (?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,0);
			st.setString(2,Name);
			st.setString(3,CardNo);
			st.setInt(4, Integer.parseInt(Cvc));
			st.setString(5,Exp);
			st.setInt(6, Integer.parseInt(AppointmentNo));
			st.setFloat(7, Float.parseFloat(Amount));
			st.setString(8,PayDate);
			st.setString(9,Email);
			
			
			st.executeUpdate();
			
			String newPayment = getAllPayments();	
			
			output = "{\"status\":\"success\", \"data\": \"" + 
					newPayment+ "\"}";
			
			
		}catch(Exception e) {
			
			output = "{\"status\":\"error\", \"data\":"
					+ "         \"Error while inserting the item.\"}";
			System.out.println(e);
		}
		
		return output;
		
	}
	public String updatePayments(String Name,String CardNo,String Cvc,String Exp,String AppointmentNo,String Amount,String PayDate,String Email,String PaymentID) {
		
		String output = "";
		
		String sql = "update paymentdetails set Name=?,CardNo=?,Cvc=?,Exp=?,AppointmentNo=?,Amount=?,PayDate=?,Email=? where PaymentID=?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
				
			st.setString(1,Name);
			st.setString(2,CardNo);
			st.setInt(3, Integer.parseInt(Cvc));
			st.setString(4,Exp);
			st.setInt(5, Integer.parseInt(AppointmentNo));
			st.setFloat(6, Float.parseFloat(Amount));
			st.setString(7,PayDate);
			st.setString(8,Email);
			st.setInt(9,Integer.parseInt(PaymentID));
			st.executeUpdate();
				
			String newPayment = getAllPayments();		
				
			output = "{\"status\":\"success\", \"data\": \"" + 
					newPayment+ "\"}";
	
			
			
		}catch(Exception e) {
			
			output = "{\"status\":\"error\", \"data\": "
					+ "        \"Error while updating the item.\"}";
			
			System.out.println(e);
		}
		
		return output;  
		
	}
	
		public String deletePayments(String PaymentID) {
			
			String output = "";
		
		String sql = "delete from paymentdetails where PaymentID=?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
				
				st.setInt(1,Integer.parseInt(PaymentID));
				st.executeUpdate();
				
				String newPayment = getAllPayments();	
				
				output = "{\"status\":\"success\", \"data\": \"" + 
						newPayment+ "\"}";
	
			
			
		}catch(Exception e) {
			
			 output = "{\"status\":\"error\", \"data\":      "
				   		+ "   \"Error while deleting the item.\"}"; 
			
			System.out.println(e);
		}
		return output;
		
	}
	

}

