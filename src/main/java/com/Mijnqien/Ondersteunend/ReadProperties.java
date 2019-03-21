package com.Mijnqien.Ondersteunend;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


// Deze file heeft alleen statische velden
// Leest een config file uit en sla deze op in statische velden 
// Deze constanten kunnen

public class ReadProperties {
	public static String setUsername;
	public static String setPassword;
	
		public static void readConfig()
		{
			try
			{

			    Properties pro = new Properties();
			    String path = System.getProperty("user.dir")+"/src/main/resources/application.properties";
			    pro.load(new FileInputStream(path));	   
			    setUsername = pro.getProperty("setUsername");
			    setPassword = pro.getProperty("setPassword");	   
			} catch(IOException e) {
				System.out.println("Properties file not read.");	
			}
		}
}
