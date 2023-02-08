package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class FileReader {

    private final static String LINE_SEPARATOR = "\n";
    private final static String WORDS_SEPARATOR = " ";

    public Profile getDataFromFile(File file) {
        String path = file.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            int ch = fileInputStream.read();
            while (ch != -1) {
                sb.append((char) ch);
                ch = fileInputStream.read();
            }
        } catch (IOException e) {
            // exception handling
        }
        List<String> lines = parseString(sb.toString(), LINE_SEPARATOR);
        Profile profile = new Profile();
        for (String line : lines) {
            String[] arr = line.split(WORDS_SEPARATOR);
            switch (arr[0]) {
                case "Name:":
                    profile.setName(arr[1]);
                    break;
                case "Age:":
                    profile.setAge(Integer.parseInt(arr[1]));
                    break;
                case "Email:":
                    profile.setEmail(arr[1]);
                    break;
                case "Phone:":
                    profile.setPhone(Long.parseLong(arr[1]));
                    break;
                default:
                    throw new NoSuchElementException();
            }
        }
        return profile;
    }

    public List<String> parseString(String line, String separator) {
        return Arrays.stream(line.split(separator)).collect(Collectors.toList());
    }
}