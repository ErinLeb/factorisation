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
    public static boolean primaliteFermat(long n){
        Long ln = Long.valueOf(n);
        Random random = new Random();
        BigInteger bigN = BigInteger.valueOf(n);
        ArrayList<Integer> entiersTest = new ArrayList<Integer>();
        for(int i = 0; i < 10; ++i){
            int randInt =  random.nextInt(ln.intValue() - 2) + 2;
            entiersTest.add(randInt);
        }
        for(int i = 0; i < 10; ++i){
            BigInteger a = BigInteger.valueOf(entiersTest.get(i)).pow(ln.intValue() - 1).mod(bigN); // Soit xi l'élément d'indice i de entiersTest, a = xi ^ (n - 1) mod n
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
        if(n <= 2 || primaliteFermat(n)){
            System.out.print(n);
        }
        else if(n % 2 == 1){
            long r = (int) Math.floor(Math.sqrt(n)) + 1;
            long s = r * r - n;
            while(true){
                if(Math.sqrt(s) == Math.floor(Math.sqrt(s))){
                    long a = r + (long) Math.sqrt(s);
                    long b = r - (long) Math.sqrt(s);
                    factorisation(a);
                    System.out.print(" x ");
                    factorisation(b);
                    break;
                }
                r++;
                s = r * r - n;
            }
        }
        else{
            System.out.print("2 x ");
            factorisation(n/2);
        }
    }

    public static void main(String[] args){
        if(args.length != 1){
            System.out.println("Donnez un nombre impair à factoriser s'il vous plaît.");
        }else{
            System.out.print(Integer.valueOf(args[0]) + " = ");
            factorisation(Long.valueOf(args[0]));
            System.out.println();
        }
    }

}
