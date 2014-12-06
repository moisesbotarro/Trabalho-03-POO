import java.util.*;

public class Menu {

	public Scanner scan = new Scanner( System.in );	

	public static void clean(){

		final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();

	}

	public static void mainMenu() {

		Scanner scan = new Scanner( System.in );	

		int cmd=0;

		while (cmd!=-1) {

			System.out.println("||==========================================||");	
			System.out.println("||Comandos disponíveis para o jogo:         ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| 1 - Configurar personagens               ||"); 
			System.out.println("|| 2 - Configurar times                     ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Sair                                ||");
			System.out.println("||==========================================||");


			cmd = scan.nextInt();
			clean();

			switch (cmd) {
				case 1:
					CharacterMenu();
					break;
				case 2:
					TeamMenu();
					break;
				case -1:
					System.exit(0);
					break;
			}
		}
		return;	
	}

	public static void CharacterMenu() {

		Scanner scan = new Scanner( System.in );	

		int cmd=0;

		while (cmd!=-1) {

			System.out.println("||==========================================||");
			System.out.println("|| Comandos disponíveis para um personagem: ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| 1 - Criar novo personagem                ||");
			System.out.println("|| 2 - Selecionar personagem                ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");
			
			cmd = scan.nextInt();
			clean();

			switch (cmd) {
				case 1:
					createCharacterMenu();
					break;
				case 2:
					selectCharacterMenu();
					break;
			}
		}
	}

	public static void createCharacterMenu() {

		Scanner scan = new Scanner( System.in );	

		int cmd=0;

		while (cmd!=-1) {

			System.out.println("||==========================================||");
			System.out.println("||Selecione o tipo de personagem:           ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| 1 - Knight                               ||");
			System.out.println("|| 2 - Wizard                               ||");
			System.out.println("|| 3 - Thief                                ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");						
			
			cmd = scan.nextInt();
			clean();

			switch (cmd) {
				case 1:
					mainProgram.createKnight();
					break;
				case 2:
					mainProgram.createWizard();
					break;
				case 3:
					mainProgram.createThief();
					break;
			}
		}
	}

	public static void selectCharacterMenu() {

		Scanner scan = new Scanner( System.in );			

		int cmd=0;

			System.out.println("||==========================================||");
			System.out.println("Selecione um dos personagens abaixo:");
			String name;
			do {
				name = scan.nextLine();
			} while(name.equals(""));
			System.out.println("||==========================================||");
			clean();
			
			GameCharacter ch = mainProgram.searchAvatar(name);
			
			if (ch==null) {
				System.out.println("ERRO - PERSONAGEM NÃO EXISTE !");
				return;
			}

			clean();
			while (cmd!=-1) {
			
			System.out.println(">>Personagem Selecionado: " + name + "\n");
			System.out.println("||==========================================||");
			System.out.println("||Selecione uma das opções:                 ||");
			System.out.println("||__________________________________________||");			
			System.out.println("|| 1 - Imprimir atributos                   ||");
			System.out.println("|| 2 - Imprimir inventário                  ||");
			System.out.println("|| 3 - Ir a uma loja (vender/comprar itens) ||");
			System.out.println("|| 4 - Equipar/Usar itens                   ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");			

			cmd = scan.nextInt();
			clean();

			switch (cmd) {
				case 1:
					mainProgram.printInfo(ch);
					break;
				case 2:
					if (ch.getInventory() == null || ch.getInventory().getSpaces()==ch.getInventory().getAvailableSpace()) {
						System.out.println("PERSONAGEM NÃO POSSUI ITENS !");
					}
					else {
						mainProgram.printInfo(ch.getInventory()); 
					}
					break;
				case 3:
					ShoppingMenu(ch);
					break;
				case 4:
					EquipUseMenu(ch);
					if (ch.getInventory() == null) {
						System.out.println("PERSONAGEM NÃO POSSUI ITENS !");
						return;
					}
					break;
			}
		}
	}

	public static void ShoppingMenu(GameCharacter ch) {

		Scanner scan = new Scanner( System.in );			

		int cmd=0;

		System.out.println("||==========================================||");
		System.out.println("||Selecione uma das opções:                 ||");
		System.out.println("||__________________________________________||");
		System.out.println("|| 1 - Loja de Armas                        ||");		
		System.out.println("|| 2 - Loja de Armaduras                    ||");		
		System.out.println("|| 3 - Loja de Poções                       ||");
		System.out.println("||__________________________________________||");
		System.out.println("|| -1 - Voltar                              ||");
		System.out.println("||==========================================||");	

		cmd = scan.nextInt();
		clean();

		switch(cmd) {
			case 1:
				mainProgram.weaponShopping(ch);
				break; 
			case 2:
				mainProgram.armorShopping(ch);
				break;
			case 3:
				mainProgram.potionShopping(ch);
				break;
		}


	}

	public static void EquipUseMenu(GameCharacter aux) {

		Scanner scan = new Scanner( System.in );
		String item_name;	

		System.out.println ("Digite o nome do item a ser equipado");
		do {
			item_name = scan.nextLine();
		} while(item_name.equals(""));

		mainProgram.EquipItem(aux, item_name);
		clean();
	}

	public static void TeamMenu () {

		int cmd=0;

		Scanner scan = new Scanner( System.in );	

		while(cmd!=-1)
		{
			System.out.println("||==========================================||");
			System.out.println("||Selecione uma das opções:                 ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| 1 - Criar time                           ||");
			System.out.println("|| 2 - Selecionar time                      ||");
			System.out.println("|| 3 - Iniciar batalha                      ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");

			cmd = scan.nextInt();
			clean();

			switch (cmd) {
				case 1:
					mainProgram.createTeam();
					break;
				case 2:
					selectTeamMenu();
					break;
				case 3:
					mainProgram.battle();
					break;
			}
		}						
	}

	public static void selectTeamMenu() {

		Scanner scan = new Scanner( System.in );
		String team_name;	
		int cmd=0;

		System.out.println ("Digite o nome do time:");
		do {
			team_name = scan.nextLine();
		} while(team_name.equals(""));

		Team tm = mainProgram.searchTeam(team_name);
			
		if (tm==null) {
			System.out.println("ERRO - TIME NÃO EXISTE !");
			return;
		}

		clean();

		while (cmd!=-1) {

			System.out.println(">>Time Selecionado: " + team_name + "\n");
			System.out.println("||==========================================||");
			System.out.println("||Selecione uma das opções:                 ||");
			System.out.println("||__________________________________________||");			
			System.out.println("|| 1 - Listar informações de um time        ||");
			System.out.println("|| 2 - Adicionar personagens                ||");
			System.out.println("|| 3 - Remover personagens                  ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");	

			cmd = scan.nextInt();
			clean();

			switch(cmd) {
				case 1:
					mainProgram.listTeam(tm);
					break;
				case 2:
					mainProgram.addCharTeam(tm);
					break;
				case 3:
					mainProgram.removeCharTeam(tm);
					break;
			}
		}
	}
}