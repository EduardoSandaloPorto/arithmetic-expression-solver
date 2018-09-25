/*
 * Contem uma classe que representa uma expressao aritmetica
 */
package expressaoaritmetica;

import java.util.*;
import pilha.*;
import fila.*;
import coordenada.*;
import matriz.ofboolean.*;

/**
 * Expressao aritmetica utilizada no Trabalho 2 da disciplina de Programação
 * Orientada a Objetos, do Colégio Técnico de Campinas. 2017
 * @author Eduardo Sandalo Porto
 * @author Felipe Reis Corerato
 * @author João Henri Carrenho Rocha
 */
public class ExpressaoAritmetica implements Comparable<ExpressaoAritmetica>
{
    /**
     * Representa a expressao aritmetica guardada no objeto, sem ter sido resolvida.
     */
    protected String exp;

    /**
     * Construtor da expressao aritmetica. Prepara o objeto para guardar uma
     * expressão aritmética passada como parâmetro. <br>
     * Caracteres aceitos: "0123456789+-/*^()"
     * @param expressaoAritmetica representa uma expressão aritmética para ser
     * armazenada
     * @throws Exception se
     * <ul>
     *      <li>a expressao passada como parametro for nula ou vazia</li>
     *      <li>a expressão conter um caracter que não está na lista de caracteres aceitos</li>
     *      <li>a expressão conter um número ímpar de tokens, divididos pelo operadores</li>
     *      <li>a expressão começar com um operador, sendo este operador não igual a "("</li>
     *      <li>a expressão terminar com um operador, sendo este operador não igual a ")"</li>
     *      <li>houver dois operadores do lado do outro (exceto alguns casos com "(" e ")")</li>
     *      <li>houver ")" na expressão sem antes ter "("</li>
     * </ul>
     */
    public ExpressaoAritmetica (String expressaoAritmetica) throws Exception
    {
        if (expressaoAritmetica==null || expressaoAritmetica.isEmpty())
            throw new Exception ("ERRO (Construtor ExpressaoAritmetica): Expressao passada"
                    + "nula ou vazia");
            
        String expressao = expressaoAritmetica.replaceAll("\\s+","");
        String filtro = "0123456789+-*/^()";
        for (int i=0; i<=expressao.length()-1; i++) 
        {
            if (filtro.indexOf(expressao.charAt(i))==-1)
                throw new Exception ("ERRO (Construtor ExpressaoAritmetica): "
                                   + "Expressao contem um caracter invalido. "
                                   + "Caractrer invalido: " + expressao.charAt(i));
        }
        
        int nParentesesSemFechar = 0;
        int nAbrirParentesesQueVeioAntes = 0;
        StringTokenizer quebrador = new StringTokenizer(expressao, "+-*/^()", true);
        if (quebrador.countTokens()%2==0) // O numero de tokens nao pode ser par.
                                          // Caso o numero de tokens fosse par,
                                          // haveria um operador inutilizado.
            throw new Exception ("ERRO (Construtor ExpressaoAritmetica): "
                    + "Expressao contem um numero invalido de operadores. ");
        
        String aux=quebrador.nextToken();
        try {
            Double.parseDouble(aux);
        }
        catch (NumberFormatException erro) { 
            if (!(aux.contains("(")))
            throw new Exception ("ERRO (Construtor ExpressaoAritmetica):"
                                  + "Expressao comeca com um operador invalido: "+aux); 
        }
        
        String aux2="";
        while ((quebrador.hasMoreTokens()) && (quebrador.countTokens()>1))
        {
            aux2=quebrador.nextToken();
            try { Double.parseDouble(aux); }
            catch (NumberFormatException erro1)
            {
                if (aux.equals("("))
                {
                    nAbrirParentesesQueVeioAntes++;
                    nParentesesSemFechar++;
                }
                
                if (aux.equals(")"))
                {   
                    if (nAbrirParentesesQueVeioAntes<=0)
                        throw new Exception ("ERRO: (Construtor ExpressaoAritmetica): "
                                + "Ha um operador de fechar parenteses sem antes haver "
                                + "um de abrir parenteses");
                    nParentesesSemFechar--;
                    nAbrirParentesesQueVeioAntes--;
                }
                else
                {
                    try { Double.parseDouble(aux2); }
                    catch (NumberFormatException erro2) 
                    { // Se o programa chegar aqui, ha dois operadores consecutivos na expressao
                        if (!aux2.equals("("))
                            throw new Exception ("ERRO (Construtor ExpressaoAritmetica): "
                                    + "Ha dois operadores consecutivos na expressao. "
                                    + "Eles sao: " + aux + " e " + aux2);
                    }
                }
            }
            aux=aux2;
        }
        aux=quebrador.nextToken();
        try { Double.parseDouble(aux); }
        catch (NumberFormatException erro3)
        {
            if (aux.equals(")"))
            {
                if (nAbrirParentesesQueVeioAntes<=0)
                    throw new Exception ("ERRO: (Construtor ExpressaoAritmetica): "
                            + "Ha um operador de fechar parenteses sem antes haver "
                            + "um de abrir parenteses");
                nParentesesSemFechar--;
                nAbrirParentesesQueVeioAntes--;
            }
            else
                throw new Exception ("ERRO (Construtor ExpressaoAritmetica): "
                        + "A expressao acaba com um operador invalido. "
                        + "Operador: " + aux);
        }
        
        if (nParentesesSemFechar!=0)
            throw new Exception ("ERRO (Construtor ExpressaoAritmetica): "
                    + "Ha parenteses que nao foram fechados ou abertos na expressao");
        
        this.exp = expressao;
    }
    
    /**
     * Devolve a resolução da expressão aritmética armazenada.
     * @return a expressão guardada em {@link #exp} resolvida
     */
    public double getExpressaoResolvida()
    {
        return etapa2(etapa1());
    }
    
    /**
     * Devolve uma String que representa a expressão guardada, em notação polonesa.
     * @return {@link #exp} em notação polonesa
     */
    public String converterParaNotacaoPolonesa()
    {
        try
        {
            String ret="";
            Fila<String> fil=this.etapa1();
            int continuar=fil.getQtd();
            for (int i=0; i<continuar; i++)
            {
                ret+=(i!=continuar?fil.getElemento()+" ":fil.getElemento());
                fil.desenfileire();
            }
            return ret;
        }
        catch (Exception erro) {}
        return null;
    }

    /**
     * Executa a primeira parte da resolução da expressão aritmética.
     * @return a expressão aritmética em notação polonesa, guardada em uma fila
     */
    protected Fila<String> etapa1()
    {
        try
            {
            Fila<String> fila;
            Pilha<String> pilha;
            MatrizBoolean matriz;

            matriz = this.configurarMatriz();
            
            StringTokenizer quebrador = new StringTokenizer(exp, "(^*/+-)", true);
            
            fila  = new Fila<String> (quebrador.countTokens());
            pilha = new Pilha<String>(quebrador.countTokens());
            
            String aux;
            
            // INICIO DA CONVERSAO DA EXPRESSAO EM NOTACAO POLONESA
            while (quebrador.hasMoreTokens())
            {
                aux=quebrador.nextToken();
                try
                {
                    Double.parseDouble(aux);
                    fila.enfileire(aux);
                }
                catch (NumberFormatException e)
                {
                    if (!aux.equals(")"))
                    {
                        if (pilha.vazia())
                            pilha.empilhe(aux);
                        else
                        {
                            if (matriz.getValorEm(pilha.getElemento(), aux))
                                try {
                                    while ((matriz.getValorEm(pilha.getElemento(), aux)))
                                    {
                                        fila.enfileire(pilha.getElemento());
                                        pilha.desempilhe();
                                    }
                                }
                                catch (Exception erro) {}
                            pilha.empilhe(aux);
                        }
                    }
                    else
                    {
                        while (!pilha.getElemento().equals("("))
                        {
                            fila.enfileire(pilha.getElemento());
                            pilha.desempilhe();
                        }
                        pilha.desempilhe();
                    }
                }
            }
            while (!pilha.vazia())
            {
                fila.enfileire(pilha.getElemento());
                pilha.desempilhe();
            }
            
            return fila;
        }
        catch (Exception erro) {}
        return null;
    }
    
    /**
     * Executa a segunda parte da resolução da expressão aritmética.
     * @param fil a expressão em notação polonesa guardada em uma fila
     * @return o resultado final da expressão aritmética guardada em {@link #exp}
     */
    protected double etapa2(Fila<String> fil)
    {
        try
        {
            Pilha<String> pilha = new Pilha<String>();
            Fila<String> fila   = (Fila)fil.clone();
            double op1;
            char  oper;
            double op2;
            String aux;

            while (!fila.vazia())
            {
                aux = fila.getElemento();
                try 
                { 
                    Double.parseDouble(aux);
                    pilha.empilhe(aux);
                    fila.desenfileire();
                }
                catch (NumberFormatException e)
                {
                    oper = aux.charAt(0);
                    fila.desenfileire();
                    op2  = Double.parseDouble(pilha.getElemento());
                    pilha.desempilhe();
                    op1  = Double.parseDouble(pilha.getElemento());
                    pilha.desempilhe();
                    switch (oper)
                    {
                        case '+':
                            pilha.empilhe(Double.toString(op1 + op2));
                            break;
                        case '-':
                            pilha.empilhe(Double.toString(op1 - op2));
                            break;
                        case '*':
                            pilha.empilhe(Double.toString(op1 * op2));
                            break;
                        case '/':
                            pilha.empilhe(Double.toString(op1 / op2));
                            break;
                        case '^':
                            pilha.empilhe(Double.toString(Math.pow(op1, op2)));
                            break;     
                    }
                }
            }
            
            return Double.parseDouble(pilha.getElemento());
        }
        catch (Exception erro) {}
        
        return 0; // nao vai ocorrer
    }
    
    /**
     * Cria uma matriz que será utilizada na etapa 1 da resolução.
     * @return uma matriz de boolean com os valores indicando como proceder
     * durante a execução da etapa 1
     */
    protected MatrizBoolean configurarMatriz ()
    {
        try
        {
            String[] vetMat = new String[7];
            String operadores = "(^*/+-)";
            for (int i=0; i<=operadores.length()-1; i++)
                vetMat[i]=Character.toString(operadores.charAt(i));

            Coordenada coord = new Coordenada(6, 6);
            MatrizBoolean ret = new MatrizBoolean (coord, vetMat, vetMat);

            ret.setItemNaMatriz(0, 0, false);
            ret.setItemNaMatriz(0, 1, false);
            ret.setItemNaMatriz(0, 2, false);
            ret.setItemNaMatriz(0, 3, false);
            ret.setItemNaMatriz(0, 4, false);
            ret.setItemNaMatriz(0, 5, false);
            ret.setItemNaMatriz(0, 6, false);
            ret.setItemNaMatriz(1, 0, false);
            ret.setItemNaMatriz(1, 1, false);
            ret.setItemNaMatriz(1, 2, true);
            ret.setItemNaMatriz(1, 3, true);
            ret.setItemNaMatriz(1, 4, true);
            ret.setItemNaMatriz(1, 5, true);
            ret.setItemNaMatriz(1, 6, true);
            ret.setItemNaMatriz(2, 0, false);
            ret.setItemNaMatriz(2, 1, false);
            ret.setItemNaMatriz(2, 2, true);
            ret.setItemNaMatriz(2, 3, true);
            ret.setItemNaMatriz(2, 4, true);
            ret.setItemNaMatriz(2, 5, true);
            ret.setItemNaMatriz(2, 6, true);
            ret.setItemNaMatriz(3, 0, false);
            ret.setItemNaMatriz(3, 1, false);
            ret.setItemNaMatriz(3, 2, true);
            ret.setItemNaMatriz(3, 3, true);
            ret.setItemNaMatriz(3, 4, true);
            ret.setItemNaMatriz(3, 5, true);
            ret.setItemNaMatriz(3, 6, true);
            ret.setItemNaMatriz(4, 0, false);
            ret.setItemNaMatriz(4, 1, false);
            ret.setItemNaMatriz(4, 2, false);
            ret.setItemNaMatriz(4, 3, false);
            ret.setItemNaMatriz(4, 4, true);
            ret.setItemNaMatriz(4, 5, true);
            ret.setItemNaMatriz(4, 6, true);
            ret.setItemNaMatriz(5, 0, false);
            ret.setItemNaMatriz(5, 1, false);
            ret.setItemNaMatriz(5, 2, false);
            ret.setItemNaMatriz(5, 3, false);
            ret.setItemNaMatriz(5, 4, true);
            ret.setItemNaMatriz(5, 5, true);
            ret.setItemNaMatriz(5, 6, true);
            ret.setItemNaMatriz(6, 0, false);
            ret.setItemNaMatriz(6, 1, false);
            ret.setItemNaMatriz(6, 2, false);
            ret.setItemNaMatriz(6, 3, false);
            ret.setItemNaMatriz(6, 4, false);
            ret.setItemNaMatriz(6, 5, false);
            ret.setItemNaMatriz(6, 6, false);

            return ret;
        }
        catch (Exception erro) {}
        return null;
    }
    
    /**
     * Método toString() da classe ExpressaoAritmetica.
     * @return o valor de {@link #exp}
     */
    public String toString()
    {
        return this.exp;
    }
    
    /**
     * Calcula o código hash da expressão guardada
     * @return o código hash da expressão
     */
    public int hashCode()
    {
        return this.exp.hashCode();
    }
    
    /**
     * Compara duas expressões aritméticas pelos seus resultados.
     * @param expAComparar outra ExpressaoAritmetica a ser comparada com a 
     * instância que chamou o método
     * @return 
     * <ul>
     *      <li><b>-1</b>, caso a instância que chamou o método seja menor que a outra</li>
     *      <li><b>0</b>, caso as duas instâncias tiverem o mesmo resultado</li>
     *      <li><b>1</b>, caso a instância que chamou o método seja maior que a outra</li>
     * </ul>
     */
    public int compareTo (ExpressaoAritmetica expAComparar)
    {
        double aux1 = this.getExpressaoResolvida();
        double aux2 = expAComparar.getExpressaoResolvida();
        if (aux1 < aux2)
            return -1;
        if (aux1 > aux2)
            return 1;
        return 0;
    }
    
}
    
    

