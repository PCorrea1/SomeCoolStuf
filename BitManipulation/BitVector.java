package BitManipulation;

public class BitVector
{
    /**
     * 32-bit data initialized to all zeros.
     */
    private int bits;



    /**
     * Sets the bit (sets to 1) pointed to by index.
     * @param index index of which bit to set.
     *              0 for the least significant bit
     *              31 for the most significant bit.
     */
    public void set(int index)
    {
        bits = bits | 0x1 << index;
    }

    /**
     * Clears the bit (sets to 0) pointed to by index.
     * @param index index of which bit to set.
     *              0 for the least significant bit (right most bit).
     *              31 for the most significant bit.
     */
    public void clear(int index)
    {
        bits = bits & ~(0x1 << index);
    }

    /**
     * Toggles the bit (sets to the opposite of its current value) pointed to by
     * index.
     * @param index index of which bit to set.
     *              0 for the least significant bit (right most bit).
     *              31 for the most significant bit.
     */
    public void toggle(int index)
    {
        bits = bits ^ (0x1 << index);
    }

    /**
     * Returns true if the bit pointed to by index is currently set.
     * @param index index of which bit to check.
     *              0 for the least significant bit (right-most bit).
     *              31 for the most significant bit.
     * @return true if the bit is set, false if the bit is clear.
     *         If the index is out of range (index >= 32), then return false.
     */
    public boolean isSet(int index)
    {
        return (bits & (0x1 << index)) == 0x1 << index && index < 32;
    }

    /**
     * Returns true if the bit pointed to by index is currently clear.
     * @param index index of which bit to check.
     *              0 for the least significant bit (right-most bit).
     *              31 for the most significant bit.
     * @return true if the bit is clear, false if the bit is set.
     *         If the index is out of range (index >= 32), then returns true.
     */
    public boolean isClear(int index)
    {
        return (bits | ~(0x1 << index)) == ~(0x1 << index) || index >= 32;
    }

    /**
     * Returns the number of bits currently set (=1) in this bit vector.
     */
    public int onesCount() {
        int counter = 0;
        while (bits != 0) {
            bits = bits & (bits - 1);
            counter++;
        }
        return counter;
    }

    /**
     * Returns the number of bits currently clear (=0) in this bit vector.
     */
    public int zerosCount()
    {
        int counter = 0;
        while(bits != ~(0)) {
            bits = bits | (bits + 1);
            counter++;
        }
        return counter;
    }

    /**
     * Returns the "size" of this BitVector.
     *
     * For example, the size of the bit vector 00010000 will be 5.
     */
    public int size()
    {
        if(bits == 0) {
            return 1;
        }
        int counter = 0;
        while(bits != 0) {
            bits = bits & ~(0x1 << counter);
            counter++;
        }
        return counter;
    }
}

