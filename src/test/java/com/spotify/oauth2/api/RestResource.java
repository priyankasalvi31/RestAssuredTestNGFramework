package com.spotify.oauth2.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.oauth2.pojo.Playlist;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResource {
	static String accessToken="BQAPsK7hMV9ALE6zbs7XRMDKHTLXYpOVndUe2bAa4i0SmGbRy_x1VRQWvcv2MF6OKYzdXsl8J7wCIOePzyYnkACtKDymPv3lJRhujoBp4ELhgjPXgRH9vF7CTp7Pu8kdtuwS5KqLfcgrVE4VVWEOMOo_aCU7zeyUEifYe7jjSuuIAlLz1XSTBoF1NjELXD9EpRPRvKEbDPHkl85r0-LvfpDBP4VgaRWTbIjqtFy0iWek24oForzYyiyjiF8C-u_IAnKFqEKm5xJOCDUh";

	public static Response post(String path, String token,Object resquestplaylist)
	{
		return (Response) given(SpecBuilder.getRequestSpec()).body(resquestplaylist).auth().oauth2(token).when()
				.post(path).then().spec(SpecBuilder.getResponseSpec()).extract().response();
		
	}
public static Response PostAccount(HashMap<String,String> formParams)
{
	
	return given(SpecBuilder.getAccountRequestSpec()).
			formParams(formParams).post(Route.API+Route.TOKEN).then().spec(SpecBuilder.getResponseSpec()).extract().response();
	
}
	public static Response get(String path,String token)
	{
		return given(SpecBuilder.getRequestSpec()).auth().oauth2(token).when().get(path).then().spec(SpecBuilder.getResponseSpec()).
				extract().response();

	}
	
	public static Response put(String path,String token, Object resquestplaylist)
	{
		return given(SpecBuilder.getRequestSpec()).auth().oauth2(token).body(resquestplaylist).when().put(path).then().spec(SpecBuilder.getResponseSpec()).assertThat().
				statusCode(200).extract().response();

	}

}
