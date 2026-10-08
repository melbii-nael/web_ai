package com.app;
import com.app.comfig.AppConfig;
import com.app.server.AppServer;

import java.io.IOException;

public class Main{
public static void main(String[] args){
  try{
  AppConfig config = new AppCOnfig();
  AppServer server = new AppServer(config);
  server.start();
  System.out.println("Server started on port "+ config.getServerPort());
  } catch (IOException e){
  System.err.println("Failed to start server:" + e.getMessage());
  e.printStackTrace();
  }
  }
  }