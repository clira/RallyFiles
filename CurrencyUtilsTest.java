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
	
	@Test
	public void testGetWrittenStringSimpleWithDecimal()
	{
		String writtenString = CurrencyUtils.getWrittenString(3.65);
		assertEquals("Three and 65/100 dollars", writtenString);
		
		writtenString = CurrencyUtils.getWrittenString(7.04);
		assertEquals("Seven and 4/100 dollars", writtenString);
		
		writtenString = CurrencyUtils.getWrittenString(9.00);
		assertEquals("Nine and 0/100 dollars", writtenString);
		
		writtenString = CurrencyUtils.getWrittenString(4);
		assertEquals("Four and 0/100 dollars", writtenString);
	}

}
