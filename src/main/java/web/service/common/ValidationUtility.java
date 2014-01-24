package web.service.common;

import java.io.IOException;
import java.util.regex.*;

//import org.apache.commons.validator.GenericValidator;

/**
 * 
 * @author aarshad
 * 
 */
public class ValidationUtility {

	public static final String DIGIT_PATTERN = "[-+]?[0-9]";
	public static final String DOUBLE_PATTERN = "[-+]?([0-9]*.[0-9]+)";
	public static final String IS_NUMERIC = "[+-]?[0-9]{1,9}";
	public static final String ALL_ZEROES = "0*";
	public static final String SOME_ZEROES = "[8]{3}[0]{4}";
	public static final String ALL_ALPHABETS = "([a-zA-Z]+)";
	public static final String ALL_ALPHABETS_WITH_SPACE = "[a-zA-z ]+";
	public static final String ALL_ALPHABETS_WITH_SPACE_AND_NUMERICS = "[a-zA-z0-9 ]+";
	public static final String IS_VALID_EMAIL = "^([a-zA-Z0-9_'+*$%\\^&!\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z:]{2,3})+$";

	/**
	 * to verify that given character is digit 0-9 form
	 * 
	 * @return boolean
	 */

	public static boolean validateRegx(String str, String pattern) {
		if (!isExists(str)) {
			return false;
		}
		Pattern patternRegx = Pattern.compile(pattern);
		Matcher matcher = patternRegx.matcher(str);
		return matcher.matches();
	}

	public static boolean isDigit(String chDigit) {
		return validateRegx(chDigit, DIGIT_PATTERN);
	}

	/*public static boolean isDouble(String str) {
		// return validateRegx(str, DOUBLE_PATTERN);
		return GenericValidator.isDouble(str);
	}*/

	/**
	 * to verify given string can be converted in integer
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	/*public static boolean isNumeric(String str) {
		// return validateRegx(str, IS_NUMERIC);
		return GenericValidator.isLong(str);
	} */

	public static boolean allZeroes(String str) {
		return validateRegx(str, ALL_ZEROES);
	}

	public static boolean allAlphabets(String str) {
		return validateRegx(str, ALL_ALPHABETS);
	}

	public static boolean allAlphabetsWithSpace(String str) {
		return validateRegx(str, ALL_ALPHABETS_WITH_SPACE);
	}
	
	public static boolean allAlphabetsWithSpaceAndNumerics(String str) {
		return validateRegx(str, ALL_ALPHABETS_WITH_SPACE_AND_NUMERICS);
	}

	public static boolean isValidEmail(String str) {
		return validateRegx(str, IS_VALID_EMAIL);
		//return GenericValidator.isEmail(str);

	}

	public static boolean isExists(Object object) {
		if (object == null) {
			return false;
		}
		String objString;
		if (object instanceof String) {
			objString = object.toString();
			if (objString.length() <= 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		String a = "abcde";
		String b = a.substring(1, 3);
		String c = a.substring(1, 4);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
	}
}
