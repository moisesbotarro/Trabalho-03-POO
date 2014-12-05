/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe ManaPotion
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
    
    // Método para imprimir informações sobre o Item
    public void printInfo(){
        
        System.out.println ( "MANA POTION: " + this.getName() );
        System.out.println ( "\tPreço: " + this.getPrice() );
        System.out.println ( "\tRestorePts: " + this.getDefensePts() );
        
    }
}