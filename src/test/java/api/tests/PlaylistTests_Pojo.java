package api.tests;

import api.Enums.StatusCode;
import api.applicationAPI.PlaylistAPI;
import api.base.BaseTest;
import api.modules.PayloadManager;
import api.pojo.Error;
import api.pojo.Playlist;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static api.Actions.AssertActions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests_Pojo extends BaseTest {
    PayloadManager payloadManager = new PayloadManager();
    @Story("Create a Playlist Story")
    @Test(description = "Create a Playlist")
    @Description("This test is to create a playlist")
    public void testCreatePlaylist() throws JsonProcessingException {
        Response response = PlaylistAPI.post(payloadManager.createPlaylist(),requestSpecification,responseSpecification);
        Playlist responsePlaylist = response.as(Playlist.class);
        verifyStatusCode(response,StatusCode.CODE_201.getCode());
        assertThat(responsePlaylist.getName()).isEqualTo(payloadManager.getRequest().getName());

        System.out.println(responsePlaylist.getExternal_urls().getSpotify());
        System.out.println(responsePlaylist.getTracks().getHref());
        System.out.println(responsePlaylist.getOwner().getUri());
        System.out.println(responsePlaylist.getOwner().getExternalURLs().getSpotify());
    }
    @Test(description = "Get the Playlist")
    @Description("This test is to fetch the details of a playlist")
    public void testGetPlaylist() throws JsonProcessingException {
        Response response = PlaylistAPI.get(requestSpecification,responseSpecification,"0GPXjXLvq27CZiXdCIXaQG");
        Playlist responsePlaylist = response.as(Playlist.class);
        verifyStatusCode(response,StatusCode.CODE_200.getCode());
        assertThat(responsePlaylist.getOwner().getDisplay_name()).isEqualTo("Debasis");
    }
    @Test(description = "Update a Playlist")
    @Description("This test is to update a playlist")
    public void testUpdatePlaylist() throws JsonProcessingException {
       /* Response response = given().spec(requestSpecification).body(payloadManager.createPlaylist()).
                when().put("playlists/0GPXjXLvq27CZiXdCIXaQG").
                then().assertThat().statusCode(200).extract().response();*/
        Response response = PlaylistAPI.put(requestSpecification,payloadManager.createPlaylist());
        verifyStatusCode(response,StatusCode.CODE_200.getCode());
    }
    @Story("Create a Playlist Story")
    @Test(description = "Test Playlist without name")
    @Description("This test is to create a playlist without name")
    public void testCreatePlaylistWithoutName() throws JsonProcessingException {
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("");
        requestPlaylist.setDescription("Test123");
        requestPlaylist.set_public(false);
        Response response = PlaylistAPI.post(requestPlaylist,requestSpecification,responseSpecification);
        verifyStatusCode(response,400);

        Error error = response.as(Error.class);
        verifyErrorStatusCode(error,StatusCode.CODE_400.getCode());
        verifyErrorMessageCode(error,StatusCode.CODE_400.getMessage());

       /* ObjectMapper objectMapper = new ObjectMapper();
        Error error = objectMapper.readValue(response.asString(), Error.class);
        assertThat(error.getError().getStatus(),equalTo(400));
        assertThat(error.getError().getMessage(),equalTo("Missing required field: name"));*/
    }
    @Story("Create a Playlist Story")
    @Test(description = "Test Playlist with invalid token")
    @Description("This is a error test to validate create playlist with invalid token")
    public void testCreatePlaylistWithInvalidToken() throws JsonProcessingException {
        Response response = PlaylistAPI.post(payloadManager.createPlaylist(),responseSpecification,"12345");

        Error error = response.as(Error.class);
        verifyErrorStatusCode(error,StatusCode.CODE_401.getCode());
        verifyErrorMessageCode(error, StatusCode.CODE_401.getMessage());

        /*ObjectMapper objectMapper = new ObjectMapper();
        Error error = objectMapper.readValue(response.asString(), Error.class);

        assertThat(error.getError().getMessage(),equalTo("Invalid access token"));
        assertThat(error.getError().getStatus()).isEqualTo(401);*/

    }
}
