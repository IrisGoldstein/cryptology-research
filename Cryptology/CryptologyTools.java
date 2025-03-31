import java.io.*;
import java.util.*;

// generally useful cryptological operations, all as static methods
public class CryptologyTools 
{
	/**
	 * Removes all of the whitespace from a string
	 * 
	 * @param originalString The string to remove whitespace from
	 * @return A new string with no whitespace
	 */
	public static String removeWhiteSpace(String originalString)
	{
		String[] stringArr = originalString.split("\\s");
		StringBuilder out = new StringBuilder();
		
		for (String str : stringArr)
		{
			out.append(str);
		}

		return out.toString();
	}

	/**
	 * Replace all of the characters in a String with and arbitrarily specified String.<br>
	 * If a character does not have a specified replacement, it is included, unchanged, in the output.
	 * 
	 * @param text The text to replace/encrypt
	 * @param charMap A Map that gives each character's replacement.
	 * @return A new string with all substitutions made
	 */
	public static String characterMapping (String text, Map<Character, String> charMap)
	{
		StringBuilder out = new StringBuilder(text.length());

		for (char chr : text.toCharArray())
		{
			if (charMap.containsKey(chr))
			{
				out.append(charMap.get(chr));
			}
			else
			{
				out.append(chr);
			}
		}

		return out.toString();
	}

	/**
	 * Replace all of the characters in a String with and arbitrarily specified String.<br>
	 * If a character does not have a specified replacement, it is included, unchanged, in the output.<br>
	 * The iterator should separate by whitespace, and take the form: "char replacementStr" on each line.
	 * 
	 * @param text The text to replace/encrypt
	 * @param charMapInput An iterator, such as a scanner, reading the file with the replacement map.
	 * @return A new string with all substitutions made
	 */
	public static String characterMapping (String text, Iterator<String> charMapInput)
	{
		HashMap<Character, String> charMap = new HashMap<>(26);

		while (charMapInput.hasNext())
		{
			charMap.put(charMapInput.next().charAt(0), charMapInput.next());
		}

		return characterMapping (text, charMap);
	}

	/**
	 * Replace all of the characters in a String with and arbitrarily specified String.<br>
	 * If a character does not have a specified replacement, it is included, unchanged, in the output.<br>
	 * The input file should take the form: "char replacementStr" on each line.
	 * 
	 * @param text The text to replace/encrypt
	 * @param charMapFile A BufferedReader for the file with the replacement map.
	 * @return A new string with all substitutions made
	 */
	public static String characterMapping (String text, BufferedReader charMapFile) throws IOException
	{
		HashMap<Character, String> charMap = new HashMap<>(26);

		while (charMapFile.ready())
		{
			charMap.put((char) charMapFile.read(), charMapFile.readLine().trim());
		}

		return characterMapping (text, charMap);
	}

	/**
	 * Replace all of the characters in a String with and arbitrarily specified String.<br>
	 * If a character does not have a specified replacement, it is included, unchanged, in the output.<br>
	 * The input file should take the form: "char replacementStr" on each line.
	 * 
	 * @param text The text to replace/encrypt
	 * @param charMapFile The name of the file with the replacement map.
	 * @return A new string with all substitutions made
	 */
	public static String characterMapping (String text, String fileName) throws IOException
	{
		BufferedReader fin = new BufferedReader(new FileReader(fileName));

		return characterMapping(text, fin);
	}
}
