/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe Potion
 */

public abstract class Potion extends Item {

    /* Atributos */
    private int restorePts;
    
    /* Métodos */
    
    // Construtor com passagem de atributos por parâmetros
    public Potion ( String name, double price, int restorePts ){
        
            // Chama construtor de Items para inicializar atributos privados da super classe
            super ( name, price );
        
            this.restorePts = restorePts;
    }
    
    // Construtor de Cópia
    public Potion ( Potion copy ){
        
        // Chama construtor de Cópia da classe pai para inicialiar atributos privados de Item
        super ( copy );
        
        this.restorePts = copy.restorePts;
    }
    
    // Método que retorna o restorePts da Potion
    public int getDefensePts(){
    
        return this.restorePts;
    }
    
    // Método getAttackPts() retorna -1 para indicar que o Item é uma potion
    public int getAttackPts(){
    
        return -1;
    }
    
    // Método Virtual - Deve ser implementado nas classes filhas
    public abstract void use( GameCharacter ch, Inventory inventory );
    
    // Método unequip(GameCharacter, Inventory) não realiza nenhuma ação para Potion, já que ela não pode ser equipada
    public void unequip ( GameCharacter character, Inventory inventory ) {
        
        System.out.println ( "Nao eh possivel desequipar uma pocao. ");
    }
    
    // Método para inprimir informações sobre o item. Deve ser implementado nas classes filhas
    public abstract void printInfo();

}