package com.spotify.oauth2.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	
	public static Properties propertyLoader(String filepath) throws IOException
	{   Properties properties = new Properties();
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(filepath));
		
		properties.load(reader);
		reader.close();
		
		return properties;
	}
}
