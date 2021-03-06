/*
 *  Trabalho 03
 *  Alunos: Igor Quintal Mendes,            N. USP: 8622353
 *          Moisés Botarro Ferraz Silva,    N. USP: 8504135
 */

// Classe que implementa uma venda para Weapons

import java.util.*;

public class WeaponShop implements Shop, Printable {


    /* Atributos */
    // Arrray para guardar as armas disponíveis para venda
    private ArrayList<Weapon> weaponsToSell;

    /* Métodos */
    public WeaponShop ( ArrayList<Weapon> weaponsToSell ){
        
        this.weaponsToSell = weaponsToSell;
    }

    // Método para receber o personagem
    public void goShopping( GameCharacter character ){

        System.out.println ("||============================================================||");
        System.out.println ("||======================== WEAPON SHOP =======================||");
        System.out.println ("||============================================================||");
        System.out.println ("||Seja bem vindo à Weapon Shop.                                 ");

        int option;
        
        do {

            System.out.println ("||____________________________________________________________||");
            System.out.println ("|| Digite 0 para comprar uma arma.                            ||"); 
            System.out.println ("|| Digite 1 para vender um de seus items.                     ||");
            System.out.println ("||____________________________________________________________||");            
            System.out.println ("|| Digite -1 para sair da loja.                               ||");
            System.out.println ("||============================================================||");

            option = Menu.getWishedOption(-1,1);

            if ( option == -1 ){
                continue;
            }

            if ( option == 0 )
                sell ( character );

            else
                buyUsedItem ( character );

        } while ( option != -1 );
    }

    // Método para vender uma arma a um personagem
    public void sell( GameCharacter buyer ){

        // Variável para armazenar temporariamente o inventário do personagem
        Inventory inventory = buyer.getInventory();
        
        // Verifica se personagem tem espaço disponível para comprar
        if ( inventory.getAvailableSpace() == 0 ){
            System.out.println ("Não há espaço disponível no inventário de " + buyer.getName() + " para um novo item");
            return;
        }

        // Imprime as armas disponíveis para venda
        print();
        
        int option;

        do {

            System.out.println ("Gold disponível para compra: " + inventory.getTotalGold() + "\nDigite -1 para voltar ao menu anterior\n");
            System.out.printf ("Opção desejada: ");
            option = Menu.getWishedOption (-1, weaponsToSell.size() - 1);

            if ( option == -1 ){
                continue;
            }

            // Arma escolhida pelo usuário
            Weapon selectedWeapon = weaponsToSell.get( option );

            // Verifica se há dinheiro disponível para comprar
            if ( selectedWeapon.getPrice() > inventory.getTotalGold() ){
                System.out.println ( buyer.getName() + " não tem dinheiro suficiente para comprar " + selectedWeapon.getName() );
                continue;
            }

            // Se há dinheiro disponível, compra a arma e a coloca no inventário do personagem, sem equipá-la
            inventory.spendGold ( selectedWeapon.getPrice() );
            inventory.insertItem ( selectedWeapon );

            System.out.println ("Obrigado pela compra!! " + selectedWeapon.getName() + " já está no inventário de " + buyer.getName() +"\n");
        
        } while ( option != -1 );


    }

    // Método para comprar um item do personagem
    public void buyUsedItem ( GameCharacter seller ){

        // Variável para armazenar temporariamente o inventário do personagem
        Inventory inventory = seller.getInventory();

        System.out.println ("\n\t\tINVENTARIO DE " + seller.getName() );
        inventory.print();

        Scanner input = new Scanner (System.in);
        String itemName;
    
        do  {

            System.out.println("Digite o nome do item que deseja vender. Digite -1 para sair");
            itemName = input.nextLine();
            
            if ( itemName.equals ("-1") )
                continue;

            // Retorna o item a ser vendido
            Item itemToSell = null;
            itemToSell = inventory.searchItem ( itemName );

            // Se o personagem não possui o item digitado
            if ( itemToSell == null ){
                System.out.println ("Item " + itemName + " não está no inventário de " + seller.getName() + "\n");
                continue;
            }

            // Remove item do inventário do personagem e adiciona seu valor no gold do personagem
            inventory.removeItem ( itemName );
            inventory.earnGold ( itemToSell.getPrice() );

        } while ( !itemName.equals("-1") );
    }


    // Imprime as armas disponíves na loja para compra
    public void print(){

        System.out.println ("\t\tAVAILABLE WEAPONS:");

        int i = 0;

        for ( Weapon weapon : weaponsToSell ){
            System.out.println ("Opção " + i + ":");
            weapon.print();
            System.out.println();
            i++;
        }
    }
    
    public static void main ( String agrgs[] ){
    
        Knight cloud = new Knight ("Cloud", 20);
        
        Inventory inventory = cloud.getInventory();
        inventory.setSpaces(10);
        inventory.earnGold(300000);
        
        Weapon ultimaW = new Weapon ("UltimaWeapon Sword", 9999, 9, 10.5);
        Weapon wizardS = new Weapon ("Wizard Staff", 8000, 5, 8.5);
        Weapon stlPhone = new Weapon ("Starlight Phone", 6000, 6, 5.5);
        HealthPotion hpPotion = new HealthPotion ("HP Potion", 10, 10);

        
        cloud.storeItem ( hpPotion );
        ArrayList<Weapon> loja = new ArrayList<Weapon>();
        loja.add( ultimaW );
        loja.add( wizardS );
        loja.add( stlPhone );
        
        WeaponShop weaponShop = new WeaponShop ( loja );
        
        weaponShop.goShopping ( cloud );
    }
}