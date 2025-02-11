// a basic caesar cypher, that offsets letters by a certain circular offset
public class CaesarCypher extends Cypher
{
	// var dec
	private final int offset;

	// constructor
	public CaesarCypher (String originalMessage, boolean messageViewable, int offset)
	{
		super(originalMessage, messageViewable, new CaesarParameters(offset));
		this.offset = offset;
	}

	// apply the encoding by rotating each character by the specified offset
	// keeps capitalization, rotates digits between themselves, and ignores special characters
	public String applyEncoding (String message, Parameters encodingParameters)
	{
		String encodedMessage = "";

		for (char chr : message.toCharArray())
		{
			if (Character.isUpperCase(chr))
			{
				//////////////////////////////////// continue here, implement wraparound
			}
			else if (Character.isLowerCase(chr))
			{

			}
			else if (Character.isDigit(chr))
			{
				
			}
		}
	}

	// gets the offset if the original message is viewable
	public int getOffset() throws IllegalAccessException
	{
		if (messageViewable())
		{
			return offset;
		}
		else
		{
			throw new IllegalAccessException("Offset cannot be viewed, it's a secret :3");
		}
	}
}
