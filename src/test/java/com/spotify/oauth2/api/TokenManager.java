package com.spotify.oauth2.api;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
		public synchronized static String getToken()
		{
			try {
				if(access_token== null||Instant.now().isAfter(expiry_time))
				{
					System.out.println("Renewing token");
				Response response = renewtoken();
				access_token=response.path("access_token");
				int expiry_DurationInSeconds = response.path("expires_in");
				expiry_time = Instant.now().plusSeconds(expiry_DurationInSeconds-300);
				}
				else
				{
					System.out.println("Token is good to use");
				}
				
			}
			catch(Exception e) {
				throw new RuntimeException("ABORT failed to get access token");
			}
			
			
			return access_token;
		}
	
	private static Response renewtoken() throws IOException
	{
		
		HashMap<String,String> formParams = new HashMap<String,String>();
		formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
		formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
		formParams.put("client_id", ConfigLoader.getInstance().getClientId());
		formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		Response response = RestResource.PostAccount(formParams);
		if(response.getStatusCode()!=200)
		{
			throw new RuntimeException("Abort :: Renew token fail");
		}
		return response;
		
	}

}
