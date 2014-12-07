/*
 *  Trabalho 03
 *  Alunos: Igor Quintal Mendes,            N. USP: 8622353
 *          Moisés Botarro Ferraz Silva,    N. USP: 8504135
 */


// Classe para guardar um par de valores no estilo (First/Second)

public class GenericPair<First, Second>{
        
        private First first; // Primeira posição do par
        private Second second; // Segunda posição do par
        
        // Construtor
        public GenericPair ( First first, Second second ){
            
            this.first = first;
            this.second = second;
        }
        
        // Método para retornar o primeiro objeto do pair
        public First getFirst(){
            
            return first;
        }
        
        // Método para retornar o segundo objeto do pair
        public Second getSecond(){
            
            return second;
        }
        
        // Método para inserir outro objeto na primeira posição do pair
        public void setFirst( First newFirst ){
            
            this.first = newFirst;
        }
    
        // Método para inserir outro objeto na segunda posição do pair
        public void setSecond( Second newSecond ){
            
            this.second = newSecond;
        }
    }