package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO seller"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES"
					+ "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "XXXX");
			st.setString(2, "yyyyy@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("17/12/1991").getTime()));
			st.setDouble(4, 10000);
			st.setInt(5, 4);
			
			int rowsAffected = st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			int id = rs.getInt(1);
			System.out.println("Rows Affected:"+rowsAffected+" Keys:"+rs);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeConnection();
			DB.closeStatement(st);
		}
		
	}

}
