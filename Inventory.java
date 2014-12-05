/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Classe Inventory
 */

import java.util.*;

public class Inventory{

    /* Atributos */
    
    private int spaces; //Espaço total do inventário (incluindo espaço utilizado e livre)
    private double gold;
    private ArrayList<GenericPair<Item, Boolean>> items;
    
    /* Atributos auxiliares criados a parte para guardar a quantidade de armas e armaduras equipadas */
    private int nEquippedWeapons;
    private int nEquippedArmors;
    
    /* Métodos */
    
    /* Construtor */
    
    // Construtor: Invetório vazio, sem espaço e sem dinheiro
    public Inventory(){
        
        spaces = 0;
        gold = 0;
        items = new ArrayList<GenericPair<Item, Boolean>>();
        nEquippedWeapons = 0;
        nEquippedArmors = 0;
    }
    
    /* Método para eliminar todos os itens contidos no inventario */
    
    public void clearInventory(){
        
        items.clear(); // Remove elementos inseridos do vetor
    }
    
    /* Manipulação e Consulta de Dinheiro */
    
    // Método para retornar a quantidade de dinheiro disponível
    public double getTotalGold(){
        
        return gold;
    }
 
    // Método para gastar ouro
    public void spendGold ( double gold ){
        
        // Quantidade a ser gasta deve ser positiva
        if ( gold < 0 ){
            System.out.println ("Quantidade de dinheiro a ser gasta deve ser positiva");
            System.out.println ("Metodo spendGold() Interrompido");
            return;
        }
        
        // Deve possuir a quantidade de dinheiro para ser gasta
        else if ( this.gold - gold < 0 )
        {
            System.out.println ("Não há " + gold + " de ouro em seu inventario para ser gasto!");
            System.out.println ("Metodo spendGold() Interrompido");
            return;
        }
        
        this.gold -= gold;
    }
    
    // Método para ganhar ouro
    public void earnGold ( double gold ){
        
        // Quantidade de ouro a ser ganha deve ser positiva
        if ( gold < 0 ){
            System.out.println ("Quantidade de dinheiro ganho deve ser positiva");
            System.out.println ("Metodo earnGold() Interrompido");
            return;
        }
        
        this.gold += gold;
    }
    
    /* Manipulação e consulta de espaço */
    
    // Método para retorna a quantidade de espaço vazio
    public int getAvailableSpace (){
        
        return spaces - items.size();
    }
    
    // Retorna o tamanho total do inventário (incluindo espaços ocupados por itens );
    public int getSpaces (){

    return this.spaces;
}
    
    // Método para alterar o atributo de espaço(tamanho total) do inventário
    public void setSpaces ( int spaces ){
        
        // Espaço deve ser positivo
        if ( spaces < 0 ){
            System.out.println ("Spaces deve conter um numero inteiro positivo");
            System.out.println ("Metodo setSpaces() Interrompido");
            return;
        }
        
        this.spaces = spaces;
    }
    
    /* Manipulação de Items */
    
    // Método para procurar um item no inventário pelo nome
    // Caso o invetário contenha mais de uma copia de um mesmo item, retorna o item na posição "mais baixa"
    public Item searchItem ( String itemName ){
    
        // Percorre o inventário buscando a primeira ocorrência do Item
        for ( GenericPair<Item, Boolean> pair : items) {
        
            if ( pair.getFirst().getName().equals(itemName) ){
                return pair.getFirst();
            }
        }
        
        //Caso não tenha encontrado o item, retorna null
        return null;
    }
    
    // Método para procurar um item no invetário por posição
    public Item searchItem ( int itemPosition ){
    
        // A posição passada deve ser menor que o tamanho do vetor de item
        if ( itemPosition >= items.size() ) {
            System.out.println ("Posicao invalida");
            return null;
        }
        
        return items.get( itemPosition ).getFirst();
    }
    
    // Método para inserir um ítem no inventário - Será suposto que o item inserido não está equipado ainda
    public void insertItem ( Item itemToInsert ) {
        
        // Cria um objeto pair para conter o item e o status dele (não equipado)
        GenericPair<Item, Boolean> pairToInsert = new GenericPair<Item, Boolean> ( itemToInsert, false );
        
        // Deve-se verificar se há espaço para inserir um novo item
        if ( items.size() >= spaces ) {
            
            System.out.println ("Nao ha espaco disponivel para inserir um novo item");
            return;
        }
        
        // Insere o item no final do vetor de coleção de items
        items.add( pairToInsert );
    }
    
    // Método para remover um item por nome
    public void removeItem ( String itemName ) {
        
        // Busca no vetor a primeira ocorrência do item e o remove dessa posição
        for ( int i = 0; i < items.size(); i++ ) {
            
            if ( items.get(i).getFirst().getName().equals(itemName) ) {
                
                items.remove(i); //Remove o Pair item-status. O método já reorganiza o vetor resultante
                return; // Já pode encerrar o método
            }
        }
        
        // Se não encontrou o item no inventário, imprime uma mensagem de erro
        System.out.println ( itemName + " nao esta presente no inventario\n"); 
    }
    
    // Método para remover um item por sua posição
    public void removeItem ( int itemPosition ) {
        
        //Deve-se verificar se a posição passada por parâmetro é válida
        if ( itemPosition >= items.size() ) {
            
            System.out.println ("Posicao para remocao de item invalida");
            return;
        }
        
        // Remove o Pair item-status da posição desejada
        items.remove ( itemPosition );
    }
    
    /* Métodos auxiliares criados a parte */
    
    // Método para usar uma poção ou equipar uma arma ou armadura no personagem passado como parâmetro, dependendo do nome do item passado como parâmetro
    public void useItem ( String itemName, GameCharacter character ){

        // Busca o item passado como parâmetro
        Item item = searchItem ( itemName );
        
        // Caso item não exista no inventário
        if ( item == null ){
            
            System.out.println ( itemName + " nao estah no inventario de " + character.getName() + ". Nao eh possivel usa-lo." );
            return;
        }
        
        // Chama o método use() do item. Se for uma ManaPotion ou HealthPotion, recuperará MP ou HP do personagem, respectivamente. Se for uma arma ou armadura, equipa ela. O método use() de cada item trata o que deverá ser feito
        item.use ( character, this );
    }
    
    // Método para desequipar uma arma ou armadura. Método unequip() das classes Armor e Weapon trata o processo específico de cada classe
    public void unequipWeaponOrArmor ( String itemName, GameCharacter character ){
        
        // Busca o item passado como parâmetro
        Item item = searchItem ( itemName );
        
        // Caso item não exista no inventário
        if ( item == null ){
            
            System.out.println ( itemName + " nao estah no inventario de " + character.getName() + ". Nao eh possivel desequipa-lo." );
            return;
        }
        
        // Chama o método unequip() do item. Se for uma ManaPotion ou HealthPotion, retornará uma mensagem de erro. Se for uma arma ou armadura, desequipa ela. O método unequip() de cada item trata o que deverá ser feito
        item.unequip ( character, this );
    
    }
    
    // Método para equipar um item. Retorna true se equipou o item. False se não equipou
    public boolean equipItem ( String itemName ){
        
        // Variável para indicar se um item já está equipado
        boolean alreadyEquipped = false;
        
        // Percorre o inventário buscando a primeira ocorrência do Item
        for ( GenericPair<Item, Boolean> pair : items ) {
        
            // Ao achar o item, muda seu status para equipado se ele estiver desequipado
            if ( pair.getFirst().getName().equals (itemName) ){
                
                // Somente equipa se ele estiver desequipado.
                if ( ( pair.getSecond() == false ) ){
                
                    pair.setSecond( true );
                    return true;
                }
                
                // Variável para indicar que há uma ocorrência do item no inventário e que ela está equipada. Caso tenha uma segunda ocorrência do item e ela estiver desequipada, equipará o item e retornará true. Caso contrário, a variável alreadyEquipped auxiliará na impressão de uma mensagem de erro
                alreadyEquipped = true;
            }
        }
        
        if ( alreadyEquipped ){
            
            System.out.println ( "O item " + itemName + " ja esta equipado." );
            return false;
        }
        
        System.out.println ( "Erro no metodo equip( stringName ) de Inventory. " + itemName + " nao estah no inventario" );
        return false;
    }
    
    // Método para desequipar um item. Retorna true se conseguiu desequipar, false se não conseguiu desequipar
    public boolean unequipItem ( String itemName ){
        
        Item item = null;
        boolean status;
        boolean unequipped = false; // Variável para indicar se desequipou o item
        
        // Procura pela primeira ocorrência do item a ser desequipado
        for ( GenericPair<Item, Boolean> pair : items ){
        
            item = pair.getFirst();
            status = pair.getSecond();
            
            // Se for o ítem procurado e ele estiver equipado
            if ( item.getName().equals (itemName) && status == true ){
            
                // Desequipa o item
                pair.setSecond (false);
                unequipped = true;
                break;
            }
        }
        
        // Se nenhum item foi desequipado
        if ( !unequipped ){
        
            System.out.println ( "Item " + itemName + " nao esta equipado no inventario" );
        }
        
        return unequipped;
    }
    
    // Método auxiliar para retornar a soma de attackPts das armas equipadas presentes no inventário
    public int getItemsAttackPts(){
        
        int itemsAttackPts = 0;
        Item auxItem;
        boolean auxStatus;
        
        // Percorre o Inventário, somando o poder de ataque de todos os itens equipados. Como getAttackPts() de armadura retorna 0 e poções não estão equipadas ( seu getAttackPts() retorna -1 ), basta somar o retorno de getAttackPts() dos itens equipados.
        for ( GenericPair<Item, Boolean> pair : items ){
        
            auxItem = pair.getFirst();
            auxStatus = pair.getSecond();
            
            // Se o item estiver equipado
            if ( auxStatus == true ){
        
                itemsAttackPts += auxItem.getAttackPts();
            }
        }
        
        return itemsAttackPts;
    }
    
    // Método auxiliar para retornar os defensePts da armadura equipada no inventário
    public int getItemsDefensePts(){
        
        int itemsDefensePts = 0;
        Item auxItem;
        boolean auxStatus;
        
        // Percorre o Inventário, somando o poder de defesa de todos os itens equipados. Como geDefensePts() de arma retorna 0 e poções não estão equipadas ( seu getDefensePts() retorna restaurePts ), basta somar o retorno de getDefensePts() dos itens equipados.
        for ( GenericPair<Item, Boolean> pair : items ){
        
            auxItem = pair.getFirst();
            auxStatus = pair.getSecond();
            
            // Se o ítem estiver equipado
            if ( auxStatus == true ){
                
                itemsDefensePts += auxItem.getDefensePts();
            }
        }
        
        return itemsDefensePts;
    }
    
    // Método auxiliar para retornar a quantidade de armas equipadas
    public int howManyEquippedWeapons() {
        
        return nEquippedWeapons;
    }
    
    // Método auxiliar para retornar a quantidade de armaduras equipadas
    public int howManyEquippedArmors (){

        return nEquippedArmors;
    }
    
    // Método auxiliar para modificar o atributo nEquippedWeapons
    public void setNumberOfEquippedWeapons ( int nEquippedWeapons ){
    
        if ( nEquippedWeapons < 0 || nEquippedWeapons > 2 ){
            
            System.out.println ( "O numero maximo de armas equipadas eh 2. Metodo setNumbersOfEquippedWeapons (int) abortado." );
            return;
        }
        
        this.nEquippedWeapons = nEquippedWeapons;
    }
    
    // Método auxiliar para modificar o atributo nEquippedArmors
    public void setNumberOfEquippedArmors ( int nEquippedArmors ){
    
        if ( nEquippedArmors < 0 || nEquippedArmors > 1 ){
        
            System.out.println ( "O numero maximo de armaduras equipadas eh 1. Metodo setNumbersOfEquippedWeapons (int) abortado");
            return;
        }
        
        this.nEquippedArmors = nEquippedArmors;
    }
    
    
    /* Método auxiliar para imprimir os itens de um inventário */
    public void printInventory () {
        
        System.out.println ("\nITENS DO INVENTARIO:");
        
        for ( GenericPair<Item, Boolean> pair : items ) {
            
            // Chama o método de impressão das informações de cada Item. É específico de cada item especializado
            pair.getFirst().printInfo();
            System.out.println ("\tEquipado: " + pair.getSecond() );
        }
        System.out.println ("\n");
    }
    
    // Programa de teste da classe Inventory e das subclasses de Item
    public static void main ( String args[] ){
    
        Knight ch = new Knight ("Moises", 20);
        
        HealthPotion hp1 = new HealthPotion( "Cure", 20, 100 );
        HealthPotion hp2 = new HealthPotion( "Cura", 40, 200 );
        ManaPotion mp1 = new ManaPotion ( "MP Restore 1", 40, 200 );
        ManaPotion mp2 = new ManaPotion ( "MP Restore 2", 80, 300 );
    
        Weapon wp1 = new Weapon( "Faca", 9.99, 1, 10 );
        Weapon wp2 = new Weapon( "Lança", 20, 6, 20 );
        Weapon wp3 = new Weapon( "Espada", 100, 18, 15 );
        
        Armor a1 = new Armor ( "Escudo de Madeira", 9.99, 2, 20 );
        Armor a2 = new Armor ( "Escudo de Aço", 20, 4, 70 );
        Armor a3 = new Armor ( "Escudo de Titanio", 100, 9, 10 );
        
        ch.storeItem ( hp1 );
        
        ch.addSpacesInInventory(10);
        
        ch.storeItem (hp1);
        ch.storeItem (hp2);
        ch.storeItem (mp1);
        ch.storeItem (mp2);
        ch.storeItem (wp1);
        ch.storeItem (wp2);
        ch.storeItem (wp3);
        ch.storeItem (a1);
        ch.storeItem (a2);
        ch.storeItem (a3);
        
        ch.printGameCharacterInventory();
        
        ch.useItem ("Curaga");
        ch.useItem ("Cure");
        ch.useItem ("MP Restore 1");


        ch.equipItem ( "Espada" );
        ch.equipItem ( "Escudo de Madeira" );
        ch.equipItem ( "Escudo de Titanio" );
        ch.equipItem ( "Escudo de Aço" );
        
        ch.equipItem ( "Escudo de Madeira" );
        ch.equipItem ( "Espada" );
        ch.equipItem ( "Lança" );
        ch.equipItem ( "Faca" );
        
        System.out.println("\nARMAS EQUIPADAS: " + ch.howManyEquippedWeapons() );
        System.out.println("ARMADURAS EQUIPADAS: " + ch.howManyEquippedArmors() );
        
        System.out.println("\nITEMS ATTACK PTS: " + ch.getItemsAttackPts() );
        System.out.println("ITEMS DEFENSE PTS: " + ch.getItemsDefensePts() );
        
        ch.printGameCharacterInventory();
    
        ch.unequipItem ( "Escudo de Aco" );
        ch.unequipItem ( "Escudo de Madeira" );
        
        ch.unequipItem ( "Espada" );
        ch.unequipItem ( "Escudo de Titanio" );
        
        ch.printGameCharacterInventory();
        
        System.out.println( "\nNUMERO DE ARMAS EQUIPADAS: " + ch.howManyEquippedWeapons() );
        System.out.println( "NUMERO DE ARMADURAS EQUIPADAS: " + ch.howManyEquippedArmors() );
        
        System.out.println( "\nITEMS ATTACK PTS: " + ch.getItemsAttackPts() );
        System.out.println( "ITEMS DEFENSE PTS: " + ch.getItemsDefensePts() );
        
        ch.equipItem ( "Escudo de Titanio");
        ch.equipItem ( "Faca" );
        
        System.out.println( "\nITEMS ATTACK PTS: " + ch.getItemsAttackPts() );
        System.out.println( "ITEMS DEFENSE PTS: " + ch.getItemsDefensePts() );
        
        ch.printGameCharacterInventory();
        
        ch.discartItem ("Espada");
        ch.discartItem ("Lança");
        ch.discartItem ("Faca");
        ch.discartItem ("Escudo de Madeira");
        ch.discartItem ("Escudo de Titanio");
        ch.discartItem ("Espada");
        
        ch.printGameCharacterInventory();
    }
}




