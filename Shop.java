// Interface com os métodos que definem o comportamento de uma loja

interface Shop {
    
    // Método para receber o personagem que irá comprar ou vender um item
    void goShopping( GameCharacter character );
    
    // Método para vender items a um personagem
    void sell( GameCharacter character );
    
    // Método para comprar items do personagem
    void buyUsedItem( GameCharacter character );

}