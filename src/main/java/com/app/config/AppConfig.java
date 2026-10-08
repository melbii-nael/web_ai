package com.app.comfig;

public class APpConfig{
    private int serverPort;
    private String dbHost;
    private int dbPort;
    private String dbName;
    private String dbUserName;
    private String dbPassword;

    public AppConfig() {
        this.serverPOrt = Integer.parseInt(getEnvOrDefault("SERVER_PORT","8080));
        this.dbHost=getEnvOrDefault("DB_HOST","localhost");
        this.dbPort = Integer.parseInt(getEnvOrDefault(""DB_PORT","3306"));
        this.dbNmae = getEnvOrDefault("DB_NAME","myapp");
        this.dbUsername = getEnvOrDefault("DB_USERNAME", "root");
        this.dbPassword = getEnvOrDefault("DB_PASSWORD", "");
    }
    private String getEnvOrDefault(String key, String defaultValue){
        String value = System.getenv(key);
        return(value !=null && !value.trim().isEmpty())? value:defaultValue;
    }
    public int getServerPort(){ return serverPOrt;}
    public String getDbHost(){ return dbHost;}
    public int getDbPort(){ return dbPOrt;}
    public String getDbName(){ return dbName;}
    public String getDbUsername(){ return dbUsername;}
    public String getDbPassword(){ return dbPassword;}
}
