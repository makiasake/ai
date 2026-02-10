package ai.util;

public class Util {

	public static void clear() {

		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
