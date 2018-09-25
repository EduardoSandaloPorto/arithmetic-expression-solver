/*
 * Contem uma classe herdade de Matriz especificamente para Boolean
*/
package matriz.ofboolean;

import matriz.*;
import coordenada.*;

/*
 * Classe para uma Matriz de Boolean
 * @author Eduardo Sandalo Porto
 * @author Felipe Reis Corerato
 * @author João Henri Carrenho Rocha
*/
public class MatrizBoolean extends Matriz<Boolean>
{
    /**
     * Vetor bidimensional que demonstra o que cada linha da matriz represnta.<br>
     * Exemplo: representa em uma tabela os valores destacados
     * <table>
     *      <tr>
     *          <td>-</td><th><i><b>a</b></i></th><th><i><b>b</b></i></th>
     *      </tr>
     *      <tr>
     *          <td>c</td><td>true</td><td>true</td>
     *      </tr>
     *      <tr>
     *          <td>d</td><td>true</td><td>false</td>
     *      </tr>
     * </table>
     */
    protected String[] oQueRepresentaCadaLinha;
    
    /**
     * Vetor bidimensional que demonstra o que cada coluna da matriz represnta. <br>
     * Exemplo: representa em uma tabela os valores destacados
     * <table>
     *      <tr>
     *          <td>-</td><td>a</td><td>b</td>
     *      </tr>
     *      <tr>
     *          <th><i><b>c</b></i></th><td>true</td><td>true</td>
     *      </tr>
     *      <tr>
     *          <th><i><b>d</b></i></th><td>true</td><td>false</td>
     *      </tr>
     * </table>
     */
    protected String[] oQueRepresentaCadaColuna;

    /**
     * Construtor para a matriz de boolean a partir de dois inteiros.
     * @param numeroLinhas quantas linhas a matriz terá no total
     * @param numeroColunas quantas colunas a matriz terá no total
     * @param vetorLinha qual valor terá {@link #oQueRepresentaCadaLinha}
     * @param vetorColuna qual valor terá {@link #oQueRepresentaCadaColuna}
     * @throws Exception se
     * <ul>
     *      <li>vetorLinha ou vetorColuna não tiverem tamanhos iguais ao numero
     *          de linhas e de colunas respectivamente</li>
     *      <li>numeroLinhas ou numeroColunas seja menor que 0</li>
     * </ul>
     */
    public MatrizBoolean (int numeroLinhas, int numeroColunas,
                          String[] vetorLinha,
                          String[] vetorColuna) throws Exception
    {
        super (numeroLinhas, numeroColunas);
        
        if (vetorLinha.length!=numeroLinhas)
            throw new Exception ("ERRO (Construtor MatrizBoolean): vetorLinha com tamanho invalido");
        if (vetorColuna.length!=numeroColunas)
            throw new Exception ("ERRO (Construtor MatrizBoolean): vetorColuna com tamanho invalido");
        
        this.oQueRepresentaCadaLinha = new String[numeroLinhas];
        this.oQueRepresentaCadaColuna = new String[numeroColunas];    
        
        for (int i=0; i<=vetorLinha.length-1; i++)
            this.oQueRepresentaCadaLinha[i] = vetorLinha[i];
        for (int i=0; i<=vetorColuna.length-1; i++)
            this.oQueRepresentaCadaColuna[i] = vetorColuna[i];
    }

    /**
     * Construtor para a matriz de boolean a partir de um objeto da classe Coordenada.
     * @param coord Coordenada que representa o último valor da {@link #matriz}.
     * @param vetorLinha qual valor terá {@link #oQueRepresentaCadaLinha}
     * @param vetorColuna qual valor terá {@link #oQueRepresentaCadaColuna}
     * @throws Exception se
     * <ul>
     *      <li>vetorLinha ou vetorColuna não tiverem tamanhos iguais ao numero
     *          de linhas e de colunas respectivamente</li>
     *      <li>a Coordenada coord seja nula</li>
     * </ul>
     */
    public MatrizBoolean (Coordenada coord,
                          String[] vetorLinha,
                          String[] vetorColuna) throws Exception
    {
        super (coord);
        
        if (vetorLinha.length!=coord.getX()+1)
            throw new Exception ("ERRO (Construtor MatrizBoolean): vetorLinha com tamanho invalido");
        if (vetorColuna.length!=coord.getY()+1)
            throw new Exception ("ERRO (Construtor MatrizBoolean): vetorColuna com tamanho invalido");        

        this.oQueRepresentaCadaLinha = new String[coord.getX()+1];
        this.oQueRepresentaCadaColuna = new String[coord.getY()+1];   
        
        for (int i=0; i<=vetorLinha.length-1; i++)
            this.oQueRepresentaCadaLinha[i] = vetorLinha[i];
        for (int i=0; i<=vetorColuna.length-1; i++)
            this.oQueRepresentaCadaColuna[i] = vetorColuna[i];
    }
    
    /**
     * Método get de {@link #oQueRepresentaCadaLinha}
     * @return um vetor igual a this.{@link #oQueRepresentaCadaLinha}
     */
    public String[] getOQueRepresentaCadaLinha()
    {
        String[] ret = new String[this.nLinhas];
        for (int i=0; i<=this.oQueRepresentaCadaLinha.length-1; i++)
            ret[i] = oQueRepresentaCadaLinha[i];
        return ret;
    }
    
    /**
     * Método get de {@link #oQueRepresentaCadaColuna}
     * @return um vetor igual a this.{@link #oQueRepresentaCadaColuna}
     */
    public String[] getOQueRepresentaCadaColuna()
    {
        String[] ret = new String[this.nColunas];
        for (int i=0; i<=this.oQueRepresentaCadaColuna.length-1; i++)
            ret[i] = oQueRepresentaCadaColuna[i];
        return ret;
    }
    
    /**
     * Troca o valor de {@link #oQueRepresentaCadaLinha}
     * @param vetorLinha novo valor para {@link #oQueRepresentaCadaLinha}
     * @throws Exception caso vetorLinha tenha um tamanho que não seja igual a this.{@link #nLinhas}
     */
    public void setOQueRepresentaCadaLinha (String[] vetorLinha) throws Exception
    {
        if (vetorLinha.length!=this.oQueRepresentaCadaLinha.length)
            throw new Exception ("ERRO (MatrizBoolean.setOQueRepresentaCadaLinha): Vetor com tamanho invalido");
        
        String[] aux = new String[vetorLinha.length];
        for (int i=0; i<=this.nLinhas-1; i++)
            aux[i] = vetorLinha[i];
        
        this.oQueRepresentaCadaLinha = aux;
    }

    /**
     * Troca o valor de {@link #oQueRepresentaCadaColuna}
     * @param vetorColuna novo valor para {@link #oQueRepresentaCadaColuna}
     * @throws Exception caso vetorColuna tenha um tamanho que não seja igual a this.{@link #nColunas}
     */
    public void setOQueRepresentaCadaColuna (String[] vetorColuna) throws Exception
    {
        if (vetorColuna.length!=this.oQueRepresentaCadaColuna.length)
            throw new Exception ("ERRO (MatrizBoolean.setOQueRepresentaCadaColuna): Vetor com tamanho invalido");
        
        String[] aux = new String[vetorColuna.length];
        for (int i=0; i<=this.nColunas-1; i++)
            aux[i] = vetorColuna[i];
        
        this.oQueRepresentaCadaColuna = aux;
    }
    
    /**
     * Procura uma String em um vetor
     * @param str String a ser procurada
     * @param vet em qual vetor procurar a String
     * @return posição da String no vetor. Caso não ache, retorna -1
     */
    protected int kd (String str, String[] vet)
    {
        for (int i=0; i<=vet.length; i++)
            if (vet[i].equals(str))
                return i;

        return -1;
    }
    
    /**
     * Retorna o valor guardado em uma coordenada da matriz a partir do nome da linha e da coluna
     * @param lin String guardada em {@link #oQueRepresentaCadaLinha}
     * @param col String guardada em {@link #oQueRepresentaCadaColuna}
     * @return o valor guardado na linha "lin" e na coluna "col"
     * @throws Exception caso não seja encontrada lin e/ou col nos vetores {@link #oQueRepresentaCadaLinha}
     * ou {@link #oQueRepresentaCadaColuna}
     */
    public boolean getValorEm (String lin, String col) throws Exception
    {
        int x = this.kd(lin, oQueRepresentaCadaLinha);
        int y = this.kd(col, oQueRepresentaCadaColuna);
        
        if (x==-1)
            throw new Exception ("ERRO (MatrizBoolean.getValorEm): linha represntada por lin nao encontrada");
        if (y==-1)
            throw new Exception ("ERRO (MatrizBoolean.getValorEm): coluna represntada por col nao encontrada");
        
        return (boolean)this.matriz[x][y];
    }
    
    /**
     * Verifica se o objeto que chamou o método é congruente ao objeto passado como parâmetro
     * @param obj Objeto para ser comparado com o objeto que chamou o método equals()
     * @return um valor verdadeiro ou falso caso os objetos sejam congruentes ou não
    */
    public boolean equals (Object obj)
    {
        if (!super.equals(obj))
            return false;

        if (!(this.getClass().equals(obj.getClass())))
            return false;
            
        MatrizBoolean mat = (MatrizBoolean)obj;
        
        for (int i=0; i<=mat.nLinhas-1; i++)
        {
            if (!(this.oQueRepresentaCadaLinha[i].equals(mat.oQueRepresentaCadaLinha[i])))
            {
                return false;
            }
        }
                
        for (int i2=0; i2<=mat.nColunas-1; i2++)
            if (!this.oQueRepresentaCadaColuna[i2].equals(mat.oQueRepresentaCadaColuna[i2]))
                return false;
        
        return true;
    }
    
    /**
     * Calcula o código hash da matriz guardada
     * @return o código hash da matriz
     */
    public int hashCode()
    {
        int ret = super.hashCode();
        for (int i=0; i<=this.nLinhas-1; i++)
            ret = ret * 3 + this.oQueRepresentaCadaLinha[i].hashCode();
        for (int i=0; i<=this.nColunas-1; i++)
            ret = ret * 3 + this.oQueRepresentaCadaColuna[i].hashCode();
        
        return ret;
    }
    
    /**
     * Transforma a matriz em uma string
     * @return uma String que representa a matriz
     */
    public String toString()
    {
        String ret="";

        ret += "        ";
        for (int i=0; i<=this.oQueRepresentaCadaColuna.length-1; i++)
        {
            String aux=this.oQueRepresentaCadaColuna[i];
            while (!(aux.length()==11))
                aux += " ";
            ret += aux;
        }
        ret += System.lineSeparator();
        
        for (int i1=0; i1<=this.nLinhas-1; i1++)
        {
            String aux=this.oQueRepresentaCadaLinha[i1];
            while (!(aux.length()==5))
                aux += " ";
            ret += aux + " ";
            
            for (int i2=0; i2<=this.nColunas-1; i2++)
            {
                    ret+="( "+this.matriz[i1][i2];
                    ret+=((boolean)matriz[i1][i2]==true?"  )":" )");
                    ret+=(((i1!=this.nLinhas-1) || (i2!=this.nColunas-1))?", ":"");
            }

            ret+=(i1!=this.nLinhas-1?System.lineSeparator():"");
        }

        return ret;
    }
    
    /**
     * Construtor de cópias - cria um objeto igual ao passado como parâmetro
     * @param modelo um objeto da classe Matriz o qual se quer copiar
     * @throws Exception caso o parâmetro passado seja nulo
     */
    public MatrizBoolean (MatrizBoolean modelo) throws Exception
    {
        super(modelo);
        this.oQueRepresentaCadaLinha = new String[modelo.oQueRepresentaCadaLinha.length];
        this.oQueRepresentaCadaColuna = new String[modelo.oQueRepresentaCadaColuna.length];
        for (int i=0; i<=modelo.oQueRepresentaCadaLinha.length-1; i++)
            this.oQueRepresentaCadaLinha[i]=modelo.oQueRepresentaCadaLinha[i];
        for (int i=0; i<=modelo.oQueRepresentaCadaColuna.length-1; i++)
            this.oQueRepresentaCadaColuna[i]=modelo.oQueRepresentaCadaColuna[i];
    }
    
    /**
     * Executra o construtor de cópias e retorna um objeto igual à classe que o chamou
     * @return um objeto igual à classe que chamou o método clone()
    */
    public Object clone()
    {
        MatrizBoolean ret=null;
        
        try
        {
            ret = new MatrizBoolean(this);
        }
        catch (Exception erro) {}
        
        return ret;
    }
    
}