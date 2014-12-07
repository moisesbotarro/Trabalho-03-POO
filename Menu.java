import java.util.*;

public class Menu {

	public Scanner scan = new Scanner( System.in );	


	//Método ultilizado para limpar a tela do terminal
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

    //Imprime o menu principal do jogo
	public static void mainMenu() {

		//variável para ver o comando que o usuário deseja
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

			//verifica se o usuário entrou com uma opção válida.
			cmd = getWishedOption( -1, 1 );
			clean();

			//Verifica qual a opção selecionada
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

	//Menu de opções para personagens
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
					//Vai para o menu de criação de personagem
					createCharacterMenu();
					break;
				case 1:
					//Vai para o menu de seleção de personagem
					selectCharacterMenu();
					break;
			}
		}
	}

	//
	public static void createCharacterMenu() {

		int cmd=0;

		while (cmd!=-1) {

			//Exibe os tipos de personagens possíveis para criação
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
					//Vai para a função de criação de Knight, na classe mainProgram
					mainProgram.createKnight();
					break;
				case 1:
					//Vai para a função de criação de Wizard, na classe mainProgram
					mainProgram.createWizard();
					break;
				case 2:
					//Vai para a função de criação de Thief, na classe mainProgram
					mainProgram.createThief();
					break;
			}
		}
	}

	//Menu para selecionar um personagem
	public static void selectCharacterMenu() {

		//Scanner necessário para E/S
		Scanner scan = new Scanner( System.in );			

		int cmd=0;
		int index=0;

			System.out.println("||==========================================||");
			System.out.println("Selecione um dos personagens abaixo: ");

			if (mainProgram.getAvatars().size()==0)
			{
				System.out.println("ERRO - NÃO EXISTEM MAIS PERSONAGENS");
				return;
			}

			mainProgram.printAllAvatars();

			index = getWishedOption (0, mainProgram.getAvatars().size()-1);

			clean();

			//Uma vez verificada a existência de um personagem, podemos continuar com a função
			//Caso o personagem não exista, o último If apresentará o erro.

			while (cmd!=-1) {
			
				//Menu para um personagem selecionado
				System.out.println(">>Personagem Selecionado: " + mainProgram.getAvatars().get(index).getName() + "\n");
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

				//Opções para a seleção de um personagem
				switch (cmd) {
					case 0:
						//Chama a função printInfo da classe mainProgram, que imprime as características de um personagem
						mainProgram.printInfo(mainProgram.getAvatars().get(index));
						break;
					case 1:
						//Imprime o inventário de um personagem. Porém, o personagem deve ter um inventário com no mínimo um item
						//Portanto verificamos se o número de espaços livres é igual ao número de espaços totais.
						if (mainProgram.getAvatars().get(index).getInventory().getSpaces()==mainProgram.getAvatars().get(index).getInventory().getAvailableSpace()) {
							System.out.println("PERSONAGEM NÃO POSSUI ITENS !");
						}
						//Caso o inventário não esteja vazio, chama-se a função printInfo da classe mainProgram, que imprime o inventário
						else {
							mainProgram.printInfo(mainProgram.getAvatars().get(index).getInventory()); 
						}
						break;
					case 2:
						//Abre o menu de opções para compras
						ShoppingMenu(mainProgram.getAvatars().get(index));
						break;
					case 3:
						//Abre o menu para equipar itens. Antes verifica se o inventário não está vazio.
						if (mainProgram.getAvatars().get(index).getInventory().getSpaces()==mainProgram.getAvatars().get(index).getInventory().getAvailableSpace()) {
							System.out.println("PERSONAGEM NÃO POSSUI ITENS !");
							return;
						}
						EquipUseMenu(mainProgram.getAvatars().get(index));
						break;
				}
		}
	}

	//Menu de opções para compras e vendas (deve receber um personagem como parâmetro)
	public static void ShoppingMenu(GameCharacter ch) {

		int cmd=0;

		//Opções de escolher a loja
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

		//Abre a função respectiva de cada loja. Todas na classe mainProgram. O personagem deve ser mandado como parâmetro
		switch(cmd) {
			case 0:
				//Loja de weapons
				mainProgram.weaponShopping(ch);
				break; 
			case 1:
				//Loja de armors
				mainProgram.armorShopping(ch);
				break;
			case 2:
				//Loja de potions
				mainProgram.potionShopping(ch);
				break;
		}
	}

	//Menu para equipar ou desequipar um item
	public static void EquipUseMenu(GameCharacter aux) {

		Scanner scan = new Scanner( System.in );
		int index;
		String item_name;

		System.out.println("Selecione um item do personagem: " + aux.getName());

		//Método imprime todos os nomes dos itens do inventário
		aux.getInventory().printIndex();
		index = getWishedOption( 0, aux.getInventory().getSpaces()-aux.getInventory().getAvailableSpace());

		//Chama uma função na classe mainProgram que equipa um item
		mainProgram.EquipItem(aux, aux.getInventory().searchItem(index).getName());
		clean();
	}


	//Menu para configurações de time
	public static void TeamMenu () {

		int cmd=0;

		while(cmd!=-1)
		{
			//Menu para seleção de opções
			System.out.println("||==========================================||");
			System.out.println("||Selecione uma das opções:                 ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| 0 - Criar time                           ||");
			System.out.println("|| 1 - Selecionar time                      ||");
			System.out.println("|| 2 - Iniciar batalha                      ||");
			System.out.println("||__________________________________________||");
			System.out.println("|| -1 - Voltar                              ||");
			System.out.println("||==========================================||");

			//Comandos devem variar de -1 a 2
			cmd = getWishedOption( -1, 2 );
			clean();

			switch (cmd) {
				case 0:
					//Chama função da classe mainProgram que cria um novo time
					mainProgram.createTeam();
					break;
				case 1:
					//Abre o menu de seleção de time
					selectTeamMenu();
					break;
				case 2:
					//Chama a função que inicia uma batalha entre dois times
					mainProgram.battle();
					break;
			}
		}						
	}

	//Menu para seleção de time
	public static void selectTeamMenu() {

		Scanner scan = new Scanner( System.in );
		String team_name;	
		int cmd=0, index=0;

		//Seleciona um dos times listados
		System.out.println("Selecione o time abaixo: ");
		
		if(mainProgram.getTeams().size()==0)
		{
			System.out.println("ERRO - NÃO EXISTEM TIMES PARA SEREM SELECIONADOS");
		}

		mainProgram.printAllTeams();

		index = getWishedOption( 0, mainProgram.getTeams().size()-1);

		clean();

		while (cmd!=-1) {

			//Menu de informações de um time
			System.out.println(">>Time Selecionado: " + mainProgram.getTeams().get(index).getName() + "\n");
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
					//Chama uma função da classe mainProgram que imprime os personagens de um time
					mainProgram.listTeam(mainProgram.getTeams().get(index));
					break;
				case 1:
					//Adiciona um personagem ao time
					mainProgram.addCharTeam(mainProgram.getTeams().get(index));
					break;
				case 2:
					//Remove um personagem do time
					mainProgram.removeCharTeam(mainProgram.getTeams().get(index));
					break;
			}
		}
	}
}