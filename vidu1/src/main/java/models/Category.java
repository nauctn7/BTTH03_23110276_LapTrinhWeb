package models;

public class Category {
    private int id;
    private String name;
    private String image; // <-- dùng cột image trong DB
    private int userId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
