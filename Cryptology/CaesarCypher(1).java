import java.security.SecureRandom;

public class CaesarCypher
{
	/// INSTANCE VARIABLES
	
	private final String original;
	private String encrypted;
	// visible: original message and rotation can be accessed
	// secret: if true, message cannot be checked
	private final boolean visible, secret; 
	private final int rotation;

	/// CONSTRUCTORS
	
	public CaesarCypher (String originalMessage, int rotation, boolean visible, boolean secret)
	{
		original = originalMessage;
		this.rotation = rotation;
		if (secret)
			visible = false;
		this.visible = visible;
		this.secret = secret;
		applyRotation();
	}

	public CaesarCypher (String originalMessage, boolean visible, boolean secret)
	{
		original = originalMessage;
		SecureRandom secRand = new SecureRandom();
		this.rotation = secRand.nextInt(1, 26);
		if (secret)
			visible = false;
		this.visible = visible;
		this.secret = secret;
		applyRotation();
	}

	public CaesarCypher (String originalmessage, boolean visible)
	{
		this(originalmessage, visible, false);
	}

	public CaesarCypher (String originalMessage)
	{
		this(originalMessage, false, false);
	}

	/// PUBLIC INTERFACE
	
	/**
	 * @return The message after encryption
	 */
	public String getEncryptedMessage()
	{
		return encrypted;
	}

	/**
	 * @return The encrypted message
	 */
	@Override
	public String toString()
	{
		return encrypted;
	}

	/**
	 * Checks whether the provided String is the same as the original message. <br>
	 * NOTE: Will only work if the cypher was not declared secret upon creation
	 * 
	 * @throws SecurityException Throws an exception if the cypher is secret, and no additional information may be revealed.
	 * @param guess The string to check against the original string
	 * @return A boolean indicating whether the guess matches the original string
	 */
	public boolean checkValue (String guess) throws SecurityException
	{
		if (secret)
		{
			throw new SecurityException("This cypher is secret, and none of the contents may be accessed");
		}
		else
		{
			return original.equals(guess);
		}
	}

	/**
	 * @return Whether the contents of the cypher are freely visible.
	 */
	public boolean isVisible () {return visible;}

	/**
	 * @return Whether any aspects of the cypher other than the encrypted message are accessible.
	 */
	public boolean isSecret () {return secret;}

	/**
	 * If the cypher is visible, returns the original message, otherwise throws an exception. <br>
	 * NOTE: will only work if the cypher is declared visible upon creation
	 * 
	 * @return The original message, if visible
	 * @throws SecurityException Throws an exception is the cypher is not set to visible
	 */
	public String getOriginalMessage () throws SecurityException
	{
		if (visible)
		{
			return original;
		}
		else
		{
			throw new SecurityException("This cypher is not visible, so the orinal message cannot be accessed");
		}
	}

	/**
	 * If the cypher is visible, returns the rotation, otherwise throws an exception. <br>
	 * NOTE: will only work if the cypher is declared visible upon creation
	 * 
	 * @return The rotation factor, if visible
	 * @throws SecurityException Throws an exception is the cypher is not set to visible
	 */
	public int getRotation () throws SecurityException
	{
		if (visible)
		{
			return rotation;
		}
		else
		{
			throw new SecurityException("This cypher is not visible, so the rotation cannot be accessed");
		}
	}

	/// BACK END METHODS

	/**
	 * Set up the encrypted message. <br>
	 * Rotates letters by the set rotation, preserving case, and leaves other characters alone
	 */
	private void applyRotation()
	{
		encrypted = "";

		for (char ch : original.toCharArray())
		{
			if (Character.isLowerCase(ch))
			{
				encrypted += (char) boundedRotation((int) ch, rotation, (int) 'a', (int) 'z');
			}
			else if (Character.isUpperCase(ch))
			{
				encrypted += (char) boundedRotation((int) ch, rotation, (int) 'A', (int) 'Z');
			}
			else 
			{
				encrypted += ch;
			}
		}
	}

	/**
	 * Rotate a value by a specified number of positions, constrained within a range. <br>
	 * If the value exceeds the given range, it will wrap around.
	 * 
	 * @param val The value that is being rotated. Usually the index or ASCII value of a character
	 * @param rot The number of places to rotate by
	 * @param min The minimum bound of the interval. If the value, after rotation, is lower than this value, it will be wrapped around
	 * @param max The maximum bound of the interval. If the value, after rotation, exceeds this value, it will be wrapped around
	 */
	private int boundedRotation(int val, int rot, int min, int max)
	{
		int range = max - min;

		rot %= range;

		val += rot;

		if (val < min)
			val += range;
		if (val > max)
			val -= range;

		return val;
	}
}
