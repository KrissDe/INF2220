import java.util.*;

public class SpellChecker{
    Tree t;
    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    //constructor
    SpellChecker(Tree tr){
        t = tr;
    }
   
   
    //method that coordinates all types of spell checking
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
    
    
    

    //method for first type of user input - re-used from assignment text but a bit edited
    public void similarOne(String word, ArrayList<String> all_generated_words){
        char[] word_array = word.toCharArray();
        char[] tmp;
        
//         System.out.println("In method 1"); --for testing bare

        String[] words = new String[word_array.length-1];
        
        for(int i=0; i<word_array.length-1; i++){
            tmp = word_array.clone();
            words[i] = swap(i, i+1, tmp);
            
//              System.out.println(words[i]); --teste om metoden fungerer som den skal

            if(t.findWord(words[i])){ //if the similar word exists in my tree
                all_generated_words.add(words[i]);
            }
            
        }
    }
    

    
    
    //method for second type of user input - assigning each letter of the alphabet to each letter of the input word
    public void similarTwo(String word, ArrayList<String> all_generated_words){
        char[] word_arr = word.toCharArray();
        char[] tmp;
        String newWord;
        
//         System.out.println("In method 2"); --for testing bare
        
        for(int i=0; i<word_arr.length; i++){ //iterating input word
            tmp = word_arr.clone();
            
            for(int j=0; j<alphabet.length; j++){ //iterating alphabet letters
               tmp[i] = alphabet[j];
               newWord = new String(tmp);
//                System.out.println(newWord);    --teste om metoden fungerer som den skal

                if(t.findWord(newWord)){ //if similar word exists in my tree
                    all_generated_words.add(newWord);
                }
                
            }
            
        }
    }
    
    
    
    //method for third type of user input - put each alphabet letter in each position of the input word
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
    
    
    
    //method for fourth and last type of user input - remove each letter in the input word
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
    
    
    //method for swapping - reused from assignment precode
    public String swap(int a, int b, char[] word){
        char tmp = word[a];
        word[a] = word[b];
        word[b] = tmp;
        
        return new String(word);
    }
}