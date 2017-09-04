package indi.zmx.jdbc;

import java.sql.*;

public class SimpleJDBC {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver loaded");

		Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Hospital", "postgres",
				"zmx757");
		System.out.println("Database connected");

		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery(
				"select drug_id, drug_name, price " + "from medication where drug_des = 'antibiotics' and price < 25");

		while (resultSet.next())
			System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));

		connection.close();

	}

}
