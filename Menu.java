import java.util.*;

public class Menu {

	public Scanner scan = new Scanner( System.in );	

	public static void mainMenu() {

		Scanner scan = new Scanner( System.in );	

		int cmd=0;

		while (cmd!=-1) {

			System.out.println("Comandos disponíveis para o jogo: ");
			System.out.println(" 1 - Configurar personagens "); 
			System.out.println(" 2 - Configurar times ");

			cmd = scan.nextInt();

			switch (cmd) {
				case 1:
					CharacterMenu();
					break;
				case 2:
					TeamMenu();
					break;
			}
		}
		return;	
	}

	public static void CharacterMenu() {

		Scanner scan = new Scanner( System.in );	

		int cmd=0;

		while (cmd!=-1) {

			System.out.println("Comandos disponíveis para um personagem: ");
			System.out.println(" 1 - Criar novo personagem ");
			System.out.println(" 2 - Selecionar personagem ");
			
			cmd = scan.nextInt();

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

			System.out.println("Selecione o tipo de personagem: ");
			System.out.println(" 1 - Knight ");
			System.out.println(" 2 - Wizard ");
			System.out.println(" 3 - Thief ");
			
			cmd = scan.nextInt();

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

		while (cmd!=-1) {

			System.out.println("Digite o nome do personagem a ser selecionado:");
			String name = scan.nextLine();
			GameCharacter ch = mainProgram.searchAvatar(name);
			System.out.println("Selecione uma das opções:");
			System.out.println(" 1 - Imprimir atributos");
			System.out.println(" 2 - Imprimir inventário");
			System.out.println(" 3 - Ir a uma loja (vender ou comprar itens)");
			System.out.println(" 4 - Equipar/Usar itens");

			cmd = scan.nextInt();

			switch (cmd) {
				case 1:
					mainProgram.printInfo(ch);
					break;
				case 2:
					mainProgram.printInfo(ch.getInventory());
					break;
				case 3:
					//mainProgram.Shopping();
					break;
				case 4:
					EquipUseMenu(ch);
					break;
			}
		}
	}

	public static void EquipUseMenu(GameCharacter aux) {

		Scanner scan = new Scanner( System.in );	

		System.out.println ("Digite o nome do item a ser equipado");
		String item_name = scan.nextLine();
		mainProgram.EquipItem(aux, item_name);

	}

	public static void TeamMenu () {

		int cmd=0;

		Scanner scan = new Scanner( System.in );	

		while(cmd!=-1)
		{
			System.out.println("Selecione uma das opções:");
			System.out.println(" 1 - Criar time");
			System.out.println(" 2 - Listar personagens de um time");
			System.out.println(" 3 - Adicionar personagem a time");
			System.out.println(" 4 - Remover personagens de um time");

			cmd = scan.nextInt();

			switch (cmd) {
				case 1:
					mainProgram.createTeam();
					break;
				case 2:
					mainProgram.listTeam();
					break;
				case 3:
					mainProgram.addCharTeam();
					break;
				case 4:
					mainProgram.removeCharTeam();
					break;
			}
		}						
	}
}