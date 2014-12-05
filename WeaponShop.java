import java.util.*;

public class WeaponShop implements Shop, Printable {


    /* Atributos */
    // Arrray para guardar as armas disponíveis para venda
    private ArrayList<Weapon> weaponsToSell;

    /* Métodos */
    public WeaponShop ( ArrayList<Weapon> weaponsToSell ){
        
        this.weaponsToSell = weaponsToSell;
    }

    // Método para recever o personagem
    public void goShopping( GameCharacter character ){

        System.out.println ("\n======================== WEAPON SHOP ========================\n");
            System.out.println ("Seja bem vindo à Weapon Shop.");

        int option;
        
        do {

            System.out.println ("Digite 0 para comprar uma arma. Digite 1 para vender um de seus items:\n");
            System.out.println ("Digite -1 para sair da loja");

            Scanner input = new Scanner ( System.in );

            option = input.nextInt();

            if ( option < 0 || option > 1 ){
                System.out.println ("Opção Inválida!!!");
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

        Scanner entrada = new Scanner (System.in);

        // Imprime as armas disponíveis para venda
        print();
        
        int option;

        do {

            System.out.println ("Gold disponível para compra: " + inventory.getTotalGold() + "\nDigite -1 para voltar ao menu anterior\n");
            System.out.printf ("Opção desejada: ");
            option = entrada.nextInt();

            if ( option <= 0 || option > weaponsToSell.size() ){

                if ( option != -1)
                    System.out.println ("Opção Indisponível. Escolha uma possível entre as listadas");

                continue;
            }

            // Arma escolhida pelo usuário
            Weapon selectedWeapon = weaponsToSell.get( option-1 );

            // Verifica se há dinheiro disponível para comprar
            if ( selectedWeapon.getPrice() > inventory.getTotalGold() ){
                System.out.println ( buyer.getName() + " não tem dinheiro suficiente para comprar " + selectedWeapon.getName() );
                continue;
            }

            // Se há dinheiro disponível, compra a arma e a coloca no inventário do personagem, sem equipá-la
            inventory.spendGold ( selectedWeapon.getPrice() );
            inventory.insertItem ( selectedWeapon );

            System.out.println ("Obrigado pela compra!!. " + selectedWeapon.getName() + " já está no inventário de " + buyer.getName() );
        
        } while ( option != -1 );


    }


    // Método para comprar uma arma do personagem
    public void buyUsedItem ( GameCharacter seller ){

        Scanner input = new Scanner (System.in);

        // Variável para armazenar temporariamente o inventário do personagem
        Inventory inventory = seller.getInventory();

        System.out.println ("Inventario de " + seller.getName() );
        inventory.print();

        String itemName;
    
        do  {

            System.out.println("Digite o nome do item que deseja vender. Digite -1 para sair");
            itemName = input.nextLine();

            if ( itemName.equals ("-1") )
                continue;

            // Retorna o item a ser vendido
            Item itemToSell = null;
            inventory.searchItem ( itemName );

            if ( itemToSell == null ){
                System.out.println ("Item " + itemName + " não está no inventário de " + seller.getName() );

            }

            // Remove item do inventário do personagem e adiciona seu valor no gold do personagem
            inventory.removeItem ( itemName );
            inventory.earnGold ( itemToSell.getPrice() );

        } while ( itemName.equals("-1") );
    }


    // Imprime as armas disponíves na loja para compra
    public void print(){

        System.out.println ("Available Weapons:");

        int i = 0;

        for ( Weapon weapon : weaponsToSell ){
            System.out.println ("Opção " + i + ":");
            weapon.print();
            System.out.println();
        }
    }
}