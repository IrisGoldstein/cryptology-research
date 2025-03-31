//NAME  :   Astra Smalley
//DATE  :   3/28/25
//TASK  :   Program that decrypts Caesar ciphers

import java.io.*;
import java.util.*;

public class Main
{
    private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) throws IOException
    {
        //File input and output
        BufferedReader cipherIn = new BufferedReader(new FileReader("src/cipher.in")); //cipher.in contains the unaltered Caesar cipher
        PrintWriter cipherOut = new PrintWriter(new BufferedWriter(new FileWriter("src/cipher.out"))); //Program will write the decrypted cipher in cipher.out
        Scanner scan = new Scanner(cipherIn); //Object that reads contents of cipher.in

        //Declare variables
        String cipher = scan.nextLine().toUpperCase(); //Stores cipher
        String decrypted = ""; //Will store decrypted text

        //Call whatever methods are needed here
        for(int i=1; i<alphabet.length; i++)
        {
            cipherOut.println(cycleX(cipher, i).toLowerCase());
        }
        cipherOut.close();
    }

    /**
     *  Changes every character in cipher to the letter of the alphabet x positions to the right
     *
     *  @param cipher Encrypted text
     *  @param x Number of places each letter is moved
     *  @return The shifted string
     */
    public static String cycleX(String cipher, int x)
    {
        String decrypted = "";
        for(int i=0; i<cipher.length(); i++)
        {
            int charPos = (int)cipher.charAt(i) - (int)alphabet[0]; //Gets the position of each letter in the alphabet

            if(cipher.charAt(i) == ' ' || cipher.charAt(i) == '\n' || cipher.charAt(i) == '\t') //Do not alter whitespace
            {
                decrypted += cipher.charAt(i);
            }
            else if(charPos + x >= alphabet.length) //If charPos + x is greater than the size of the alphabet, loop back around and start from 'A'
            {
                decrypted += alphabet[charPos + x - alphabet.length];
            }
            else //Otherwise, add as normal
            {
                decrypted += alphabet[charPos + x];
            }
        }
        return decrypted;
    }

    /**
     * Removes every x character in cipher
     *
     * @param cipher Encrypted text
     * @param x Characters at positions evenly divisible by x will be removed
     * @return cipher, missing every x character
     */
    public static String removeEveryX(String cipher, int x)
    {
        String decrypted = "";
        int whiteSpaceCt = 0;
        for(int i=0; i<cipher.length(); i++)
        {
            if(cipher.charAt(i) == ' ' || cipher.charAt(i) == '\n' || cipher.charAt(i) == '\t')
            {
                decrypted += cipher.charAt(i);
                whiteSpaceCt++;
            }
            else if((i-whiteSpaceCt) != 0 && (i-whiteSpaceCt)%x == 0)
            {
                decrypted += "";
            }
            else
            {
                decrypted += cipher.charAt(i);
            }
        }
        return decrypted;
    }
}
