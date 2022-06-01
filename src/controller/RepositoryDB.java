package controller;
import java.sql.Connection;

public class RepositoryDB {

	private Connection connection;
	
	public RepositoryDB(Connection connection) {
		this.connection = connection;
	}
}
