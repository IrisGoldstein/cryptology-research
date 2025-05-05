import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Pattern;

// generally useful cryptological operations, all as static methods
public class CryptologyTools 
{
	/// Useful static variables 
	final static Pattern whiteSpace = Pattern.compile("\\s+");
	final static Pattern punctuation = Pattern.compile("[\\p{P}\\p{S}\\p{C}]+");
	final static Pattern nonAlphaNumeric = Pattern.compile("[^\\p{L}^\\p{N}]+");

	/**
	 * Rotate a value by a specified number of positions, constrained within a range. <br>
	 * If the value exceeds the given range, it will wrap around.
	 * 
	 * @param val The value that is being rotated. Usually the index or ASCII value of a character
	 * @param rot The number of places to rotate by
	 * @param min The minimum bound of the interval (inclusive). If the value, after rotation, is lower than this value, it will be wrapped around
	 * @param max The maximum bound of the interval (exclusive). If the value, after rotation, is greater than or equal to, it will be wrapped around
	 */
	public static int boundedRotation(int val, int rot, int min, int max)
	{
		int range = max - min;

		// rot %= range;

		val += rot;

		if (val < min)
			val += range;
		if (val >= max)
			val -= range;

		return val;
	}

	/**
	 * Remove all instances of a given regex Pattern from a string
	 * 
	 * @param originalString The original string, unchanged
	 * @param pattern The Pattern to be removed
	 * @return A new String with all elements matching the pattern removed
	 */
	public static String removePattern (String originalString, Pattern pattern)
	{
		Scanner scan = new Scanner(originalString).useDelimiter(pattern);
		StringBuilder out = new StringBuilder();
		
		while (scan.hasNext())
		{
			out.append(scan.next());
		}

		scan.close();
		return out.toString();
	}

	/**
	 * Removes all of the whitespace from a String
	 * 
	 * @param originalString The String to remove whitespace from
	 * @return A new String with no whitespace
	 */
	public static String removeWhiteSpace (String originalString)
	{
		return removePattern (originalString, whiteSpace);
	}

	/**
	 * Removes all punctuation from a String
	 * 
	 * @param originalString The String to remove punctuation from
	 * @return A new String with no punctuation
	 */
	public static String removePunctuation (String originalString)
	{
		return removePattern(originalString, punctuation);
	}

	/**
	 * Removes all non-alphanumeric characters from a String
	 * @param originalString The String to remove non-alphanumeric characters from
	 * @return A new String with only letters and numbers
	 */
	public static String removeNonAlNum (String originalString)
	{
		return removePattern(originalString, nonAlphaNumeric);
	}

	/**
	 * Periodically adds junk data into a String
	 * @param inputStr The String to be garbled
	 * @param isUpperCase Indicates whether junk characters should be uppercase or lowercase. Lowercase unless indicated otherwise.
	 * @param frequency The number of characters between junk data
	 * @param offset The offset in the junk data from the origin
	 * @return A new String, with new periodic meaningless data.
	 */
	public static String addJunk (String inputStr, int frequency, int offset, boolean isUpperCase)
	{
		StringBuilder out = new StringBuilder();
		offset %= frequency;
		SecureRandom secRand = new SecureRandom();
		int junk;
		
		for (int i = 0; i < inputStr.length(); ++i)
		{
			if (i % frequency == offset)
			{
				junk = (int) 'a' + secRand.nextInt(26);
				if (isUpperCase)
				{
					junk -= 32;
				}
				out.append((char) junk);
			}
			out.append(inputStr.charAt(i));
		}

		return out.toString();
	}

	/**
	 * Periodically adds junk data into a String
	 * @param inputStr The String to be garbled
	 * @param isUpperCase Indicates whether junk characters should be uppercase or lowercase. Lowercase unless indicated otherwise.
	 * @param frequency The number of characters between junk data, starting from the first character
	 * @return A new String, with new periodic meaningless data.
	 */
	public static String addJunk (String inputStr, int frequency, boolean isUpperCase)
	{
		return addJunk(inputStr, frequency, 0, isUpperCase);
	}

	/**
	 * Periodically adds junk data into a String with a random (2-7 characters), evenly spaced gap between junk characters
	 * @param inputStr The String to be garbled
	 * @param isUpperCase Indicates whether junk characters should be uppercase or lowercase. Characters are lowercase unless indicated otherwise.
	 * @return A new String, with new periodic meaningless data.
	 */
	public static String addJunk (String inputStr, boolean isUpperCase)
	{
		SecureRandom secRand = new SecureRandom();
		return addJunk(inputStr, secRand.nextInt(2, 8), 0, isUpperCase);
	}

	/**
	 * Periodically adds (lowercase) junk data into a String
	 * @param inputStr The String to be garbled
	 * @param frequency The number of characters between junk data
	 * @param offset The offset in the junk data from the origin
	 * @return A new String, with new periodic meaningless data.
	 */
	public static String addJunk (String inputStr, int frequency, int offset)
	{
		return addJunk(inputStr, frequency, offset, false);
	}

	/**
	 * Periodically adds (lowercase) junk data into a String
	 * @param inputStr The String to be garbled
	 * @param frequency The number of characters between junk data
	 * @return A new String, with new periodic meaningless data.
	 */
	public static String addJunk (String inputStr, int frequency)
	{
		return addJunk(inputStr, frequency, 0, false);
	}

	/**
	 * Periodically adds (lowercase) junk data into a String with a random (2-7 characters), evenly spaced gap between junk characters
	 * @param inputStr The String to be garbled
	 * @return A new String, with new, periodic meaningless data.
	 */
	public static String addJunk (String inputStr)
	{
		return addJunk (inputStr, false);
	}

	/**
	 * Add spaces periodically into a String
	 * @param inputStr The initial String
	 * @param frequency The number of characters between spaces
	 * @param offset The offset of the characters from the origin
	 * @return A new String, with new, periodic spaces
	 */
	public static String addSpaces (String inputStr, int frequency, int offset)
	{
		StringBuilder out = new StringBuilder(inputStr.substring(0,1));
		offset %= frequency;
		
		for (int i = 1; i < inputStr.length(); ++i)
		{
			if (i % frequency == offset)
			{
				out.append(' ');
			}
			out.append(inputStr.charAt(i));
		}

		return out.toString();
	}

	/**
	 * Add spaces periodically into a String
	 * @param inputStr The initial String
	 * @param frequency The number of characters between spaces
	 * @return A new String, with new, periodic spaces
	 */
	public static String addSpaces (String inputStr, int frequency)
	{
		return addSpaces(inputStr, frequency, 0);
	}

	/**
	 * Add spaces periodically into a String, with a random length for the gap (2-7 characters) between the spaces
	 * @param inputStr The initial String
	 * @return A new String, with new, periodic spaces
	 */
	public static String addSpaces (String inputStr)
	{
		SecureRandom secRand = new SecureRandom();

		return addSpaces(inputStr, secRand.nextInt(2,8), 0);
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
