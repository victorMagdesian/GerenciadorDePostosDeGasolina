package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.PostoDeGasolina;

public class RepositoryDB {

	private Connection connection;
	private Statement statement;
	
	public RepositoryDB(Connection connection) throws SQLException {
		this.connection = connection;
		statement = connection.createStatement();
	}
	
	public void init() throws SQLException {
		String sqlEnderecos = "CREATE TABLE enderecos (id int NOT NULL AUTO_INCREMENT, rua varchar(50), bairro varchar(50), cidade varchar(50), estado varchar(50), PRIMARY KEY (id));";
		String sqlPlugs = "CREATE TABLE plugs (id int NOT NULL AUTO_INCREMENT, tipo1 int NOT NULL, tipo2 int NOT NULL, css2 int NOT NULL, cha int NOT NULL, PRIMARY KEY (id))";
		String sqlPostos = "CREATE TABLE postos (id int NOT NULL AUTO_INCREMENT, nome varchar(50), enderecoID int, avaliacao varchar(3), plugsID int, preco varchar(15), PRIMARY KEY (id), FOREIGN KEY (enderecoID) REFERENCES enderecos(id), FOREIGN KEY (plugsID) REFERENCES plugs(id));";
		
		statement.execute(sqlEnderecos);
		statement.execute(sqlPlugs);
		statement.execute(sqlPostos);
		
		System.out.println("------------------------");
		System.out.println("Tabelas criadas.");
		System.out.println("------------------------\n\n\n");
		
	}
	
	public void inserirPosto(/*PostoDeGasolina posto*/) throws SQLException {
		String sql = "INSERT INTO postos (nome) VALUES ('pedrinho');";
		
		statement.executeUpdate(sql);
		statement.executeUpdate(sql);
		statement.executeUpdate(sql);
		
		ResultSet result = statement.executeQuery("SELECT * FROM postos");
		
		while (result.next()) {
			System.out.println(result.getString("nome"));
		}
		
		
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
}
