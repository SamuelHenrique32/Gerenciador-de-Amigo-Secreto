package principal;

import modelo.Mensagem;
import modelo.MensagemEnviadaComparator;
import modelo.Participante;
import modelo.Sorteio;
import serial.Deserializador;
import serial.Serializador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MainClass {	
	
	public static void main(String[] args) {		
		
		List<Sorteio> listaSorteios = new ArrayList<>(); 
		Serializador serializador = new Serializador();
		Deserializador deserializador = new Deserializador();	
		boolean initControl = true;
		
		System.out.println("########## BEM VINDO AO SAAS ##########\n");
		System.out.println("LOGADO COMO USUARIO ADMINISTRADOR\n");
		System.out.println("AGUARDE ENQUANTO OS ARQUIVOS SAO CARREGADOS...\n");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		try {
			listaSorteios = (List<Sorteio>) deserializador.deserializar("dados");
			System.out.println("ARQUIVOS CARREGADOS COM SUCESSO!\n");
		} catch (Exception ex) {
			System.out.println("O ARQUIVO DE INICIALIZACAO NAO FOI ENCONTRADO OU ESTA CORROMPIDO!\n");
		}
			
		while(true) {			
			voltaMenuPrincipal();			
			avancaLinhasPrincipal();
			mostraMenuPrincipal();
			System.out.print("\nINFORME UMA OPCAO: ");		
			Scanner input = new Scanner(System.in);
			int opc = input.nextInt();
			while(verificaOpcaoPrincipal(opc)==0){
				avancaLinhasPrincipal();
				System.out.println("OPCAO INVALIDA!");
				mostraMenuPrincipal();
				System.out.print("\nINFORME UMA OPCAO VALIDA: ");
				opc = input.nextInt();
			}
			
			switch(opc){
			
				case(1): {
					avancaLinhasPrincipal();
					System.out.println("########## CADASTRA SORTEIO ##########\n");
					System.out.print("INFORME O NOME DO SORTEIO: ");
					Scanner scan = new Scanner(System.in);
					String nomeSorteio = scan.nextLine();
					listaSorteios.add(new Sorteio(nomeSorteio, listaSorteios.size()+1));					
					System.out.println("\nSORTEIO CADASTRADO COM SUCESSO!\n");
					
					break;					
				}
				
				case(2): {
					avancaLinhasPrincipal();
					System.out.println("########## SORTEIOS CADASTRADOS: ##########\n");
					exibeSorteios(listaSorteios);
					System.out.print("\nINFORMA O ID DE UM SORTEIO: ");
					Scanner scan = new Scanner(System.in);
					int id = scan.nextInt();
					while(verificaSorteio(id, listaSorteios) == null){
						avancaLinhasPrincipal();
						System.out.println("SORTEIO INVALIDO!\n");
						exibeSorteios(listaSorteios);
						System.out.print("\nINFORME UM SORTEIO VALIDO: ");
						id = scan.nextInt();						
					}
					Sorteio s = verificaSorteio(id, listaSorteios);										//pega referencia para o sorteio
					System.out.println("\nSORTEIO " + s.getNome() + " SELECIONADO!\n");
					try {
						s.menu();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					break;					
				}		
				
				case(0): {
					avancaLinhasPrincipal();
					System.out.println("AGUARDE ENQUANTO OS ARQUIVOS SAO GRAVADOS...");
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					try {
						serializador.serializar("dados", listaSorteios);
						System.out.println("\nARQUIVOS GRAVADOS COM SUCESSO!");
					} catch (Exception ex) {
						System.out.println("\nFALHA AO SERIALIZAR!");
					}
					System.out.println("\nSISTEMA ENCERRADO\n");
					return;
				}
			}		
		}		
	}

	private static Sorteio verificaSorteio(int id, List<Sorteio> listaSorteios) {
		for (Sorteio s : listaSorteios) {
			if(s.getId()==id) {
				return s;
			}
		}
		return null;		
	}

	private static void exibeSorteios(List<Sorteio> listaSorteios) {
		for (Sorteio s : listaSorteios) {
			System.out.println(s.getId() + "- " + s.getNome());
		}	
	}

	private static int verificaOpcaoPrincipal(int opc) {		
		if(opc >= 0 && opc <= 2){
			return 1;
		}
		return 0;
	}

	private static void mostraMenuPrincipal() {
		System.out.println("\nOPCOES DISPONIVEIS:\n");
		System.out.println("0- SAIR");
		System.out.println("1- CADASTRAR SORTEIO");
		System.out.println("2- ENTRA MENU SORTEIO");		
	}

	private static void voltaMenuPrincipal() {
		System.out.println("APERTE ENTER PARA IR AO MENU PRINCIPAL");
		Scanner scan = new Scanner(System.in);
		String aux = scan.nextLine();
		return;			
	}
	
	private static void avancaLinhasPrincipal(){
		for(int i=0 ; i<100 ; i++){
			System.out.println();
		}
	}
}
