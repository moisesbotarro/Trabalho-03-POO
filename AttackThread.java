/*
 *  Trabalho 03
 *  Alunos: Igor Quintal Mendes,            N. USP: 8622353
 *          Moisés Botarro Ferraz Silva,    N. USP: 8504135
 */

// Classe para Criar uma Thread que representa um personagem atacando outro personagem

import java.util.*;
import java.lang.*;

public class AttackThread implements Runnable{

    /* Atributos */
    private Thread t; // Thread que tratará a execução do ataque
    private GameCharacter attacker; // Personagem que irá atacar
    private GameCharacter attacked; // Personagem que receberá o ataque
    private String threadName;
    
    // Construtor da Thread - Recebe o personagem que irá atacar e o personagem que será atacado
    public AttackThread( GameCharacter attacker, GameCharacter attacked ){
        
        this.attacker = attacker;
        this.attacked = attacked;
        threadName = attacker + " atacando " + attacked;
    
    }
    
    // Método que dará partida na Thread
    public void start (){
    
        // Se o personagem que irá atacar estiver morto, não inicia a thread
        if ( attacker.isDead() )
            return;
        
        System.out.println ( attacker.getName() + " se preparando para atacar " + attacked.getName() );
        
        t = new Thread (this, threadName );
        t.start();
    }
    
    // Método que executa o ataque na Thread
    public void run() {
    
        System.out.println ( "--> " + attacker.getName() + " correndo em direção a " + attacked.getName() );
        
        // Sorteia um tempo aleatório para o personagem esperar para atacar o outro, entre 0s e 5s
        double randomNumber =  ( 5 ) * ( 1000 * Math.random() ); // O tempo deve estar em milissegundos
        
        try {
        
            synchronized(this){
            
                // Variável usada para verificar se o personagem irá parar por pelo menos 1s
                int intNumberToCompare = (int)(randomNumber/1000);
            
                // Imprime a mensagem apenas se o personagem parou por pelo menos 1s
                if ( intNumberToCompare != 0 )
                    System.out.format ( "--x " + attacker.getName() + " parou para descansar por pelo menos %.0f segundos.\n", (randomNumber/1000) );
                
                Thread.sleep ((long)randomNumber);
            }
            
        } catch ( InterruptedException e ) {
        
            // Não é necessário tratar a exceção nesse contexto
        }
        
        // O próximo bloco deve ser executada em sequência, sem ser interrompido - Realiza o ataque
        synchronized(attacked) {
        
            // Verifica se o personagem ainda está vivo
            if ( !attacker.isDead() ){
                
                // Verifica se o alvo ainda está vivo
                if ( attacked.isDead() ){
                    
                    System.out.println ( "-----| " + attacker.getName() + " não pode atacar " + attacked.getName() + " porque " + attacked.getName() + " está morto." );
                    return; // Termina a thread
                }
                
                // Realiza o ataque
                attacker.attack ( attacked );
            }
        }
        
    }
    
    // Método join para chamar o join() da thread criada por AttackThread
    public void join(){
    
        // Deve-se verificar se já foi iniciada uma thread
        if ( t != null ){
            
            try{
            
                t.join();
                
            } catch ( InterruptedException e ){
            
                // Não é necessário tratar a exceção nesse contexto
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
        
        AttackThread attack1 = new AttackThread ( cloud, cid );
        AttackThread attack2 = new AttackThread ( aeris, caitSith );
        
        attack1.start();
        attack2.start();
    
    }
}