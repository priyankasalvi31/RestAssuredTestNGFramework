package com.spotify.oauth2.api;

import java.io.IOException;

import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.response.Response;

public class PlaylistApi {
	public static Response post(Playlist resquestplaylist) throws IOException
	{
		return RestResource.post(Route.USERS+"/"+ConfigLoader.getInstance().getUser_Id()+Route.PLAYLISTS,TokenManager.getToken() , resquestplaylist);
		
	}
	public static Response post(String Token,Playlist resquestplaylist) throws IOException
	{
		return RestResource.post(Route.USERS+"/"+ConfigLoader.getInstance().getUser_Id()+Route.PLAYLISTS, Token, resquestplaylist);
		
	}
	
	public static Response get(String playListId)
	{
		return RestResource.get(Route.PLAYLISTS+"/"+playListId,TokenManager.getToken());
	}
	
	public static Response put(Playlist resquestplaylist,String playListId)
	{
		return RestResource.put(Route.PLAYLISTS+"/"+playListId, TokenManager.getToken() , resquestplaylist);
		

	}

}
