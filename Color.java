/*
 *  Aluno: Moisés Botarro Ferraz Silva
 *  N. USP: 8504135
 *  Implementação em Java da Enumeração Color
 */

public enum Color{

    BLUE("Blue", 0, 0, 255),
    RED("Red", 255, 0, 0),
    GREEN("Green", 0, 255, 0),
    YELLOW("Yellow", 255, 255, 0),
    WHITE("White", 255, 255, 255),
    BLACK("Black", 0, 0, 0);

    /* Atributos de cada objeto da enumeração */
    private String name;
    private int R;
    private int G;
    private int B;

    private Color( String name, int R, int G, int B ){

        this.name = name;
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public String getName(){

        return name;
    }

    public int getR(){

        return R;
    }

    public int getG(){

        return G;
    }

    public int getB(){

        return B;
    }
}