package model;
import java.util.ArrayList;

import model.enums.*;

public class PostoDeGasolina {
	private String nome;
	private Endereco endereco;
	private String avaliacao;
	private ArrayList<tipoPlug> plug;
	private String precoKWH;
	
	public PostoDeGasolina(String nomeC, Endereco enderecoC, String avaliacaoC, ArrayList<tipoPlug> plugC, String precoKWHC) {
		mudarNome(nomeC);
		mudarEndereco(enderecoC);
		mudarAvaliacao(avaliacaoC);
		mudarPlug(plugC);
		mudarPrecoKWH(precoKWHC);
	}

	public String getNome() {
		return nome;
	}

	public void mudarNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void mudarEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void mudarAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public ArrayList<tipoPlug> getPlug() {
		return plug;
	}

	public void mudarPlug(ArrayList<tipoPlug> plug) {
		this.plug = plug;
	}

	public String getPrecoKWH() {
		return precoKWH;
	}

	public void mudarPrecoKWH(String precoKWH) {
		this.precoKWH = precoKWH;
	}
	
}

