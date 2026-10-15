package CheckItOff;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the LinkedChain class.
 *
 * @author Nishitha Kancharla
 * @version 2026.10.15
 */
public class LinkedChainTest
    extends TestCase
{
    // ~ Fields ................................................................

    private LinkedChain<String> chain;

    // ~ Setup .................................................................

    /**
     * Creates an empty chain before each test.
     */
    @Override
    public void setUp()
    {
        chain = new LinkedChain<String>();
    }

    // ~ Public Methods ........................................................

    /**
     * Tests that a new chain is empty.
     */
    public void testConstructor()
    {
        assertEquals(0, chain.size());
        assertTrue(chain.isEmpty());
        assertEquals("[]", chain.toString());
    }


    /**
     * Tests that separate chains have independent storage.
     */
    public void testIndependentChains()
    {
        LinkedChain<String> other = new LinkedChain<String>();

        chain.add("A");

        assertEquals(1, chain.size());
        assertEquals(0, other.size());
        assertTrue(other.isEmpty());
    }


    /**
     * Tests adding entries to the front.
     */
    public void testAdd()
    {
        assertTrue(chain.add("A"));
        assertTrue(chain.add("B"));
        assertTrue(chain.add("C"));

        assertEquals(3, chain.size());
        assertEquals("C", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("A", chain.get(2));
    }


    /**
     * Tests that adding null is rejected without changing the chain.
     */
    public void testAddNull()
    {
        chain.add("A");

        try
        {
            chain.add(null);
            fail("Adding null should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals(1, chain.size());
            assertEquals("A", chain.get(0));
        }
    }


    /**
     * Tests adding to the end of an empty chain.
     */
    public void testAddToEndEmpty()
    {
        assertTrue(chain.addToEnd("A"));

        assertEquals(1, chain.size());
        assertEquals("A", chain.get(0));
        assertFalse(chain.isEmpty());
    }


    /**
     * Tests that adding to the end preserves insertion order.
     */
    public void testAddToEnd()
    {
        assertTrue(chain.addToEnd("A"));
        assertTrue(chain.addToEnd("B"));
        assertTrue(chain.addToEnd("C"));

        assertEquals(3, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }


    /**
     * Tests that addToEnd rejects null without changing the chain.
     */
    public void testAddToEndNull()
    {
        try
        {
            chain.addToEnd(null);
            fail("Adding null should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals(0, chain.size());
            assertTrue(chain.isEmpty());
        }
    }


    /**
     * Tests using front and end insertion together.
     */
    public void testMixedAdds()
    {
        chain.add("B");
        chain.addToEnd("C");
        chain.add("A");

        assertEquals("[A, B, C]", chain.toString());
        assertEquals(3, chain.size());
    }


    /**
     * Tests removing from an empty chain.
     */
    public void testRemoveEmpty()
    {
        assertNull(chain.remove());
        assertEquals(0, chain.size());
    }


    /**
     * Tests that remove returns entries from the front.
     */
    public void testRemove()
    {
        chain.add("A");
        chain.add("B");
        chain.add("C");

        assertEquals("C", chain.remove());
        assertEquals(2, chain.size());

        assertEquals("B", chain.remove());
        assertEquals(1, chain.size());

        assertEquals("A", chain.remove());
        assertEquals(0, chain.size());
        assertTrue(chain.isEmpty());

        assertNull(chain.remove());
        assertEquals(0, chain.size());
    }


    /**
     * Tests removing a specified entry from an empty chain.
     */
    public void testRemoveEntryEmpty()
    {
        assertFalse(chain.remove("A"));
        assertEquals(0, chain.size());
    }


    /**
     * Tests removing null from empty and nonempty chains.
     */
    public void testRemoveNull()
    {
        assertFalse(chain.remove(null));

        chain.add("A");

        assertFalse(chain.remove(null));
        assertEquals(1, chain.size());
        assertEquals("A", chain.get(0));
    }


    /**
     * Tests removing the first entry by value.
     */
    public void testRemoveFirstEntry()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.remove("A"));

        assertEquals(2, chain.size());
        assertEquals("[B, C]", chain.toString());
    }


    /**
     * Tests removing a middle entry.
     */
    public void testRemoveMiddleEntry()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");
        chain.addToEnd("D");

        assertTrue(chain.remove("C"));

        assertEquals(3, chain.size());
        assertEquals("[A, B, D]", chain.toString());
    }


    /**
     * Tests removing the last entry.
     */
    public void testRemoveLastEntry()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.remove("C"));

        assertEquals(2, chain.size());
        assertEquals("[A, B]", chain.toString());

        chain.addToEnd("D");
        assertEquals("[A, B, D]", chain.toString());
    }


    /**
     * Tests removing the only entry by value.
     */
    public void testRemoveOnlyEntry()
    {
        chain.add("A");

        assertTrue(chain.remove("A"));

        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        assertNull(chain.remove());
    }


    /**
     * Tests removing an entry that is not present.
     */
    public void testRemoveMissingEntry()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        assertFalse(chain.remove("C"));

        assertEquals(2, chain.size());
        assertEquals("[A, B]", chain.toString());
    }


    /**
     * Tests that removal deletes only the first matching entry.
     */
    public void testRemoveFirstOccurrence()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("A");
        chain.addToEnd("C");

        assertTrue(chain.remove("A"));

        assertEquals(3, chain.size());
        assertEquals(1, chain.count("A"));
        assertEquals("[B, A, C]", chain.toString());
    }


    /**
     * Tests clearing a nonempty chain and adding afterward.
     */
    public void testClear()
    {
        chain.add("A");
        chain.add("B");

        chain.clear();

        assertEquals(0, chain.size());
        assertTrue(chain.isEmpty());
        assertFalse(chain.contains("A"));
        assertEquals("[]", chain.toString());
        assertNull(chain.remove());

        chain.add("C");

        assertEquals(1, chain.size());
        assertEquals("C", chain.get(0));
    }


    /**
     * Tests clearing an empty chain.
     */
    public void testClearEmpty()
    {
        chain.clear();

        assertEquals(0, chain.size());
        assertTrue(chain.isEmpty());
    }


    /**
     * Tests getting entries without removing them.
     */
    public void testGet()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));

        assertEquals(3, chain.size());
    }


    /**
     * Tests getting a negative index.
     */
    public void testGetNegativeIndex()
    {
        chain.add("A");

        try
        {
            chain.get(-1);
            fail("A negative index should throw IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException exception)
        {
            assertEquals(1, chain.size());
        }
    }


    /**
     * Tests getting an index equal to the chain size.
     */
    public void testGetIndexEqualToSize()
    {
        chain.add("A");

        try
        {
            chain.get(1);
            fail("An index equal to size should be rejected");
        }
        catch (IndexOutOfBoundsException exception)
        {
            assertEquals(1, chain.size());
        }
    }


    /**
     * Tests getting an index greater than the chain size.
     */
    public void testGetIndexGreaterThanSize()
    {
        chain.add("A");

        try
        {
            chain.get(5);
            fail("An index greater than size should be rejected");
        }
        catch (IndexOutOfBoundsException exception)
        {
            assertEquals(1, chain.size());
        }
    }


    /**
     * Tests getting an entry from an empty chain.
     */
    public void testGetEmpty()
    {
        try
        {
            chain.get(0);
            fail("Getting from an empty chain should be rejected");
        }
        catch (IndexOutOfBoundsException exception)
        {
            assertTrue(chain.isEmpty());
        }
    }


    /**
     * Tests contains with present, missing, and null entries.
     */
    public void testContains()
    {
        assertFalse(chain.contains("A"));
        assertFalse(chain.contains(null));

        chain.add("A");
        chain.add("B");

        assertTrue(chain.contains("A"));
        assertTrue(chain.contains("B"));
        assertFalse(chain.contains("C"));
        assertFalse(chain.contains(null));
    }


    /**
     * Tests counting repeated, missing, and null entries.
     */
    public void testCount()
    {
        assertEquals(0, chain.count("A"));
        assertEquals(0, chain.count(null));

        chain.add("A");
        chain.add("B");
        chain.add("A");

        assertEquals(2, chain.count("A"));
        assertEquals(1, chain.count("B"));
        assertEquals(0, chain.count("C"));
        assertEquals(0, chain.count(null));
    }


    /**
     * Tests that searches and removal use value equality.
     */
    public void testValueEquality()
    {
        chain.add(new String("A"));
        String equalEntry = new String("A");

        assertTrue(chain.contains(equalEntry));
        assertEquals(1, chain.count(equalEntry));
        assertTrue(chain.remove(equalEntry));
        assertTrue(chain.isEmpty());
    }


    /**
     * Tests size after adding, removing, and clearing.
     */
    public void testSize()
    {
        assertEquals(0, chain.size());

        chain.add("A");
        chain.addToEnd("B");
        assertEquals(2, chain.size());

        chain.remove("A");
        assertEquals(1, chain.size());

        chain.remove("Missing");
        assertEquals(1, chain.size());

        chain.clear();
        assertEquals(0, chain.size());
    }


    /**
     * Tests empty status as the chain changes.
     */
    public void testIsEmpty()
    {
        assertTrue(chain.isEmpty());

        chain.add("A");
        assertFalse(chain.isEmpty());

        chain.remove();
        assertTrue(chain.isEmpty());
    }


    /**
     * Tests copying entries into an array in chain order.
     */
    public void testToArray()
    {
        chain.add("A");
        chain.add("B");
        chain.add("C");

        String[] values = new String[3];
        String[] result = chain.toArray(values);

        assertSame(values, result);
        assertEquals("C", result[0]);
        assertEquals("B", result[1]);
        assertEquals("A", result[2]);
        assertEquals(3, chain.size());
    }


    /**
     * Tests converting an empty chain into an empty array.
     */
    public void testToArrayEmpty()
    {
        String[] values = new String[0];

        assertSame(values, chain.toArray(values));
        assertEquals(0, values.length);
    }


    /**
     * Tests that entries beyond the chain size remain unchanged.
     */
    public void testToArrayLargerArray()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        String[] values = {"old", "old", "keep", "keep"};
        String[] result = chain.toArray(values);

        assertSame(values, result);
        assertEquals("A", result[0]);
        assertEquals("B", result[1]);
        assertEquals("keep", result[2]);
        assertEquals("keep", result[3]);
    }


    /**
     * Tests that a null destination array is rejected.
     */
    public void testToArrayNull()
    {
        try
        {
            chain.toArray(null);
            fail("A null array should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals(0, chain.size());
        }
    }


    /**
     * Tests that an undersized destination array is rejected.
     */
    public void testToArrayTooSmall()
    {
        chain.add("A");
        chain.add("B");

        String[] values = {"unchanged"};

        try
        {
            chain.toArray(values);
            fail("An undersized array should be rejected");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals(2, chain.size());
            assertEquals("unchanged", values[0]);
        }
    }


    /**
     * Tests the string representation of an empty chain.
     */
    public void testToStringEmpty()
    {
        assertEquals("[]", chain.toString());
    }


    /**
     * Tests the string representation of a single entry.
     */
    public void testToStringOneEntry()
    {
        chain.add("A");

        assertEquals("[A]", chain.toString());
    }


    /**
     * Tests the string representation of multiple entries.
     */
    public void testToStringMultipleEntries()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("[A, B, C]", chain.toString());
    }


    /**
     * Tests that the generic chain can also store integers.
     */
    public void testIntegerChain()
    {
        LinkedChain<Integer> numbers = new LinkedChain<Integer>();

        numbers.add(10);
        numbers.addToEnd(20);

        assertEquals(Integer.valueOf(10), numbers.get(0));
        assertEquals(Integer.valueOf(20), numbers.get(1));
        assertTrue(numbers.contains(20));
        assertEquals(1, numbers.count(10));

        Integer[] values = numbers.toArray(new Integer[2]);

        assertEquals(Integer.valueOf(10), values[0]);
        assertEquals(Integer.valueOf(20), values[1]);
    }
}
