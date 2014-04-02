import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CurrencyUtilsTest
{
	@Test
	public void testGetWrittenStringSimplest()
	{
		String writtenString = CurrencyUtils.getWrittenString(7);
		assertEquals("Seven and 0/100 dollars", writtenString);
	}

}
