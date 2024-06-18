package com.spotify.oauth2.tests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.spotify.oauth2.api.PlaylistApi;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class PlaylistTest {
RequestSpecification requestspecification;
ResponseSpecification responsespecification;

public Playlist playlistBuilder(String name,String description, boolean _public)
{
	return new Playlist().setName(name).setDescription(description).setPublic(_public);

}

public void assertPlayListEqual(Playlist requestPlaylist,Playlist responsePlaylist)
{
	assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
	assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
	assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));	
}

public void assertStatusCode(int actualStatusCode,int expectedStatusCode)
{
	assertThat(actualStatusCode, equalTo(expectedStatusCode));
	}
public void assertError(Error responseErr,int expectedStatusCode, String expectedMsg)
{
	assertThat(responseErr.getError().getStatus(), equalTo(expectedStatusCode));
	assertThat(responseErr.getError().getMessage(), equalTo(expectedMsg));

}
@Test
public void ShouldNotBeAbleToCreatePlaylistwithName() throws IOException
{
	Playlist playlist = playlistBuilder("",FakerUtils.generateDescription(), false);
	Response response = PlaylistApi.post(playlist);
	assertStatusCode(response.statusCode(), StatusCode.CODE_400.getCode());
	assertError(response.as(Error.class),StatusCode.CODE_400.getCode(),StatusCode.CODE_400.getMsg());
}

@Test
public void ShouldNotBeAbleToCreatePlaylistwithExpiredToken() throws IOException
{ String Invalid_token="12345";
	Playlist playlist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
	Response response = PlaylistApi.post(Invalid_token,playlist);
	assertStatusCode(response.statusCode(), StatusCode.CODE_401.getCode());
	assertError(response.as(Error.class),StatusCode.CODE_401.getCode(),StatusCode.CODE_401.getMsg());
}

@Description("This is the description")
@Test(description = "should be create a playlist ")
public void ShouldBeAbleToCreatePlaylistPOJO() throws IOException
{
	Playlist playlist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
	Response response = PlaylistApi.post(playlist);
	assertStatusCode(response.statusCode(),StatusCode.CODE_201.getCode());
	Playlist responseplaylist = response.as(Playlist.class);
	assertPlayListEqual(response.as(Playlist.class), responseplaylist);


}
@Test
public void ShouldBeAbleToUpdatePlaylistPOJO() throws IOException
{
	Playlist playlist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
		Response response=PlaylistApi.put(playlist, DataLoader.getInstance().getUpdatedPlaylistId());
		assertStatusCode(response.statusCode(),StatusCode.CODE_200.getCode());
}
@Test
public void ShouldBeAbleToGetPlaylistPOJO() throws IOException
{
	Playlist playlist = playlistBuilder("pojo playlist1", "pojo descritpion", true);
	Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
	assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());
	Playlist responseplaylist = response.as(Playlist.class);
	assertPlayListEqual(response.as(Playlist.class), responseplaylist);

}


}
