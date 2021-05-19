package BitManipulation;




public class Bases
{
    /**
     * Converts a string containing ASCII characters (in binary) to an int.
     *
     *
     * Example: binaryStringToInt("111"); // => 7
     */
    public static int binaryStringToInt(String binary)
    {
        int total = 0;
        for(int i = binary.length() - 1 ; i >= 0; i--) {
            total+=((binary.charAt(i) - '0') << binary.length() - 1 - i);
        }
        return total;
    }

    /**
     * Converts a string containing ASCII characters (in decimal) to an int.
     *
     *
     * Example: decimalStringToInt("46"); // => 46
     */
    public static int decimalStringToInt(String decimal)
    {
        int total = 0;
        int i = 0;
        while(i < decimal.length()) {
            total*= 10;
            total+= (decimal.charAt(i) - '0');
            i++;
        }
        return total;
    }

    /**
     * Converts a string containing ASCII characters (in hex) to an int.
     *
     *
     * Example: hexStringToInt("2B"); // => 43
     */
    public static int hexStringToInt(String hex)
    {
        int total = 0 ;
        int helper = 0;
        for(int i = hex.length() - 1; i >= 0; i--) {
            if(hex.charAt(i) <= 57 && hex.charAt(i) >= 48) {
                total += (hex.charAt(i) - 48) << helper;
                helper = helper + 4;
            }
            else if(hex.charAt(i) <= 90 && hex.charAt(i) >= 65) {
                total += (hex.charAt(i) - 55) << helper;
                helper = helper + 4;
            }
            }
        return total;
    }

    /**
     * Converts a int into a String containing ASCII characters (in octal).
     *
     *
     * Example: intToOctalString(166); // => "246"
     *
     */
    public static String intToOctalString(int octal)
    {
        String octString = "";
        int i = octal;
        while(i > 7) {
            octString = (i & 0x7) + octString;
            i = i >> 3;
        }
        return i + octString;
    }

    /**
     * Converts a String containing ASCII characters representing a number in
     * hex into a String containing ASCII characters that represent that same
     * value in binary.
     *
     *
     * Example: hexStringToBinaryString("06A1E4C0"); // => 00000110101000011110010011000000
     *
     */
    public static String hexStringToBinaryString(String hex)
    {
        String binString = "";
        int total = 0 ;
        int helper = 0;
        for(int i = hex.length() - 1; i >= 0; i--) {
            if(hex.charAt(i) <= 57 && hex.charAt(i) >= 48) {
                total += (hex.charAt(i) - 48) << helper;
                helper = helper + 4;
            }
            else {
                total += (hex.charAt(i) - 55) << helper;
                helper = helper + 4;
            }
        }
        while(total >= 1 || binString.length() < 32) {
            binString = (total & 0x1) + binString;
            total = total >> 1;
            if(binString.length() < 32 && total < 1) {
                binString = "0" + binString;
            }
        }
        return binString;
    }
}

