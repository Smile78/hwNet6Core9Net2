package hwHttpNasa;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaApodSerializ {


    @JsonProperty("date")
    private String date;
    @JsonProperty("explanation")
    private String explanation;
    @JsonProperty("media_type")
    private String mediaTyp;
    @JsonProperty("service_version")
    private String serviceVer;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getMediaTyp() {
        return mediaTyp;
    }

    public void setMediaTyp(String mediaTyp) {
        this.mediaTyp = mediaTyp;
    }

    public String getServiceVer() {
        return serviceVer;
    }

    public void setServiceVer(String serviceVer) {
        this.serviceVer = serviceVer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NasaApodSerializ{" +
                "date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", mediaTyp='" + mediaTyp + '\'' +
                ", serviceVer='" + serviceVer + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }




}
