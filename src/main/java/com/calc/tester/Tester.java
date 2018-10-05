package com.calc.tester;

import com.calc.constants.ErrorConstants;
import com.calc.exception.CalException;
import com.calc.impl.PayCalculatorImpl;
import com.calc.to.CalculatorRequest;
import com.calc.to.CalculatorResponse;
import com.calc.to.Response;
import com.calc.utils.CSVUtils;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class Tester {
    private static final String COMA = ",";

    public static void main(String[] args) {
        File input = null;
        System.out.println("------------ STARTING ------------");
        if (args.length > 0 && null != args[0]) {
            input = new File(args[0]);
        } else {
            input = new File("input.csv");
        }
        StringBuilder line = new StringBuilder();
        List<CalculatorRequest> requests = null;
        try {
            requests = CSVUtils.readCSVFile(input);
            for (CalculatorRequest request : requests) {
                Response response = new PayCalculatorImpl().calculate(request);
                if (response.getErrCode() == null) {
                    CalculatorResponse calResp = response.getCalculatorResponse();
                    line.append(calResp.getName())
                            .append(COMA)
                            .append(calResp.getPayPeriod())
                            .append(COMA)
                            .append(calResp.getGrossIncome())
                            .append(COMA)
                            .append(calResp.getIncomeTax())
                            .append(COMA)
                            .append(calResp.getNetIncome())
                            .append(COMA)
                            .append(calResp.getSuperContribution())
                            .append("\n");
                } else {
                    line.append(response.getErrCode().concat(" - ").concat(response.getErrMsg()).concat("\n"));
                }
            }
            if (line.length() > 0) {
                writeToCSV(new File("output.csv"), line);
            }
        } catch (CalException e) {
            System.out.println(e.getErrorCode().concat(" - ").concat(e.getMessage()));
        }
        System.out.println("------------ FINISHED ------------");
    }

    private static void writeToCSV(File file, StringBuilder content) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
            pw.write(content.toString());
            System.out.println("Output: " + file.getName());
        } catch (Exception e) {
            System.out.println(ErrorConstants.CODE_WRITE_ERROR.concat(" - ").concat(ErrorConstants.MSG_WRITE_ERROR));
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
