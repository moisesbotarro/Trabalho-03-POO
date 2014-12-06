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


	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void addCharTeam(Team tm) {

		Scanner scan = new Scanner( System.in );
		String char_name;		
		System.out.println("Digite o nome do personagem a ser adicionado:");
		do {
			char_name = scan.nextLine();
		} while(char_name.equals(""));

		GameCharacter ch = searchAvatar(char_name);

		if(ch!=null) {
			tm.addChar(ch);
		}
		else {
			System.out.println ("ERRO - PERSONAGEM NÃO EXISTE !");
		}
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void removeCharTeam(Team tm) {

		Scanner scan = new Scanner( System.in );
		String char_name;

		System.out.println("Digite o nome do personagem a ser removido:");
		do {
			char_name = scan.nextLine();
		} while(char_name.equals(""));
		
		GameCharacter ch = searchAvatar(char_name);

		if(ch!=null) {
			tm.removeChar(ch);
		}

		Menu.clean();

	}	

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void listTeam(Team tm) {

		tm.print();
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
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

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void createTeam() {

		int cmd;
		String team_name;
		Scanner scan = new Scanner( System.in );

		System.out.println("Digite o nome do novo time: ");
		do {
			team_name = scan.nextLine();
		} while(team_name.equals(""));
		
		System.out.println("Selecione a cor do time: ");
		System.out.println(" 1 - Blue");
		System.out.println(" 2 - Red");
		System.out.println(" 3 - Green");
		System.out.println(" 4 - Yellow");
		System.out.println(" 5 - White");
		System.out.println(" 6 - Black");

		cmd = scan.nextInt();

		Team tm;

		switch (cmd) {
			case 1:
				tm = new Team(team_name, Color.blue);
				teams.add(tm);
				break;
			case 2:
				tm = new Team(team_name, Color.red);
				teams.add(tm);
				break;
			case 3:
				tm = new Team(team_name, Color.green);
				teams.add(tm);
				break;
			case 4:
				tm = new Team(team_name, Color.yellow);
				teams.add(tm);
				break;
			case 5:
				tm = new Team(team_name, Color.white);
				teams.add(tm);
				break;
			case 6:
				tm = new Team(team_name, Color.black);
				teams.add(tm);
				break;
		}
		Menu.clean();			
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
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


	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
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
		int power = scan.nextInt();			
		Knight aux = new Knight(name, power);
		defineAtributes(aux);
		avatars.add(aux);
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
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
		int wisdom = scan.nextInt();			
		Wizard aux = new Wizard(name, wisdom);
		defineAtributes(aux);
		avatars.add(aux);
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
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
		int stealth = scan.nextInt();			
		Thief aux = new Thief(name, stealth);
		defineAtributes(aux);
		avatars.add(aux);
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void EquipItem(GameCharacter aux, String name) {

		aux.equipItem(name);
	}	

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void defineAtributes (GameCharacter aux) {

		Scanner scan = new Scanner( System.in );

		System.out.print(" -> Strenght: ");
		int strength = scan.nextInt();
		aux.setStrength(strength);

		System.out.print(" -> Dexterity: ");
		int dexterity = scan.nextInt();
		aux.setDexterity(dexterity);

		System.out.print(" -> Constitution:");
		int constitution = scan.nextInt();
		aux.setConstitution(constitution);		

		System.out.print(" -> Speed:");
		int speed = scan.nextInt();
		aux.setSpeed(speed);

		System.out.print(" -> Gold:");
		int gold = scan.nextInt();
		aux.getInventory().earnGold(gold);

		System.out.print(" -> Spaces:");
		int spaces = scan.nextInt();
		aux.getInventory().setSpaces(spaces);
		Menu.clean();
	} 

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void printInfo(Printable aux) {

		aux.print();
		return;
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void weaponShopping(GameCharacter ch) {

		weaponshop.goShopping(ch);
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void armorShopping(GameCharacter ch) {

		armorshop.goShopping(ch);
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void potionShopping(GameCharacter ch) {

		potionshop.goShopping(ch);
	}

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


	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void main (String args[]) throws Exception {

	Scanner scan = new Scanner( System.in );
	
	int cmd=1;

	avatars = new ArrayList<GameCharacter>();
	teams = new ArrayList<Team>();
	ArmorList = new ArrayList<Armor>();
	WeaponList = new ArrayList<Weapon>();
	PotionList = new ArrayList<Potion>();

	weaponshop = new WeaponShop(WeaponList);
	armorshop = new ArmorShop(ArmorList);
	potionshop = new PotionShop(PotionList);

	fillLists();

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