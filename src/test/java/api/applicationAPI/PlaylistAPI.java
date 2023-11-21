package api.applicationAPI;

import api.endpoints.APIConstants;
import api.utils.ConfigLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class PlaylistAPI {
    @Step
    public static Response post(Object request, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) throws JsonProcessingException {
       return given().spec(requestSpecification).
                body(request).when().post(APIConstants.CREATE_PLAYLIST + ConfigLoader.getInstance().getUserID() + "/" + APIConstants.PLAYLIST).
                then().spec(responseSpecification).
                extract().response();
    }
    @Step
    public static Response get(RequestSpecification requestSpecification,ResponseSpecification responseSpecification,String playlistID) throws JsonProcessingException {
       return given().spec(requestSpecification).
                when().get(APIConstants.PLAYLIST + playlistID).
                then().spec(responseSpecification).
                extract().response();
    }
    @Step
    public static Response put(RequestSpecification requestSpecification,Object request) throws JsonProcessingException {
        return given().spec(requestSpecification).body(request).
                when().put(APIConstants.UPDATE_PLAYLIST).
                then().extract().response();
    }
    @Step
    public static Response post(Object request,ResponseSpecification responseSpecification,String token){
        return given().baseUri(APIConstants.BASE_URI).basePath(APIConstants.BASE_PATH).
                contentType(ContentType.JSON).
                header("Authorization","Bearer " + token).
                body(request).when().post(APIConstants.CREATE_PLAYLIST).
                then().spec(responseSpecification).
                extract().response();
    }
    @Step
    public static Response post_token(HashMap<String,String>formParams){
        return given().baseUri(APIConstants.AUTH_URI).basePath("api/token").
                contentType(ContentType.URLENC).
                formParams(formParams).
                when().post().then().log().all().extract().response();
    }
}
