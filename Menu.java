import java.util.*;

public class Menu {

	public Scanner scan = new Scanner( System.in );	

	public static void clean(){

		final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();

	}
    
    // Método para ler opção numérica (inteira) digitada pelo usuário. Se usuário digitar um valor diferente do esperado, trata como exceção
    // Parâmetros low e high determinam faixa de valores possíveis para entrada do usuário
    // Retorna opção digitada
    public static int getWishedOption ( int low, int high ){
        
        Scanner input = new Scanner (System.in);
        int inputInt = 0;
        
        // Permance false enquanto usuário não digitar uma opção válida
        boolean valid = false;
        
        while ( !valid ){
            
            try {
                inputInt = input.nextInt();
            
            }
            
            // Caso o usuário não tenha digitado um int
            catch (InputMismatchException e){
                
                System.out.println ("O valor digitado não é um inteiro. Por favor, digite um número entre " + low + " e " + high );
                input.nextLine(); // Para limpar o que foi digitado e não é um número, permitindo a leitura de um novo inteiro
                continue;
            }
            
            // Caso o usuário tenha digitado um inteiro fora da faixa especificada pelo programa
            if ( inputInt < low || inputInt > high ){
            
                System.out.println ("O valor digitado não está na faixa de valores possíveis. Por favor, digite um número entre " + low + " e " + high );
                continue;
            }
            
            valid = true;
        }
        
        return inputInt;
    }

	public static void mainMenu() {

		int cmd=0;

		while (cmd!=-1) {

			System.out.println("||==========================================||");	
			System.out.println("||Comandos disponíveis para o jogo:         ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| 0 - Configurar personagens               ||");
			System.out.println("|| 1 - Configurar times                     ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Sair                                ||");
			System.out.println("||==========================================||");


			cmd = getWishedOption( -1, 1 );
			clean();

			switch (cmd) {
				case 0:
					CharacterMenu();
					break;
				case 1:
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

		int cmd=0;

		while (cmd!=-1) {

			System.out.println("||==========================================||");
			System.out.println("|| Comandos disponíveis para um personagem: ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| 0 - Criar novo personagem                ||");
			System.out.println("|| 1 - Selecionar personagem                ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");
			
			cmd = getWishedOption ( -1, 1 );
			clean();

			switch (cmd) {
				case 0:
					createCharacterMenu();
					break;
				case 1:
					selectCharacterMenu();
					break;
			}
		}
	}

	public static void createCharacterMenu() {

		int cmd=0;

		while (cmd!=-1) {

			System.out.println("||==========================================||");
			System.out.println("||Selecione o tipo de personagem:           ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| 0 - Knight                               ||");
			System.out.println("|| 1 - Wizard                               ||");
			System.out.println("|| 2 - Thief                                ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");						
			
			cmd = getWishedOption (-1, 2);
			clean();

			switch (cmd) {
				case 0:
					mainProgram.createKnight();
					break;
				case 1:
					mainProgram.createWizard();
					break;
				case 2:
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
			System.out.println("|| 0 - Imprimir atributos                   ||");
			System.out.println("|| 1 - Imprimir inventário                  ||");
			System.out.println("|| 2 - Ir a uma loja (vender/comprar itens) ||");
			System.out.println("|| 3 - Equipar/Usar itens                   ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");			

			cmd = getWishedOption( -1, 3 );
			clean();

			switch (cmd) {
				case 0:
					mainProgram.printInfo(ch);
					break;
				case 1:
					if (ch.getInventory() == null || ch.getInventory().getSpaces()==ch.getInventory().getAvailableSpace()) {
						System.out.println("PERSONAGEM NÃO POSSUI ITENS !");
					}
					else {
						mainProgram.printInfo(ch.getInventory()); 
					}
					break;
				case 2:
					ShoppingMenu(ch);
					break;
				case 3:
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

		int cmd=0;

		System.out.println("||==========================================||");
		System.out.println("||Selecione uma das opções:                 ||");
		System.out.println("||__________________________________________||");
		System.out.println("|| 0 - Loja de Armas                        ||");
		System.out.println("|| 1 - Loja de Armaduras                    ||");
		System.out.println("|| 2 - Loja de Poções                       ||");
		System.out.println("||__________________________________________||");
		System.out.println("|| -1 - Voltar                              ||");
		System.out.println("||==========================================||");	

		cmd = getWishedOption( -1, 2 );
		clean();

		switch(cmd) {
			case 0:
				mainProgram.weaponShopping(ch);
				break; 
			case 1:
				mainProgram.armorShopping(ch);
				break;
			case 2:
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

		while(cmd!=-1)
		{
			System.out.println("||==========================================||");
			System.out.println("||Selecione uma das opções:                 ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| 0 - Criar time                           ||");
			System.out.println("|| 1 - Selecionar time                      ||");
			System.out.println("|| 2 - Iniciar batalha                      ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");

			cmd = getWishedOption( -1, 2 );
			clean();

			switch (cmd) {
				case 0:
					mainProgram.createTeam();
					break;
				case 1:
					selectTeamMenu();
					break;
				case 2:
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
			System.out.println("|| 0 - Listar informações de um time        ||");
			System.out.println("|| 1 - Adicionar personagens                ||");
			System.out.println("|| 2 - Remover personagens                  ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");	

			cmd = getWishedOption ( -1, 2 );
			clean();

			switch(cmd) {
				case 0:
					mainProgram.listTeam(tm);
					break;
				case 1:
					mainProgram.addCharTeam(tm);
					break;
				case 2:
					mainProgram.removeCharTeam(tm);
					break;
			}
		}
	}
}