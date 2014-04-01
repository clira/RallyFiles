/**
 * Utility class providing functionality for working with currency.
 * 
 * @author		Chris Lira
 */
public class CurrencyUtils
{
	/**
	 * Accepts a double value as a param, and returns a written string representation
	 * of it, as one would write on a check.  For example, 27.43 would return
	 * "Twenty-seven and 43/100 dollars".  Currently localized for English and 
	 * dollars.
	 * 
	 * It divides the param into whole number and decimal parts, handles them
	 * separately, then pieces together for final returned string.
	 * 
	 * @param doubleParam	the value to be converted to a written string.
	 * @return 				string representation of doubleParam, as a monetary amount.
	 * @throws				IllegalArgumentException if < 0 or > 999,999,999.99
	 */
	
	public static String getWrittenString(double doubleParam)
	{
		return "";
	}
}
