package controller;
import java.util.ArrayList;

import model.PostoDeGasolina;

public class CadastroController {
	
	private ArrayList<PostoDeGasolina> postosCadastrados = new ArrayList<PostoDeGasolina>();

	public ArrayList<PostoDeGasolina> getPostosCadastrados() {
		return postosCadastrados;
	}
	
	public void cadastrarPosto(PostoDeGasolina postoNovo) {
		if (postoNovo != null) {
			postosCadastrados.add(postoNovo);
			
			System.out.println("POSTOS CADASTRADOS");
			System.out.println("------------------------");
			for (PostoDeGasolina postoDeGasolina : postosCadastrados) {
				System.out.println(postoDeGasolina.getNome());
			}
			System.out.println("------------------------\n\n\n");
			
		}
	}
	
}
