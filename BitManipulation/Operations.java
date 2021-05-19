package BitManipulation;

public class Operations
{
    /**
     * Get a 4-bit nibble from an int.
     *
     *
     * @param num The int to get a nibble from.
     * @param which Determines which nibble gets returned - 0 for
     *              least-significant nibble.
     *
     * @return A nibble corresponding to the "which" parameter from num.
     */
    int getNibble(int num, int which)
    {

        return (num >> (which << 2)) & (0xF);
    }

    /**
     * Set a 16-bit short in an int.
     *
     * @param num The int that will be modified.
     * @param a_short The short to insert into the integer.
     * @param which Selects which short to modify - 0 for least-significant
     * short.
     *
     * @return The modified int.
     */
    int setShort(int num, int a_short, int which)
    {
        return (num & ~(0xFFFF << (which << 4))) | ((a_short) << (which << 4));
    }

    /**
     * Pack a byte, a short, and another byte into an int.
     *
     *
     * @param b2 Most significant byte
     * @param s1 Middle short
     * @param b0 Least significant byte
     *
     * @return a 32-bit value
     */
    int pack(int b2, int s1, int b0)
    {
        return (b2 << 24) ^ (s1 << 8) ^ (b0);
    }

    /**
     * Extract a range of bits from a number.
     *
     *
     * @param num An n-bit 2's complement number.
     * @param s The starting bit to grab
     * @param n The number of bits to return.
     * @return The n-bit number
     */
    int bitRange(int num, int s, int n)
    {
        return (num >> s) & ~(~0 << n) ;
    }

    /**
     *
     * Perform an exclusive-nor on two 32-bit ints.
     *
     * Example:
     *     xnor(0xFF00FF00, 0x00FF00FF); // => 0x00000000
     *
     *
     * @param num1 An int
     * @param num2 Another int
     *
     * @return num1 XNOR num2
     */
    int xnor(int num1, int num2)
    {
        return ~((num1 | num2) & (~(num1 & num2)));
    }

    /**
     * Return true if the given number is multiple of 2.
     *
     * Example:
     *     multipleOf2(32);   // => true
     *     multipleOf2(13);   // => false
     *
     *
     * @param num a 32-bit int. Since this is an int, it is SIGNED!
     * @return true if num is a multiple of 2 else false.
     */

    boolean multipleOf2(int num)
    {
        return ((num & 0x1) == 0);
    }

    /**
     * Return true if the given number is multiple of 64.
     *
     * Examples:
     *     multipleOf64(256); // => true
     *     multipleOf64(135); // => false
     *     multipleOf64(96);  // => false
     *
     *
     * @param num a 32-bit int. Since this is an int, it is SIGNED!
     * @return true if num is a multiple of 64 else false.
     */

    boolean multipleOf64(int num)
    {
        return ((num & 0x3F) == 0) ;
    }
}

