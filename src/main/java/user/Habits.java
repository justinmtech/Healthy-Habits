package user;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Habits {
    @Id
    private int habitId;
    private String category;
    private String name;
    private int rating;
    private Date dateAdded;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
