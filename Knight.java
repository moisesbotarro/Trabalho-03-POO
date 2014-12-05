/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe Knight
 */

import java.util.*;

public class Knight extends GameCharacter{

    /* Atributos */
    protected int power;
    
    /* Métodos */
    
    // Construtor
    public Knight ( String name, int power ){
        
        // Chama construtor da classe pai para inicializar o nome
        super ( name );
        
        this.power = power;
    }
    
    // Método getAttackPts()
    protected int getAttackPoints(){
        
        // Como o speed do personagem pode estar afetado pelo uso de uma armadura, chama o método getSpeed() para recuperar seu valor real, com a penalidade
        
        // Cálculo de attackPts é genérico
        return (int) (( ( strength*0.5 + dexterity*0.3 + getSpeed()*0.2 ) + getItemsAttackPts() ) * ( XP/3.0 ));
    }
    
    // Método getDefensePts()
    protected int getDefensePoints(){
        
        // Como o speed do personagem pode estar afetado pelo uso de uma armadura, chama o método getSpeed() para recuperar seu valor real, com a penalidade
        
        // Retorna defensePts genérico + power
        return (int) ( (( ( constitution*0.5 + dexterity*0.3 + getSpeed()*0.2 ) + getItemsDefensePts() ) * ( XP/2.0 )) + power );
    }
    
    // Método para adicionar power ( permitindo valores negativos )
    public void addPower ( int morePower ){
        
        this.power += morePower;
    }

    //Método da interface Printable que imprime as características de um knight
    public void print () {

        System.out.println ( "\n===== KNIGHT INFO ======" );
        System.out.println ( "Nome: " + alias );
        System.out.println ( "HP: " + HP );
        System.out.println ( "MP: " + MP );
        System.out.println ( "XP: " + XP );
        System.out.println ( "Stregth: " + strength );
        System.out.println ( "Speed: " + getSpeed() );
        System.out.println ( "Dexterity: " + dexterity );
        System.out.println ( "Constitution: " + constitution );
        System.out.println ( "Power: " + power );
        System.out.println ( "Nro. de Armas Equipadas: " + howManyEquippedWeapons() );
        System.out.println ( "Nro. de Armaduras Equipadas: " + howManyEquippedArmors() + '\n');
    }
}