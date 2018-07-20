package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Participante implements Comparable<Participante>, Serializable{

	private String nome;
	private String codinome;
	//private static int nextID = 1;
	private int id;
	private Participante remetente;
	private Participante destinatario;
	private List<String> sugestaoPresentes;
	private List<Mensagem> mensagensRecebidas;
	private List<Mensagem> mensagensEnviadas;
		
	public Participante(String nome, String codinome, int ID) {
		this.nome = nome;
		this.codinome = codinome;
		this.id = ID;
		//nextID++;
		this.sugestaoPresentes = new ArrayList<>(); 
		this.mensagensRecebidas = new ArrayList<>(); 
		this.mensagensEnviadas = new ArrayList<>(); 
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCodinome() {
		return codinome;
	}
	
	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}
	
	public int getId() {
		return id;
	}	
	
	public Participante getRemetente() {
		return remetente;
	}

	public void setRemetente(Participante remetente) {
		this.remetente = remetente;
	}

	public Participante getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Participante destinatario) {
		this.destinatario = destinatario;
	}

	public int quantMsgRecebidas() {
		return this.mensagensRecebidas.size();
	}
	
	public int quantMsgEnviadas() {
		return this.mensagensEnviadas.size();
	}

	public void mostraSugestaoPresentes() {
		for (String sugestao : sugestaoPresentes) {
			System.out.println(sugestao);
		}
	}

	public void adicionaSugestaoPresentes(String sugestao) {
		this.sugestaoPresentes.add(sugestao);
	}
	
	public void adicionaMensagemRecebida(Mensagem mensagemRecebida) {
		this.mensagensRecebidas.add(mensagemRecebida);
	}

	public void adicionaMensagemEnviada(Mensagem mensagemEnviada) {
		this.mensagensEnviadas.add(mensagemEnviada);
	}
	
	public List<Mensagem> getMensagensRecebidas(){
		if(mensagensRecebidas.size()>=1){
			return mensagensRecebidas;
		}
		return null;
	}
	
	public List<Mensagem> getMensagensEnviadas(){
		if(mensagensEnviadas.size()>=1){
			return mensagensEnviadas;
		}
		return null;
	}

	@Override
	public String toString() {
		return "ID: " + this.getId() + " NOME: " + this.getNome() + " CODINOME: " + this.getCodinome();
	}

	@Override
	public int compareTo(Participante outro) {															//compara por quantidade de mensagens recebidas
		if(this.mensagensRecebidas.size() > outro.mensagensRecebidas.size()) {						
			return -1;
		}
		return 0;
	}	
}
