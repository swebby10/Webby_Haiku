package haiku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 *
 * @author Steve Webby
 *
 */
/*
 * 
 * Steps to counting # of syllables
 * 1. Count number of values.(done)
 * 2. Add 1 every time a 'y' makes a vowel noise.(included in vowels for now)
 * 3. Subtract 1 for each silent vowel, like silent 'e' at the end.(done)
 * 4. Subtract 1 for each dipthong and triphthong.(need to fix for triphthongs and add to triphthong list.)
 * 5. If word ends in 'le' or 'les' add 1 if the letter before is a consonant.(need to fix)
 * Ending # is the number of syllables the word has.
 * 
 */
public class Run {
public char[] vowles = {'a','e','i','o','u','y'};
public String[] diphthong = {
		"aa","ae","ai","ao","au","ay",
		"ea","ei","ee","eo","eu","ey",
		"ia","ie","ii","io","iu","iy",
		"oa","oe","oi","oo","ou","oy",
		"ua","ue","ui","uo","uu","uy"};
public String[] triphthong = {"eye","eau","iou"};//TODO add more triphthongs...
public char[] consonant = {
		'b','c','d','f','g',
		'h','j','k','k','m',
		'n','p','q','r','s',
		't','v','w','x','z'};
public char[] special = {'y'};
String[] allWords;
String[] allHaiku;
public Run(){
	fileReader();
	cleanup();
	haikuSolver(allWords);
	System.out.println("Length:"+allWords.length);
	for(int i = 0; i < allHaiku.length; i++){
		System.out.println(i+".) "+allHaiku[i]);
	}
	System.out.println("PROF. GREEN, all 26,000 Lines takes a while but it works, I set line 67 to just the first 5,000 words.");
}
	public void haikuSolver(String[] allWords){
		List<String> temps = new ArrayList<String>();
		int location= 0 ;
		int part = 0;
		int counter = 0;
		String current = "";
		String firstLine = "";
		String secondLine ="";
		String thirdLine = "";
		int tmpLoc = 0;
		int oldLoc = 0;
		while(location < 5000){
			if(part == 0){
			for(int i = 0; i < 4; i++){
			if(counter < 5){
				current += " "+allWords[location+i];
				counter += syllablecounter(allWords[location+i]);
				tmpLoc += 1;
				//System.out.println(current);
			}
		}
			if(counter ==5){
				oldLoc = location;
				location += tmpLoc;
				part = 1;
				firstLine = current;
				tmpLoc = 0;
				counter = 0;
				current = "";
			}
			if(counter > 5){
				location += 1;
				tmpLoc = 0;
				counter = 0;
				current = "";
				part = 0;
				firstLine = "";
				secondLine ="";
				thirdLine = "";
			}
			}
				if(part == 1){
				for(int i = 0; i < 6; i++){
					if(counter < 7){
						current += " "+allWords[location+i];
						counter += syllablecounter(allWords[location+i]);
						tmpLoc += 1;
						//System.out.println(current);
					}
				}
					if(counter ==7){
						location += tmpLoc;
						part = 2;
						secondLine = current;
						tmpLoc = 0;
						counter = 0;
						current = "";
					}
					if(counter > 7){
						tmpLoc = 0;
						location = oldLoc + 1;
						counter = 0;
						current = "";
						part = 0;
						firstLine = "";
						secondLine ="";
						thirdLine = "";
					}
				}
				if(part == 2){
				for(int i = 0; i < 4; i++){
					if(counter < 5){
						current += " "+allWords[location+i];
						counter += syllablecounter(allWords[location+i]);
						tmpLoc += 1;
						//System.out.println(current);
					}
				}
					if(counter ==5){
						location += tmpLoc;
						oldLoc = location;
						thirdLine = current;
						String haiku = firstLine + " - "+secondLine+" - "+thirdLine+".";
						temps.add(haiku);
						firstLine = "";
						secondLine = "";
						thirdLine = "";
						tmpLoc = 0;
						part = 0;
						counter = 0;
					}
					if(counter > 5){
						tmpLoc = 0;
						location = oldLoc + 1;
						counter = 0;
						current = "";
						part = 0;
						firstLine = "";
						secondLine ="";
						thirdLine = "";
					}
				
			}
		}
		
		 String[] tempsArray = temps.toArray(new String[0]);
		 allHaiku = tempsArray;
	}
	public int countVowles(String word){
		//currently counting y as a vowel...
		String word1 = word.toLowerCase();
		int vowle = 0;
		for(int i = 0; i < word1.length(); i++){
			char ch = word.charAt(i);
			for(int j= 0; j < vowles.length;j++){
				if(ch == vowles[j]){
					vowle++;
				}
			}
			if(word1.length()==i){
				if(ch == 'e'){
					vowle--;
				}
			}
		}
		return vowle;
	}
	
	public int thongchecker(String word){
		String word1 = word.toLowerCase();
		int diCount = 0;
		int triCount = 0;
		//count dipthongs
		for(int i =0; i < word1.length()-1;i++){
			String di = ""+word1.charAt(i)+""+word1.charAt(i+1);
			for(int j=0;j < diphthong.length; j++){
				if(di == diphthong[j]){
					diCount += 1;
				}
			}
		}
		//count triphthongs
		for(int i =0; i <word1.length()-2;i++){
			String tri = ""+word1.charAt(i)+""+word1.charAt(i+1)+""+word1.charAt(i+2);
			for(int j = 0; j < triphthong.length; j++){
				if(tri == triphthong[j]){
					triCount++;
				}
			}
		}
		return diCount;
	}

	public int silente(String word){
		int silent = 0;
		String word1 = word.toLowerCase();
		for(int i = word1.length(); i > word1.length()-1; i--){
			if(word1.charAt(i-1)== 'e'){
				silent = 1;
			}
		}
		return silent;
	}

	public int endingle(String word){
		int ending = 0;
		String word1 = word.toLowerCase();
		if(word1.length() >= 2){
		for(int i = word.length(); i > word.length()-1; i--){
			String le = word1.charAt(i-2)+""+word1.charAt(i-1);
			if(le == "le"){
				for(int j = 0; j < consonant.length; j++){
					if(word1.charAt(i-3)==consonant[j]){
						ending = 1;
					}
				}
			}
		}}
		return ending;
	}
	
	public int endingles(String word){
		int ending = 0;
		String word1 = word.toLowerCase();
		if(word1.length() >= 3){
		for(int i = word.length(); i > word.length()-1; i--){
			String les = word1.charAt(i-3)+""+word1.charAt(i-2)+""+word1.charAt(i-1);
			if(les == "les"){
				for(int j = 0; j < consonant.length; j++){
					if(word1.charAt(i-4)==consonant[j]){
						ending = 1;
					}
				}
			}
		}
		}
		return ending;
	}

	public int syllablecounter(String word){
		int syllables = countVowles(word)-thongchecker(word)-silente(word)+endingle(word)+endingles(word);
		//printer(word);
		return syllables;
	}
	public void printer(String word){
		System.out.println("Vowles: "+countVowles(word)+" || Thong Check: "+thongchecker(word)+" || Silent e: "+silente(word)+" || LE: "+endingle(word)+" || LES: "+endingles(word));
	}
	
	public static void main(String[] args) {
		new Run();	
	}
	public void fileReader(){
		 try {
			String word = "";
			@SuppressWarnings("resource")
			Scanner inFile1 = new Scanner(new File("C:\\Users\\Steve\\Documents\\eclipseWorkspace\\haiku\\src\\text.txt")).useDelimiter(" ");
			List<String> temps = new ArrayList<String>();
		    while (inFile1.hasNext()) {
		      word = inFile1.next().trim();
		     if(word != null){
		    	 temps.add(word);
		     }
		      }
		    inFile1.close();
		    String[] tempsArray = temps.toArray(new String[0]);
		    allWords = tempsArray;
		    for (String s : tempsArray) {
		      //System.out.println(s);
		    }
		 } catch (FileNotFoundException e) {
			System.out.println("Trouble with the fileReader method.");
			e.printStackTrace();
		}
	}
	public void cleanup(){
		List<String> temps = new ArrayList<String>();
		for(int i = 0; i < allWords.length;i++){
			allWords[i].trim();
			if(allWords[i].isEmpty()){
			}else{
				temps.add(allWords[i]);
			}
		}
			allWords = temps.toArray(new String[0]);
		}
	}
