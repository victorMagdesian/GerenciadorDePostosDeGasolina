import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryDB {

	private Connection connection;
	
	public RepositoryDB(Connection connection) {
		this.connection = connection;
	}
	
	public void init() throws SQLException {
		String sqlEnderecos = "CREATE TABLE enderecos (rua varchar(50), bairro varchar(50), cidade varchar(50), estado varchar(50))";
		String sqlPostos = "CREATE TABLE postos (nome varchar(50))";
		
		Statement statement = connection.createStatement();
		statement.execute(sqlEnderecos);
		statement.execute(sqlPostos);
		
		System.out.println("------------------------");
		System.out.println("Tabelas criadas.");
		System.out.println("------------------------\n\n\n");
		
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
}
