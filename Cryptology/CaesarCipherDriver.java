import java.io.*;

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
		BufferedReader fin = new BufferedReader( new FileReader("message.txt"));
		PrintWriter fout = new PrintWriter ( new BufferedWriter ( new FileWriter("encrypted.txt")));
		String message = "";
		int rotation;
		CaesarCypher cypher;

		// input
		rotation = Integer.parseInt(fin.readLine());

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
		
		// output
		fout.print(cypher);
		System.out.println(cypher);

		// exit
		fin.close();
		fout.close();
		System.exit(0);
	}
}
