import java.io.*;
import java.util.Arrays;

public class CaesarCipherDriver 
{
	/**
	 * Reads the file "message.txt" for a rptation value and the text to be encrypted.<br>
	 * Encrypts the text accordingly, and outputs it to a file called "encrypted.txt"<br>
	 * If rotation is set to zero, rotate randomly.
	 */
	public static void main (String[] args) throws IOException
	{
		// var dec
		BufferedReader fin = new BufferedReader( new FileReader("Cryptology/message.txt"));
		// PrintWriter fout = new PrintWriter ( new BufferedWriter ( new FileWriter("encrypted.txt")));
		String message = "";
		int rotation, junkSpacing, junkOffset, whiteSpacing, spaceOffset;
		CaesarCypher cypher;
		String output;

		// input
		rotation = Integer.parseInt(fin.readLine());
		int[] temp = Arrays.stream(fin.readLine().split("\\s")).mapToInt(x -> Integer.parseInt(x)).toArray();
		junkSpacing = temp[0];
		junkOffset = temp[1];
		whiteSpacing = temp[2];
		spaceOffset = temp[3];

		while (fin.ready())
		{
			message += fin.readLine() + "\n";
		}

		// processing
		if (rotation == 0)
		{
			cypher = new CaesarCypher(message);
		}
		else
		{
			cypher = new CaesarCypher(message, rotation);
		}

		// convolution

		output = cypher.getEncryptedMessage();

		// removing spaces if spacing changes are set
		if (whiteSpacing != -1)
		{
			output = CryptologyTools.removeWhiteSpace(output);
		}

		// Adding junk
		// if junk spacing is zero, add randomly spaced junk, -1 -> no junk
		switch (junkSpacing) 
		{
			case -1 -> {
                }
			case 0 -> output = CryptologyTools.addJunk(output);
			default -> output = CryptologyTools.addJunk(output, junkSpacing, junkOffset);
		}

		// Adding spaces
		// if white spacing is zero, add randomly spaced spaces, -1 -> no spaces
		switch (whiteSpacing) 
		{
			case -1 -> {
                }
			case 0 -> output = CryptologyTools.addSpaces(output);
			default -> output = CryptologyTools.addSpaces(output, whiteSpacing, spaceOffset);
		}
		
		// output
		// fout.print(cypher);
		System.out.println(output);

		// exit
		fin.close();
		// fout.close();
		System.exit(0);
	}
}
