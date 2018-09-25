/*
 * Contem uma classe para uma matriz (vetor bidimensional)
 */
package matriz;

import coordenada.*;
import java.lang.reflect.*;

/**
 * Classe que representa uma matriz e seus métodos possíveis
 * @author Eduardo Sandalo Porto
 * @author Felipe Reis Corerato
 * @author João Henri Carrenho Rocha
 * @param <X> objeto que será guardado na matriz
 */
public class Matriz <X> implements Cloneable
{
    // ATRIBUTOS
    /**
     * Vetor bidimensional no qual dados serão guardados
    */
    protected Object[][] matriz;

    /**
     * Numero de linhas da matriz {@link #matriz}. <br>
     * Menor número possível: <b>1</b>
    */
    protected int nLinhas;

    /**
	 * Numero de colunas de {@link #matriz}. <br>
	 * Menor número possível: <b>1</b>
    */
    protected int nColunas;



    // METODOS
    /**
     * Inicia os atributos da classe.
     * @param numeroLinhas Numero de linhas que {@link #matriz} terá.
     * @param numeroColunas Numero de colunas que {@link #matriz} terá.
    */
    protected void iniciacao (int numeroLinhas, int numeroColunas)
    {
        this.matriz = new Object[numeroLinhas][numeroColunas];
        this.nLinhas = numeroLinhas;
        this.nColunas = numeroColunas;
    }

    /**
     * Construtor sem parâmetros. <br>
     * Será executado iniciacao() com os parâmetros <i>10</i> e <i>10</i>.
    */
    public Matriz ()
    {
        this.iniciacao(10, 10);
    }

	/**
	 * Construtor com {@link #nLinhas} e {@link #nColunas} especificado. <br>
	 * Sera executado iniciacao() com os parâmetros numeroLinhas e numeroColunas.
	 * @param numeroLinhas Numero de linhas que {@link #matriz} terá.
	 * @param numeroColunas Numero de colunas que {@link #matriz} terá.
	 * @throws Exception se o numeroLinhas ou numeroColunas forem menores que 0.
	*/
	public Matriz (int numeroLinhas, int numeroColunas) throws Exception
	{
		if (numeroLinhas<1)
			throw new Exception ("ERRO (Construtor Matriz): Numero de linhas invalido");
		if (numeroColunas<1)
			throw new Exception ("ERRO (Construtor Matriz): Numero de colunas invalido");

		this.iniciacao(numeroLinhas, numeroColunas);
	}

	/**
	 * Construtor da Matriz utilizando a classe Coordenada. <br>
	 * A {@link #matriz} terá o sua última posição como a coordenada passada como parâmetro.
	 * @param coord Coordenada que representa o último valor da {@link #matriz}.
	 * @throws Exception se a coordenada passada como parâmetro for nula.
	*/
	public Matriz (Coordenada coord) throws Exception
	{
		if (coord == null)
			throw new Exception ("ERRO (Construtor Matriz): Coordenada invalida");
		this.iniciacao (coord.getX()+1, coord.getY()+1);
	}

	/**
	 * Método get do atributo {@link #nLinhas}
	 * @return o valor de {@link #nLinhas}
	*/
	public int getNumeroLinhas() { return this.nLinhas; }

	/**
	 * Método get do atributo {@link #nColunas}
	 * @return o valor de {@link #nColunas}
	*/
	public int getNumeroColunas() { return this.nColunas; }

	/**
	 * Método get do atributo {@link #matriz}
	 * @return uma matriz idêntica à guardada pelo objeto
	*/
	public Object[][] getMatriz()
	{
		Object[][] ret = new Object[this.nLinhas][this.nColunas];
		for (int i=0; i<=this.nLinhas-1; i++)
			for (int j=0; j<=this.nColunas-1; j++)
				ret[i][j]=this.matriz[i][j];
		return ret;

	}

	/**
	 * Método get de algum item na matriz utilizando a posicao i e j do item como parâmetro
	 * @param i valor da linha de 0 à {@link #nLinhas}-1 na matriz
	 * @param j valor da coluna de 0 à {@link #nColunas}-1 na matriz
	 * @return valor de um dado guardado na matriz
	 * @throws Exception se os parametros forem menor que 0 ou maior que nLinhas ou nColunas
	*/
	public Object getItemNaMatriz (int i, int j) throws Exception
	{
		if (i<0 || i>nLinhas-1)
			throw new Exception ("ERRO (Matriz.getItemNaMatriz): numero da linha invalido");
		if (j<0 || j>nColunas-1)
			throw new Exception ("ERRO (Matriz.getItemNaMatriz): numero da coluna invalido");

		if (this.matriz[i][j] instanceof Cloneable)
			return this.meuCloneDeX((X)this.matriz[i][j]);
		return this.matriz[i][j];
	}

	/**
	 * Método get de algum item na matriz utilizando uma coordenada.
	 * @param coord coordenada da matriz
	 * @return valor de um dado guardado na matriz
	 * @throws Exception se a coordenada passada como parâmetro for nula
	*/
	public Object getItemNaMatriz (Coordenada coord) throws Exception
	{
		if (coord==null)
			throw new Exception ("ERRO (Matriz.getItemNaMatriz): Coordenada invalida");

		int i = coord.getX();
		int j = coord.getY();

		if (this.matriz[i][j] instanceof Cloneable)
			return this.meuCloneDeX((X)this.matriz[i][j]);
		return this.matriz[i][j];
	}

	/**
	 * Armazena um dado em uma determinada posição do vetor.
	 * @param i valor da linha de 0 à {@link #nLinhas}-1 na matriz
	 * @param j valor da coluna de 0 à {@link #nColunas}-1 na matriz
	 * @param x valor para se guardar na matriz
	 * @throws Exception se os parametros i e j forem menor que 0 ou maior que nLinhas ou nColunas
	*/
	public void setItemNaMatriz (int i, int j, X x) throws Exception
	{
            if (i<0 || i>nLinhas-1)
                throw new Exception ("ERRO (Matriz.setItemNaMatriz): numero da linha invalido");
            if (j<0 || j>nColunas-1)
                throw new Exception ("ERRO (Matriz.setItemNaMatriz): numero da coluna invalido");

            if (x instanceof Cloneable)
                this.matriz[i][j] = this.meuCloneDeX(x);
            else
                this.matriz[i][j] = x;
	}

	/**
	 * Armazena um dado em uma determinada posição do vetor.
	 * @param coord coordenada da matriz
	 * @param x valor para se guardar na matriz
	 * @throws Exception se a coordenada passada como parâmetro for nula
	*/
	public void setItemNaMatriz (Coordenada coord, X x) throws Exception
	{
		if (coord==null)
			throw new Exception ("ERRO (Matriz.getItemNaMatriz): Coordenada invalida");

		int i = coord.getX();
		int j = coord.getY();

		if (x instanceof Cloneable)
			this.matriz[i][j] = this.meuCloneDeX(x);
		else
			this.matriz[i][j] = x;
	}

	/**
	 * Método que força o programa a executar a função clone de um objeto guardado na pilha.
	 * @param x elemento que sera feito o clone
	 * @return  clone do elemento x
	*/
	protected X meuCloneDeX (X x)
	{
		X ret = null;

		try
		{
			Class<?> classe = x.getClass();
			Class<?>[] tipoDoParametroFormal = null;
			Method metodo = classe.getMethod ("clone", tipoDoParametroFormal);
			Object[] parametroReal = null;
			ret = ((X)metodo.invoke (x, parametroReal));
		}
		catch (NoSuchMethodException erro)
		{}
		catch (InvocationTargetException erro)
		{}
		catch (IllegalAccessException erro)
		{}

		return ret;
    }

	/**
	* Transforma a matriz em uma string
	* @return uma String que representa a matriz
	*/
	public String toString()
	{
            String ret="";

            for (int i1=0; i1<=this.nLinhas-1; i1++)
            {
                for (int i2=0; i2<=this.nColunas-1; i2++)
                {
                    ret+="( "+this.matriz[i1][i2]+" )";
                    ret+=(((i1!=this.nLinhas-1) || (i2!=this.nColunas-1))?", ":"");
                }

                ret+=(i1!=this.nLinhas-1?System.lineSeparator():"");
            }

            return ret;
	}

    /**
	 * Calcula o código hash da matriz guardada
	 * @return o código hash da matriz
    */
    public int hashCode()
    {
		int ret = 2;

		ret = ret*3 + new Integer(this.nLinhas).hashCode();
		ret = ret*3 + new Integer(this.nColunas).hashCode();
		for (int i=0; i<=this.nLinhas-1; i++)
			for (int j=0; j<=this.nColunas-1; j++)
			{
				if (this.matriz[i][j]==null)
					ret = ret * 3 + 7;
				else
					ret = ret * 3 + matriz[i][j].hashCode();
			}

		return ret;
	}

	/**
	 * Verifica se o objeto que chamou o método é congruente ao objeto passado como parâmetro
	 * @param obj Objeto para ser comparado com o objeto que chamou o método equals()
	 * @return um valor verdadeiro ou falso caso os objetos sejam congruentes ou não
	*/
	public boolean equals(Object obj)
	{
		if (obj==null)
			return false;
		if (obj==this)
			return true;
		if (!(obj instanceof Matriz))
		{
			System.out.println("AAAAAAAAAAAAAAAAAA");
            return false;
		}

        Matriz mat = (Matriz)obj;

        if (this.nLinhas != mat.nLinhas)
        	return false;
        if (this.nColunas != mat.nColunas)
        	return false;
        for (int i=0; i<=this.nLinhas-1; i++)
        	for (int j=0; j<=this.nColunas-1; j++)
        	{
				if (this.matriz[i][j]==null)
				{
					if (mat.matriz[i][j]!=null)
						return false;
				}
				else
					if (!this.matriz[i][j].equals(mat.matriz[i][j]))
						return false;
			}

		return true;
	}

        /**
         * Construtor de cópias - cria um objeto igual ao passado como parâmetro
         * @param modelo um objeto da classe Matriz o qual se quer copiar
         * @throws Exception caso o parâmetro passado seja nulo
         */
	public Matriz (Matriz modelo) throws Exception
	{
		if (modelo==null)
			throw new Exception ("ERRO (Construtor Matriz): Matriz modelo passada como parâmetro nula");

		this.nLinhas  = modelo.nLinhas;
		this.nColunas = modelo.nColunas;
		this.matriz   = new Object[modelo.nLinhas][modelo.nColunas];
		for (int i=0; i<=modelo.nLinhas-1; i++)
			for (int j=0; j<=modelo.nColunas-1; j++)
			{
				if (modelo.matriz[i][j]==null)
					this.matriz[i][j]=null;
				else
				{
					if (modelo.matriz[i][j] instanceof Cloneable)
						this.matriz[i][j]=modelo.meuCloneDeX((X)modelo.matriz[i][j]);
					else
						this.matriz[i][j]=modelo.matriz[i][j];
				}
			}
	}
        
	/**
	 * Executra o construtor de cópias e retorna um objeto igual à classe que o chamou
	 * @return um objeto igual à classe que chamou o método clone()
	*/
	public Object clone ()
	{
		Matriz ret = null;

		try
		{
			ret = new Matriz (this);
		}
		catch (Exception erro)
		{}

		return ret;
    }

}
