package api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class Playlist {
    @JsonProperty("collaborative")
    private boolean collaborative;
    private String description;
    private ExternalURLs external_urls;
    private Followers followers;
    private String href;
    private String id;
    private List<Object> images;
    private String name;
    private Owner owner;
    @JsonProperty("public")
    private boolean _public;
    private String primary_color;
    private String snapshot_id;
    private Tracks tracks;
    private String type;
    private String uri;
}
