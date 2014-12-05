/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe ManaPotion
 */

public class ManaPotion extends Potion implements Printable{

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

        System.out.println ( "\n===== MANA POTION INFO ======" );
        System.out.println ( "Nome: " + this.getName() );
        System.out.println ( "Preço: " + this.getPrice );
        System.out.println ( "Restore Points: " + this.getDefensePts() );
    }
}