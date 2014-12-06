import java.util.*;

// Classe que implementa uma venda para Potions

public class PotionShop implements Shop, Printable {


    /* Atributos */
    // Arrray para guardar os potions disponíveis para venda
    private ArrayList<Potion> potionsToSell;

    /* Métodos */
    public PotionShop ( ArrayList<Potion> potionsToSell ){
        
        this.potionsToSell = potionsToSell;
    }

    // Método para receber o personagem
    public void goShopping( GameCharacter character ){

        System.out.println ("||============================================================||");
        System.out.println ("||======================== POTION SHOP =======================||");
        System.out.println ("||============================================================||");
        System.out.println ("||Seja bem vindo à Potion Shop.                                 ");

        int option;
        
        do {

            System.out.println ("||____________________________________________________________||");
            System.out.println ("|| Digite 0 para comprar uma poções.                          ||"); 
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

    // Método para vender uma potion a um personagem
    public void sell( GameCharacter buyer ){

        // Variável para armazenar temporariamente o inventário do personagem
        Inventory inventory = buyer.getInventory();
        
        // Verifica se personagem tem espaço disponível para comprar
        if ( inventory.getAvailableSpace() == 0 ){
            System.out.println ("Não há espaço disponível no inventário de " + buyer.getName() + " para um novo item");
            return;
        }

        Scanner entrada = new Scanner (System.in);

        // Imprime as potions disponíveis para venda
        print();
        
        int option;

        do {

            System.out.println ("Gold disponível para compra: " + inventory.getTotalGold() + "\nDigite -1 para voltar ao menu anterior\n");
            System.out.printf ("Opção desejada: ");
            option = entrada.nextInt();

            if ( option <= 0 || option > potionsToSell.size() ){

                if ( option != -1)
                    System.out.println ("Opção Indisponível. Escolha uma possível entre as listadas");

                continue;
            }

            // Potion escolhida pelo usuário
            Potion selectedPotion = potionsToSell.get( option - 1 );

            // Verifica se há dinheiro disponível para comprar
            if ( selectedPotion.getPrice() > inventory.getTotalGold() ){
                System.out.println ( buyer.getName() + " não tem dinheiro suficiente para comprar " + selectedPotion.getName() );
                continue;
            }

            // Se há dinheiro disponível, compra a potion e a coloca no inventário do personagem, sem usá-la
            inventory.spendGold ( selectedPotion.getPrice() );
            inventory.insertItem ( selectedPotion );

            System.out.println ("Obrigado pela compra!! " + selectedPotion.getName() + " já está no inventário de " + buyer.getName() +"\n");
        
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


    // Imprime os potions disponíves na loja para compra
    public void print(){

        System.out.println ("\t\tAVAILABLE POTIONS:");

        int i = 1;

        for ( Potion potion : potionsToSell ){
            System.out.println ("Opção " + i + ":");
            potion.print();
            System.out.println();
            i++;
        }
    }
    
    public static void main ( String agrgs[] ){
    
        Knight cloud = new Knight ("Cloud", 20);
        
        Inventory inventory = cloud.getInventory();
        inventory.setSpaces(10);
        inventory.earnGold(300000);
        
        HealthPotion hpPotion = new HealthPotion ("HP Potion", 10, 10);
        HealthPotion hiPotion = new HealthPotion ("Hi-Potion", 20, 20);
        HealthPotion xPotion = new HealthPotion ("X-Potion", 50, 30);
        HealthPotion elixir = new HealthPotion ("Elixir", 100, 50);
        
        ManaPotion mpPotion = new ManaPotion ("MP Potion", 100, 10);
        ManaPotion ether = new ManaPotion ("Ether", 500, 20);
        
        cloud.storeItem ( hpPotion );
        ArrayList<Potion> loja = new ArrayList<Potion>();
        loja.add( hpPotion );
        loja.add( hiPotion );
        loja.add( xPotion );
        loja.add( elixir );
        loja.add( mpPotion );
        loja.add( ether );
        
        PotionShop potionShop = new PotionShop ( loja );
        
        potionShop.goShopping ( cloud );
    }
}