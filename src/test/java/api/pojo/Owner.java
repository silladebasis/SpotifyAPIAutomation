package api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Owner {
    @JsonProperty("display_name")
    private String display_name;
    @JsonProperty("external_urls")
    private Owner_ExternalURLs external_urls;
    @JsonProperty("href")
    private String href;
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("uri")
    private String uri;

    @JsonProperty("display_name")
    public String getDisplay_name() {
        return display_name;
    }
    @JsonProperty("display_name")
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public Owner_ExternalURLs getExternalURLs() {
        return external_urls;
    }

    public void setExternalURLs(Owner_ExternalURLs externalURLs) {
        this.external_urls = externalURLs;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
