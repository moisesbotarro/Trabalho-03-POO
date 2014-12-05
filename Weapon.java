/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe Weapon
 */

public class Weapon extends Item implements Weapon {
    
    /* Atributos */
    protected int attackPts;
    protected double range;
    
    /* Métodos */
    
    /* Construtores */
    
    // Construtor com atributos passador por parâmetro
    public Weapon ( String name, double price, int attackPts, double range ) {
        
        // Chama construtor da classe pai, inicializando nome e preço
        super ( name, price );
        
        // Inicializa demais atributos
        
        // Tratamento do parâmetro attackPts
        if ( attackPts < 1 ){
            System.out.println ("AttackPts de " + name + " deve estar entre 1 e 9. Inicializado em 1");
            this.attackPts = 1;
        }
        
        else if ( attackPts > 9 ){
            System.out.println ("AttackPts de " + name + " deve estar entre 1 e 9. Inicializado em 9");
            this.attackPts = 9;
        }
        
        else
            this.attackPts = attackPts;
        
        // Atributo Range
        this.range = range;
    }
    
    // Construtor de Cópia
    public Weapon ( Weapon copy ){
        
        // Chama construtor de cópia da classe pai para inicializar atributos que são privados da classe Item
        super ( copy );
    
        // Não precisa tratar o valor de attackPts pois ele já foi inicializado corretamente ao instaciar o objeto que é passado como parâmetro
        this.attackPts = copy.attackPts;
        this.range = copy.range;
    }
    
    /* Getters */
    
    // attackPts
    public int getAttackPts(){
        
        return attackPts;
    }
    
    // Implementação do método getDefensePts retornando 0 pois Weapon não tem defensePts
    public int getDefensePts(){
    
        return 0;
    }
    
    
    // range
    public double getRange(){
        
        return range;
    }
    
     // Método use() é usado para equipar uma arma
    public void use( GameCharacter character, Inventory inventory ) {
    
        // Verifica se pode equipar uma arma. O número máximo de armas equipadas é 2
        if ( inventory.howManyEquippedWeapons() >= 2 ){
        
            System.out.println ( "Numero maximo de armas equipadas (2) ja foi atingido. Nao foi possivel equipar " + this.getName() + " em " + character.getName() );
            return;
        }
        
        // Equipa a arma no inventário do personagem
        if ( inventory.equipItem ( this.getName() ) ){ // Se conseguiu equipar a arma
        
            System.out.println ( character.getName() + " equipou a arma " + this.getName() );
            
            // Altera o número de armas equipadas no inventário do personagem
            inventory.setNumberOfEquippedWeapons ( inventory.howManyEquippedWeapons() + 1 );
    
        }
    }
    
    // Método para desequipar uma arma
    public void unequip ( GameCharacter character, Inventory inventory ){
    
        // Chama o método unequipItem
        if ( inventory.unequipItem ( this.getName() ) ){
         
            // Se conseguiu desequipar a arma
            System.out.println ( character.getName() + " desequipou a arma " + this.getName() );
            
            // Diminui o número de armas equipadas
            inventory.setNumberOfEquippedWeapons ( inventory.howManyEquippedWeapons() - 1 );
        }
    }
    
    // Método para teste da classe
    public static void main ( String args[] ){
    
        Weapon w1 = new Weapon( "faca", 10, 0, 70);
        Weapon w2 = new Weapon( "espada", 20, 10, 50);
        Weapon w3 = new Weapon( "adaga", 30, 6, 70);
        Weapon w4 = new Weapon( w3 );
        
        w1.printInfo();
        w2.printInfo();
        w3.printInfo();
        w4.printInfo();
    }

    //Método da interface Printable que imprime as características de um weapon
    public void print () {

        System.out.println ( "\n===== THIEF INFO ======" );
        System.out.println ( "Nome: " + this.getName() );
        System.out.println ( "Preço: " + this.getPrice );
        System.out.println ( "Pontos de Ataque: " + this.getAttackPts() );
        System.out.println ( "Range: " + this.getRange() );
    }
}