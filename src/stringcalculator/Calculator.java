package stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
	public Calculator(){
		
	}
	
	public int Add(String numbers){
		if(numbers.isEmpty())
			return 0;
		
		String[] nums;
		String newDelimeter = "";
		String delimiters = "";
		
		// Checks that incoming string has custom delimiters
		// Still has issues due to regex issues
		if (numbers.startsWith("//")){
			if(numbers.split("\n")[0].substring(2).startsWith("[")) {
				ArrayList<String> customDelimiters = new ArrayList<String>();
				
				Pattern p = Pattern.compile("\\[(.*?)\\]");
				Matcher m = p.matcher(numbers.split("\n")[0]);
				while(m.find()){
					customDelimiters.add(m.group(1));
				}
				
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				for(String s : customDelimiters){
					sb.append(s);
				}
				sb.append("]");
				
				delimiters = sb.toString();
			}
			//Checks if custom delimiter is one single character delimiter
			else {
				newDelimeter = numbers.split("\n")[0].substring(2);
				numbers = numbers.split("\n")[1];
				delimiters = "[,\\n" + newDelimeter + "]";
			}			
		}
		//Run using , or \n delimiter
		else {
			delimiters = "[,\\n]";
		}
		
		// Any length delimiter regex is causing weird splits
		// Split on defined delimiter
		nums = numbers.split(delimiters);
		int sum = 0;
		
		// Sum all numbers through delimited string
		ArrayList<Integer> negatives = new ArrayList<Integer>();
		for(int i = 0; i < nums.length; i++){
			if(Integer.parseInt(nums[i]) < 0)
				negatives.add(Integer.parseInt(nums[i]));
			else if( Integer.parseInt(nums[i]) < 1000)
				sum += Integer.parseInt(nums[i]);
		}
		
		// Builds negative number message and throws alert
		try{
			StringBuilder sb = new StringBuilder();
			if(!negatives.isEmpty()){
				for(int neg : negatives){
					sb.append(neg);
					sb.append(" ");
				}
					
				throw new IllegalArgumentException("negatives not allowed " + sb.toString());
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		// Returns sum of string
		return sum;

	}
}