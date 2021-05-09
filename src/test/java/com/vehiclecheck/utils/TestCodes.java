package com.vehiclecheck.utils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class TestCodes {
    public static void main(String[] args) {
        try {
            String filePath = PropertyReader.getInputDataFilePath();
            Pattern carRegistrationPattern = Pattern.compile(PropertyReader.getCarRegistrationPattern());
            List<String> carList = FileReader.readInputDataFromFiles(filePath, carRegistrationPattern);
            for (String carReg : carList
                 ) {
                System.out.println(carReg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*try {
            String expectedDataFile = PropertyReader.getExpectedDataFilePath();
            List<CarDTO> carDetails = FileReader.readExpectedDatafromFile(expectedDataFile);
            for(CarDTO carDTO : carDetails) {
                System.out.println(carDTO.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
