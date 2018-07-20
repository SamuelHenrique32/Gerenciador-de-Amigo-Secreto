package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import modelo.Participante;

public class Sorteio implements Serializable{
	
	private String nome;	
	//private static int nextID = 1;
	private int id;
	private boolean isEncerrado = false;
	private List<Participante> participantes;
	private List<Participante> participantesmsgRecebida ;							//mantem ordem crescente de mensagem recebida
	private List<Participante> participantesmsgEnviada ;							//mantem ordem crescente de mensagem enviada		
	private List<String> avisosGerais;
	private List<SugestaoGeral> sugestoesGerais;
 	
	public Sorteio(String nome, int ID) {
		this.nome = nome;
		this.id = ID;
		//nextID++;
		this.participantes = new ArrayList<>();
		this.participantesmsgRecebida = new ArrayList<>();
		this.participantesmsgEnviada = new ArrayList<>();
		this.avisosGerais = new ArrayList<>();
		this.sugestoesGerais = new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public int getId() {
		return id;
	}

	public void menu() throws InterruptedException {	
		
		while(true){			
			voltaMenu();
			avancaLinhas();
			mostraMenu(isEncerrado);
			System.out.print("\nINFORME UMA OPCAO: ");		
			Scanner input = new Scanner(System.in);
			int opc = input.nextInt();
			while(verificaOpcao(opc)==0){
				avancaLinhas();
				System.out.println("OPCAO INVALIDA!");
				mostraMenu(isEncerrado);
				System.out.print("\nINFORME UMA OPCAO VALIDA: ");
				opc = input.nextInt();
			}			
			
			switch(opc){
				case(1): {
					avancaLinhas();
					System.out.println("########## CADASTRO DE PARTICIPANTE ##########\n");
					System.out.print("INFORME O NOME PARA O CADASTRO: ");
					Scanner scan = new Scanner(System.in);
					String nome = scan.nextLine();
					System.out.print("INFORME O CODINOME DO PARTICIPANTE " + nome + ": ");
					String codinome = scan.nextLine();					
					participantes.add(new Participante(nome, codinome, participantes.size()+1));
					System.out.println("\nO PARTICIPANTE FOI CADASTRADO COM SUCESSO!\n");
					
					break;
				}
				
				case(2): {
					avancaLinhas();
					System.out.println("########## CADASTRA SUGESTAO DE PRESENTE ##########\n");
					exibeParticipantes(participantes);
					System.out.print("\nINFORMA O ID DE UM PARTICIPANTE: ");
					Scanner scan = new Scanner(System.in);
					int id = scan.nextInt();
					while(verificaParticipante(id, participantes) == null){
						avancaLinhas();
						System.out.println("PARTICIPANTE INVALIDO!\n");
						exibeParticipantes(participantes);
						System.out.print("\nINFORME UM PARTICIPANTE VALIDO: ");
						id = scan.nextInt();						
					}
					avancaLinhas();
					Participante p = verificaParticipante(id, participantes);
					System.out.print("INFORME A SUGESTAO DE PRESENTE DESEJADA: ");
					Scanner scan2 = new Scanner(System.in);
					String sugestao = scan2.nextLine();
					p.adicionaSugestaoPresentes(sugestao);		
					System.out.println("\nA SUGESTAO FOI CADASTRADA COM SUCESSO!\n");
					
					break;					
				}
				
				case(3): {
					avancaLinhas();
					System.out.println("########## REALIZA SORTEIO ##########\n");
					sorteia(participantes);
					System.out.println("\nAGUARDE ENQUANTO O SORTEIO E REALIZADO...\n");
					//Thread.sleep(5000);
					System.out.println("\nO SORTEIO FOI REALIZADO COM SUCESSO!\n");

					break;
				}
				
				case(4): {
					
					avancaLinhas();
					System.out.println("########## CADASTRA MENSAGEM ##########\n");
					exibeParticipantes(participantes);
					System.out.print("\nINFORMA O ID DO REMETENTE: ");
					Scanner scan = new Scanner(System.in);
					int id = scan.nextInt();
					while(verificaParticipante(id, participantes) == null){
						avancaLinhas();
						System.out.println("PARTICIPANTE INVALIDO!\n");
						exibeParticipantes(participantes);
						System.out.print("\nINFORME UM PARTICIPANTE VALIDO: ");
						id = scan.nextInt();						
					}
					avancaLinhas();
					Participante remetente = verificaParticipante(id, participantes);															//pega referencia do remetente
					
					System.out.print("INFORME A MENSAGEM: ");
					Scanner scan2 = new Scanner(System.in);
					String mensagem = scan2.nextLine();
					avancaLinhas();
					exibeParticipantes(participantes);
					System.out.print("\nINFORMA O ID DO DESTINATARIO: ");
					id = scan.nextInt();
					while(verificaParticipante(id, participantes) == null){
						avancaLinhas();
						System.out.println("PARTICIPANTE INVALIDO!\n");
						exibeParticipantes(participantes);
						System.out.print("\nINFORME UM PARTICIPANTE VALIDO: ");
						id = scan.nextInt();						
					}
					avancaLinhas();
					Participante destinatario = verificaParticipante(id, participantes);														//pega referencia do destinatario					
					
					remetente.adicionaMensagemEnviada(new Mensagem(mensagem, remetente, destinatario));											//adiciona mensagem as enviadas do remetente
					destinatario.adicionaMensagemRecebida(new Mensagem(mensagem, remetente, destinatario));										//adiciona mensagem as recebidas do destinatario					
					
					System.out.println("\nMENSAGEM CADASTRADA COM SUCESSO!\n");
					
					break;
				}
				
				case(5): {
					
					avancaLinhas();
					System.out.println("########## EXIBE MENSAGENS ##########\n");
					exibeParticipantes(participantes);
					System.out.print("\nINFORMA O ID DE UM PARTICIPANTE: ");
					Scanner scan = new Scanner(System.in);
					int id = scan.nextInt();
					while(verificaParticipante(id, participantes) == null){
						avancaLinhas();
						System.out.println("PARTICIPANTE INVALIDO!\n");
						exibeParticipantes(participantes);
						System.out.print("\nINFORME UM PARTICIPANTE VALIDO: ");
						id = scan.nextInt();						
					}
					avancaLinhas();
					Participante p = verificaParticipante(id, participantes);										//pega referencia do participante
					
					System.out.println("MENSAGENS ENVIADAS:\n");
					List<Mensagem> mensagensEnviadas = p.getMensagensEnviadas();
					if(mensagensEnviadas!=null) {
						for (Mensagem m : mensagensEnviadas) {
							System.out.println(m.toString());
						}
					} else {
						System.out.println("NAO HA MENSAGENS ENVIADAS!\n");
					}
					
					System.out.println("\nMENSAGENS RECEBIDAS:\n");
					List<Mensagem> mensagensRecebidas = p.getMensagensRecebidas();
					if(mensagensRecebidas!=null) {
						for (Mensagem m : mensagensRecebidas) {
							System.out.println(m.toString());
						}
					} else {
						System.out.println("NAO HA MENSAGENS RECEBIDAS!\n");
					}
					System.out.println("\n");
					
					break;
				}
				
				case(6): {
					
					avancaLinhas();
					System.out.println("########## EXIBE SUGESTOES DE PRESENTES ##########\n");
					exibeParticipantes(participantes);
					System.out.print("\nINFORMA O ID DE UM PARTICIPANTE: ");
					Scanner scan = new Scanner(System.in);
					int id = scan.nextInt();
					while(verificaParticipante(id, participantes) == null){
						avancaLinhas();
						System.out.println("PARTICIPANTE INVALIDO!\n");
						exibeParticipantes(participantes);
						System.out.print("\nINFORME UM PARTICIPANTE VALIDO: ");
						id = scan.nextInt();						
					}
					avancaLinhas();
					Participante p = verificaParticipante(id, participantes);
					System.out.print("SUGESTOES CADASTRADAS:\n");
					p.mostraSugestaoPresentes();
					System.out.println();
					
					break;					
				}
				
				case(7): {
					avancaLinhas();
					System.out.println("########## CADASTRA SUGESTAO GERAL ##########\n");
					exibeParticipantes(participantes);
					System.out.print("\nINFORMA O ID DE UM PARTICIPANTE: ");
					Scanner scan = new Scanner(System.in);
					int id = scan.nextInt();
					while(verificaParticipante(id, participantes) == null){
						avancaLinhas();
						System.out.println("PARTICIPANTE INVALIDO!\n");
						exibeParticipantes(participantes);
						System.out.print("\nINFORME UM PARTICIPANTE VALIDO: ");
						id = scan.nextInt();						
					}
					avancaLinhas();
					Participante p = verificaParticipante(id, participantes);								//pega referencia do participante
					
					System.out.print("INFORME A SUGESTAO DO PARTICIPANTE " + p.getNome() + ": ");
					Scanner scan2 = new Scanner(System.in);
					String sugestao = scan2.nextLine();
					sugestoesGerais.add(new SugestaoGeral(p, sugestao));
					System.out.println("\nSUGESTAO CADASTRADA COM SUCESSO!\n");
					
					break;					
				}
				
				case(8): {
					avancaLinhas();
					System.out.println("########## SUGESTOES GERAIS CADASTRADAS ##########\n");
					for (SugestaoGeral sg : sugestoesGerais) {
						System.out.println(sg.toString());
					}
					System.out.println();
					
					break;
				}
								
				case(9): {																					//mensagens recebidas usa comparable e enviadas usa comparator
					avancaLinhas();
					System.out.println("########## ESTATISTICAS DE MENSAGENS ##########\n");
					estatisticaMSG();
					
					break;					
				}
				
				case(10): {
					avancaLinhas();
					System.out.println("########## EXIBE AMIGO SECRETO DE PARTICIPANTES ##########\n");
					for (Participante p : participantes) {
						System.out.println("Participante: " + p.getNome() + "\nRemetente: " + p.getRemetente().getNome() + "\nDestinatario: " + p.getDestinatario().getNome() + "\n");
					} 
					System.out.println();
					break;
				}
				
				case(11): {
					avancaLinhas();
					System.out.println("########## CADASTRA AVISO GERAL ##########\n");
					System.out.print("INFORME O AVISO: ");
					Scanner scan  = new Scanner(System.in);
					String sugestao = scan.nextLine();
					this.avisosGerais.add(sugestao);
					System.out.println("\nAVISO CADASTRADO COM SUCESSO!\n");
					
					break;
				}
				
				case(12): {
					avancaLinhas();
					System.out.println("########## AVISOS GERAIS CADASTRADOS ##########\n");
					for (String aviso : avisosGerais) {
						System.out.println(aviso);
					}
					System.out.println();
					break;
				}
				
				case(13): {
					avancaLinhas();
					exibeParticipantes(participantes);
					System.out.println();									
					break;
				}
				
				case(14): {
					avancaLinhas();
					isEncerrado = true;
					
					System.out.println("########## ENCERRAMENTO DO SORTEIO ##########\n");
					System.out.println("TOTAL DE MENSAGENS TROCADAS: " + contaMensagens(participantes) + "\n");
					
					System.out.println("########## ESTATISTICAS DE MENSAGENS ##########\n");
					estatisticaMSG();
					
					System.out.println("\n########## CODINOMES DE PARTICIPANTES ##########\n");
					exibeParticipantes(participantes);			
					
					System.out.println("\n########## AMIGO SECRETO DE PARTICIPANTES ##########\n");
					for (Participante p : participantes) {
						System.out.println("Participante: " + p.getNome() + "\nRemetente: " + p.getRemetente().getNome() + "\nDestinatario: " + p.getDestinatario().getNome() + "\n");
					} 
					System.out.println();					
					
					break;
				}
				
				case(0): {
					avancaLinhas();
					return;
				}
			}
		}	
	}

	private void estatisticaMSG() {
		participantesmsgRecebida = participantes;
		participantesmsgEnviada = participantes;
		
		Collections.sort(participantesmsgRecebida);
		System.out.println("ORDEM DESCENDENTE DE MENSAGEM RECEBIDA:\n");
		for (Participante p : participantesmsgRecebida) {
			System.out.println("Nome: " + p.getNome() + " possui " + p.quantMsgRecebidas() + " mensagens recebidas");
		}
		System.out.println();
		
		MensagemEnviadaComparator comparator = new MensagemEnviadaComparator();
		Collections.sort(participantesmsgEnviada, comparator);
		System.out.println("ORDEM DESCENDENTE DE MENSAGEM ENVIADA:\n");
		for (Participante p : participantesmsgEnviada) {
			System.out.println("Nome: " + p.getNome() + " possui " + p.quantMsgEnviadas() + " mensagens enviadas");
		}
		System.out.println();
	}

	private int contaMensagens(List<Participante> participantes) {
		int quantMSG = 0;
		for (Participante p : participantes) {
			if(p.getMensagensEnviadas()!=null) {
				quantMSG+=p.getMensagensEnviadas().size();
			}			
		}
		return quantMSG;
	}

	private void sorteia(List<Participante> participantes) {
		for (Participante p : participantes) {
			Random rand = new Random(participantes.size());
			int rand_id = rand.nextInt((participantes.size() - 1) + 1) + 1;												//rand.nextInt((maximo - minimo) + 1) + minimo;
			
			if(verificaParticipante(rand_id, participantes).getRemetente()==null && rand_id!=p.getId()) {				//participante com remetente nulo e diferente do atual no laco
				Participante destinatario = verificaParticipante(rand_id, participantes);
				destinatario.setRemetente(p);
				p.setDestinatario(destinatario);
			} else {
				while(verificaParticipante(rand_id, participantes).getRemetente()!=null || rand_id==p.getId()) {		//enquanto participante gerado possui remetente nao nulo ou for o mesmo que o atual
					rand_id = rand.nextInt((participantes.size() - 1) + 1) + 1;					
				}	
				Participante destinatario = verificaParticipante(rand_id, participantes);
				destinatario.setRemetente(p);
				p.setDestinatario(destinatario);
			}			
	   }
	}

	private static Participante verificaParticipante(int id, List<Participante> participantes) {
		
		for (Participante p : participantes) {
			if(p.getId() == id){
				return p;
			}
		}
		return null;
	}
	
	private static void exibeParticipantes(List<Participante> participantes) {
		System.out.println("PARTICIPANTES DISPONIVEIS:\n");
		for (Participante p : participantes) {
			System.out.println(p.toString());
		}
	}
	
	private static void voltaMenu() {
		System.out.println("APERTE ENTER PARA IR AO MENU");
		Scanner scan = new Scanner(System.in);
		String aux = scan.nextLine();
		return;		
	}
	
	private static int verificaOpcao(int opc) {
		if(opc >= 0 && opc <= 14){
			return 1;
		}
		return 0;
	}
	
	private static void mostraMenu(boolean isEncerrado) {
		
		if(isEncerrado==false) {
			System.out.println("\nOPCOES DISPONIVEIS:\n");
			System.out.println("0- VOLTA MENU PRINCIPAL");
			System.out.println("1- CADASTRA PARTICIPANTE");
			System.out.println("2- CADASTRA SUGESTAO DE PRESENTE");
			System.out.println("3- REALIZA SORTEIO");
			System.out.println("4- CADASTRA MENSAGEM");
			System.out.println("5- MOSTRA MENSAGENS");
			System.out.println("6- EXIBE SUGESTOES DE PRESENTE");
			System.out.println("7- CADASTRA SUGESTOES GERAIS");
			System.out.println("8- MOSTRA SUGESTOES GERAIS");
			System.out.println("9- ESTATISTICAS DE MENSAGENS");
			System.out.println("10- EXIBE AMIGO SECRETO DE PARTICIPANTE");
			System.out.println("11- CADASTRA AVISO GERAL");
			System.out.println("12- MOSTRA AVISOS GERAIS");		
			System.out.println("13- EXIBE PARTICIPANTES");
			System.out.println("14- ENCERRA AMIGO SECRETO");
		} else {
			System.out.println("\nO SORTEIO ATUAL FOI ENCERRADO!\n");
			System.out.println("\nOPCOES DISPONIVEIS:\n");
			System.out.println("0- VOLTA MENU PRINCIPAL");
			System.out.println("5- MOSTRA MENSAGENS");
			System.out.println("6- EXIBE SUGESTOES DE PRESENTE");
			System.out.println("8- MOSTRA SUGESTOES GERAIS");
			System.out.println("9- ESTATISTICAS DE MENSAGENS");
			System.out.println("10- EXIBE AMIGO SECRETO DE PARTICIPANTE");
			System.out.println("12- MOSTRA AVISOS GERAIS");		
			System.out.println("13- EXIBE PARTICIPANTES");
		}		
	}
	
	private static void avancaLinhas(){
		for(int i=0 ; i<100 ; i++){
			System.out.println();
		}
	}
}
