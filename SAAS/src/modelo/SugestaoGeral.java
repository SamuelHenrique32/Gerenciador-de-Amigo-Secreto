package modelo;

import java.io.Serializable;

public class SugestaoGeral implements Serializable{

	private Participante participante;
	private String sugestao;	
	
	public SugestaoGeral(Participante participante, String sugestao) {
		super();
		this.participante = participante;
		this.sugestao = sugestao;
	}
	
	@Override
	public String toString() {
		return "Participante " + participante.getNome() + " deu a sugestao: " + this.sugestao;		
	}	
}
