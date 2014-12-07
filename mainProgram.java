import java.util.*;

public class mainProgram {
	
	public static ArrayList<GameCharacter> avatars;
	public static ArrayList<Team> teams;
	public static ArrayList<Armor> ArmorList;
	public static ArrayList<Weapon> WeaponList;
	public static ArrayList<Potion> PotionList;
	public static WeaponShop weaponshop;
	public static ArmorShop armorshop;
	public static PotionShop potionshop; 


	//Metodo adiciona um personagem em um time
	public static void addCharTeam(Team tm) {

		Scanner scan = new Scanner( System.in );
		int index = 0;	
		System.out.println("Selecione o personagem a ser inserido: ");

		//Imprime todos os personagens que ainda NÃO estão em times
		printAllAvatars();

		index = Menu.getWishedOption ( 0, avatars.size()-1);

		//Adiciona personagem em time
		tm.addChar(avatars.get(index));
		//Remove personagem da lista avatars
		avatars.remove(index);
	}

	//Remove um personagem de um time
	public static void removeCharTeam(Team tm) {

		Scanner scan = new Scanner( System.in );
		int index=0;

		//Imprime personagens do time
		System.out.println("Selecione o personagem a ser removido");
		tm.printIndex();

		//Seleciona um dos personagens
		index = Menu.getWishedOption ( 0, avatars.size()-1);

		//Adiciona novamente na lista avatars (personagem se torna disponível para outro time), e remove do time
		avatars.add(tm.searchChar(index));
		tm.removeChar(index);

		Menu.clean();

	}	

	//Imprime as informações de um determinado time
	public static void listTeam(Team tm) {

		tm.print();
	}

	//Procura por um time a partir do nome
	public static Team searchTeam(String name) {

		for(int i=0; i<teams.size(); i++)
		{
			if (teams.get(i).getName().equals(name)==true)
			{
				return teams.get(i);
			}
		}
		return null;
	}

	//Método que cria um novo time
	public static void createTeam() {

		int cmd;
		String team_name;
		Scanner scan = new Scanner( System.in );

		System.out.println("Digite o nome do novo time: ");
		do {
			team_name = scan.nextLine();
		} while(team_name.equals(""));
		
		//Selecionar cor do time
		System.out.println("Selecione a cor do time: ");
		System.out.println(" 1 - Blue");
		System.out.println(" 2 - Red");
		System.out.println(" 3 - Green");
		System.out.println(" 4 - Yellow");
		System.out.println(" 5 - White");
		System.out.println(" 6 - Black");

		cmd = Menu.getWishedOption( 1, 6 );

		Team tm;

		//Define cor do time
		switch (cmd) {
			case 1:
				tm = new Team(team_name, Color.BLUE);
				teams.add(tm);
				break;
			case 2:
				tm = new Team(team_name, Color.RED);
				teams.add(tm);
				break;
			case 3:
				tm = new Team(team_name, Color.GREEN);
				teams.add(tm);
				break;
			case 4:
				tm = new Team(team_name, Color.YELLOW);
				teams.add(tm);
				break;
			case 5:
				tm = new Team(team_name, Color.WHITE);
				teams.add(tm);
				break;
			case 6:
				tm = new Team(team_name, Color.BLACK);
				teams.add(tm);
				break;
		}
		Menu.clean();			
	}

	//Métod procura por um determinado personagem na list avatars a partir do nome
	public static GameCharacter searchAvatar(String name)
	{
		for (int i =0; i<avatars.size(); i++)
		{
			if (avatars.get(i).getName().equals(name)==true)
			{
				return avatars.get(i);
			}
		}
		return null;	
	}	


	//Cria um personagem do tipo Knight
	public static void createKnight() {

		Scanner scan = new Scanner( System.in );
		String name;

		System.out.println("||===============================================||");
		System.out.println("Digite os valores para os seguintes atributos: ");
		System.out.print(" -> Nome: ");
		do {
			name = scan.nextLine();
		} while(name.equals(""));
		
		System.out.print(" -> Power: ");		
		int power = Menu.getWishedOption( 1, 100 );
		Knight aux = new Knight(name, power);
		//Método que define atributos comuns a todos os tipos de personagens
		defineAtributes(aux);
		//Adiciona peronagem na list avatars
		avatars.add(aux);
	}

	//Cria um personagem do tipo Wizard
	public static void createWizard() {

		Scanner scan = new Scanner( System.in );
		String name;

		System.out.println("||===============================================||");
		System.out.println("Digite os valores para os seguintes atributos:");
		System.out.print(" -> Nome: ");
		do {
			name = scan.nextLine();
		} while(name.equals(""));
		
		System.out.print(" -> Wisdom: ");		
		int wisdom = Menu.getWishedOption( 1, 100 );
		Wizard aux = new Wizard(name, wisdom);
		//Método que define atributos comuns a todos os tipos de personagens
		defineAtributes(aux);
		//Adiciona peronagem na lista avatars
		avatars.add(aux);
	}

	//Cria um personagem do tipo Thief
	public static void createThief() {

		Scanner scan = new Scanner( System.in );
		String name;

		System.out.println("||===============================================||");
		System.out.println("Digite os valores para os seguintes atributos:");
		System.out.print(" -> Nome: ");
		do {
			name = scan.nextLine();
		} while(name.equals(""));

		System.out.print(" -> Stealth: ");		
		int stealth = Menu.getWishedOption( 1, 100 );
		Thief aux = new Thief(name, stealth);
		//Método que define atributos comuns a todos os tipos de personagens
		defineAtributes(aux);
		//Adiciona peronagem na lista avatars
		avatars.add(aux);
	}

	//Equipa um item com base no personagem e no nome do item
	public static void EquipItem(GameCharacter aux, String name) {

		aux.equipItem(name);
	}	

	//Imprime todos os times do jogo com índice
	public static void printAllTeams() {

		int i=0;

		for (Team team : teams) {
			
			System.out.println(" " + i + " - " + team.getName());
			i++;
		}

	}

	//Imprime todos os personagens presentes na 
	public static void printAllAvatars() {

		int i=0;

		for (GameCharacter ch : avatars) {
			
			System.out.println(" " + i + " - " + ch.getName());
			i++;
		}

	}


	//Define atributos comuns a todos os tipos de personagens (speed, dexterity, constitution, strenght...)
	public static void defineAtributes (GameCharacter aux) {

		Scanner scan = new Scanner( System.in );

        int remainingPoints = aux.getRemainingPoints();
        System.out.println ( "\nQuantidade de pontos disponíveis para ser distribuído: " + (remainingPoints - 3) );
        System.out.println ( "\nAinda há mais 3 atributos para serem setados. Casso digite essa quantidade total de pontos, os demais atributos receberão 1." );
		System.out.print(" -> Strenght: ");
		int strength = Menu.getWishedOption ( 1, remainingPoints - 3 );
		aux.setStrength(strength);
        
        // Caso usuário tenha digitado a quantidade máxima de pontos dispóveis para strength, redistribui os 3 pontos restantes nos demais atributos
        if ( strength == (remainingPoints-3) ){
            
            aux.setDexterity( 1 );
            aux.setConstitution( 1 );
            aux.setSpeed( 1 );
            
            System.out.println ( "Dexterity, Constitution e Speed inicializados em 1\n");
        }
        
        else{
        
            remainingPoints = aux.getRemainingPoints();
            System.out.println ("\nQuantidade de pontos disponíveis para ser distribuído: " + (remainingPoints -2)  );
            System.out.println ( "\nAinda há mais 2 atributos para serem setados. Casso digite essa quantidade total de pontos, os demais atributos receberão 1." );
            System.out.print(" -> Dexterity: ");
            int dexterity = Menu.getWishedOption ( 1, remainingPoints - 2 );
            aux.setDexterity(dexterity);
            
            // Caso usuário tenha digitado a quantidade máxima de pontos dispóveis para dexterity, redistribui os 2 pontos restantes nos demais atributos
            if ( dexterity == (remainingPoints-2) ){
                
                aux.setConstitution( 1 );
                aux.setSpeed( 1 );
                
                System.out.println ( "Constitution e Speed inicializados em 1\n");
            }

            else {
            
                remainingPoints = aux.getRemainingPoints();
                System.out.println ("\nQuantidade de pontos disponíveis para ser distribuído: " + (remainingPoints -1) );
                System.out.println ( "\nAinda há mais 1 atributo para ser setado. Casso digite essa quantidade total de pontos, speed será inicializado em 1." );
                System.out.print(" -> Constitution: ");
                int constitution = Menu.getWishedOption ( 1, remainingPoints - 1);
                aux.setConstitution(constitution);		
            
                // Caso usuário tenha digitado a quantidade máxima de pontos dispóveis para Constitution, redistribui o ponto restante em speed
                if ( constitution == (remainingPoints-1) ){
                    
                    aux.setSpeed( 1 );
                    
                    System.out.println ( "Speed inicializado em 1\n");
                }
                
                else{
            
                    System.out.println ("\nQuantidade de pontos disponíveis para ser distribuído: " + aux.getRemainingPoints() );
                    System.out.print(" -> Speed: ");
                    int speed = Menu.getWishedOption ( 1, aux.getRemainingPoints());
                    aux.setSpeed(speed);
                    
                }
            }
        }
        
		System.out.print(" -> Gold:");
		int gold = Menu.getWishedOption ( 0, 999999);
		aux.getInventory().earnGold(gold);

		System.out.print(" -> Spaces:");
		int spaces = Menu.getWishedOption ( 0, 999);
		aux.getInventory().setSpaces(spaces);
		Menu.clean();
	} 

	//Chama a função print de varios tipos da interface Printable
	public static void printInfo(Printable aux) {

		aux.print();
		return;
	}

	//Chama o método que abre a loja de weapons
	public static void weaponShopping(GameCharacter ch) {

		weaponshop.goShopping(ch);
	}

	//Chama o método que abre a loja de armors
	public static void armorShopping(GameCharacter ch) {

		armorshop.goShopping(ch);
	}

	//chama o metodo que abre a loja de potions
	public static void potionShopping(GameCharacter ch) {

		potionshop.goShopping(ch);
	}

	//Inicia uma batalha entre dois times
	public static void battle () {

		Scanner scan = new Scanner( System.in );
		String team_name1, team_name2;
		int index1, index2;

		if(teams.size()<2)
		{
			System.out.println("ERRO - NUMERO DE TIMES INSUFICIENTE");
			return;
		}

		System.out.println("Selecione primeiro time da batalha: ");
		
		//Imprime lista dos times para a batalha (time 1)
		for (int i=0; i<teams.size(); i++)
		{
			System.out.println( i + " - " + teams.get(i).getName());
		}

		index1 = scan.nextInt();

		if (index1<0 || index1>teams.size())
		{
			System.out.println ("ERRO - SELEÇÃO INVÁLIDA DE TIME");
			return;
		}

		//Imprime lista dos times para a batalha (time 2)
		for (int i=0; i<teams.size(); i++)
		{
			if(i!=index1) {
				System.out.println( i + " - " + teams.get(i).getName());
			}
		}

		index2 = scan.nextInt();

		if (index2<0 || index2>teams.size())
		{
			System.out.println ("ERRO - SELEÇÃO INVÁLIDA DE TIME");
			return;
		}

		//Inicia a batalha entre times
		startBattle(teams.get(index1), teams.get(index2));

	}

	//Função cria itens para as lojas. 
	public static void fillLists() {

		Armor ar1, ar2, ar3, ar4, ar5;
		Weapon we1, we2, we3, we4, we5;
		HealthPotion hp1, hp2, hp3, hp4, hp5;

		/*CONSTRUINDO ARMADURAS*/
		ar1 = new Armor("Ancient Nord Armor", 125, 1, 15);
		ar2 = new Armor("Ebony Armor", 1500, 1, 9);
		ar3 = new Armor("Daedric Armor", 3200, 1, 9);
		ar4 = new Armor("Dragonplate Armor", 5000, 1, 5);
		ar5 = new Armor("Dwarven Armor", 1200, 1, 10);

		/*CONSTRUINDO WEAPONS*/
		we1 = new Weapon("Ebony Dagger", 290, 9, 5);
		we2 = new Weapon("Dragonbone Mace", 2000, 8, 15);
		we3 = new Weapon("Cajado de Gandalf", 750000, 7, 200);
		we4 = new Weapon("Gauldur Blackblade", 1470, 9, 11);
		we5 = new Weapon("Harkons Sword", 750, 9, 7);

		/*CONSTRUINDO POÇÕES*/
		hp1 = new HealthPotion("Potion of Health", 200, 20);
		hp2 = new HealthPotion("Potions of the Defender", 250, 40);
		hp3 = new HealthPotion("Potion of Strength", 50, 10);
		hp4 = new HealthPotion("Conjurer's Potion", 400, 45);
		hp5 = new HealthPotion("Enchanter's Potion", 500, 100);

		//Adiciona itens nas lista a serem enviadas para as lojas
		WeaponList.add(we1);
		WeaponList.add(we2);
		WeaponList.add(we3);
		WeaponList.add(we4);
		WeaponList.add(we5);

		ArmorList.add(ar1);
		ArmorList.add(ar2);
		ArmorList.add(ar3);
		ArmorList.add(ar4);
		ArmorList.add(ar5);

		PotionList.add(hp1);
		PotionList.add(hp2);
		PotionList.add(hp3);
		PotionList.add(hp4);
		PotionList.add(hp5);

	}

	// Método para inciar a batalhe entre dois times, passados como parâmetro
    public static void startBattle ( Team team1, Team team2 ){
    
        // Vetor de Threads para gerenciar os ataques do Time 1
        ArrayList<AttackThread> threadsTeam1 = new ArrayList<AttackThread>( team1.size() );
        
        // Vetor de Threads para gerenciar os ataques do Time 2
        ArrayList<AttackThread> threadsTeam2 = new ArrayList<AttackThread>( team2.size() );
        
        // Percorre o time 1, preparando seus personagens para atacar
        for ( int i = 0; i < team1.size(); i++ ){
            
            GameCharacter characterToAttack = team2.getRandomCharacter();
            
            // Se não houver personagens vivos no time2, getRandomCharacter() retorna null. Caso isso ocorra, não é possível batalhar
            if ( characterToAttack == null ) {
                System.out.println ("Todos os personagens de " + team2.getName() + " estão mortos. Não é possível iniciar uma batalha.");
                return;
            }
            
            // Prepara a thread para o ataque do personagem
            AttackThread newAttackThread = new AttackThread( team1.searchChar(i), characterToAttack );
            
            // Insere a thread no vetor de threads do time 1
            threadsTeam1.add( newAttackThread );
        }
        
        // Percorre o time 2, preparando seus personagens para atacar
        for ( int i = 0; i < team2.size(); i++ ){
            
            GameCharacter characterToAttack = team1.getRandomCharacter();
            
            // Se não houver personagens vivos no time1, getRandomCharacter() retorna null. Caso isso ocorra, não é possível batalhar
            if ( characterToAttack == null ) {
                System.out.println ("Todos os personagens de " + team1.getName() + " estão mortos. Não é possível iniciar uma batalha.");
                return;
            }
            
            // Prepara a thread para o ataque do personagem
            AttackThread newAttackThread = new AttackThread( team2.searchChar(i), characterToAttack );
            
            // Insere a thread no vetor de threads do time 1
            threadsTeam2.add( newAttackThread );
            
        }
        
        System.out.println ("================================= THE BATTLE BEGINS =================================\n\n");
        
        // Inicializa os ataques dos dois times, de forma intercalada
        
        int startedAttacksTeam1 = 0;
        int startedAttacksTeam2 = 0;
        
        while ( startedAttacksTeam1 < team1.size() && startedAttacksTeam2 < team2.size() ){
            
            threadsTeam1.get(startedAttacksTeam1).start();
            threadsTeam2.get(startedAttacksTeam2).start();
            
            startedAttacksTeam1++;
            startedAttacksTeam2++;
            
        }
        
        // Se ainda há personagens no Time 1 para atacar
        while ( startedAttacksTeam1 < team1.size() ){
            
            threadsTeam1.get(startedAttacksTeam1).start();
            startedAttacksTeam1++;
        
        }
        
        
        // Se ainda há personagens no Time 2 para atacar
        while ( startedAttacksTeam2 < team2.size() ){
        
            threadsTeam2.get(startedAttacksTeam2).start();
            startedAttacksTeam2++;
            
        }
        
        // Percorre os dois vetores de Threads, esperando a finalização de todas
        for ( int i = 0; i < threadsTeam1.size(); i++ ){
        
            threadsTeam1.get(i).join();
        }
        
        for ( int i = 0; i < threadsTeam2.size(); i++ ){
        
            threadsTeam2.get(i).join();
        }
        
        System.out.println ("\n\n================================== BATTLE FINISHED ==================================\n\n");
        System.out.println ("==== Result ====");
        
        // Chama o método para determinar qual time ganhou a batalha e atualizar seus resultados
        int result; // Usada para determinar qual time venceu a batalha
        
        result = team1.resolveBattle ( team2 );
        
        if ( result > 0 )
            System.out.println ( "\t" + team1.getName() + " venceu a batalha contra " + team2.getName() );
        
        else if ( result == 0 )
            System.out.println ( "\tOcorreu empate na batalha entre " + team1.getName() + " e " + team2.getName() );
        
        else
            System.out.println ( "\t" + team2.getName() + " venceu a batalha contra " + team1.getName() );

        // Atualiza as estatísticas do Time2
        team2.resolveBattle ( team1 );
        
        System.out.println("\n"); 
    }

    //Função principal. Inicia o jogo e abre o menu
	public static void main (String args[]) throws Exception {

	Scanner scan = new Scanner( System.in );
	
	int cmd=1;

	//Inicialização de atributos auxiliares usados durante o jogo
	avatars = new ArrayList<GameCharacter>();
	teams = new ArrayList<Team>();
	ArmorList = new ArrayList<Armor>();
	WeaponList = new ArrayList<Weapon>();
	PotionList = new ArrayList<Potion>();

	weaponshop = new WeaponShop(WeaponList);
	armorshop = new ArmorShop(ArmorList);
	potionshop = new PotionShop(PotionList);

	//Cria intes das lojas
	fillLists();

	//Inicia jogo abrindo menus
	while (cmd != 0) {

		Menu.mainMenu();
		cmd = scan.nextInt();

		switch(cmd) {
			case 1:
				Menu.CharacterMenu();
				break;
			case 2:
				Menu.TeamMenu();
				break;
		}
		cmd = scan.nextInt();
	}

}

}