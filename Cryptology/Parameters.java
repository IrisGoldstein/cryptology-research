// class to encapsulate a set of parameters to instantiate a cypher
// should be implemented as a separate subclass for any given cypher type
public abstract class Parameters 
{
	// List of types of cyphers that can be created
	public static enum CipherType 
	{
		CAESAR,
		MODIFIED_CAESAR,
		SUBSTITUTION,
		MODIFIED_SUBSTITUTION,
		RSA,
		NESTED
	};

	private final CipherType type;

	public Parameters (CipherType type)
	{
		this.type = type;
	}

	protected CipherType getCipherType() {return type;}

	@Override
	public abstract String toString();
}