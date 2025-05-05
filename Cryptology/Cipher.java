public abstract class Cipher
{
	// var dec
	private final String originalMessage;
	private final String encodedMessage;
	private final boolean messageViewable;
	private final boolean secret;
	private final Parameters encodingParameters;

	/**
	 * @param originalMessage The message to be encrypted
	 * @param messageViewable Whether or not the original message and key can be freely viewed, useful for decryption practice.
	 * @param secret Whether or not a message can be checked to see if it is the original method.
	 * 					Makes brute force attacks involving generating sequential possiblities and testing them directly infeasible.
	 * 					Turn off to allow for verification.
	 * @param encodingParameters The parameters used to encrypt the message; the private key. Should be personalized to each cypher type.
	 */
	public Cipher (String originalMessage, boolean messageViewable, boolean secret, Parameters encodingParameters)
	{
		this.originalMessage = originalMessage;
		this.encodedMessage = applyEncoding(originalMessage, encodingParameters);
		if (secret)
			messageViewable = false;
		this.messageViewable = messageViewable;
		this.secret = secret;
		this.encodingParameters = encodingParameters;
	}

	/**
	 * @param otheCipher Another cipher to add another layer of encryption to
	 * @param messageViewable Whether or not the original message and key can be freely viewed, useful for decryption practice.
	 * @param secret Whether or not a message can be checked to see if it is the original method.
	 * 					Makes brute force attacks involving generating sequential possiblities and testing them directly infeasible.
	 * 					Turn off to allow for verification.
	 * @param encodingParameters The parameters used to encrypt the message; the private key. Should be personalized to each cypher type.
	 */
	public Cipher (Cipher otherCipher, boolean messageViewable, boolean secret, Parameters encodingParameters)
	{
		this.originalMessage = otherCipher.toString();
		this.encodedMessage = applyEncoding(originalMessage, encodingParameters);
		if (secret)
			messageViewable = false;
		this.messageViewable = messageViewable;
		this.secret = secret;
		this.encodingParameters = encodingParameters;
	}

	@Override
	public String toString () {return encodedMessage;}

	public boolean messageViewable () {return messageViewable;}

	public boolean isSecret () {return secret;}

	public Parameters getParameters () throws SecurityException
	{
		if (messageViewable)
			return encodingParameters;

		throw new SecurityException("Parameters are not accessible");
	}

	public String getOriginalMessage() throws SecurityException
	{
		if (messageViewable)
		{
			return originalMessage;
		}
		else
		{
			throw new SecurityException("Original message cannot be viewed, it's a secret :3");
		}
	}

	public boolean guessOriginalMessage(String guess) throws SecurityException
	{
		if (secret)
			throw new SecurityException("Message is secret, and cannot be guessed");

		return originalMessage.equals(guess);
	}

	// returns the encoded message
	public abstract String applyEncoding (String message, Parameters encodingParameters);
}