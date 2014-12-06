/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe HealthPotion
 */

public class HealthPotion extends Potion{

    /* Métodos */
    
    // Construtor com passagem dos atributos como parâmetros
    public HealthPotion( String name, double price, int restorePts ){
        
        // Chama construtor da classe pai
        super ( name, price, restorePts );
    }
    
    // Método para usar uma HealthPotion em um personagem passado como parâmetro
    public void use ( GameCharacter character, Inventory inventory ){
    
        // Adiciona restorePts ao HP do personagem
        character.addHP ( this.getDefensePts() );
        
        System.out.println ( character.getName() + " usou " + getName() + ". Novo HP = " + character.getHP() );
        
        // Remove a HealthPotion do inventário do personagem.
        character.discartItem ( this.getName() );
    }
    
    //Método da interface Printable que imprime as características de um weapon
    public void print () {

        System.out.println ( "\n\t===== HEALTH POTION INFO ======" );
        System.out.println ( "\tNome: " + this.getName() );
        System.out.println ( "\tPreço: " + this.getPrice() );
        System.out.println ( "\tRestore Points: " + this.getDefensePts() );
    }
}