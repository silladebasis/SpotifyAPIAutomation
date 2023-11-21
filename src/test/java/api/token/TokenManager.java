package api.token;

import api.applicationAPI.PlaylistAPI;
import api.endpoints.APIConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expires_in;
    private static Response renewToken() throws JsonProcessingException {
        HashMap<String,String> formParams = new LinkedHashMap<>();
        formParams.put("client_id","c8eb5c23919f48edaf558f0e1cd3072f");
        formParams.put("client_secret","dcc179c62bab4a36aa99b34a9d302273");
        formParams.put("refresh_token","AQAm_XVbQbXHklfM1DSVt81XxIHgoXaLdIL6oF2oUV3TD0-HRvoGPwImp8d0HiVTRGaHVzahcCzbCyLkIogzrKrV_JLF2wylfioIqgArwJymR9Mc-bBIgUCXm5WebLKtDfE");
        formParams.put("grant_type","refresh_token");

        Response response = PlaylistAPI.post_token(formParams);
        if(response.statusCode()!=200){
            throw new RuntimeException("Abort!!! Failed to renew token");
        }
        return response;
    }

    public static String getAccessToken() throws JsonProcessingException {
        if(access_token == null || Instant.now().isAfter(expires_in)){
            System.out.println("Renewing the token");
            Response response = renewToken();
            access_token = response.path("access_token");
            int expiryTime = response.path("expires_in");
            expires_in = Instant.now().plusSeconds(expiryTime);
        }
        else {
            System.out.println("Token is good to use");
        }
        return access_token;
    }
}
