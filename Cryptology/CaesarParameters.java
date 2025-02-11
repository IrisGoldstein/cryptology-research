// the specific parameter inputs for a caesar cypher
public class CaesarParameters extends Parameters
{
	// var dec
	private final int offset;

	// constructor
	public CaesarParameters (int offset)
	{
		this.offset = offset;
	}

	public int getOffset() {return offset;}
}
