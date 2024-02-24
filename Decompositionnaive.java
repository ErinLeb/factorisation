import java.lang.Math;
import java.util.ArrayList;


public class Decompositionnaive{

    public static void affichage(long x, ArrayList<Long> diviseurs){
        if(diviseurs.isEmpty()){
            System.out.println(x + " est premier.");
            return;
        }
        
        System.out.print(x + " = " );
        for(int i = 0; i < diviseurs.size(); i++){
            System.out.print(diviseurs.get(i));
            if(i != diviseurs.size()-1){
                System.out.print(" x ");
            }else{
                System.out.println();
            }
        }
    }
        
    //implémenter la méthode avec une liste n'a un intérêt que si on implémente la suppression
    //des multiples, or en java, une suppression dans une liste se fait en temps linéaire, donc 
    //complexité linéaire quadratique 

        

    public static void factorisation_it(long x){
        long k = 2;
        ArrayList<Long> diviseurs = new ArrayList<Long>();

        //au lieu de calculer la racine, vérifier si (k+1)² dépasse x 
        //pour les grands entiers : BigInteger

        long q = x;
        int r = (int)Math.sqrt(x);
        while(k <= r && q > 1){
            if(q % k == 0){
                q = q/k;
                r = (int)Math.sqrt(q);
                diviseurs.add(k);
            }else{
                k++;
            }
        }
        if(q > 1){
            diviseurs.add(q);
        }

        affichage(x, diviseurs);
        
    }

    public static void main(String[] args) {
        factorisation_it(9);
        factorisation_it(15);
        factorisation_it(19);
        factorisation_it(1999);
    }
}