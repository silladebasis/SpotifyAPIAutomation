package api.Actions;

import api.pojo.Error;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AssertActions {
    @Step
    public static void verifyStatusCode(Response response,int statusCode){
        assertThat(response.statusCode()).isEqualTo(statusCode);
    }
    @Step
    public static void verifyErrorStatusCode(Error error,int statusCode){
       assertThat(error.getError().getStatus()).isEqualTo(statusCode);
    }
    @Step
    public static void verifyErrorMessageCode(Error error,String message){
        assertThat(error.getError().getMessage()).isEqualTo(message);
    }
}
