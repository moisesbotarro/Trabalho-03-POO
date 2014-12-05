/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe Armor
 */

public class Armor extends Item {

    /* Atributos */
    protected int defensePts;
    protected double weight;
    
    /* Métodos */
    
    /* Construtores */
    
    // Construtor com atributos passados como parâmetros
    public Armor ( String name, double price, int defensePts, double weight ){
    
        // Chama construtor da classe pai para inicializar atributos privados a Item
        super ( name, price );
        
        // Tratamento do parâmetro defensePts
        if ( defensePts < 1 ){
            System.out.println ("DefensePts de " + name + " deve estar entre 1 e 20. Inicializado em 1");
            this.defensePts = 1;
        }
        
        else if ( defensePts > 20 ){
            System.out.println ("DefensePts de " + name + " deve estar entre 1 e 20. Inicializado em 20");
            this.defensePts = 20;
        }
        
        else
            this.defensePts = defensePts;
        
        // Tratamento do parâmetro Weight
        if ( weight < 1 ){
            System.out.println ( "Weight de " + name + " deve estar entre 1 e 20. Inicializado em 1" );
            this.weight = 1;
        }
        
        else if ( weight > 20 ){
            System.out.println ( "Weight de " + name + " deve estar entre 1 e 20. Inicializado em 20" );
            this.weight = 20;
        }
        
        else
            this.weight = weight;
    }
    
    // Construtor de Cópia
    public Armor ( Armor copy ){
        
        // Chama construtor de cópia da classe pai para copiar atributos não acessíveis em Armor
        super ( copy );
        
        // Não precisa tratar os valores de defensePts e weight pois eles já foram inicializados corretamente ao instaciar o objeto que é passado como parâmetro
        this.defensePts = copy.defensePts;
        this.weight = copy.weight;
    }
    
    /* Getters */
    
    // defensePts
    public int getDefensePts(){
        
        return defensePts;
    }
    
    // Implementação do método getAttackPts retornando 0 pois Armor não tem attackPts
    public int getAttackPts(){
    
        return 0;
    }
    
    // Método para retornar o peso da armadura
    public double getWeight(){
        
        return weight;
    }
    
     // Método use() é utilizado apra equipar a armadura
    public void use( GameCharacter character, Inventory inventory ) {
    
        // Verifica se pode equipar uma armadura. O número máximo de armaduras equipadas é 1
        if ( inventory.howManyEquippedArmors() >= 1 ){
        
            System.out.println ( "Numero maximo de armaduras equipadas (1) ja foi atingido. Nao foi possivel equipar " + this.getName() + " em " + character.getName() );
            return;
        }
        
        // Equipa a armadura no inventário do personagem
        if ( inventory.equipItem ( this.getName() ) ){ // Se conseguiu equipar a armadura
        
            System.out.println ( character.getName() + " equipou a armadura " + this.getName() );
            
            // Altera o número de armaduras equipadas no inventário do personagem
            inventory.setNumberOfEquippedArmors ( inventory.howManyEquippedArmors() + 1 );
            
            // Ao equipar uma armadura, há uma penalidade no speed do personagem. Modifica o atributo speedPenality do personagem
            double newSpeedPenality = Math.exp ( - Math.pow ( this.weight/20.0, 2 ) );
            
            character.setSpeedPenality ( newSpeedPenality );
        }
    }
    
    // Método para desequipar uma armadura
    public void unequip ( GameCharacter character, Inventory inventory ){
        
        // Chama o método unequipItem
        if ( inventory.unequipItem ( this.getName() ) ){
         
            // Se conseguiu desequipar a armadura
            System.out.println ( character.getName() + " desequipou a armadura " + this.getName() );
            
            // Retira a penalidade de speed do personagem
            character.setSpeedPenality (1); //Multiplica a speed do personagem
            
            // Diminui o número de armaduras equipadas
            inventory.setNumberOfEquippedArmors ( inventory.howManyEquippedArmors() - 1 );
        }
    }
    
    // Método auxiliar para imprimir informaçõe do Item
    public void printInfo(){
        System.out.println ( "ARMADURA: " + this.getName() );
        System.out.println ( "\tPreço: " + this.getPrice() );
        System.out.println ( "\tDenfensePts: " + this.getDefensePts() );
        System.out.println ( "\tWeight: " + this.getWeight() );
    }
    
    // Método para teste da classe
    public static void main ( String args[] ){
    
        Armor a1 = new Armor ( "Escudo de Madeira", 1, -2, 1);
        Armor a2 = new Armor ( "Escudo de Aço", 1, 6, 10);
        Armor a3 = new Armor ( "Escudo de Ferro", 1, 22, 20);
        Armor a4 = new Armor ( a3 );
        
        a1.printInfo();
        a2.printInfo();
        a3.printInfo();
        a4.printInfo();
    }
}