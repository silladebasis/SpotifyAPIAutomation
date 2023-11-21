package api.base;

import api.endpoints.APIConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import static api.token.TokenManager.getAccessToken;
public class BaseTest {
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;
    protected RequestSpecBuilder requestSpecBuilder;
    protected ResponseSpecBuilder responseSpecBuilder;
    @BeforeMethod
    public void beforeMethod() throws JsonProcessingException {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(APIConstants.BASE_URI).setBasePath(APIConstants.BASE_PATH).setContentType(ContentType.JSON);
        requestSpecBuilder.addHeader("Authorization","Bearer " + getAccessToken()).log(LogDetail.ALL);
        requestSpecBuilder.addFilter(new AllureRestAssured());
        requestSpecification = requestSpecBuilder.build();

        responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.log(LogDetail.ALL).expectContentType(ContentType.JSON);
        responseSpecification = responseSpecBuilder.build();
    }
}
