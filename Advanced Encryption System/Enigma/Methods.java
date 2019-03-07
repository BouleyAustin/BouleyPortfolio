
package enigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Methods
{
    /*
    variables to store information that I will manipulate and change later.
    */
    private String document;
    private ArrayList<String> words;
    private ArrayList<String> changed;
    private final Map<Character, Character> rotorI = new HashMap<>();
    private final Map<Character, Character> rotorII = new HashMap<>();
    private final Map<Character, Character> rotorIII = new HashMap<>();
    
 
    /*
    will take in the original text which are all converted to lower case
    and then calls the method to intialize the values to the rotors
    */
    Methods(String document)
    {
        this.document = document;
        addValuesToRotorsandReflector();
    }
    /*
    gives character values to rotorsI, II, and III
    */
    private void addValuesToRotorsandReflector()
    {
        rotorI.put('a','z');
        rotorI.put('b','y');
        rotorI.put('c','x');
        rotorI.put('d','w');
        rotorI.put('e','v');
        rotorI.put('f','u');
        rotorI.put('g','t');
        rotorI.put('h','s');
        rotorI.put('i','r');
        rotorI.put('j','q');
        rotorI.put('k','p');
        rotorI.put('l','o');
        rotorI.put('m','n');
        rotorI.put('n','m');
        rotorI.put('o','l');
        rotorI.put('p','k');
        rotorI.put('q','j');
        rotorI.put('r','i');
        rotorI.put('s','h');
        rotorI.put('t','g');
        rotorI.put('u','f');
        rotorI.put('v','e');
        rotorI.put('w','d');
        rotorI.put('x','c');
        rotorI.put('y','b');
        rotorI.put('z','a');
        
        rotorII.put('a','h');
        rotorII.put('b','i');
        rotorII.put('c','j');
        rotorII.put('d','k');
        rotorII.put('e','l');
        rotorII.put('f','m');
        rotorII.put('g','n');
        rotorII.put('h','o');
        rotorII.put('i','p');
        rotorII.put('j','q');
        rotorII.put('k','r');
        rotorII.put('l','s');
        rotorII.put('m','t');
        rotorII.put('n','u');
        rotorII.put('o','v');
        rotorII.put('p','w');
        rotorII.put('q','x');
        rotorII.put('r','y');
        rotorII.put('s','z');
        rotorII.put('t','a');
        rotorII.put('u','b');
        rotorII.put('v','c');
        rotorII.put('w','d');
        rotorII.put('x','e');
        rotorII.put('y','f');
        rotorII.put('z','g');
        
        rotorIII.put('a','q');
        rotorIII.put('b','a');
        rotorIII.put('c','z');
        rotorIII.put('d','w');
        rotorIII.put('e','s');
        rotorIII.put('f','x');
        rotorIII.put('g','e');
        rotorIII.put('h','d');
        rotorIII.put('i','c');
        rotorIII.put('j','r');
        rotorIII.put('k','f');
        rotorIII.put('l','v');
        rotorIII.put('m','t');
        rotorIII.put('n','g');
        rotorIII.put('o','b');
        rotorIII.put('p','y');
        rotorIII.put('q','h');
        rotorIII.put('r','n');
        rotorIII.put('s','u');
        rotorIII.put('t','j');
        rotorIII.put('u','m');
        rotorIII.put('v','i');
        rotorIII.put('w','k');
        rotorIII.put('x','o');
        rotorIII.put('y','l');
        rotorIII.put('z','p');
        
    }
    
    /*
    takes the main documents and stores each word 
    individually in order change the specific words later.
    */
    void disectWordsFromMainDocument(String document)
    {
        String word;
        words = new ArrayList<>(); 
        Scanner wordChopper = new Scanner(document);
        while(wordChopper.hasNext())
        {
            word = wordChopper.next();
            words.add(word);
        }
    }
    
    /*
    encrypts each character in each word then puts 
    word back in the word list.
    */
    void rotorIandIIandIII()
    {
        for(int i = 0; i<words.size(); i++)
        {
            String word="";
            for(char a: words.get(i).toCharArray())
            {
                char b = rotorI.get(a);

                char c = rotorII.get(b);

                char d = rotorIII.get(c);

                word += ""+d;
            }
            words.set(i,word);
            moveRotors();
        }
    }
    
    /*
    reflects back through the rotors which t
    he original enigma machine did. 
    */
    void reflect()
    {
        rotorIandIIandIII();
    }
    
    /*
    takes the list of words that have been encrypted 
    and prints them out in a message popup dialog 
    */
    void print()
    {
        String word = "";
        for(String s : words)
        {
            word+= s+" ";
        }
        JOptionPane.showMessageDialog(null,word);
    }

    /*
    moves rotors just like the original enigma machine
    did
    */
    private void moveRotors() 
    {
        for(Character key3 : rotorIII.keySet())
        {
            Character value3 = rotorI.get(key3);
            rotorI.put(key3, moveRotorsHelper(value3));
                  
            for(Character key2 : rotorII.keySet())
            {
                Character value2 = rotorI.get(key2);
                rotorI.put(key2, moveRotorsHelper(value2));

                for(Character key : rotorI.keySet())
                {
                      Character value = rotorI.get(key);
                      rotorI.put(key, moveRotorsHelper(value));
                }
            }
        }
    }
    
    private Character moveRotorsHelper(Character a)
    {
        switch(a)
        {
            case 'a' : return 'b';
            case 'b' : return 'c';
            case 'c' : return 'd';
            case 'd' : return 'e';
            case 'e' : return 'f';
            case 'f' : return 'g';
            case 'g' : return 'h';
            case 'h' : return 'i';
            case 'i' : return 'j';
            case 'j' : return 'k';
            case 'k' : return 'l';
            case 'l' : return 'm';
            case 'm' : return 'n';
            case 'n' : return 'o';
            case 'o' : return 'p';
            case 'p' : return 'q';
            case 'q' : return 'r';
            case 'r' : return 's';
            case 's' : return 't';
            case 't' : return 'u';
            case 'u' : return 'z';
            case 'v' : return 'w';
            case 'w' : return 'x';
            case 'x' : return 'y';
            case 'y' : return 'z';
            case 'z' : return 'a';
                    
        }
        return 'a';
    }
  
}
