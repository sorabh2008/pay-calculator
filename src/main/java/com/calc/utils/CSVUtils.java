package com.calc.utils;

import com.calc.constants.ErrorConstants;
import com.calc.exception.CalException;
import com.calc.to.CalculatorRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUtils {

    private static final char SEPARATOR = ',';
    private static final char ESCAPE_CHAR = '"';
    private static final String FREQUENCY = "monthly";

    private static final int FIRST_NAME = 0,
            LAST_NAME = 1,
            ANNUAL_SALARY = 2,
            SUPER_RATE = 3,
            PAY_PERIOD = 4,
            PAY_FREQUENCY = 5;

    public static List<CalculatorRequest> readCSVFile(File file) throws CalException {
        List<CalculatorRequest> requests = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if (line.size() < 5) {
                    throw new CalException(ErrorConstants.CODE_INVALID_INPUT, ErrorConstants.MSG_INVALID_INPUT);
                }
                CalculatorRequest request = new CalculatorRequest();
                request.setFirstName(line.get(FIRST_NAME));
                request.setLastName(line.get(LAST_NAME));

                BigDecimal annualSalary = ConversionUtils.stringToBigDecimal(line.get(ANNUAL_SALARY)),
                        superRate = ConversionUtils.stringToBigDecimal(line.get(SUPER_RATE));

                if (ValidationUtils.isPositive(annualSalary) && ValidationUtils.isPositive(superRate)) {
                    request.setAnnualSalary(annualSalary);
                    request.setSuperRate(superRate);
                }
                request.setPaymentStartDate(line.get(PAY_PERIOD));
                if (line.size() >= 6) {
                    if (!ValidationUtils.isEmpty(line.get(PAY_FREQUENCY)) && ValidationUtils.isValidFrequency(line.get(PAY_FREQUENCY))) {
                        request.setPayFrequency(line.get(PAY_FREQUENCY));
                    } else {
                        request.setPayFrequency(FREQUENCY);
                    }
                } else {
                    request.setPayFrequency(FREQUENCY);
                }
                requests.add(request);
            }
        } catch (FileNotFoundException e) {
            throw new CalException(ErrorConstants.CODE_FILE_MISSING, ErrorConstants.MSG_FILE_MISSING);
        } finally {
            if (null != scanner)
                scanner.close();
        }
        return requests;

    }

    private static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, SEPARATOR, ESCAPE_CHAR);
    }

    private static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = ESCAPE_CHAR;
        }

        if (separators == ' ') {
            separators = SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }

}
