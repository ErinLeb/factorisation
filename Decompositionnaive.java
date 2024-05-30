import java.lang.Math;
import java.util.ArrayList;

public class Decompositionnaive{
    static ArrayList<Integer> DiviseursPremiers = new ArrayList<>();

    /**
	 * Construit la liste des nombres premiers inférieurs ou égaux à @param b 
	 * avec la méthode du crible d'Eratosthène
	 * @param b borne
	 * @return la liste des nombres premiers inférieurs ou égaux à @param b
	 */
	public static ArrayList<Integer> premiersInfB(int b){
		boolean[] tab = new boolean[b + 1];
		tab[0] = false;
		tab[1] = false;
		for(int i = 2; i < tab.length; i++){
			tab[i] = true;
		}
		int i = 2;
		while(i * i <= b){
			if(tab[i]){
				for(int j = i * i; j < tab.length; j += i){
					tab[j] = false;
				}
			}
			i++;
		}
		ArrayList<Integer> premiers = new ArrayList<>();
		for(int j = 0; j < tab.length; j++){
			if(tab[j]){
				premiers.add(j);
			}
		}
		return premiers;
	}

    public static boolean est_premier(int x){
        for(int i = 0; i < DiviseursPremiers.size(); i++){
            if(DiviseursPremiers.get(i) > (int) Math.floor(Math.sqrt(x))){
                break;
            }
            if(x%DiviseursPremiers.get(i) == 0){
                return false;
            }
        }
        return true;
    }

    public static void factorisation(int x){
        if(est_premier(x)){
            System.out.println("Ce nombre est premier.");
            System.out.println(x + " = 1 x " + x);
            return;
        }

        ArrayList<Integer> diviseurs = new ArrayList<Integer>();
        int pi = 0; //indice du plus petit diviseur 
        int reste = x;
        while(reste > 1){
            for(int j = pi; j < DiviseursPremiers.size(); j++){ //on commence au plus petit diviseur
                if(reste%DiviseursPremiers.get(j) == 0){
                    diviseurs.add(DiviseursPremiers.get(j));
                    reste = reste/DiviseursPremiers.get(j);
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
            System.out.println("Donnez un nombre à factoriser s'il vous plaît.");
        }else{
            DiviseursPremiers = premiersInfB((int) Math.floor(Math.sqrt(Integer.valueOf(args[0]))));
            factorisation(Integer.valueOf(args[0]));
        }
    }
}