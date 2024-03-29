import java.util.ArrayList;

public class Friable{
	
	/**
	 * Fonction renvoyant la liste des entiers inférieurs ou égaux à la borne et b-friables avec la méthode du crible quadratique.
	 * @param borne borne jusqu'à laquelle on teste la b-friabilité des entiers.
	 * @param b valeur pour laquelle on teste si les entiers n'ont que des diviseurs premiers inférieurs ou égaux.
	 * @return la liste des entiers b-friables inférieurs ou égaux à la @param borne (Les entiers dont les diviseurs premiers sont <= @param b).
	 */
	public static ArrayList<Integer> cribleQuadratique(int borne, int b){
		if(borne <= 1 || b <= 1){
			System.out.println("Veuillez entrer une borne et un entier B supérieurs à 1.");
			return null;
		}
		ArrayList<Integer> bfriables = new ArrayList<>();
		int[] entiers = new int[borne];
		for(int i = 0; i < borne; i++){
			entiers[i] = i+1;
		}
		for(int i = 2; i <= b; i++){
			for(int j = i - 1; j < entiers.length; j += i){
				while(entiers[j] % i == 0){
					entiers[j] /= i;
				}
			}
		}
		for(int i = 0; i < entiers.length; i++){
			if(entiers[i] == 1){
				bfriables.add(i + 1);
			}
		}
		return bfriables;
	}

	/**
	 * Fonction testant si un entier @param n est @param b friable.
	 * @param n entier dont on veut tester la friabilité.
	 * @param b valeur pour laquelle on teste si @param n n'a que des diviseurs premiers inférieurs ou égaux.
	 * @return true si @param n est @param b friable, false sinon.
	 */
	public static boolean friable(int n, int b){
		if(n < 1 || b < 1) {
			return false;
		}
		for(int i = 2; i <= b; i++) {
			while(n % i == 0){
				n /= i;
			}
		}
		return n == 1;
	}

	public static void main(String[] args){
		if(args.length != 2){
			System.out.println("Le programme attend en premier une borne jusqu'à laquelle tester la B-friablilité des entiers et en second un entier B.");
			return;
		}
		int borne = Integer.valueOf(args[0]);
		int b = Integer.valueOf(args[1]);
		ArrayList<Integer> bfriables = cribleQuadratique(borne, b);
		for(int i = 0; i < bfriables.size(); i++){
			System.out.println(bfriables.get(i) + " est " + b + " friable");
		}

		System.out.println("10 est il 5-friable ? " + friable(10,5));
		System.out.println("10 est il 3-friable ? " + friable(10,3));
	}
}