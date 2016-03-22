# Webby_Haiku
Breaks down Text.txt into haikus ( 5 - 7 - 5 ).

 * Steps to counting # of syllables
 * 1. Count number of values.(done)
 * 2. Add 1 every time a 'y' makes a vowel noise.(included in vowels for now)
 * 3. Subtract 1 for each silent vowel, like silent 'e' at the end.(done)
 * 4. Subtract 1 for each dipthong and triphthong.(need to fix for triphthongs and add to triphthong list.)
 * 5. If word ends in 'le' or 'les' add 1 if the letter before is a consonant.(need to fix)
 * Ending # is the number of syllables the word has.
 

 
 diphthong = {
		"aa","ae","ai","ao","au","ay",
		"ea","ei","ee","eo","eu","ey",
		"ia","ie","ii","io","iu","iy",
		"oa","oe","oi","oo","ou","oy",
		"ua","ue","ui","uo","uu","uy"};
		
	triphthong = {
		"eye","eau","iou"};//TODO add more triphthongs...
 
