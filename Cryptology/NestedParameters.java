import java.util.ArrayList;
import java.util.Iterator;

public class NestedParameters extends Parameters implements Iterable<Parameters>
{
	private ArrayList<Parameters> parameters;

	/**
	 * Stores a list of encryption parameters, in the order they were applied. <br>
	 * Behaves essentially identically to an ArrayList, but considered Parameters. <br>
	 * Will automatically compress nested parameters 
	 * @param firstParam The parameters for the first encryption applied
	 * @param secondParam The parameters for the second encryption applied
	 */
	public NestedParameters (Parameters firstParam, Parameters secondParam)
	{
		super(Parameters.CipherType.NESTED);
		parameters = new ArrayList<>();

		// check types for other NestedParameters to unpack
		if (firstParam instanceof NestedParameters nestedParameters)
			parameters.add(nestedParameters);
		else
			parameters.add(firstParam);
			
		if (firstParam instanceof NestedParameters nestedParameters)
			parameters.add(nestedParameters);
		else
			parameters.add(secondParam);
	}

	/**
	 * Adds another set of encryption parameters to the array.
	 * Automatically flattens other nested parameters.
	 * @param param The parameters to be added
	 */
	public void add (Parameters param)
	{
		parameters.add(param);
	}

	/**
	 * Adds another set of encryption parameters to the array.
	 * Automatically flattens other nested parameters.
	 * @param param The parameters to be added
	 */
	public void add (NestedParameters params)
	{
		for (Parameters param : params)
			parameters.add(param);
	}

	/**
	 * Gets the parameters to a given layer of the encryption
	 * @param encryptionLayer The number of layers before this one (zero indexed)
	 */
	public Parameters get (int encryptionLayer) {return parameters.get(encryptionLayer);}

	/**
	 * Gets the parameters to a given layer of the encryption
	 * @param encryptionLayer The number of layers before this one (zero indexed)
	 */
	public Parameters getFirst () {return parameters.get(0);}
	
	/**
	 * Gets the parameters to a given layer of the encryption
	 * @param encryptionLayer The number of layers before this one (zero indexed)
	 */
	public Parameters getLast () {return parameters.get(parameters.size() - 1);}

	/**
	 * @return The number of layers of encryption
	 */
	public int size () {return parameters.size();}

	/**
	 * Satisfying the Iterable gods.
	 * @return An iterator of the Parameters in the NestedParameters
	 */
	@Override
	public NestedParametersIterator iterator ()
	{
		return new NestedParametersIterator(this);
	}

	// satisfying the Iterable gods
	public static class NestedParametersIterator implements Iterator<Parameters>
	{
		private int index;
		private final NestedParameters nestedParams;

        public NestedParametersIterator (NestedParameters nestedParams) 
		{
			this.nestedParams = nestedParams;
			index = 0;
        }
		
		@Override
		public boolean hasNext ()
		{
			return index < nestedParams.size();
		}

		@Override
		public Parameters next ()
		{
			return nestedParams.get(index++);
		}
	}
}
