
package aees;
import javax.swing.JOptionPane;

public class AEES 
{
        // performs encryption & decryption 
        public static void main(String[] args) throws Exception 
        {

                String document;
                document = JOptionPane.showInputDialog("Please copy and paste your document.");
                Methods.disectPlainText(document);
                Methods.printEncryptedText();
                Methods.decryptPlainText();

        }
}
