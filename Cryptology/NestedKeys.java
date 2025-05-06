import java.util.ArrayList;
import java.util.Iterator;

public class NestedKeys extends Key implements Iterable<Key>
{
	private ArrayList<Key> keys;

	/**
	 * Stores a list of encryption keys, in the order they were applied. <br>
	 * Behaves essentially identically to an ArrayList, but considered type Key. <br>
	 * Will automatically compress nested keys when added.
	 * @param firstKey The key for the first encryption applied
	 * @param secondKey The key for the second encryption applied
	 */
	public NestedKeys (Key firstKey, Key secondKey)
	{
		super(Key.CipherType.NESTED);
		keys = new ArrayList<>();

		// check types for other NestedParameters to unpack
		if (firstKey instanceof NestedKeys nestedKeys)
			keys.add(nestedKeys);
		else
			keys.add(firstKey);
			
		if (firstKey instanceof NestedKeys nestedKeys)
			keys.add(nestedKeys);
		else
			keys.add(secondKey);
	}

	/**
	 * Adds another key to the array.
	 * Automatically flattens other nested keys.
	 * @param key The key to be added
	 */
	public void add (Key key)
	{
		keys.add(key);
	}

	/**
	 * Adds another set of keys to the array.
	 * Automatically flattens other nested keys.
	 * @param keys The keys to be added
	 */
	public void add (NestedKeys keys)
	{
		for (Key key : keys)
			this.keys.add(key);
	}

	/**
	 * Gets the key for a given layer of the encryption
	 * @param encryptionLayer The number of layers before this one (zero indexed)
	 */
	public Key get (int encryptionLayer) {return keys.get(encryptionLayer);}

	/**
	 * Gets the key for a the first layer of encryption
	 */
	public Key getFirst () {return keys.get(0);}
	
	/**
	 * Gets the key for the last layer of encryption
	 */
	public Key getLast () {return keys.getLast();}

	/**
	 * @return The number of layers of encryption
	 */
	public int size () {return keys.size();}

	/**
	 * @return String forms of each of the keys, one per line.
	 */
	@Override
	public String toString()
	{
		StringBuilder out = new StringBuilder("Nested Keys:\n");
		for (Key key : keys)
		{
			out.append(key.toString());
			out.append("\n");
		}
		return out.toString();
	}

	/**
	 * Satisfying the Iterable gods.
	 * @return An iterator of each Key in the NestedKeys
	 */
	@Override
	public NestedKeysIterator iterator ()
	{
		return new NestedKeysIterator(this);
	}

	// satisfying the Iterable gods
	public static class NestedKeysIterator implements Iterator<Key>
	{
		private int index;
		private final NestedKeys nestedKeys;

        public NestedKeysIterator (NestedKeys nestedKeys) 
		{
			this.nestedKeys = nestedKeys;
			index = 0;
        }
		
		@Override
		public boolean hasNext ()
		{
			return index < nestedKeys.size();
		}

		@Override
		public Key next ()
		{
			return nestedKeys.get(index++);
		}
	}
}
