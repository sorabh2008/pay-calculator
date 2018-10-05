package com.calc.test;

import com.calc.constants.ErrorConstants;
import com.calc.exception.CalException;
import com.calc.to.CalculatorRequest;
import com.calc.utils.CSVUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestCSVUtils {

    @Test
    public void testInvalidInput() {
        File file = new File("src/test/resources/InvalidInput.csv");
        List<CalculatorRequest> requests = null;
        try {
            requests = CSVUtils.readCSVFile(file);
        } catch (CalException e) {
            Assert.assertEquals(ErrorConstants.CODE_INVALID_INPUT, e.getErrorCode());
        }
        Assert.assertNull(requests);
    }

    @Test
    public void testHappyPath() throws CalException {
        File file = new File("src/test/resources/input.csv");
        List<CalculatorRequest> requests = CSVUtils.readCSVFile(file);
        Assert.assertEquals(1, requests.size());
    }

    @Test
    public void testFileMissing() {
        File file = new File("inpusst.csv");
        List<CalculatorRequest> requests = null;
        try {
            requests = CSVUtils.readCSVFile(file);
        } catch (CalException e) {
            Assert.assertEquals(e.getErrorCode(), ErrorConstants.CODE_FILE_MISSING);
        }
        Assert.assertNull(requests);
    }
}
