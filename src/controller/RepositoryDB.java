package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.enums.*;
import model.Endereco;
import model.PostoDeGasolina;

public class RepositoryDB {

	private Connection connection;
	private Statement statement;
	private int index = 1;
	
	public RepositoryDB(Connection connection) throws SQLException {
		this.connection = connection;
		statement = connection.createStatement();
	}
	
	private void salvarPlugs(ArrayList<tipoPlug> plugs) throws SQLException {
		
		String sqlInit = "INSERT INTO plugs (tipo1, tipo2, css2, chademo) VALUES (0,0,0,0)";
		statement.executeUpdate(sqlInit);
		
		for (tipoPlug plug : plugs) {
			
			String sql = "UPDATE plugs SET "+ plug.name() +" = 1 WHERE id = " + index;
			statement.executeUpdate(sql);
		}
		
	}
	
	public void init() throws SQLException {
		String sqlEnderecos = "CREATE TABLE enderecos (id int NOT NULL AUTO_INCREMENT, rua varchar(50), bairro varchar(50), cidade varchar(50), estado varchar(50), PRIMARY KEY (id));";
		String sqlPlugs = "CREATE TABLE plugs (id int NOT NULL AUTO_INCREMENT, tipo1 int, tipo2 int , css2 int , chademo int , PRIMARY KEY (id))";
		String sqlPostos = "CREATE TABLE postos (id int NOT NULL AUTO_INCREMENT, nome varchar(50), avaliacao varchar(3), preco varchar(15), PRIMARY KEY (id));";
		
		statement.execute(sqlEnderecos);
		statement.execute(sqlPlugs);
		statement.execute(sqlPostos);
		
		System.out.println("------------------------");
		System.out.println("Tabelas criadas.");
		System.out.println("------------------------\n\n\n");
		
	}
	
	public void inserirPosto(PostoDeGasolina posto) throws SQLException {
		String sqlEnderecos = "INSERT INTO enderecos (rua, bairro, cidade, estado) VALUES ('"+ posto.getEndereco().getRua() +"','"+ posto.getEndereco().getBairro() +"','"+ posto.getEndereco().getCidade() +"','"+ posto.getEndereco().getEstado() +"');";
		String sqlPostos = "INSERT INTO postos (nome, avaliacao, preco) VALUES ('"+ posto.getNome() +"', '"+ posto.getAvaliacao() +"', '"+ posto.getPrecoKWH() +"')";
		
		statement.executeUpdate(sqlEnderecos);
		salvarPlugs(posto.getPlug());
		statement.executeUpdate(sqlPostos);
		
		System.out.println("------------------------");
		System.out.println("posto " + posto.getNome() +" criado.");
		System.out.println("------------------------\n\n\n");
		
		index++;
	}
	
	public ArrayList<PostoDeGasolina> getPostos() throws SQLException {
		
		ArrayList<PostoDeGasolina> postos = new ArrayList<PostoDeGasolina>();
		
		
		ResultSet result = statement.executeQuery("SELECT * FROM postos");
		
		while (result.next()) {
			postos.add(new PostoDeGasolina(result.getString("nome"), null, result.getString("avaliacao"), null, result.getString("preco")));
			
		}
		
		ResultSet resultPlugs = statement.executeQuery("SELECT * FROM plugs");
		
		while (resultPlugs.next()) {
			ArrayList<tipoPlug> plugs = new ArrayList<tipoPlug>();
			
			if (resultPlugs.getInt("tipo1") == 1) {
				plugs.add(tipoPlug.tipo1);
			}
			if (resultPlugs.getInt("tipo2") == 1) {
				plugs.add(tipoPlug.tipo2);
			}
			if (resultPlugs.getInt("css2") == 1) {
				plugs.add(tipoPlug.css2);
			}
			if (resultPlugs.getInt("chademo") == 1) {
				plugs.add(tipoPlug.chademo);
			}
			
			PostoDeGasolina atual = postos.get(resultPlugs.getInt("id") - 1);
			atual.mudarPlug(plugs);
			postos.set(resultPlugs.getInt("id") - 1, atual);
			
		}
		
		ResultSet resultEnderecos = statement.executeQuery("SELECT * FROM enderecos");
		
		while (resultEnderecos.next()) {
			Endereco endereco = new Endereco(resultEnderecos.getString("rua"), resultEnderecos.getString("bairro"), resultEnderecos.getString("cidade"), resultEnderecos.getString("estado"));
			
			PostoDeGasolina atual = postos.get(resultEnderecos.getInt("id") - 1);
			atual.mudarEndereco(endereco);
			postos.set(resultEnderecos.getInt("id") - 1, atual);
		}	
		
		
		return postos;
	}
 	
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	public Connection getConnection() {
		return connection;
	}
}
