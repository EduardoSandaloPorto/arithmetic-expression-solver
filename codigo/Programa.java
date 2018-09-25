
import expressaoaritmetica.*;
import java.io.*;

/**
 * Calculadora de expressão aritmética. <br>
 * Desenvolvido para a disciplina de Programação Orientada a Objetos, do
 * Colégio Técnico de Campinas
 * @author Eduardo Sandalo Porto
 * @author Felipe Reis Corerato
 * @author João Henri Carrenho Rocha
 */
public class Programa {
    
    /**
     * Método main
     * @param args não faz diferença
     */
    public static void main (String[] args)
    {
        try
        {
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            
            boolean fim=false;
            while (!fim)
            {
                System.out.println("===================================================");
                System.out.println("Resolvedor de expressao aritmetica");
                System.out.println("===================================================");
                System.out.println();
                System.out.println("Operadores aceitos: '+-*/^()'");
                System.out.println("Digite uma expressao aritmetica");
                
                String exp = teclado.readLine();
                try
                {
                    ExpressaoAritmetica expressao = new ExpressaoAritmetica (exp);
                    System.out.println("Resultado da expressao: ");
                    System.out.println(expressao.getExpressaoResolvida());
                    System.out.println();
                }
                catch (Exception erro) { System.out.println(erro); }
                
                System.out.println("Deseja executar o programa novmente? (S/N)");
                String resp=teclado.readLine();
                if (!resp.isEmpty())
                    if (Character.toUpperCase(resp.charAt(0)) == 'N' )
                        fim=true;
                System.out.println("===================================================");
                System.out.println();
            }
        }
        catch (Exception erro) {System.err.println(erro);}
    }
}
