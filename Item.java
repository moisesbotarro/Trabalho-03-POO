/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe Item
 */

public abstract class Item implements Printable {

    /* Atributos */
    private String name;
    private double price;
    
    /* Métodos */
    
    /* Construtores */
    
    // Construtor passando atributos como parâmetros
    public Item ( String name, double price ){
        
        // Inicialização do nome do item
        this.name = name;
        
        // Inicialização e tratamento do valor de price
        if ( price < 0 ){
            System.out.println ("Valor do item deve ser positivo");
            System.out.println ("Valor do item inicializado em 0");
            this.price = 0;
        }
        else
            this.price = price;
    }
    
    // Construtor de Cópia
    public Item ( Item copy ) {
        
        // Inicialização dos atributos do novo objeto a partir dos atributos do objeto passado como parâmetro
        this.name = copy.name;
        this.price = copy.price;
    }
    
    /* Getters para atributos da Classe Item */
    public String getName(){
        
        return this.name;
    }
    
    public double getPrice(){
        
        return this.price;
    }
    
    // Métodos Abstratos - Deve ser implementado nas classes filhas
    public abstract void use( GameCharacter character, Inventory inventory );
    
    public abstract int getDefensePts();
    
    public abstract int getAttackPts();
    
    // Método auxiliar para desequipar um item. Deve ser implementado nas classes filhas
    public abstract void unequip ( GameCharacter character, Inventory inventory );
    
    // Método para imprimir informações de um item
    public abstract void print ();
}