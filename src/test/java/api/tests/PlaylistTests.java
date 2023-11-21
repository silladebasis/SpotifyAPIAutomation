package api.tests;

import api.pojo.Playlist;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    RequestSpecBuilder requestSpecBuilder;
    ResponseSpecBuilder responseSpecBuilder;
    String access_token = "BQAHv8OzXOGJN5q0Uozy1cbSjOfstqHA5po5gaDSZOowhByANhATyGKv_JtYybPpWLI0KFZY_IZZWvyOdk1oPJr-U-Ic_lwAOA8UlhwYlwe8EQkpPGp5Wukwdd5yVK9cbU-lF4161l3yw0GGodxKRd_Q2_Z8edM95m-P_TsZz0ZqC7I1BdjPTcY2T9RPMR1WtUzXpWFnWvc5MtO8ySPq6I7up7DdyXw-Te6OXIxnebm3x4w1cOAAM75SjvdIPTgR9qkkoZBqyoKnDGT4Jop34HU2s-c";
    @BeforeMethod
    public void beforeMethod(){
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.spotify.com").setBasePath("/v1").setContentType(ContentType.JSON);
        requestSpecBuilder.addHeader("Authorization","Bearer " + access_token).log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.log(LogDetail.ALL).expectContentType(ContentType.JSON);
        responseSpecification = responseSpecBuilder.build();
    }
    @Test
    public void testCreatePlaylist(){
        String payload = "{\n" +
                "    \"name\": \"New Playlist 3\",\n" +
                "    \"description\": \"New playlist description 1\",\n" +
                "    \"public\": false\n" +
                "}";
        Response response = given().spec(requestSpecification).
                body(payload).when().post("users/31bxtx5f7rovqtc2o67rme3fx4ju/playlists").
                then().spec(responseSpecification).
                assertThat().statusCode(201).
                extract().response();
        System.out.println(response.prettyPrint());

        JsonPath jsonPath = new JsonPath(response.asString());
        String name = jsonPath.get("name");
        String displayName = jsonPath.getString("owner.display_name");
        System.out.println(name);
        System.out.println(displayName);
        assertThat(name,equalTo("New Playlist 3"));
        assertThat(displayName,equalTo("Debasis"));
    }
    @Test
    public void testGetPlaylist(){
        given().spec(requestSpecification).
                when().get("playlists/0GPXjXLvq27CZiXdCIXaQG").
                then().spec(responseSpecification).
                assertThat().statusCode(200);
    }
    @Test
    public void testUpdatePlaylist(){
        String payload = "{\n" +
                "    \"name\": \"Updated Playlist 3\",\n" +
                "    \"description\": \"New playlist description 1\",\n" +
                "    \"public\": false\n" +
                "}";
        given().spec(requestSpecification).body(payload).
                when().put("playlists/0GPXjXLvq27CZiXdCIXaQG").
                then().assertThat().statusCode(200);
    }
    @Test
    public void testCreatePlaylistWithoutName(){
        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description 1\",\n" +
                "    \"public\": false\n" +
                "}";
        Response response = given().spec(requestSpecification).
                body(payload).when().post("users/31bxtx5f7rovqtc2o67rme3fx4ju/playlists").
                then().spec(responseSpecification).
                assertThat().statusCode(400).
                extract().response();
        System.out.println(response.prettyPrint());
        assertThat(response.getStatusCode(),equalTo(400));
        assertThat(response.jsonPath().get("error.message"),equalTo("Missing required field: name"));
    }
    @Test
    public void testCreatePlaylistWithInvalidToken(){
        String payload = "{\n" +
                "    \"name\": \"New Playlist 4\",\n" +
                "    \"description\": \"New playlist description 1\",\n" +
                "    \"public\": false\n" +
                "}";
        Response response = given().baseUri("https://api.spotify.com").basePath("/v1").
                contentType(ContentType.JSON).
                header("Authorization","Bearer " + "12235").
                body(payload).when().post("users/31bxtx5f7rovqtc2o67rme3fx4ju/playlists").
                then().spec(responseSpecification).
                assertThat().statusCode(401).
                extract().response();
        System.out.println(response.prettyPrint());
        assertThat(response.getStatusCode()).isEqualTo(401);
        assertThat(response.jsonPath().getString("error.message")).isEqualTo("Invalid access token");

    }
}
