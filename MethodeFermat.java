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
     * Méthode de factorisation de Fermat. Basées sur le fait qu'un entier peut s'écrire comme différence de deux carrés.
     * @param n entier composé impair à factoriser
     * @param puissance puissance de 2 à afficher
     */
    public static void factorisation(long n, int puissance){
        if(n % 2 == 0 && n > 0){
            factorisation(n/2, puissance + 1);
        }
        else if(puissance == 1){
            System.out.print("2 x ");
        }
        else if(puissance > 1){
            System.out.print("2^" + puissance + " x ");
        }
        if(n < 2 || primaliteFermat(n)){
            System.out.print(n);
        }
        else if(n % 2 == 1){
            long r;
            if(Math.sqrt(n) * Math.sqrt(n) == n){
                r = (int) Math.sqrt(n);
            }
            else{
                r = (int) Math.floor(Math.sqrt(n)) + 1;
            }
            long s = r * r - n;
            while(true){
                if(Math.sqrt(s) == Math.floor(Math.sqrt(s))){
                    long a = r + (long) Math.sqrt(s);
                    long b = r - (long) Math.sqrt(s);
                    factorisation(a, 0);
                    System.out.print(" x ");
                    factorisation(b, 0);
                    break;
                }
                r++;
                s = r * r - n;
            }
        }
    }

    public static void main(String[] args){
        if(args.length != 1){
            System.out.println("Donnez un nombre impair à factoriser s'il vous plaît.");
        }else{
            System.out.print(Integer.valueOf(args[0]) + " = ");
            factorisation(Long.valueOf(args[0]), 0);
            System.out.println();
        }
    }

}
