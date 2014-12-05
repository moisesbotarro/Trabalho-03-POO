/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe Thief
 */

import java.util.*;

public class Thief extends GameCharacter{

    /* Atributos */
    protected int stealth;
    
    /* Métodos */
    
    // Construtor
    public Thief ( String name, int stealth ){
        
        // Chama construtor da classe pai para inicializar atributo privado de GameCharacter
        super ( name );
        
        this.stealth = stealth;
    }
    
    // Sobrescrita do método getAttackPoints()
    protected int getAttackPoints(){
        
        // Como o speed do personagem pode estar afetado pelo uso de uma armadura, chama o método getSpeed() para recuperar seu valor real, com a penalidade
        
        // Retorna AttackPts genérico + stealth
        return (int) ( (( ( strength*0.5 + dexterity*0.3 + getSpeed()*0.2 ) + getItemsAttackPts() ) * ( XP/3.0 )) + stealth );
    }
    
    // Sobrescrita do método getDefensePoints()
    protected int getDefensePoints(){
        
        // Como o speed do personagem pode estar afetado pelo uso de uma armadura, chama o método getSpeed() para recuperar seu valor real, com a penalidade
        
        // Cálculo de defensePts é o modo genérico
        return (int) (( ( constitution*0.5 + dexterity*0.3 + getSpeed()*0.2 ) + getItemsDefensePts() ) * ( XP/2.0 ));
    }

    // Método para adicionar Stealh ( permitindo valores negativos )
    public void addStealth ( int moreStealth ){
        
        this.stealth += moreStealth;
    }
}