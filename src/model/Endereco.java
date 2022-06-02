package model;

public class Endereco {
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private int id;
	
	public Endereco(String ruaC, String bairroC, String cidadeC, String estadoC) { 
		rua = ruaC;
		bairro = bairroC;
		cidade = cidadeC;
		estado = estadoC;
	}

	public String getEstado() {
		return estado;
	}
	
	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
