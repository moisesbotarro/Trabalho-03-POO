import java.util.*;

public class Battle{


    // Método para inciar a batalhe entre dois times, passados como parâmetro
    public static void startBattle ( Team team1, Team team2 ){
    
        // Vetor de Threads para gerenciar os ataques do Time 1
        ArrayList<AttackThread> threadsTeam1 = new ArrayList<AttackThread>( team1.size() );
        
        // Vetor de Threads para gerenciar os ataques do Time 2
        ArrayList<AttackThread> threadsTeam2 = new ArrayList<AttackThread>( team2.size() );
        
        // Percorre o time 1, preparando seus personagens para atacar
        for ( int i = 0; i < team1.size(); i++ ){
            
            // Prepara a thread para o ataque do personagem
            AttackThread newAttackThread = new AttackThread( team1.searchChar(i), team2.getRandomCharacter() );
            
            // Insere a thread no vetor de threads do time 1
            threadsTeam1.add( newAttackThread );
        }
        
        // Percorre o time 2, preparando seus personagens para atacar
        for ( int i = 0; i < team2.size(); i++ ){
            
            // Prepara a thread para o ataque do personagem
            AttackThread newAttackThread = new AttackThread( team2.searchChar(i), team1.getRandomCharacter() );
            
            // Insere a thread no vetor de threads do time 1
            threadsTeam2.add( newAttackThread );
            
        }
        
        System.out.println ("================================= THE BATTLE BEGINS =================================\n\n");
        
        // Inicializa os ataques dos dois times, de forma intercalada
        
        int startedAttacksTeam1 = 0;
        int startedAttacksTeam2 = 0;
        
        while ( startedAttacksTeam1 < team1.size() && startedAttacksTeam2 < team2.size() ){
            
            threadsTeam1.get(startedAttacksTeam1).start();
            threadsTeam2.get(startedAttacksTeam2).start();
            
            startedAttacksTeam1++;
            startedAttacksTeam2++;
            
        }
        
        // Se ainda há personagens no Time 1 para atacar
        while ( startedAttacksTeam1 < team1.size() ){
            
            threadsTeam1.get(startedAttacksTeam1).start();
            startedAttacksTeam1++;
        
        }
        
        
        // Se ainda há personagens no Time 2 para atacar
        while ( startedAttacksTeam2 < team2.size() ){
        
            threadsTeam2.get(startedAttacksTeam2).start();
            startedAttacksTeam2++;
            
        }
        
        // Percorre os dois vetores de Threads, esperando a finalização de todas
        for ( int i = 0; i < threadsTeam1.size(); i++ ){
        
            threadsTeam1.get(i).join();
        }
        
        for ( int i = 0; i < threadsTeam2.size(); i++ ){
        
            threadsTeam2.get(i).join();
        }
        
        System.out.println ("\n\n================================== BATTLE FINISHED ==================================\n\n");
        System.out.println ("==== Result ====");
        
        // Chama o método para determinar qual time ganhou a batalha e atualizar seus resultados
        int result; // Usada para determinar qual time venceu a batalha
        
        result = team1.resolveBattle ( team2 );
        
        if ( result > 0 )
            System.out.println ( "\t" + team1.getName() + " venceu a batalha contra " + team2.getName() );
        
        else if ( result == 0 )
            System.out.println ( "\tOcorreu empate na batalha entre " + team1.getName() + " e " + team2.getName() );
        
        else
            System.out.println ( "\t" + team2.getName() + " venceu a batalha contra " + team1.getName() );

        // Atualiza as estatísticas do Time2
        team2.resolveBattle ( team1 );
        
        System.out.println("\n"); 
    }
    
    public static void main (String args[]){
    
        // Declaração e instanciamento de 8 personagens
        Knight cloud = new Knight ("Cloud", 20);
        Knight cid = new Knight ("Cid", 15);
        Wizard aeris = new Wizard ("Aeris", 30);
        Thief caitSith = new Thief ("Cait Sith", 10);
        
        Knight sephirot = new Knight ("Sephirot", 20);
        Wizard jenova = new Wizard ("Jenova", 25);
        Wizard hojo = new Wizard ("Professor Hojo", 26);
        Thief rufus = new Thief ("Rufus", 8);
        
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
        
        sephirot.addXP (30);
        sephirot.setStrength (30);
        sephirot.setSpeed (30);
        sephirot.setConstitution (20);
        sephirot.setDexterity (20);
        
        jenova.addXP (5);
        jenova.setStrength (20);
        jenova.setSpeed (25);
        jenova.setConstitution (25);
        jenova.setDexterity (20);
        
        hojo.addXP (3);
        hojo.setStrength (10);
        hojo.setSpeed (15);
        hojo.setConstitution (20);
        hojo.setDexterity (20);
        
        rufus.addXP (1);
        rufus.setStrength (20);
        rufus.setSpeed (15);
        rufus.setConstitution (25);
        rufus.setDexterity (20);
        
        // Declaração e instanciamento de 2 times
        Team avalanche = new Team ("Avalanche", Color.green);
        Team blackMateria = new Team ("Black Materia", Color.black);
        
        // Distribuição dos Personagens nos Times
        avalanche.addChar (cloud);
        avalanche.addChar (cid);
        avalanche.addChar (aeris);
        avalanche.addChar (caitSith);
        
        blackMateria.addChar (sephirot);
        blackMateria.addChar (jenova);
        blackMateria.addChar (hojo);
        blackMateria.addChar (rufus);
        
        // Inicia a batalha
        startBattle ( avalanche, blackMateria );
    }
    

}