/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe Team
 */

import java.util.*;

public class Team{

    /* Atributos */
    private String name;
    private Color color;
    private int win;
    private int lose;
    private int draw;
    private ArrayList<GameCharacter> characters;
    
    /* Métodos */
    
    // Construtor
    public Team ( String name, Color color ){
        
        this.name = name;
        this.color = color;
        characters = new ArrayList<GameCharacter>();
        
        // Inicialização dos resultados com todos valendo 0
        this.win = 0;
        this.lose = 0;
        this.draw = 0;
    }
    
    // Método para eliminar todos os personagens do time)
    public void clearTeam() {
    
        characters.clear(); //Remove todos os elementos do ArrayList characters
    }
    
    // Método que retorna o nome do time
    public String getName(){
    
        return this.name;
    }
    
    // Método que retorna os resultados do time
    public String getResults(){
    
        return "Vitorias: " + win + "\nDerrotas: " + lose + "\nEmpates: " + draw + '\n';
    }
    
    // Método que retorna em uma string, nome e cor do time
    public String toString(){
        return "Time: " + name + " Cor: " + color + '\n';
    }
    
    // Método para resolver uma batalha com um time com o qual acabou de lutar
    public int resolveBattle ( Team teamB ){
        
        // Guarda em result a diferença da média de HP dos personagens do time que chama o método em relação ao time B
        double result = this.getPoints() - teamB.getPoints();
        
        // Caso a média de HP dos personagens do time que chamou o método seja mair -> time que chamou o método venceu
        if ( result > 0 ){
            this.win++;
        }
        
        // Caso a média de HP dos personagens dos dois times seja igual -> Ocorreu empate
        else if ( result == 0 ){
            this.draw++;
        }
        
        // Caso a média de HP do time que chamou o método seja menor que a do time B -> time que chamou o método perdeu
        else{ // if( result < 0 )
            this.lose++;
        }
        
        return (int)result;
    }
    
    // Método para adicionar um personagem ao time
    public void addChar ( GameCharacter character ){
        
        //Adiciona personagem na lista de characters
        characters.add( character );
    }
    
    // Método para remover um personagem passando sua posição no ArrayList como parâmetro
    public void removeChar ( int charPosition ){
        
        // Se posição passada como parâmetro está fora do ArrrayList, imprime um erro na tela
        if ( charPosition >= characters.size() ){
            System.out.println ("Posicao passada em removeChar() é invalida. Nenhum personagem removido");
            return;
        }
        
        // Remove o personagem
        characters.remove( charPosition );
    }
    
    // Método para remover um personagem do time passando uma referência desse personagem como parâmetro
    public void removeChar ( GameCharacter character ){
        
        // Procura a primeira referência a esse personagem no ArrayList e o remove do time
        for ( int i = 0; i < characters.size(); i++ ){
            
            if ( characters.get(i) == character ){
                characters.remove(i);
                return; // Termina o método ao remover a primeira referência do personagem
            }
        }
        
        System.out.println ("ERRO - removeChar( GameCharacter ): Personagem " + character.getName() + " não está presente no time " + name );
    }
    
    // Método para procurar um personagem por seu nome, retornando uma referência para ele
    public GameCharacter searchChar ( String charName ){
        
        GameCharacter matchedChar;
        
        // Procura o personagem pelo nome no ArrayList, retornando uma cópia do personagem cujo nome é o mesmo de charName passado como parâmetro
        for ( int i = 0; i < characters.size(); i++ ){
            
            matchedChar = characters.get(i);
            
            if ( matchedChar.getName().equals( charName )  )
                return matchedChar;
        }
        
        System.out.println ("Personagem com nome + " + charName + " não está presente no time " + this.name );
        return null;
    }
    
    // Método que retorna a pontuação do time (double), indicada pela média dos HP de todos os personagens
    public double getPoints (){
        
        double media = 0;
        
        // Percorre o vetor de personagens, somando o HP de todos
        for ( int i = 0; i < characters.size(); i++ ){
            
            media += characters.get(i).getHP();
        }
        
        // Divide a soma dos HP pelo tamanho do time para obter a média
        media =  ( media / (double)characters.size() );
    
        return media;
    }
    
    // Método que retorna quantos personagens existem no time
    public int size(){
        
        return characters.size();
    }
    
    // Método auxiliar para retornar o personagem na posição do vetor characters passada por parâmetro - Usado na main
    public GameCharacter searchChar ( int charPosition ){

        if ( charPosition < 0 || charPosition >= characters.size() ){
            System.out.println ( "position: " + charPosition + " size: " + characters.size() );
            
            System.out.println ( "Posicao passada para GameCharacter::searchChar( int charPosition ) eh invalida" );
            return null;
            
        }
        
        return characters.get(charPosition);
    }
}