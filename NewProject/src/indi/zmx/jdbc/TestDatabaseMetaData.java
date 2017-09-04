package indi.zmx.jdbc;

import java.sql.*;

public class TestDatabaseMetaData {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver loaded");

		Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Hospital", "postgres",
				"zmx757");
		System.out.println("Database connected");

		DatabaseMetaData dbMetaData = connection.getMetaData();
		System.out.println("database URL: " + dbMetaData.getURL());
		System.out.println("database username: " + dbMetaData.getUserName());
		System.out.println("database product name: " + dbMetaData.getDatabaseProductName());
		System.out.println("database product version: " + dbMetaData.getDatabaseProductVersion());
		System.out.println("JDBC driver name: " + dbMetaData.getDriverName());
		System.out.println("JDBC driver version: " + dbMetaData.getDriverVersion());
		System.out.println("JDBC driver major version: " + dbMetaData.getDriverMajorVersion());
		System.out.println("JDBC driver minor version: " + dbMetaData.getDriverMinorVersion());
		System.out.println("Max number of connections: " + dbMetaData.getMaxConnections());
		System.out.println("MaxTableNameLength: " + dbMetaData.getMaxTableNameLength());
		System.out.println("MaxColumnInTable: " + dbMetaData.getMaxColumnsInTable());

		connection.close();

	}

}
