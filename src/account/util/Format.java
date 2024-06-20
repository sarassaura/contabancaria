package account.util;

public class Format {
	public static final int LENGTH = 70;
	public static final String CHAR = ".";

	public static void text(String text, int newLineBefore, int newLineAfter, boolean center) {

		int rest = (LENGTH - text.length());
		
		for (int i = 0; i < newLineBefore; i++) {
			System.out.println();
		}

		if (center) {
			for (int i = 0; i < (rest / 2); i++) {
				System.out.print(" ");
			}

			System.out.print(text);

			for (int i = 0; i < (rest / 2); i++) {
				System.out.print(" ");
			}

			if (rest % 2 == 1) {
				System.out.print(" ");
			}

		} else {

			System.out.print(text);

			for (int i = 0; i < rest; i++) {
				System.out.print(" ");
			}
		}

		for (int i = 0; i < newLineAfter; i++) {
			System.out.println();
		}
	}
	
	public static void stars(String text) {
		System.out.print(text);
		
		for (int i = 0; i < LENGTH; i++) {
			System.out.print(CHAR);
		}
		
		System.out.println();
	}
	
	public static void title(String text) {
		stars("");
		text(text,0,1,true);
		stars("");
		System.out.println();
	}



}
