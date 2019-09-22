package br.com.jpa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "PES_PESSOA")
public class Pessoa {
	
	@Id //chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO) //auto-increment, o strategy define a estrategia de auto-incremento, deixar com parametro auto significa que vai se adaptar ao banco desejado.
	@Column(name = "PES_ID") //nome da coluna que sera gerada, além de eu poder utilizar outros tipos de definições, como constraints etc.
	private int id;
	
	@Column(name = "PES_NOME", nullable = false, length = 20)
	private String nome;
	
	@Column(name = "PES_SOBRENOME", nullable = false, length = 30)
	private String sobrenome;
	
	@Column(name = "PES_IDADE", nullable = false)
	private int idade;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
