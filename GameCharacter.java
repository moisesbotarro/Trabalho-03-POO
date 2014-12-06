/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe GameCharacter
 */

import java.util.*;

public abstract class GameCharacter implements Printable {

    /* Atributos */
    private String alias;
    private Inventory myItems;
    private int HP;
    private int MP;
    protected int XP;
    protected int strength;
    protected int speed;
    protected int dexterity;
    protected int constitution;
    
    /* Atributos extras criados a parte */

    private double speedPenality; // Atributo para guardar a penalidade gerada por equipar uma armadura. Deve ser multiplicada por speed para gerar a speed real do personagem
    private boolean dead; // Atributo para indicar se o personagem morreu (true) ou não (false)
    
    /* Métodos */
    
    /* Construtor para inicializar um personagem passando seu nome como parâmetro */
    public GameCharacter ( String alias ) {
        this.alias = alias;
        
        // Inicializa o Inventário
        myItems = new Inventory();
        
        // Inicialização dos demais atributos
        this.HP = 100;
        this.MP = 0;
        this.XP = 1;
        this.strength = 0;
        this.speed = 0;
        this.dexterity = 0;
        this.constitution = 0;
        this.speedPenality = 1; // É multiplicado com speed
        this.dead = false;
    }
    
    // Método para retornar o nome do personagem
    public String getName() {
        
        return this.alias;
    }

    public Inventory getInventory() {

        return myItems;
    }
    
    // Calcula os pontos de defesa do personagem. Deve ser implementado nas classes filhas
    protected abstract int getDefensePoints();
    
    // Calcula os pontos de ataque do personagem. Deve ser implementado nas classes filhas
    protected abstract int getAttackPoints ();
    
    // Método para atacar um outro personagem
    public void attack ( GameCharacter character2 ) {
        
        // Cálculo do dano gerado pelo ataque
        int damage;
        damage = (this.getAttackPoints() - character2.getDefensePoints() ) + rnd(-5, 5);
        
        // Se o dano gerado for menor ou igual a 0, deve-se aplicar dano 1
        if ( damage <= 0 ) {
            damage = 1;
        }
        
        // Determinação se haverá ou não falha de ataque (miss)
        boolean miss = false; //Inicialmente, não haverá miss
        
        double randNumber; // Número aleatório gerado entre 0 e 1
        randNumber = Math.random();
        
        if ( randNumber >= 0 && randNumber <= ( 0.1/(double)XP ) ) {
            miss = true; // Se o número aleatório gerado cair no intervalo entre 0 e a chance de ocorrer um miss, ocorrerá um miss
        }
        
        if ( miss ) { //Caso haja uma falha de ataque, não realiza o ataque
            System.out.println ("---->> Falha de ataque de " + getName() );
            return;
        }
    
        // Realiza o ataque
    
        // Determina se o ataque será crítico ou não
        boolean criticalStrike = false; // Inicialmente, não haverá ataque crítico
        
        randNumber = Math.random();
        
        if ( randNumber >= 0 && randNumber <= ( 0.02 * ( (double)XP / 2.0 ) ) ) {
            criticalStrike = true; // Se o número aleatório gerado cair no intervalo entre 0 e a chance de ocorrer um ataque crítico, ocorrerá um ataque crítico
        }
        
        // Se o ataque for crítico, dobra o dano que será aplicado
        if ( criticalStrike ) {
            damage = damage * 2;
            System.out.print (">>>>>> ATAQUE CRITICO!! ");
        }
        
        character2.receiveDamage ( damage );
        
        // O próximo teste é utilizado apenas para controlar o formato de impressão
        if ( criticalStrike )
            System.out.println (this.getName() + " tirou " + damage + "HP de " + character2.getName() );
        
        else
             System.out.println ("---->> " + this.getName() + " tirou " + damage + "HP de " + character2.getName() );
        
        // Se o HP do personagem atacado chegou ao fim, indica que ele morreu
        if ( character2.getHP() <= 0 ){
            
            System.out.println ( "xx__xx " + character2.getName() + " IS DEAD");
            character2.dead = true;
            character2.HP = 0; // Personagem não pode ter HP negativo. Evita problemas na hora de contabilizar pontos do time.
        }
    }
    
    
    // Método auxiliar para gerar um número aleatótio entre minimo e maximo
    public static int rnd ( int minimo, int maximo ) {
        
        int number =  ( (int)( Math.random() * ( maximo - minimo + 1) ) + minimo ); // Math.random() gera um double entre 0 e 1. Multiplica pela quantidade de números inteiros que se deseja gerar ( maximo - minimo + 1). Assim, ajusta a quantidade de números inteiros que devem ser gerados. Deve-se adicionar a essa quantidade, o número mínimo que se deseja obter, uma vez que a operação anterior extende a quantidade de números inteiros que podem ser gerados, mas continua iniciando em 0. Soma-se 1 em ( maximo - minimo + 1 ) porque random() gera números entre 0 (inclusivo) e 1 (exlcusivo).
        
        return number;
    }
    
    // Método para adicionar pontos de experiência XP ( quantidade adicionada pode ser negativa )
    public void addXP ( int XPgained ) {
        
        // O atributo XP não deve ultrapassar 100 pontos
        if ( (this.XP + XPgained) >= 100 ) {
            System.out.println ("XP nao deve ultrapassar 100 pontos");
            return;
        }
        
        // Aumenta o atributo XP
        this.XP += XPgained;
    }
    
    /* Setters para atributos */
    
    // Strength
    public void setStrength ( int strength ) {
        
        // Primeiramente, checa se o valor do atributo está dentro da faixa permitida (entre 1 e 100)
        if ( strength < 1 || strength > 100 ){
            System.out.println ("Strenght deve valer entre 1 e 100. Valor de strenght não alterado");
            return;
        }
        
        // Altera o atributo e checa se a soma dos atributos básicos é válida. Caso não seja, retorna no atributo seu valor antigo
        int temp = this.strength;
        this.strength = strength;
        
        if ( !sumOfBasicAtributesIsValid() ) {
            System.out.println ("Soma dos atributos basicos nao eh valida. Strength nao foi alterado");
            this.strength = temp;
            return;
        }
    }
    
    // Speed
    public void setSpeed ( int speed ) {
        
        // Primeiramente, checa se o valor do atributo está dentro da faixa permitida (entre 1 e 100)
        if ( speed < 1 || speed > 100 ){
            System.out.println ("Speed deve valer entre 1 e 100. Valor de speed não alterado");
            return;
        }
        
        // Deve-se checar se o personagem está usando uma armadura que altera o seu speed. Nesse caso, deve-se alterar a speed sem a penalidade da armadura e aplicar novamente a penalidade
        
        // Altera o atributo e checa se a soma dos atributos básicos é válida. Caso não seja, retorna no atributo seu valor antigo
        int temp = this.speed;
        this.speed = speed;
        
        if ( !sumOfBasicAtributesIsValid() ) {
            System.out.println ("Soma dos atributos basicos nao eh valida. Speed nao foi alterado");
            this.speed = temp;
            return;
        }
    }
    
    // Dexterity
    public void setDexterity ( int dexterity ) {
        
        // Primeiramente, checa se o valor do atributo está dentro da faixa permitida (entre 1 e 100)
        if ( dexterity < 1 || dexterity > 100 ){
            System.out.println ("Dexterity deve valer entre 1 e 100. Valor de dexterity não alterado");
            return;
        }
        
        // Altera o atributo e checa se a soma dos atributos básicos é válida. Caso não seja, retorna no atributo seu valor antigo
        int temp = this.dexterity;
        this.dexterity = dexterity;
        
        if ( !sumOfBasicAtributesIsValid() ) {
            System.out.println ("Soma dos atributos basicos nao eh valida. Dexterity nao foi alterado");
            this.dexterity = temp;
            return;
        }
    }
    
    // Constitution
    public void setConstitution ( int constitution ) {
        
        // Primeiramente, checa se o valor do atributo está dentro da faixa permitida (entre 1 e 100)
        if ( constitution < 1 || constitution > 100 ){
            System.out.println ("Constitution deve valer entre 1 e 100. Valor de constitution não alterado");
            return;
        }
        
        // Altera o atributo e checa se a soma dos atributos básicos é válida. Caso não seja, retorna no atributo seu valor antigo
        int temp = this.constitution;
        this.constitution = constitution;
        
        if ( !sumOfBasicAtributesIsValid() ) {
            System.out.println ("Soma dos atributos basicos nao eh valida. Constitution nao foi alterado");
            this.constitution = temp;
            return;
        }
    }
    
    // Método para adicionar uma quantidade (inclusive negativa) ao HP do personagem
    public void addHP ( int moreHP ){
        
        this.HP += moreHP;
    }
    
    // Método para adicionar uma quantidade (inclusive negativa) ao MP do personagem
    public void addMP ( int moreMP ){
        
        this.MP += moreMP;
    }
    
    // Método privado para auxiliar os setters para os atributos básicos ( strengt, speed, dexterity e constitution ), uma vez que a soma deles não deve ultrapassar 100
    private boolean sumOfBasicAtributesIsValid () {
        
        int sum = strength + speed + dexterity + constitution;
        
        if ( sum > 100 ){
            return false; // A soma dos atributos não é uma soma válida
        }
        
        return true;
    }
    
    // Método que retorna a quantidade de pontos que o personagem ainda pode distribuir entre os atributos básicos
    public int getRemainingPoints(){
    
        return 100 - ( strength + speed + dexterity + constitution );
    }
    
    /* Método auxiliar para receber dano de outro personagem */
    protected void receiveDamage ( int damage ) {
        
        HP -= damage;
    }
    
    /* Método auxiliar para retornar a quantidade de HP de um personagem */
    public int getHP () {
        
        return HP;
    }
    
    // Método que retorna a quantidade de MP do personagem
    public int getMP(){

        return MP;
    }

    // Método que retorna a quantidade de XP do personagem
    public int getXP(){

        return XP;
    }

    public int getStrength(){

        return strength;
    }

    public int getDexterity(){

        return dexterity;
    }

    public int getConstitution(){

        return constitution;
    }
    
    // Método para adicionar espaço no inventário do personagem
    public void addSpacesInInventory ( int moreSpaces ){
        
        // O novo tamanho do inventário vai ser o tamanho atual mais a quantidade a mais que se deseja somar
        myItems.setSpaces ( myItems.getSpaces() + moreSpaces );
    }
    
    // Método para adicinar um item ao inventário do personagem
    public void storeItem ( Item item ){

        myItems.insertItem ( item );
    }
    
    // Método para equipar uma arma ou armadura
    public void equipItem ( String itemName ){
        
        // Chama o método useItem(String, GameCharacter) da Classe Inventory. O método use() de cada item tratará o que deve ser feito em cada caso.
        myItems.useItem ( itemName, this );
        
    }
    
    // Método para desequipar uma arma ou armadura
    public void unequipItem( String itemName ){
        
        // Chama o método unequipWeaponOrAmor(String, GameCharacter) da Classe Inventory. O método unequip() de cada item tratará o que deve ser feito em cada caso.
        myItems.unequipWeaponOrArmor ( itemName, this );
    }
    
    // Método para usar uma poção. Tem a mesma função de equipItem(). Só foi criado para ficar de acordo com a intuição de que se equipa uma arma ou armadura e se usa uma poção
    public void useItem ( String itemName ){

        // Chama o método useItem(String, GameCharacter) da Classe Inventory. O método use() de cada item tratará o que deve ser feito em cada caso.
        myItems.useItem ( itemName, this );
    }
    
    // Método para descartar um item
    public void discartItem ( String itemName ){
        
        // Chama método da classe Inventory
        this.myItems.removeItem ( itemName );
    }
    
    // Método para imprimir inventário do personagem
    public void printGameCharacterInventory(){
        
        // Chama o método de Inventory
        myItems.print();
    }
    
    // Método auxiliar para retornar o atributo speed de um personagem. Se tiver com uma armadura equipada, retorna o speed com a penalidade
    public int getSpeed(){

        return (int) ( this.speed * this.speedPenality );
    }
    
    // Método para alterar o atributo speedPenality
    public void setSpeedPenality ( double speedPenality ){
        
        this.speedPenality = speedPenality;
    }
    
    // Método auxiliar para indicar se um personagem morreu ou não
    public boolean isDead(){

        return this.dead;
    }
    
    // Método para retornar o número de armas equipadas no personagem
    public int howManyEquippedWeapons(){
    
        return myItems.howManyEquippedWeapons();
    }
    
    // Método para retornar o número de armaduras equipadas no personagem
    public int howManyEquippedArmors(){
    
        return myItems.howManyEquippedArmors();
    }
    
    // Método para retornar a soma dos attackPts das armas equipadas no personagem
    public int getItemsAttackPts(){
    
        return myItems.getItemsAttackPts();
    }
    
    // Método para retornar a soma do defensePts da armadura equipada no personagem
    public int getItemsDefensePts(){
    
        return myItems.getItemsDefensePts();
    }
    
    // Método auxiliar para imprimir atributos de um personagem
    public void print(){

        System.out.println ( "\n===== CHARACTER INFO ======" );
        System.out.println ( "Nome: " + alias );
        System.out.println ( "HP: " + HP );
        System.out.println ( "MP: " + MP );
        System.out.println ( "XP: " + XP );
        System.out.println ( "Stregth: " + strength );
        System.out.println ( "Speed: " + getSpeed() );
        System.out.println ( "Dexterity: " + dexterity );
        System.out.println ( "Constitution: " + constitution );
        System.out.println ( "Nro. de Armas Equipadas: " + howManyEquippedWeapons() );
        System.out.println ( "Nro. de Armaduras Equipadas: " + howManyEquippedArmors() + '\n');
    }
}