/*
 *  Trabalho 03
 *  Alunos: Igor Quintal Mendes,            N. USP: 8622353
 *          Moisés Botarro Ferraz Silva,    N. USP: 8504135
 */

public class ManaPotion extends Potion{

    /* Métodos */
    
    // Construtor com atributos passados como parâmetros
    public ManaPotion( String name, double price, int restorePts ){
    
        // Chama construtor da classe Item (pai) para inicilizar os atributos
        super ( name, price, restorePts );
    }
    
    // Método para usar uma ManaPotion em um personagem passado como parâmetro
    public void use( GameCharacter character, Inventory inventory ){

        // Adiciona restorePts ao MP do personagem
        character.addMP ( this.getDefensePts() );
        
        System.out.println ( character.getName() + " usou " + getName() + ". Novo MP = " + character.getMP() );
        
        // Remove a ManaPotion do inventário do personagem.
        character.discartItem ( this.getName() );
    }
    
    //Método da interface Printable que imprime as características de um weapon
    public void print () {

        System.out.println ( "\n\t===== MANA POTION INFO ======" );
        System.out.println ( "\tNome: " + this.getName() );
        System.out.println ( "\tPreço: " + this.getPrice() );
        System.out.println ( "\tRestore Points: " + this.getDefensePts() );
    }
}