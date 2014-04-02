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
		
		writtenString = CurrencyUtils.getWrittenString(0.65);
		assertEquals("Zero and 65/100 dollars", writtenString);
	}
	
	@Test
	public void testGetWrittenStringTwoDigitsWithDecimal()
	{
		String writtenString = CurrencyUtils.getWrittenString(23.65);
		assertEquals("Twenty-three and 65/100 dollars", writtenString);
	}
	
	@Test
	public void testGetWrittenStringTwoDigitsRoundWithDecimal()
	{
		String writtenString = CurrencyUtils.getWrittenString(20.65);
		assertEquals("Twenty and 65/100 dollars", writtenString);
	}
	
	@Test
	public void testGetWrittenStringTeenWithDecimal()
	{
		String writtenString = CurrencyUtils.getWrittenString(14.65);
		assertEquals("Fourteen and 65/100 dollars", writtenString);
	}
	
	@Test
	public void testGetWrittenStringHundredsWithDecimal()
	{
		String writtenString = CurrencyUtils.getWrittenString(314.65);
		assertEquals("Three hundred fourteen and 65/100 dollars", writtenString);
		
		writtenString = CurrencyUtils.getWrittenString(307.65);
		assertEquals("Three hundred seven and 65/100 dollars", writtenString);
		
		writtenString = CurrencyUtils.getWrittenString(330.65);
		assertEquals("Three hundred thirty and 65/100 dollars", writtenString);
		
		writtenString = CurrencyUtils.getWrittenString(300.65);
		assertEquals("Three hundred and 65/100 dollars", writtenString);
	}
	
	@Test
	public void testGetWrittenStringHundredThousandWithDecimal()
	{
		String writtenString = CurrencyUtils.getWrittenString(314000.65);
		assertEquals("Three hundred fourteen thousand and 65/100 dollars", writtenString);
		
		writtenString = CurrencyUtils.getWrittenString(217220.65);
		assertEquals("Two hundred seventeen thousand two hundred twenty and 65/100 dollars", writtenString);
	
		writtenString = CurrencyUtils.getWrittenString(654989.65);
		assertEquals("Six hundred fifty-four thousand nine hundred eighty-nine and 65/100 dollars", writtenString);
	
		writtenString = CurrencyUtils.getWrittenString(700221.65);
		assertEquals("Seven hundred thousand two hundred twenty-one and 65/100 dollars", writtenString);
	}
	
	@Test
	public void testGetWrittenStringHundredMillionWithDecimal()
	{
		String writtenString = CurrencyUtils.getWrittenString(615314000.65);
		assertEquals("Six hundred fifteen million three hundred fourteen thousand and 65/100 dollars", writtenString);
		
		writtenString = CurrencyUtils.getWrittenString(700217220.65);
		assertEquals("Seven hundred million two hundred seventeen thousand two hundred twenty and 65/100 dollars", writtenString);
	
		writtenString = CurrencyUtils.getWrittenString(943654989.65);
		assertEquals("Nine hundred forty-three million six hundred fifty-four thousand nine hundred eighty-nine and 65/100 dollars", writtenString);
	
		writtenString = CurrencyUtils.getWrittenString(550654989.65);
		assertEquals("Five hundred fifty million six hundred fifty-four thousand nine hundred eighty-nine and 65/100 dollars", writtenString);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNegativeNumber()
	{		
		CurrencyUtils.getWrittenString(-567.44);
		fail();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testExceededBoundary()
	{		
		CurrencyUtils.getWrittenString(1111111111);
		fail();
	}

}
