// the specific parameter inputs for a caesar cypher
public class CaesarKey extends Key
{
	// var dec
	private final int rotation;

	// constructor
	public CaesarKey (int rotation)
	{
		super(Key.CipherType.CAESAR);
		this.rotation = rotation;
	}

	/**
	 * @return The rotation factor for the cipher; the private key.
	 */
	public int getRotation() {return rotation;}

	/**
	 * @return A String in which the first line declares the type (Caesar Cipher), followed by the rotation indented.
	 */
	@Override
	public String toString ()
	{
		return "Caesar Cipher:\n\t- Rotation: " + rotation;
	}
}
