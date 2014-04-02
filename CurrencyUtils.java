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
		if (doubleParam < 0 || doubleParam > 999999999.99)
		{
			throw new IllegalArgumentException(bundle.getString("illegalArg"));
		}
		// split into whole number and decimal parts
		int intPart = (int) doubleParam;
		double decimalPartRaw = doubleParam - intPart;
		int decimalPart = (int) Math.round(decimalPartRaw * 100);
		

		// handle the whole number part, then format it
		String firstPart = handleIntPart(intPart, new StringBuilder());
		firstPart = firstPart.replace("  ", " ");
		String capitalizedString = firstPart.substring(0, 1).toUpperCase() + firstPart.substring(1);
		
		//create a new builder, add what we already have, then use it for the decimal part
		StringBuilder builder = new StringBuilder();
		builder.append(capitalizedString);
		// handle the decimal part
		builder.append(handleDecimalPart(decimalPart));
		return builder.toString();
	}
	
	/*
	 * Handles successive 3-digit groups from highest to lowest value.
	 * e.g., XXX,YYY,ZZZ is handled as XXX, then YYY, then ZZZ, recursively, as needed.
	 */
	private static String handleIntPart(int number, StringBuilder builder)
	{
		int currentDigitGroup;
		
		if (number < 1000)
		{
			handleDigitGroup(number, builder);
		}
		else if (number < 1000000)
		{
			//get the number of thousands and append with proper label
			currentDigitGroup = number / 1000;
			handleDigitGroup(currentDigitGroup, builder);
			builder.append(" ");
			builder.append(bundle.getString("thousandKey"));
			builder.append(" ");
			//recurse for the final 3 digits
			handleIntPart(number % 1000, builder);
		}
		else 
		{
			//get the number of millions and append with proper label
			currentDigitGroup = number / 1000000;
			handleDigitGroup(currentDigitGroup, builder);
			builder.append(" ");
			builder.append(bundle.getString("millionKey"));
			builder.append(" ");
			//recurse for the thousands
			handleIntPart(number % 1000000, builder);
		}
		
		return builder.toString().trim();
	}
	
	/*
	 * Handles a 3 digit-group, unaware of context.  So for XXX,YYY,ZZZ
	 * it will be called to handle the XXX part by handleIntPart and will return
	 * a string like "Three hundred forty-two", to which
	 * handleIntPart will add a " million" label, and call it again for YYY, add 
	 * a label, then ZZZ. 
	 */

	private static String handleDigitGroup(int number, StringBuilder builder)
	{
		String numString;
		int currentDigit;
		if (number == 0 && builder.length() !=0)
		{
			/*
			 * For numbers like 700, 7000, etc. do nothing here, so we don't end
			 * up with "seven hundred and zero", "seven thousand and zero", etc.
			 * But if input was 0.37, we need to output "zero" here.
			 * 
			 * No-op for clarity.
			 * 
			 */
		}
		else if (number < 10)
		{
			numString = bundle.getString(String.valueOf(number));
			builder.append(numString);
		}
		else if (number < 20)
		// for 11-19, get a string and just return, since last digit is already accounted for
		{
			numString = bundle.getString(String.valueOf(number));
			builder.append(numString);
		}
		else if (number < 100)
		{
			currentDigit = number / 10;
			numString = bundle.getString(String.valueOf(currentDigit) + "_decadeKey");
			// for numbers ending in 0, like 20, 30, etc., simply append the string
			if (number % 10 == 0)
			{
				builder.append(numString);
			}
			// for non-teen numbers ending 1-9, get the number for the decade digit then recurse for last digit
			else
			{
				builder.append(numString);
				builder.append("-");
				handleDigitGroup(number % 10, builder);
			}
		}
		else 
		{
			//get the number of hundreds and append with proper label
			currentDigit = number / 100;
			numString = bundle.getString(String.valueOf(currentDigit));
			builder.append(numString);
			builder.append(" ");
			builder.append(bundle.getString("hundredKey"));
			builder.append(" ");
			//recurse for the final 2 digits
			handleDigitGroup(number % 100, builder);
		}

		return builder.toString().trim();
	}

	private static String handleDecimalPart(int decimalPart)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(" ");
		builder.append(bundle.getString("decimalJunctionKey"));
		builder.append(" ");
		builder.append(decimalPart);
		builder.append("/100 ");
		builder.append(bundle.getString("moneyUnitKey"));
		return builder.toString();
	}
}
