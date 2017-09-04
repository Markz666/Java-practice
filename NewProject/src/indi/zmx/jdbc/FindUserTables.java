package indi.zmx.jdbc;

import java.sql.*;

public class FindUserTables {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver loaded");

		Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Hospital", "postgres",
				"zmx757");
		System.out.println("Database connected");

		DatabaseMetaData dbMetaData = connection.getMetaData();
		ResultSet rsTables = dbMetaData.getTables(null, null, null, new String[] { "TABLE" });
		System.out.print("User tables: ");
		while (rsTables.next())
			System.out.print(rsTables.getString("TABLE_NAME") + " ");

		connection.close();
	}
}
