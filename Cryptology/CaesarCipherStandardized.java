// a basic caesar cypher, that offsets letters by a certain circular offset
public class CaesarCipherStandardized extends Cipher
{
	// var dec
	private final int rotation;

	// constructor
	public CaesarCipherStandardized (String originalMessage, boolean messageViewable, boolean secret, int rotation)
	{
		super(originalMessage, messageViewable, secret, new CaesarKey(rotation));
		this.rotation = rotation;
	}

	// apply the encoding by rotating each character by the specified offset
	// keeps capitalization, rotates digits between themselves, and ignores special characters
	@Override
	public String applyEncoding (String originalMessage, Key encodingParameters)
	{
		String encrypted = "";

		for (char ch : originalMessage.toCharArray())
		{
			if (Character.isLowerCase(ch))
			{
				encrypted += (char) CryptologyTools.boundedRotation((int) ch, rotation, (int) 'a', (int) 'z' + 1);
			}
			else if (Character.isUpperCase(ch))
			{
				encrypted += (char) CryptologyTools.boundedRotation((int) ch, rotation, (int) 'A', (int) 'Z' + 1);
			}
			else 
			{
				encrypted += ch;
			}
		}

		return encrypted;
	}
}
