package com.abhishek.jdbc;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcApp13 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		FileOutputStream fos = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", "root", "Abhishek123");
			st = con.createStatement();
			
			rs = st.executeQuery("select * from emp1");
			String data = "";
			data = data + "<html><body><table align='center' border='1'";
			data = data + "<tr><th>ENO</th><th>ENAME</th><th>ESAL</th><th>EADDR</th></tr>";
			while(rs.next()) {
				data = data + "<tr>";
				data = data + "<td>" +rs.getInt("ENO")+ "</td>";
				data = data + "<td>" +rs.getString("ENAME")+ "</td>";
				data = data + "<td>" +rs.getFloat("ESAL")+ "</td>";
				data = data + "<td>" +rs.getString("EADDR")+ "</td>";
				data = data + "</tr>";
			}
			data = data + "</table></body></html>";
			fos = new FileOutputStream("C:\\Users\\Dell\\Documents\\PracticeLab\\AAA\\emp.html");
			byte[] b = data.getBytes();
			fos.write(b);
			System.out.println("emp1 table transferred to C:\\Users\\Dell\\Documents\\PracticeLab\\AAA\\emp.html");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
				rs.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}