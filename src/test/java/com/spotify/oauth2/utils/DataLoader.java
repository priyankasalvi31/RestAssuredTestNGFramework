package com.spotify.oauth2.utils;

import java.io.IOException;
import java.util.Properties;

public class DataLoader {

	private final Properties properties;
	private static DataLoader dataloader;
	
	private DataLoader() throws IOException
	{
		properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
			}
	public static DataLoader getInstance() throws IOException
	{
		if(dataloader==null)
		{
			dataloader =new DataLoader();
		}
		return dataloader;
	}
	
	public String getPlaylistId()
	{
		String prop =properties.getProperty("get_playlist_id");
		if(prop!=null)
			return prop;
		else
			throw new RuntimeException("data get playlist id is not specified in the data.properties");
	}
	
	public String getUpdatedPlaylistId()
	{
		String prop =properties.getProperty("update_playtlist");
		if(prop!=null)
			return prop;
		else
			throw new RuntimeException("data get playlist id is not specified in the data.properties");
	}
	
	
	
	
}
