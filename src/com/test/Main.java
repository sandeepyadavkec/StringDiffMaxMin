package com.test;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Utility util = new Utility();
        String inputFile = util.getConfigByKey("inputfile"); //get input file name and path from config.properties
        List<String> inputList = util.readInputFile(inputFile);
        int testCountInt = 0;


        String testCaseCountStr = inputList.get(0);
        validateTestCaseCountNotNull(testCaseCountStr); //validate that test count is not empty
        testCountInt = Integer.parseInt(testCaseCountStr.trim());
        validateConstraints(testCountInt, inputList.size()); //apply constraints to the test count

        int size = inputList.size();

        for(int i=1; i<size; i=i+2)
        {
            String s1 = inputList.get(i);
            String s2 = inputList.get(i+1);

            Boolean isInputValid = validateStringConstraints(s1,s2); //apply constraints to the input strings s1, s2
            if(!isInputValid)
            {
                continue; //skip to next data if the input strings do not satisfy the constraints
            }

            minimalDifference(s1, s2); //calculate and print minimal difference in strings s1, s2
            System.out.print(" ");
            maximalDifference(s1, s2); //calculate and print maximal difference in strings s1, s2
            System.out.print("\n");
        }

    }

    /*
    apply constraints to strings s1, s2
    returns true if both strings are valid, else returns false
     */
    private static boolean validateStringConstraints(String s1, String s2) {
        if(s1.length()!=s2.length())
        {
            System.err.println("Length of input strings is not same!\t"+s1+"\t"+s2);
            return false;
        }
        if(s1.length()<1 || s1.length()>100)
        {
            System.err.println("String lengths should be between 1 and 100.");
            return false;
        }
        return true;
    }

    /*
   calculate and print minimal difference between strings s1, s2
    */
    private static void minimalDifference(String s1, String s2) {
        int diff=0;
        for(int i = 0; i < s1.length(); i++)
        {
            char s1i = s1.charAt(i);
            char s2i = s2.charAt(i);
            //if neither char is ? and both char are not same, mark them as diff
            if((s1i!='?' &&  s2i !='?') && s1i != s2i)
                diff++;
        }
        System.out.print(diff);
    }

    /*
   calculate and print maximal difference between strings s1, s2
   */
    private static void maximalDifference(String s1, String s2) {
        int diff=0;
        for(int i = 0; i < s1.length(); i++)
        {
            char s1i = s1.charAt(i);
            char s2i = s2.charAt(i);
            //if either char is ?, mark them as diff
            if(s1i=='?' ||  s2i =='?')
            {
                diff++;
            }
            //if both char are not same, mark as diff
            else if(s1i != s2i)
            {
                diff++;
            }
        }
        System.out.print(diff);
    }

    /*
    apply constraints to test count input
     */
    private static void validateConstraints(int testCount, int length) {
        if(testCount < 1 || testCount > 100)
        {
            System.err.println("Invalid total test count was provided!\nValue must be from 1 to 100.");
            System.exit(0);
        }

        if(testCount*2!=length-1)
        {
            System.err.println("Provided number of test data does not match the given test count!");
            System.err.println("Test count provided was "+testCount);
            System.err.println("Rows of test data: "+(length-1));
            System.exit(0);
        }
    }

    /*
    validate that test count is not empty
     */
    private static void validateTestCaseCountNotNull(String testCaseCountStr) {
        if(testCaseCountStr.isEmpty())
        {
            System.err.println("Total test count was not provided in first line of input file!");
            System.exit(0);
        }
    }
}
