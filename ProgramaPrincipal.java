/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java do Programa Principal para teste das Classes
 */

 /* OBS: Utilizando um Pacote Items com a classe GameCharacter fora desse pacote, o compilador não conseguia compilar as classes internas ao pacote que faziam uso de GameCharacter. Dessa forma, não foi utilizado o pacote Items e todas as classes do trabalho foram colocadas em uma mesma pasta. */

import java.util.*;

public class ProgramaPrincipal {

    // Método para retornar se um personagem já foi escolhido ou não para atacar em uma rodada
    private static boolean hasAlreadyChosen( ArrayList<Integer> alreadyChosen, int characterPosition ){

        // Procura no vetor de personagens que já atacaram em uma rodada, se o personagem na posição passada por parâmetro já atacou
        for ( int i = 0; i < alreadyChosen.size(); i++ ){
            
            if ( characterPosition == alreadyChosen.get(i) )
                return true; // Personagem já atacou na rodada atual
        
        }
        
        return false; // Personagem não atacou na rodada atual
    }


    // Função para escolher o próximo personagem a atacar
    private static int nextToAttack ( Team team, ArrayList<Integer> alreadyChosen ){
        
        // Variável para indicar quantas tentativas foram feitas na escolha do próximo personagem para atacar
        int howManyAttemptsOnAttack = 0;
        
        // Escolhe um personagem aleatório
        int nextAttack = GameCharacter.rnd(0, team.size() - 1 );
        
        // Flag para indicar se um personagem foi escolhido
        boolean chosen = false;
        
        do{
            howManyAttemptsOnAttack++;
            
            // Se o personagem escolhido  não está morto e não atacou anteriormente, sua escolha é válida
            if ( !hasAlreadyChosen( alreadyChosen, nextAttack ) && !team.searchChar(nextAttack).isDead() ){
                chosen = true;
            
            }
        
            //Caso contrário escolhe o personagem imediatamente seguinte na posição do vetor characters da Classe Team
            else{
            
                // Caso chegue ao final do vetor, o operador % garante que o próximo personagem será o primeiro elemento do vetor
                nextAttack = ( nextAttack + 1 ) % team.size();
            }
        
        
        } while ( !chosen && ( howManyAttemptsOnAttack < team.size() ) ); // Permanece no Loop enquanto não foi escolhido um personagem e ainda há possibilidade de haver um personagem para ser escolhido
        
        // Se um personagem foi escolhido, retorna sua posição no vector characters da classe Team
        if ( chosen ){
            
            //Insere a posição do personagem escolhido no vetor alreadyChosen
            alreadyChosen.add(nextAttack);
            
            return nextAttack;
            
        }
        
        // Retorna -1 caso nenhum personagem foi escolhido
        return -1;
    }

    // Função para escolher o próximo personagem a receber ataque
    private static int nextToReceiveAttack ( Team team ){
        
        // Variável para indicar quantas tentativas foram feitas na escolha do próximo personagem para receber ataque
        int howManyAttemptsOnReceiveAttack = 0;
        
        // Escolhe um personagem de uma posição aletória
        int next = GameCharacter.rnd ( 0, team.size() - 1);
        
        // Flag para indicar se um personagem foi escolhido
        boolean chosen = false;
        
        do{
            howManyAttemptsOnReceiveAttack++;
            
            // Se o personagem escolhido  não está morto, sua escolha é válida
            if ( !team.searchChar(next).isDead() ){
                chosen = true;
            
            }
        
            //Caso contrário, escolhe o personagem imediatamente seguinte na posição do vetor characters da Classe Team
            else{
             
                // Caso chegue ao final do vetor, o operador % garante que o próximo personagem será o primeiro elemento do vetor
                next = ( next + 1 ) % team.size();
            }

        } while ( !chosen && howManyAttemptsOnReceiveAttack < team.size() );// Permanece no Loop enquanto não foi escolhido um personagem e ainda há possibilidade de haver um personagem para ser escolhido

        // Se um personagem foi escolhido, retorna sua posição no vector characters da classe Team
        if ( chosen ){
        
            return next;
        }
        
        // Retorna -1 caso nenhum personagem foi escolhido
        return -1;

    }


    // Função que recebe como parâmetro os dois times que irão lutar e retorna nos respectivos vetores attackOrder a ordem de ataque dos personagens de cada time
    private static void battle ( Team teamA, Team teamB ){
        
        // Vetores para guardar a posição dos personagens que já foram escolhidos
        ArrayList<Integer> alreadyChosenTeamA, alreadyChosenTeamB;
        alreadyChosenTeamA = new ArrayList<Integer>();
        alreadyChosenTeamB = new ArrayList<Integer>();
        
        // Variável para indicar quando terminar a batalha
        boolean endBattle = false;
        
        // Variável que indica quem inicia a batalha.
        int nextTeam = GameCharacter.rnd (0,1);
        
        // Variável pra indicar quantas tentativas foram feitas dentro de um time na escolha do personagem que recebe o ataque
        int howManyAttemptsOnReceivingAttack = 0;
        
        // Variáveis para guardar a posição do personagem que irá atacar e do personagem que receberá o ataque
        int nextGameCharacterToAttack, nextGameCharacterToReceiveAttack;
        
        // Enquando todos os personagens de um time não estiverem mortos, continua a batalha
        while ( !endBattle ){
            
            switch ( nextTeam ){
            
                case 0: // Time A ataca
                
                    // Escolhe um personagem aleatório do time A para atacar. O personagem deve estar vivo
                    nextGameCharacterToAttack = nextToAttack ( teamA, alreadyChosenTeamA );
                    
                    // Se nenhum personagem foi escolhido e o vetor alreadyChosenA estiver vazio, indica que todos os personagens do time A estão mortos
                    if ( nextGameCharacterToAttack == -1 && alreadyChosenTeamA.size() == 0 ){
                        
                        endBattle = true;
                        break;
                    }
                    
                    // Se há personagens em alreadyChosen, indica que é preciso esvaziar o vetor, liberando os personagens cujas posições estão nele, para atacar novamente
                    else if ( nextGameCharacterToAttack == -1 && alreadyChosenTeamA.size() > 0 ){
                        
                        alreadyChosenTeamA.clear();
                        break; // Para sair do Switch e voltar novamente para escolher o próximo personagem para atacar
                    }
                    
                    // O personagem escolhido ataca um personagem escolhido aleatoriamente do outro time
                    nextGameCharacterToReceiveAttack = nextToReceiveAttack( teamB );
                    
                    // Se não há mais personagem para receber ataque, significa que todos os personagens do outro time morreram
                    if ( nextGameCharacterToReceiveAttack == -1 ){
                        System.out.println ( "Todos os personagens de " + teamB.getName() + " morreram" );
                        endBattle = true;
                    }
                    
                    else{
                        
                        // Realiza o ataque
                        System.out.println ( "TIME A ataca" );
                        
                        teamA.searchChar( nextGameCharacterToAttack ).attack ( teamB.searchChar(nextGameCharacterToReceiveAttack) );
                        nextTeam = 1; // Próximo time a atacar é o time B
                        
                    }
                    
                    break;
                
                case 1: // Time B ataca
                
                    // Escolhe um personagem aleatório do time B para atacar. O personagem deve estar vivo
                    nextGameCharacterToAttack = nextToAttack ( teamB, alreadyChosenTeamB );
                    
                    // Se nenhum personagem foi escolhido e o vetor alreadyChosenB estiver vazio, indica que todos os personagens do time B estão mortos
                    if ( nextGameCharacterToAttack == -1 && alreadyChosenTeamB.size() == 0 ){
                        
                        endBattle = true;
                        break;
                    }
                    
                    // Se há personagens em alreadyChosen, indica que é preciso esvaziar o vetor, liberando os personagens cujas posições estão nele, para atacar novamente
                    else if ( nextGameCharacterToAttack == -1 && alreadyChosenTeamB.size() > 0 ){
                        
                        alreadyChosenTeamB.clear();
                        break; // Para sair do Switch e voltar novamente para escolher o próximo personagem para atacar
                    }
                    
                    // O personagem escolhido ataca um personagem escolhido aleatoriamente do outro time
                    nextGameCharacterToReceiveAttack = nextToReceiveAttack( teamA );
                    
                    // Se não há mais personagem para receber ataque, significa que todos os personagens do outro time morreram
                    if ( nextGameCharacterToReceiveAttack == -1 ){
                        
                        endBattle = true;
                    }
                    
                    else{
                        
                        // Realiza o ataque
                        System.out.println ( "TIME B ataca" );
                        
                        teamB.searchChar( nextGameCharacterToAttack ).attack ( teamA.searchChar(nextGameCharacterToReceiveAttack) );
                        nextTeam = 0; // Próximo time a atacar é o time A
                        
                    }
                    
                    break;
            }
        }
    }

    public static void  main ( String args[] ){
        
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
        
        // Declaração e instaciamento de 6 armaduras
        Armor bronzeB = new Armor ("Bronze Bangle", 100, 4, 20);
        Armor ironB = new Armor ("Iron Bangle", 160, 5, 19.5);
        Armor titanB = new Armor ("Titan Bangle", 280, 7, 17.5 );
        Armor carbonB = new Armor ("Carbon Bangle", 800, 10, 13.2);
        Armor diamondB = new Armor ("Diamond Bangle", 3200, 15, 10.1);
        Armor crystalB = new Armor ("Crystal Bangle", 4800, 20, 5.7);

        // Declaração e instanciamento de 10 armas
        Weapon ultimaW = new Weapon ("UltimaWeapon Sword", 9999, 9, 10.5);
        Weapon wizardS = new Weapon ("Wizard Staff", 8000, 5, 8.5);
        Weapon stlPhone = new Weapon ("Starlight Phone", 6000, 6, 5.5);
        Weapon spiritL = new Weapon ("Spirit Lance", 9000, 8, 15.0);
        Weapon ragnarok = new Weapon ("Ragnarok Sword", 7000, 7, 9.9);
        
        Weapon masamune = new Weapon ("Masamune Sword", 9999, 9, 11.2);
        Weapon blackS = new Weapon ("Black Sword", 7500, 7, 6.8);
        Weapon apocalypse = new Weapon ("Apocalypse Sword", 7000, 6, 8.5);
        Weapon microLaser = new Weapon ("MicroLaser", 8000, 6, 5.5);
        Weapon boomerang = new Weapon ("Boomerang", 1000, 1, 20.0);
        
        // Declaração e Instanciamento de 5 HealthPotion
        HealthPotion hpPotion = new HealthPotion ("HP Potion", 10, 10);
        HealthPotion hiPotion = new HealthPotion ("Hi-Potion", 20, 20);
        HealthPotion xPotion = new HealthPotion ("X-Potion", 50, 30);
        HealthPotion elixir = new HealthPotion ("Elixir", 100, 50);
        HealthPotion megalixir = new HealthPotion ("Megalixir", 500, 100);
        
        // Declaração e Instanciamento de 3 ManaPotion
        ManaPotion mpPotion = new ManaPotion ("MP Potion", 100, 10);
        ManaPotion ether = new ManaPotion ("Ether", 500, 20);
        ManaPotion tEther = new ManaPotion  ("Turbo Ether", 1000, 50);
        
        // Distribuição dos Personagens nos Times
        avalanche.addChar (cloud);
        avalanche.addChar (cid);
        avalanche.addChar (aeris);
        avalanche.addChar (caitSith);
        
        blackMateria.addChar (sephirot);
        blackMateria.addChar (jenova);
        blackMateria.addChar (hojo);
        blackMateria.addChar (rufus);
        
        // Distribuição das armas, armaduras e poções
        // Obs: apesar de já existirem ponteiros para os personagems, será usado o método Team::searchChar() para teste
        avalanche.searchChar ("Cloud").addSpacesInInventory (10);
        avalanche.searchChar ("Cloud").storeItem (ultimaW);
        avalanche.searchChar ("Cloud").storeItem (microLaser);
        avalanche.searchChar ("Cloud").storeItem (crystalB);
        avalanche.searchChar ("Cloud").storeItem (megalixir);
        
        avalanche.searchChar ("Cid").addSpacesInInventory(10);
        avalanche.searchChar ("Cid").storeItem (spiritL);
        avalanche.searchChar ("Cid").storeItem (titanB);
        avalanche.searchChar ("Cid").storeItem (xPotion);
        
        avalanche.searchChar ("Aeris").addSpacesInInventory(10);
        avalanche.searchChar ("Aeris").storeItem (wizardS);
        avalanche.searchChar ("Aeris").storeItem (ironB);
        avalanche.searchChar ("Aeris").storeItem (tEther);
        
        avalanche.searchChar ("Cait Sith").addSpacesInInventory(10);
        avalanche.searchChar ("Cait Sith").storeItem (stlPhone);
        avalanche.searchChar ("Cait Sith").storeItem (hiPotion);
        
        blackMateria.searchChar ("Sephirot").addSpacesInInventory(10);
        blackMateria.searchChar ("Sephirot").storeItem (masamune);
        blackMateria.searchChar ("Sephirot").storeItem (boomerang);
        blackMateria.searchChar ("Sephirot").storeItem (diamondB);
        blackMateria.searchChar ("Sephirot").storeItem (elixir);
        
        blackMateria.searchChar ("Jenova").addSpacesInInventory(10);
        blackMateria.searchChar ("Jenova").storeItem (blackS);
        blackMateria.searchChar ("Jenova").storeItem (bronzeB);
        blackMateria.searchChar ("Jenova").storeItem (ether);
        
        blackMateria.searchChar ("Professor Hojo").addSpacesInInventory(10);
        blackMateria.searchChar ("Professor Hojo").storeItem (apocalypse);
        blackMateria.searchChar ("Professor Hojo").storeItem (carbonB);
        blackMateria.searchChar ("Professor Hojo").storeItem (mpPotion);
        
        blackMateria.searchChar ("Rufus").addSpacesInInventory(10);
        blackMateria.searchChar ("Rufus").storeItem (ragnarok);
        blackMateria.searchChar ("Rufus").storeItem (hpPotion);
        
        // Personagens usam Poção
        avalanche.searchChar("Cloud").useItem ("Megalixir");
        avalanche.searchChar("Cid").useItem ("X-Potion");
        avalanche.searchChar("Aeris").useItem ("Turbo Ether");
        avalanche.searchChar("Cait Sith").useItem ("Hi-Potion");
        
        blackMateria.searchChar("Sephirot").useItem ("Elixir");
        blackMateria.searchChar("Jenova").useItem ("Ether");
        blackMateria.searchChar("Professor Hojo").useItem ("MP Potion");
        blackMateria.searchChar("Rufus").useItem ("HP Potion");
        
        // Personagens equipam as armas e armaduras
        avalanche.searchChar ("Cloud").equipItem ("UltimaWeapon Sword");
        avalanche.searchChar ("Cloud").equipItem ("MicroLaser");
        avalanche.searchChar ("Cid").equipItem ("Spirit Lance");
        avalanche.searchChar ("Aeris").equipItem ("Wizard Staff");
        avalanche.searchChar ("Cait Sith").equipItem ("Starlight Phone");
        
        avalanche.searchChar ("Cloud").equipItem ("Crystal Bangle");
        avalanche.searchChar ("Cid").equipItem ("Titan Bangle");
        avalanche.searchChar ("Aeris").equipItem ("Iron Bangle");
        
        blackMateria.searchChar ("Sephirot").equipItem ("Masamune Sword");
        blackMateria.searchChar ("Sephirot").equipItem ("Boomerang");
        blackMateria.searchChar ("Jenova").equipItem ("Black Sword");
        blackMateria.searchChar ("Professor Hojo").equipItem ("Apocalypse Sword");
        blackMateria.searchChar ("Rufus").equipItem ("Ragnarok Sword");
        
        blackMateria.searchChar ("Sephirot").equipItem ("Diamond Bangle");
        blackMateria.searchChar ("Jenova").equipItem ("Bronze Bangle");
        blackMateria.searchChar ("Professor Hojo").equipItem ("Carbon Bangle");
        
        // Imprime atributos dos personagens na tela
        cloud.printInfo();
        cid.printInfo();
        aeris.printInfo();
        caitSith.printInfo();
        sephirot.printInfo();
        jenova.printInfo();
        hojo.printInfo();
        rufus.printInfo();
        
        // Inicia-se a Batalha
        System.out.println ("=================================================================" );
        System.out.print ( "*" );
        System.out.print ( "                        START BATTLE" );
        System.out.println ( "                           *" );
        System.out.println ( "=================================================================" );
        
        battle ( avalanche, blackMateria );
        
        System.out.println ( "=================================================================" );
        System.out.print ( "*" );
        System.out.print ( "                        END OF BATTLE" );
        System.out.println ( "                          *" );
        System.out.println ( "=================================================================" );
        
        avalanche.resolveBattle ( blackMateria );
        blackMateria.resolveBattle ( avalanche );
        
        System.out.println ( "=================================================================" );
        System.out.print ( "*" );
        System.out.print ( "                      RESULTADO FINAL" );
        System.out.println ( "                          *" );
        System.out.println ( "=================================================================" );
        
        System.out.println ( "--------------- " + avalanche.getName() + " ---------------" );
        System.out.println ( avalanche.toString() );
        System.out.println ( avalanche.getResults() );
        
        System.out.println ( "--------------- " + blackMateria.getName() + " ---------------" );
        System.out.println ( blackMateria.toString() );
        System.out.println ( blackMateria.getResults() );
        
    }


}