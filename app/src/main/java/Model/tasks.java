package Model;

import com.google.gson.annotations.SerializedName;

//TODO: add the attrubet and ther mutch in API  using SerializedName
//TODO: add the constracteur and all the setter and getter
//TODO: define the constarteur without id as pasrmats becouse it well generate automatiquem from the rest api:



public class tasks {
    //SerializedName is to matuch the name of the att of API mautch it with the variable
  @SerializedName("_id")
    private String id;
  @SerializedName("description")
    private String description;
  @SerializedName("title")
  private String title;
  @SerializedName("category")
    private  String category;
  @SerializedName("time")
    private String time;
  @SerializedName("createdAt")
    private String createdAt;
  @SerializedName("updatedAt")
    private String updatedAt;


    public tasks( String description, String title, String category, String time, String createdAt, String updatedAt) {
        this.description = description;
        this.title = title;
        this.category = category;
        this.time = time;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }




}
