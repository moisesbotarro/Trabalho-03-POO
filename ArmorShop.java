/*
 *  Trabalho 03
 *  Alunos: Igor Quintal Mendes,            N. USP: 8622353
 *          Moisés Botarro Ferraz Silva,    N. USP: 8504135
 */

// Classe que implementa uma venda para Armors

import java.util.*;

public class ArmorShop implements Shop, Printable {


    /* Atributos */
    // Arrray para guardar as armaduras disponíveis para venda
    private ArrayList<Armor> armorsToSell;

    /* Métodos */
    public ArmorShop ( ArrayList<Armor> armorsToSell ){
        
        this.armorsToSell = armorsToSell;
    }

    // Método para receber o personagem
    public void goShopping( GameCharacter character ){

        System.out.println ("||============================================================||");
        System.out.println ("||======================== ARMOR SHOP ========================||");
        System.out.println ("||============================================================||");
        System.out.println ("||Seja bem vindo à Armor Shop.                                  ");

        int option;
        
        do {
                                                                                               
            System.out.println ("||____________________________________________________________||");
            System.out.println ("|| Digite 0 para comprar uma armadura.                        ||"); 
            System.out.println ("|| Digite 1 para vender um de seus items.                     ||");
            System.out.println ("||____________________________________________________________||");            
            System.out.println ("|| Digite -1 para sair da loja.                               ||");
            System.out.println ("||============================================================||");

            Scanner input = new Scanner ( System.in );

            option = input.nextInt();

            if ( option < 0 || option > 1 ){
                
                // Caso ainda não se deseja sair do programa
                if ( option != -1 )
                    System.out.println ("Opção Inválida!!!");
    
                continue;
            }

            if ( option == 0 )
                sell ( character );

            else
                buyUsedItem ( character );

        } while ( option != -1 );

    }

    // Método para vender uma armadura a um personagem
    public void sell( GameCharacter buyer ){

        // Variável para armazenar temporariamente o inventário do personagem
        Inventory inventory = buyer.getInventory();
        
        // Verifica se personagem tem espaço disponível para comprar
        if ( inventory.getAvailableSpace() == 0 ){
            System.out.println ("Não há espaço disponível no inventário de " + buyer.getName() + " para um novo item");
            return;
        }

        Scanner entrada = new Scanner (System.in);

        // Imprime as armaduras disponíveis para venda
        print();
        
        int option;

        do {

            System.out.println ("Gold disponível para compra: " + inventory.getTotalGold() + "\nDigite -1 para voltar ao menu anterior\n");
            System.out.printf ("Opção desejada: ");
            option = entrada.nextInt();

            if ( option <= 0 || option > armorsToSell.size() ){

                if ( option != -1)
                    System.out.println ("Opção Indisponível. Escolha uma possível entre as listadas");

                continue;
            }

            // Armadura escolhida pelo usuário
            Armor selectedArmor = armorsToSell.get( option - 1 );

            // Verifica se há dinheiro disponível para comprar
            if ( selectedArmor.getPrice() > inventory.getTotalGold() ){
                System.out.println ( buyer.getName() + " não tem dinheiro suficiente para comprar " + selectedArmor.getName() );
                continue;
            }

            // Se há dinheiro disponível, compra a armadura e a coloca no inventário do personagem, sem equipá-la
            inventory.spendGold ( selectedArmor.getPrice() );
            inventory.insertItem ( selectedArmor );

            System.out.println ("Obrigado pela compra!! " + selectedArmor.getName() + " já está no inventário de " + buyer.getName() +"\n");
        
        } while ( option != -1 );
    }

    // Método para comprar um item do personagem
    public void buyUsedItem ( GameCharacter seller ){

        Scanner input = new Scanner (System.in);

        // Variável para armazenar temporariamente o inventário do personagem
        Inventory inventory = seller.getInventory();

        System.out.println ("\n\t\tINVENTARIO DE " + seller.getName() );
        inventory.print();

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


    // Imprime as armaduras disponíves na loja para compra
    public void print(){

        System.out.println ("\t\tAVAILABLE ARMORS:");

        int i = 1;

        for ( Armor armor : armorsToSell ){
            System.out.println ("Opção " + i + ":");
            armor.print();
            System.out.println();
            i++;
        }
    }
    
    public static void main ( String agrgs[] ){
    
        Knight cloud = new Knight ("Cloud", 20);
        
        Inventory inventory = cloud.getInventory();
        inventory.setSpaces(10);
        inventory.earnGold(300000);
        
        Armor bronzeB = new Armor ("Bronze Bangle", 100, 4, 20);
        Armor ironB = new Armor ("Iron Bangle", 160, 5, 19.5);
        Armor titanB = new Armor ("Titan Bangle", 280, 7, 17.5 );
        HealthPotion hpPotion = new HealthPotion ("HP Potion", 10, 10);

        
        cloud.storeItem ( hpPotion );
        ArrayList<Armor> loja = new ArrayList<Armor>();
        loja.add( bronzeB );
        loja.add( ironB );
        loja.add( titanB );
        
        ArmorShop armorShop = new ArmorShop ( loja );
        
        armorShop.goShopping ( cloud );
    }
}