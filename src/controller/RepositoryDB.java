package controller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
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
		String sqlEnderecos = "CREATE TABLE enderecos (id int NOT NULL AUTO_INCREMENT, rua varchar(150), bairro varchar(150), cidade varchar(150), estado varchar(150), PRIMARY KEY (id));";
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
	
	public ArrayList<PostoDeGasolina> getPostosOrdenado() throws SQLException {
		ArrayList<PostoDeGasolina> postos = new ArrayList<PostoDeGasolina>();
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		
		ResultSet result = statement.executeQuery("SELECT * FROM enderecos ORDER BY estado");
	
		while (result.next()) {
			Endereco endereco = new Endereco(result.getString("rua"), result.getString("bairro"), result.getString("cidade"), result.getString("estado"));
			endereco.setId(result.getInt("id"));
			enderecos.add(endereco);
		}
		
		for (Endereco endereco : enderecos) {
			ArrayList<tipoPlug> plugs = new ArrayList<tipoPlug>();
			ResultSet resultPlugs = statement.executeQuery("SELECT * FROM plugs WHERE id = " + endereco.getId());
			
			while (resultPlugs.next()) {				
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
			}
			
			ResultSet resultPostos = statement.executeQuery("SELECT * FROM postos WHERE id = " + endereco.getId());
			
			while (resultPostos.next()) {
				postos.add(new PostoDeGasolina(resultPostos.getString("nome"), endereco, resultPostos.getString("avaliacao"), plugs, resultPostos.getString("preco")));
			}
		}
		
		return postos;
	}
	
	public String gerarStringAPI(String nome) throws SQLException {
		
		ResultSet result = statement.executeQuery("SELECT id FROM postos WHERE nome = '" + nome + "';");

		result.next();
		
		ResultSet resultEndereco = statement.executeQuery("SELECT * FROM enderecos WHERE id = " + result.getInt("id"));
		
		resultEndereco.next();
		
		return resultEndereco.getString("rua") + "," + resultEndereco.getString("bairro") + "," + resultEndereco.getString("cidade");
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
 	
	public void downloadImg(String lugar, String nome) throws IOException {
		
		String link = "https://maps.googleapis.com/maps/api/staticmap?center="+lugar+",CA&zoom=14&size=400x400&key=AIzaSyDBwJXa5HGFbveZMhryeNkP2Cu_tzsKen4";
		URL url = new URL(link);

   String downloadFileLocation = "src/assets/buscas" + nome + ".jpg";

   InputStream is = url.openStream();
   
   OutputStream fos = new FileOutputStream(downloadFileLocation);

   int ch;
   while ((ch = is.read()) != -1) {
          fos.write(ch);
   }
   
   System.out.println("Image from specified URL has been downloaded at "
                +downloadFileLocation);
   is.close();
   fos.close();
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	public Connection getConnection() {
		return connection;
	}
}
