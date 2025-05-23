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
	
	/**
	 * @param originalMessage The message to be encrypted
	 * @param rotation The secret key; how many positions in the alphabet to rotate.
	 * 					For instance, a value of 2 would make a -> c, etc.
	 * @param visible Whether or not the original message and key can be freely viewed, useful for decryption practice.
	 * @param secret Whether or not a message can be checked to see if it is the original method. <br>
	 * 					Makes brute force attacks involving generating sequential possiblities and testing them directly infeasible. <br>
	 * 					Turn off to allow for verification.
	 */
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
		this.rotation = secRand.nextInt(1, 25);
		if (secret)
			visible = false;
		this.visible = visible;
		this.secret = secret;
		applyRotation();
	}

	public CaesarCypher (String originalmessage, int rotation, boolean visible)
	{
		this(originalmessage, rotation, visible, false);
	}

	public CaesarCypher (String originalMessage, int rotation)
	{
		this(originalMessage, rotation, false, false);
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
	}

	
}
