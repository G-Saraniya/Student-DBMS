package dbms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
    
    
    public List<String[]> readCSVFile(String filePath) {
        List<String[]> records = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (isFirstLine && (line.toLowerCase().contains("name") || line.toLowerCase().contains("roll"))) {
                    isFirstLine = false;
                    continue;
                }
                isFirstLine = false;
                
                String[] values = parseCSVLine(line);
                
                if (values.length >= 9) {
                    records.add(values);
                } else {
                    System.out.println("Warning: Skipping invalid line: " + line);
                }
            }
            
            System.out.println("Successfully read " + records.size() + " records from file.");
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return records;
    }
    
    private String[] parseCSVLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        boolean inQuotes = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                values.add(currentValue.toString().trim());
                currentValue = new StringBuilder();
            } else {
                currentValue.append(c);
            }
        }
        
        values.add(currentValue.toString().trim());
        
        return values.toArray(new String[0]);
    }
    
    public boolean validateRecord(String[] record) {
        if (record == null || record.length < 9) {
            return false;
        }
        
        for (int i = 0; i < 9; i++) {
            if (record[i] == null || record[i].trim().isEmpty()) {
                return false;
            }
        }
        
        try {
            Integer.parseInt(record[4].trim());
        } catch (NumberFormatException e) {
            System.out.println("Warning: Invalid year format in record");
            return false;
        }
        
        try {
            Float.parseFloat(record[8].trim());
        } catch (NumberFormatException e) {
            System.out.println("Warning: Invalid CGPA format in record");
            return false;
        }
        
        return true;
    }
    
    public List<String[]> readAndValidateFile(String filePath) {
        List<String[]> allRecords = readCSVFile(filePath);
        List<String[]> validRecords = new ArrayList<>();
        
        int lineNumber = 1;
        for (String[] record : allRecords) {
            if (validateRecord(record)) {
                validRecords.add(record);
            } else {
                System.out.println("Line " + lineNumber + ": Invalid record - " + String.join(",", record));
            }
            lineNumber++;
        }
        
        System.out.println("Valid records: " + validRecords.size() + " / " + allRecords.size());
        return validRecords;
    }
}
