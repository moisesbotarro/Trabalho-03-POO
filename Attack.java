// Classe para Criar uma Thread que representa um personagem atacando outro personagem

import java.util.*;

public class Attack implements Runnable{

    /* Atributos */
    private Thread t; // Thread que tratará a execução do ataque
    private GameCharacter attacker; // Personagem que irá atacar
    private GameCharacter attacked; // Personagem que receberá o ataque
    private String threadName;
    
    // Construtor da Thread - Recebe o personagem que irá atacar e o personagem que será atacado
    public Attack( GameCharacter attacker, GameCharacter attacked ){
        
        this.attacker = attacker;
        this.attacked = attacked;
        threadName = attacker + " atacando " + attacked;
    
    }
    
    // Método que dará partida na Thread
    public void start (){
    
        System.out.println ( attacker.getName() + " se preparando para atacar " + attacked.getName() );
        
        t = new Thread (this, threadName );
        t.start();
    }
    
    // Método que executa o ataque na Thread
    public void run() {
    

        System.out.println ( attacker.getName() + " correndo em direção a " + attacked.getName() );
        
        // Sorteia um tempo aleatório para o personagem esperar para atacar o outro, entre 0s e 5s
        double randomNumber =  ( 5 ) * ( 1000 * Math.random() ); // O tempo deve estar em milissegundos
        
        try {
        
            Thread.sleep ((long)randomNumber);
            
        } catch ( InterruptedException e ){
            
            System.out.println( attacker.getName() + "parou para dormir por pelo menos " + (randomNumber/1000) + "segundos" );
        }
        
        // O próximo bloco deve ser executada em sequência, sem ser interrompido - Realiza o ataque
        synchronized(this) {
        
            // Verifica se o personagem ainda está vivo
            if ( !attacker.isDead() ){
                
                // Verifica se o alvo ainda está vivo
                if ( attacked.isDead() ){
                    
                    System.out.println ( attacker.getName() + " não pode atacar " + attacked.getName() + " porque " + attacked.getName() + " está morto." );
                    return; // Termina a thread
                }
                
                // Realiza o ataque
                attacker.attack ( attacked );
            }
        }
        
    }
    
    public static void main ( String args[] ){
    
         // Declaração e instanciamento de 4 personagens
        Knight cloud = new Knight ("Cloud", 20);
        Knight cid = new Knight ("Cid", 15);
        Wizard aeris = new Wizard ("Aeris", 30);
        Thief caitSith = new Thief ("Cait Sith", 10);
        
        // Atribuição de pontos nos atributos dos personagens
        cloud.addXP (30);
        cloud.setStrength (30);
        cloud.setSpeed (30);
        cloud.setConstitution (20);
        cloud.setDexterity (20);
        
        cid.addXP (5);
        cid.setStrength (25);
        cid.setSpeed (30);
        cid.setConstitution (25);
        cid.setDexterity (10);
        
        aeris.addXP (3);
        aeris.setStrength (10);
        aeris.setSpeed (30);
        aeris.setConstitution (10);
        aeris.setDexterity (20);
        
        caitSith.addXP (1);
        caitSith.setStrength (20);
        caitSith.setSpeed (25);
        caitSith.setConstitution (15);
        caitSith.setDexterity (10);
        
        Attack attack1 = new Attack ( cloud, cid );
        Attack attack2 = new Attack ( aeris, caitSith );
        
        attack1.start();
        attack2.start();
    
    }
}