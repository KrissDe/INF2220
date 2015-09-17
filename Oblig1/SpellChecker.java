import java.util.*;


/** Class SpellChecker that generates similar words for the user input 
 *  in case if it was not found in the dictionary.
 */
 
public class SpellChecker{
    Tree t;
    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    //constructor
    SpellChecker(Tree tr){
        t = tr;
    }
   
   
    /** Method spellCheck() has control over all the methods used to generate similar words for the user input.
     * @param word  the user's word that was not found in the dictionary
     */
     
    public void spellCheck(String word){
        ArrayList<String> all_generated_words = new ArrayList<String>();
        long startTime = System.nanoTime();
        
        //calling spell checking methods
        similarOne(word, all_generated_words);
        similarTwo(word, all_generated_words);
        similarThree(word, all_generated_words);
        similarFour(word, all_generated_words);
        
        long estimatedTime = System.nanoTime() - startTime;
        
        if(all_generated_words.size() == 0){
            System.out.println("No items similar to '" + word + "' have been found.");
        }else{
            System.out.println("Word '" + word + "' is not found. Possible suggestions:");
            
            for(int i=0; i<all_generated_words.size(); i++){ //display suggestions
                System.out.println(all_generated_words.get(i));
            }
            System.out.println();
        }
        System.out.println("The number of lookups that gave a positive answer: " + all_generated_words.size());
        System.out.println("Time used to generate and look for similar words: " + estimatedTime/1000000 + " milliseconds.");
    }
    
    
    

    /** Method similarOne() for the first type of user input - re-used from assignment text but a bit edited. 
     *  Calls method from the Tree class to find each generated word in the dictionary. If found, add to the list of possible word suggestions.
     * @param word    the user's word that was not found in the dictionary
     * @param all_generated_words  ArrayList that is used to store all the similar words being generated from all the spell-checking methods
     */
     
    public void similarOne(String word, ArrayList<String> all_generated_words){
        char[] word_array = word.toCharArray();
        char[] tmp;
        
//         System.out.println("In method 1"); --for testing only

        String[] words = new String[word_array.length-1];
        
        for(int i=0; i<word_array.length-1; i++){
            tmp = word_array.clone();
            words[i] = swap(i, i+1, tmp);
            
//              System.out.println(words[i]);  --test if method executes correctly

            if(t.findWord(words[i])){ //if the similar word exists in my tree
                all_generated_words.add(words[i]);
            }
            
        }
    }
    

    
    
    /** Method similarTwo() for the second type of user input - assigning each letter of the alphabet to each letter of the input word.
     *  Calls method from the Tree class to find each generated word in the dictionary. If found, add to the list of possible word suggestions.
     * @param word   the user's word that was not found in the dictionary
     * @param all_generated_words   ArrayList that is used to store all the similar words being generated from all the spell-checking methods
     */
     
    public void similarTwo(String word, ArrayList<String> all_generated_words){
        char[] word_arr = word.toCharArray();
        char[] tmp;
        String newWord;
        
//         System.out.println("In method 2"); --for testing only
        
        for(int i=0; i<word_arr.length; i++){ //iterating input word
            tmp = word_arr.clone();
            
            for(int j=0; j<alphabet.length; j++){ //iterating alphabet letters
               tmp[i] = alphabet[j];
               newWord = new String(tmp);
//                System.out.println(newWord);    --test if method executes correctly

                if(t.findWord(newWord)){ //if similar word exists in my tree
                    all_generated_words.add(newWord);
                }
                
            }
            
        }
    }
    
    
    
    /** Method similarThree() for the third type of user input - put each alphabet letter in each position of the input word.
     *  Calls method from the Tree class to find each generated word in the dictionary. If found, add to the list of possible word suggestions.
     * @param word   the user's word that was not found in the dictionary
     * @param all_generated_words    ArrayList that is used to store all the similar words being generated from all the spell-checking methods
     */
     
    public void similarThree(String word, ArrayList<String> all_generated_words){
        String newWord;
        
//         System.out.println("In method 3"); --for bedre oversikt under testing

        for(int i=0; i<word.length()+1; i++){
           
           for(int j=0; j<alphabet.length; j++){
              newWord = word.substring(0, i) + alphabet[j] + word.substring(i, word.length());
//               System.out.println(newWord);  --for testing bare
              
              if(t.findWord(newWord)){
                  all_generated_words.add(newWord);
              }
              
           }           
        }
    }
    
    
    
    /** Method similarFour() for the fourth and last type of user input - remove each letter in the input word.
     *  Calls method from the Tree class to find each generated word in the dictionary. If found, add to the list of possible word suggestions.
     * @param word    the user's word that was not found in the dictionary
     * @param all_generated_words     ArrayList that is used to store all the similar words being generated from all the spell-checking methods   
     */
     
    public void similarFour(String word, ArrayList<String> all_generated_words){
        String newWord;
        
//         System.out.println("In method 4"); --for bedre oversikt under testing

        for(int i=0; i<word.length(); i++){
            newWord = word.substring(0,i) + word.substring(i+1,word.length());
//             System.out.println(newWord); --for testing bare
            
            if(t.findWord(newWord)){
                all_generated_words.add(newWord);
            }
            
        }
    }
    
    
    /** Method swap() for swapping letters in the word - reused from assignment precode.
     *  @param a     current letter in the word
     *  @param b     letter next to the current in the word
     *  @param word  word letters of which are being swapped
     *  @return      new generated word as a String
     */
     
    public String swap(int a, int b, char[] word){
        char tmp = word[a];
        word[a] = word[b];
        word[b] = tmp;
        
        return new String(word);
    }
}