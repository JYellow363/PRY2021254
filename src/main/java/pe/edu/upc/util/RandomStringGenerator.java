package pe.edu.upc.util;

public class RandomStringGenerator {
	
	public static String NUMBERS = "0123456789";
	public static String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	public static String SPECIAL = "ñÑ";


	public static String getString() {
		return getString(8);
	}

	public static String getString(int length) {
		return getString(NUMBERS + UPPERCASE + LOWERCASE, length);
	}

	public static String getString(String key, int length) {
		String pswd = "";

		for (int i = 0; i < length; i++) {
			pswd += (key.charAt((int) (Math.random() * key.length())));
		}
		return pswd;
	}
}
