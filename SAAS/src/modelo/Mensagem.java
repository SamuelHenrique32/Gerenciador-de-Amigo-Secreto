package modelo;

import java.io.Serializable;

public class Mensagem implements Serializable{

	private String conteudo;
	private Participante remetente;	
	private Participante destinatario;		

	public Mensagem(String conteudo, Participante remetente, Participante destinatario) {
		super();
		this.conteudo = conteudo;
		this.remetente = remetente;
		this.destinatario = destinatario;
	}

	@Override
	public String toString() {
		return "CONTEUDO DA MENSAGEM: " + this.conteudo + " REMETENTE: " + this.remetente.getCodinome() + " DESTINATARIO: " + this.destinatario.getCodinome();
	}
}
