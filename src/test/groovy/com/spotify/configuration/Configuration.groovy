package com.spotify.configuration

class Configuration {

    public static final CONFIGURATION = new Configuration()
    private final Properties properties = new Properties()

    private Configuration() {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties")
        try{
            properties.load(inputStream)
        } catch (IOException e){
            throw new RuntimeException(e)
        }
    }

    String getValue(String key){
        return properties.getProperty(key)
    }
}
