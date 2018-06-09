package razvan.Tema_finala.model;

public class PostModel {

    private String postMessage;
    private String visibility;

    public PostModel() {
    }

    public PostModel(String postMessage, String visibility) {
        this.postMessage = postMessage;
        this.visibility = visibility;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "postMessage='" + postMessage + '\'' +
                ", visibility=" + visibility +
                '}';
    }
}
