package com.vehiclecheck.utils;

import com.vehiclecheck.dto.CarDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    //private static final String OUTPUT_DATA_FILE = "/car_output.txt";
    private static final Logger log = LoggerFactory.getLogger(FileReader.class);

    public static List<Path> listFilesFromDirectory(String pathName) throws IOException {
        try(Stream<Path> stream = Files.list(Paths.get(pathName))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .collect(Collectors.toList());
        }
    }

    public static List<String> readInputDataFromFiles(String inputDir, Pattern pattern) throws IOException {
        List<Path> pathList = FileReader.listFilesFromDirectory(inputDir);
        List<String> inputDataList = new ArrayList<>();
        for (Path path : pathList) {
            try(Stream<String> stream = Files.lines(path)) {
                stream.forEach(input -> pattern.matcher(input)
                        .results()
                        .forEach(matcher -> inputDataList.add(matcher.group().replace(" ", ""))));
            }
        }
        return inputDataList;
    }

    public static Map<String, CarDTO> readExpectedDatafromFile(String fileName) throws IOException {
        Pattern pattern = Pattern.compile(",");
        try(Stream<String> lines = Files.lines(Path.of(fileName))) {
            Map<String, CarDTO> carDetails = lines.skip(1).map(line -> {
                String[] arr = pattern.split(line);
                return new CarDTO(
                        arr[0],
                        arr[1],
                        arr[2],
                        arr[3],
                        arr[4]);
            }).collect(Collectors.toMap(o -> o.getRegistration(), o -> o));
            return carDetails;
        }
    }
}
