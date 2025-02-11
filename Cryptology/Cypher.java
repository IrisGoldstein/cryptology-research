public abstract class Cypher
{
	// var dec
	private final String originalMessage;
	private final String encodedMessage;
	private final boolean messageViewable;

	// constructor
	public Cypher (String originalMessage, boolean messageViewable, Parameters encodingParemeters)
	{
		this.originalMessage = originalMessage;
		this.encodedMessage = applyEncoding(originalMessage, encodingParemeters);
		this.messageViewable = messageViewable;
	}

	@Override
	public String toString () {return encodedMessage;}

	public boolean messageViewable () {return messageViewable;}

	public String getOriginalMessage() throws IllegalAccessException
	{
		if (messageViewable)
		{
			return originalMessage;
		}
		else
		{
			throw new IllegalAccessException("Original message cannot be viewed, it's a secret :3");
		}
	}

	public boolean guessOriginalMessage(String guess)
	{
		return originalMessage.equals(guess);
	}

	// returns the encoded message
	public abstract String applyEncoding (String message, Parameters encodingParameters);
}