import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Rozmowa {
	
	public static Pattern numbers = Pattern.compile("[0-9]");
	public static Pattern characters = Pattern.compile("[a-zA-Z]");
	
	public static Matcher chars;
	public static Matcher nums;
	
	private String imie;
	private String lata;
	private String miejscowosc;
	private String zajecie;
	
	
	public void setImie(String imie) {
		this.imie = imie;
	}

	public void setLata(String lata) {
		this.lata = lata;
	}

	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}

	public void setZajecie(String zajecie) {
		this.zajecie = zajecie;
	}

	
	
	
	public String getImie() {
		return imie;
	}

	public String getLata() {
		return lata;
	}

	public String getMiejscowosc() {
		return miejscowosc;
	}

	public String getZajecie() {
		return zajecie;
	}

	
	Rozmowa(String s0, String s1, String s2, String s3) {
		imie = s0;
		lata = s1;
		miejscowosc = s2;
		zajecie = s3;
	}
	
	//Konstruktor rozmowy
	Rozmowa() throws IOException {
		
		BufferedReader we = new BufferedReader(new InputStreamReader(System.in));
	
		int pom =0;
		boolean npoprawne = true;
		
		//Pytanie o imię
		while(npoprawne) {
			System.out.println("Jak masz na imię? ");
			imie = we.readLine().trim(); //odcinanie spacji przed i po ciągu znakowym
			
			//gdy zostanie podane więcej niż jedno imię, tylko pierwsze zostanie zapisane
			if(imie.contains(" ")) {
				pom = imie.indexOf(' ');
				imie = imie.substring(0, pom);
			}
			
			
			//sprawdzanie czy w podany ciąg spełnia reguły
			nums = numbers.matcher(imie);
			if(imie.isEmpty()) { //jeżeli pusty
				System.out.println("Nie podałeś swojego imienia!");
			} else if (nums.find()) { //jeżeli nie spełnia reguły
				System.out.println("Imię nie może zawierać liczb!");
			} else npoprawne = false;
		}
		
		
		//Pytanie o wiek
		npoprawne = true;
		while(npoprawne) {
			System.out.println("Ile masz lat? ");
			lata = we.readLine().trim();
			
			if(lata.contains(" ")) {
				pom = lata.indexOf(' ');
				lata = lata.substring(0, pom);
			}
			
			chars = characters.matcher(lata);
			if(lata.isEmpty()) {
				System.out.println("Nie podałeś swojego wieku!");
			} else if (chars.find()) {
				System.out.println("Twój wiek musi być liczbą!");
			} else npoprawne = false;
		}
		
		
		//Pytanie o miejscowosc
		npoprawne = true;
		while(npoprawne) {
		System.out.println("Skad jestes? ");
			miejscowosc = we.readLine().trim();
			nums = numbers.matcher(miejscowosc);
			if(miejscowosc.isEmpty()) {
				System.out.println("Nie podałeś miejscowości!");
			} else if (nums.find()) {
				System.out.println("Miejcowość nie może zawierać liczb!");
			} else npoprawne = false;
		}
		
		
		
		//Pytanie o zajecie
		npoprawne = true;
		while(npoprawne) {
			System.out.println("Jakie jest twoje hobby? ");
			zajecie = we.readLine().trim();
			nums = numbers.matcher(zajecie);
			if(zajecie.isEmpty()) {
				System.out.println("Nie podałeś zajęcia!");
			} else if (nums.find()) {
				System.out.println("Zajęcie nie może zawierać liczb!");
			} else npoprawne = false;
		}
	}
	
	
	//Wypisanie
	public void Wypisanie() {
		imie = imie.substring(0, 1).toUpperCase() + imie.substring(1);
	    miejscowosc = miejscowosc.substring(0, 1).toUpperCase() + miejscowosc.substring(1);
	    System.out.println("\nCześć "+ imie +"! Masz "+ lata +" lat");
	    System.out.println("mieszkasz w " + miejscowosc +" i twoim hobby jest "+ zajecie);
	}
	
	
	//public static void main(String[] args) throws IOException {
	//	Rozmowa roz1 = new Rozmowa();
	//	roz1.Wypisanie();
	//}
}
