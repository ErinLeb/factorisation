import java.lang.Math;
import java.util.ArrayList;

public class Decompositionnaive{
    static final int[] DiviseursPremiers = new int[]{2,3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,73, 79, 83, 89, 97};

    public static boolean est_premier(int x){
        assert(x <= 10000);
        
        for(int i = 0; i < DiviseursPremiers.length; i++){
            if(DiviseursPremiers[i] > (int) Math.floor(Math.sqrt(x))){
                break;
            }
            if(x%DiviseursPremiers[i] == 0){
                return false;
            }
        }
        return true;
    }

    public static void factorisation(int x){
        if(x > 10000){
            System.out.println("Nombre trop grand");
            return;
        }

        if(est_premier(x)){
            System.out.println("Ce nombre est premier.");
            System.out.println(x + " = 1 x " + x);
            return;
        }

        ArrayList<Integer> diviseurs = new ArrayList<Integer>();
        int pi = 0; //indice du plus petit diviseur 
        int reste = x;
        while(reste > 1){
            for(int j = pi; j < DiviseursPremiers.length; j++){ //on commence au plus petit diviseur
                if(reste%DiviseursPremiers[j] == 0){
                    diviseurs.add(DiviseursPremiers[j]);
                    reste = reste/DiviseursPremiers[j];
                    pi = j;
                    break;
                }
            }
            if(est_premier(reste)){
                diviseurs.add(reste);
                reste = 1;
                break;
            }
        }

        if(reste != 1){
            return;
        }

        System.out.print(x + " = ");
        for(int j = 0; j < diviseurs.size(); j++){
            System.out.print(diviseurs.get(j));
            if(j != diviseurs.size() - 1){
                System.out.print(" x ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Donnez un nombre à factoriser plus petit que 10 000 s'il vous plaît.");
        }else{
            factorisation(Integer.valueOf(args[0]));
        }
    }
}