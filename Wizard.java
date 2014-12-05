/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe Wizard
 */

import java.util.*;

public class Wizard extends GameCharacter{

    /* Atributos */
    protected int wisdom;
    
    /* Métodos */
    
    // Construtor
    public Wizard ( String name, int wisdom ){
        
        // Chama construtor da classe pai para inicialiar o nome do personagem
        super ( name );
        
        this.wisdom = wisdom;
    }
    
    // Método getAttackPoints()
    protected int getAttackPoints(){
        
        // Como o speed do personagem pode estar afetado pelo uso de uma armadura, chama o método getSpeed() para recuperar seu valor real, com a penalidade
        
        // O cálculo de attackPts é genérico
        return (int) (( ( strength*0.5 + dexterity*0.3 + getSpeed()*0.2 ) + getItemsAttackPts() ) * ( XP/3.0 ));
    }

    // Método getDefensePoints()
    protected int getDefensePoints(){
        
        // Como o speed do personagem pode estar afetado pelo uso de uma armadura, chama o método getSpeed() para recuperar seu valor real, com a penalidade
        
        // Retorna defensePts genérico + (wisdom/2)
        return (int) ( (( ( constitution*0.5 + dexterity*0.3 + getSpeed()*0.2 ) + getItemsDefensePts() ) * ( XP/2.0 )) + (wisdom/2) );
    }
    
    // Método para adicionar wisdom ( permitindo valores negativos )
    public void addWisdom ( int moreWisdom ){
        
        this.wisdom += moreWisdom;
    }
}