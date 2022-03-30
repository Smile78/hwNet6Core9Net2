package hwHttpCats;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cats {


    @JsonProperty("id")
    private String id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("type")
    private String type;
    @JsonProperty("user")
    private String user;
    @JsonProperty("upvotes")
//    private byte upvotes;
    private int upvotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

//    public byte getUpvotes() {
    public int  getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(byte upvotes) {
        this.upvotes = upvotes;
    }

    @Override
    public String toString() {
        return "Cats{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }

}
