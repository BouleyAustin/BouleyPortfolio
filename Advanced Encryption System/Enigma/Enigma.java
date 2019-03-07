
package enigma;

import javax.swing.JOptionPane;


public class Enigma
{

    public static void main(String[] args) 
    {
        /*
        input box opens and allows for text and number for randomization to be entered 
        then saved in a private variable. Then calls all methods to encrypt the text.
         */
        String document = JOptionPane.showInputDialog("Please copy and paste your document.");
        document = document.toLowerCase();
    
        Methods methods = new Methods(document);
        methods.disectWordsFromMainDocument(document);
        methods.rotorIandIIandIII();
        methods.reflect();
        methods.print();
    }
    
}
