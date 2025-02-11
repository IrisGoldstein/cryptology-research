// generally useful cryptological operations, all as static methods
public class CryptologyTools 
{
	public static String replaceCharacters(String originalString, char originalChar, char newChar)
	{
		return originalString.replace(originalChar, newChar);
	}

	public static String removeWhiteSpace(String originalString)
	{
		String[] stringArr = originalString.split("\\s");
		String out = "";
		
		for (String str : stringArr)
		{
			out += str;
		}

		return out;
	}
}