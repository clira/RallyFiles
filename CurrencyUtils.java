import java.util.ResourceBundle;

/**
 * Utility class providing functionality for working with currency.
 * 
 * @author Chris Lira
 */
public class CurrencyUtils
{
	/**
	 * Accepts a double value as a param, and returns a written string
	 * representation of it, as one would write on a check. For example, 27.43
	 * would return "Twenty-seven and 43/100 dollars". Currently localized for
	 * English and dollars.
	 * 
	 * It divides the param into whole number and decimal parts, handles them
	 * separately, then pieces together for final returned string.
	 * 
	 * @param doubleParam
	 *            the value to be converted to a written string.
	 * @return string representation of doubleParam, as a monetary amount.
	 * @throws IllegalArgumentException
	 *             if < 0 or > 999,999,999.99
	 */

	private static ResourceBundle bundle = ResourceBundle.getBundle("numbers");

	public static String getWrittenString(double doubleParam)
	{
		// split into whole number and decimal parts
		int intPart = (int) doubleParam;
		double decimalPart = doubleParam - intPart;
		StringBuilder builder = new StringBuilder();
		// handle the whole number part
		String firstPart = handleDigitGroup(intPart, new StringBuilder());
		String capitalizedString = firstPart.substring(0, 1).toUpperCase() + firstPart.substring(1);
		builder.append(capitalizedString);
		// handle the decimal part
		builder.append(handleDecimalPart(decimalPart));
		return builder.toString();
	}

	private static String handleDigitGroup(int number, StringBuilder builder)
	{
		String numString;
		numString = bundle.getString(String.valueOf(number));
		builder.append(numString);

		return builder.toString().trim();
	}

	private static String handleDecimalPart(double decimalPart)
	{
		StringBuilder builder = new StringBuilder();
		// truncate off anything beyond 2 decimal places
		int noDecimal = (int) (decimalPart * 100);
		builder.append(" ");
		builder.append(bundle.getString("decimalJunctionKey"));
		builder.append(" ");
		builder.append(noDecimal);
		builder.append("/100 ");
		builder.append(bundle.getString("moneyUnitKey"));
		return builder.toString();
	}
}
