package api.utils;

import java.util.Properties;

public class ConfigLoader {
    private Properties properties;
    private static ConfigLoader configLoader;
    private ConfigLoader(){
        properties = PropertyUtil.propertyReader("src/test/resources/globalConfig.properties");
    }
    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }
    public String getClientID(){
        return properties.getProperty("client_id");
    }
    public String getClientSecret(){
        return properties.getProperty("client_secret");
    }
    public String getGrantType(){
        return properties.getProperty("grant_type");
    }
    public String getRefreshToken(){
        return properties.getProperty("refresh_token");
    }
    public String getUserID(){
        return properties.getProperty("user_id");
    }
}
