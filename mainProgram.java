import java.util.*;

public class mainProgram {
	
	public static ArrayList<GameCharacter> avatars;
	public static ArrayList<Team> teams;


	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void addCharTeam() {

		Scanner scan = new Scanner( System.in );

		System.out.println("Digite o nome do time onde o personagem será adicionado:");
		String team_name = scan.nextLine();
		Team tm = searchTeam(team_name);
		System.out.println("Digite o nome do personagem a ser adicionado:");
		String char_name = scan.nextLine();
		GameCharacter ch = searchAvatar(char_name);

		tm.addChar(ch);
		System.out.println("Personagem adicionado !");
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void removeCharTeam() {

		Scanner scan = new Scanner( System.in );

		System.out.println("Digite o nome do time onde o personagem será removido:");
		String team_name = scan.nextLine();
		Team tm = searchTeam(team_name);
		System.out.println("Digite o nome do personagem a ser removido:");
		String char_name = scan.nextLine();
		GameCharacter ch = searchAvatar(char_name);

		tm.removeChar(ch);
		System.out.println("Personagem removido !");

	}	

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void listTeam() {

		Scanner scan = new Scanner( System.in );

		System.out.println("Digite o nome do time:");
		String team_name = scan.nextLine();
		Team tm = searchTeam(team_name);

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

		Scanner scan = new Scanner( System.in );

		System.out.println("Digite o nome do novo time: ");
		String team_name = scan.nextLine();
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

		System.out.println("Digite os valores para os seguintes atributos:");
		System.out.print(" -> Nome: ");
		String name = scan.nextLine();
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

		System.out.println("Digite os valores para os seguintes atributos:");
		System.out.print(" -> Nome: ");
		String name = scan.nextLine();
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

		System.out.println("Digite os valores para os seguintes atributos:");
		System.out.print(" -> Nome: ");
		String name = scan.nextLine();
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
	} 

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void printInfo(Printable aux) {

		aux.print();
	}

	//0000000000000000000000000000000000000000000000000000000000000000000000000
	//0000000000000000000000000000000000000000000000000000000000000000000000000
	public static void main (String args[]) throws Exception {

	Scanner scan = new Scanner( System.in );
	
	int cmd=1;

	avatars = new ArrayList<GameCharacter>();
	teams = new ArrayList<Team>();

	while (cmd != 0) {

		Menu.mainMenu();
		cmd = scan.nextInt();

		switch(cmd) {
			case 1:
				Menu.CharacterMenu();
		}
		cmd = scan.nextInt();
	}

}

}