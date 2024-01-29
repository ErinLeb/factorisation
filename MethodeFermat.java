import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;
import java.math.BigInteger;

public class MethodeFermat {

    /**
     * Test de primalité basé sur le petit théorème de Fermat.
     * @param n Entier dont on veut tester la primalité.
     * @return Si le test renvoie faux, l'entier n est composé. S'il renvoie vrai, l'entier est premier avec une probabilité de 1 - 1/2^10
     */
    public static boolean primaliteFermat(int n){
        Random random = new Random();
        BigInteger bigN = BigInteger.valueOf(n);
        ArrayList<Integer> entiersTest = new ArrayList<Integer>();
        for(int i = 0; i < 10; ++i){
            int randInt =  random.nextInt(n - 2) + 2;
            entiersTest.add(randInt);
        }
        for(int i = 0; i < 10; ++i){
            BigInteger a = BigInteger.valueOf(entiersTest.get(i)).pow(n - 1).mod(bigN); // Soit xi l'élément d'indice i de entiersTest, a = xi ^ (n - 1) mod n
            if(!a.equals(BigInteger.valueOf(1))){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Méthode de factorisation de Fermat. Fonctionne avec des entiers composés impairs. On peut tester la primalité de n avec la méthode primaliteFermat mais elle réduit énormément l'efficacité pour les grands entiers
     * @param n entier composé impair à factoriser
     */
    public static void factorisation(long n){
        long r = (int) Math.floor(Math.sqrt(n)) + 1;
        long x = r * r - n;
        while(true){
            if(Math.sqrt(x) == Math.floor(Math.sqrt(x))){
                long a = r + (long) Math.sqrt(x);
                long b = r - (long) Math.sqrt(x);
                System.out.println(n + " = " + a + " x " + b);
                break;
            }
            r++;
            x = x + 2 * r - 1;
        }
    }

    public static void main(String[] args){
        if(args.length != 1){
            System.out.println("Donnez un nombre impair à factoriser s'il vous plaît.");
        }else{
            if(Integer.valueOf(args[0]) % 2 == 0){
                System.out.println("Donnez un nombre impair à factoriser s'il vous plaît.");
            }
            else{
                factorisation(Integer.valueOf(args[0]));
            }
        }
    }

}
