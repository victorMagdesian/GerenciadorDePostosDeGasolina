import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFrame;

import controller.CadastroController;
import model.*;
import model.enums.*;
import view.home;

public class App {

	private static Connection connection;
	private static RepositoryDB repository;
	
	public static void main(String[] args) {
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:gerenciadorpostosdb");
			System.out.println("------------------------");
			System.out.println("Connected to h2 database.");
			System.out.println("------------------------\n\n\n");
			repository = new RepositoryDB(connection);
			repository.init();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home(new CadastroController(), connection);
					window.home.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//    public static void main(String[] args) {
//    	Scanner userInput = new Scanner(System.in);
//    	
//        System.out.println("Qual seu nome?"); 
//    	String name = userInput.nextLine();
//    	
//    	PostoDeGasolina postoDoCaio = new PostoDeGasolina(name, null, 0, null, null);
//    	
//    	System.out.println(postoDoCaio); 
//        System.out.println("Hello, " + postoDoCaio.getNome()); 
//        
//    }
}
