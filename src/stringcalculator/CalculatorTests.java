package stringcalculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTests {
	
	Calculator calc;
	
	@Before
	public void before(){
		calc = new Calculator();
	}

	@Test
	public void testThatEmptyStringReturnsZero() {
		assertEquals(calc.Add(""), 0);
	}

	@Test
	public void testThatOneNumberReturnsThatNumber(){
		assertEquals(calc.Add("1"), 1);
	}
	
	@Test
	public void testThatTwoNumbersReturnsTheSumOfThoseTwoNumbers(){
		assertEquals(calc.Add("1,2"), 3);
	}
	
	@Test
	public void testThatAnUnkownAmountOfNumbersReturnsTheSumOfThoseNumbers(){
		assertEquals(calc.Add("1,2,3,4"), 10);
	}
	
	@Test
	public void testThatNewLinesCanActAsDelimeters(){
		assertEquals(calc.Add("1\n2,3"), 6);
	}
	
	@Test
	public void testThatCustomDelimeterWorks(){
		assertEquals(calc.Add("//;\n1;2"), 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testThatExceptionIsThrownIfNegativeNumber(){
		calc.Add("1,-2,-4");
	}
	
	@Test
	public void testThatIntegersOver1000AreIgnored(){
		assertEquals(calc.Add("2,1001"), 2);
	}
	
	@Test
	public void testThatDelimeterCanBeAnyLength(){
		assertEquals(calc.Add("//[***]\n1***2***3"),6);
	}
	
	@Test
	public void testThatMultipleDelimitersWorks(){
		assertEquals(calc.Add("//[*][%]\n1*2%3"), 6);
	}
}
