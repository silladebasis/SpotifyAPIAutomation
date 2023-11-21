package api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private static Properties properties;
    private static FileInputStream fileInputStream;
    public static Properties propertyReader(String filePath){
        properties = new Properties();
        try {
            fileInputStream = new FileInputStream(new File(filePath));
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load file : " + filePath);
        }
        return properties;
    }
}
