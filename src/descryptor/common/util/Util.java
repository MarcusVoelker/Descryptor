package descryptor.common.util;

public final class Util {
	public static byte base64ToValue(char value)
	{
		if ('a' <= value && value <= 'z')
			return (byte) (value-'a');
		if ('A' <= value && value <= 'Z')
			return (byte) (value-'A'+26);
		if ('0' <= value && value <= '9')
			return (byte) (value-'0'+52);
		if (value == '?')
			return 62;
		return 63;
	}
	
	public static char valueToBase64(byte value)
	{
		if (0 <= value && value < 26)
			return (char) ('a'+value);
		if (26 <= value && value < 52)
			return (char) ('A'+value-26);
		if (52 <= value && value < 62)
			return (char) ('0'+value-52);
		if (value == 62)
			return '?';
		return '!';
	}
	
	public static String seedToSeedString(long seed)
	{
		String result = "";
		while (seed > 0)
		{
			result += valueToBase64((byte) (seed % 64));
			seed /= 64;
		}
		return result;
	}
	
	public static long seedStringToSeed(String seed)
	{
		long result = 0;
		while (seed.length() > 0)
		{
			result *= 64;
			result += base64ToValue(seed.charAt(seed.length()-1));
			seed = seed.substring(0,seed.length()-1);				
		}
		return result;
	}
}
