package api.modules;

import api.pojo.Playlist;
import api.utils.ConfigLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PayloadManager {
    ObjectMapper objectMapper;
    Playlist requestPlaylist;
    @Step
    public String createPlaylist() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        requestPlaylist = new Playlist();
        requestPlaylist.setName("New Playlist 5");
        requestPlaylist.setDescription("Test");
        requestPlaylist.set_public(false);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestPlaylist);
    }
    public Playlist getRequest(){
        objectMapper = new ObjectMapper();
        requestPlaylist = new Playlist();
        requestPlaylist.setName("New Playlist 5");
        requestPlaylist.setDescription("Test");
        requestPlaylist.set_public(false);
        return requestPlaylist;
    }
    public HashMap<String, String> createAuthPayload(){
        HashMap<String,String>formParams = new LinkedHashMap<>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientID());
        formParams.put("client_secret",ConfigLoader.getInstance().getClientSecret());
        formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formParams.put("grant_type",ConfigLoader.getInstance().getGrantType());
        return formParams;
    }
}
