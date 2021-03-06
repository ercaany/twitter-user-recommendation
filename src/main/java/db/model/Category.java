package db.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ercan on 17.12.2016.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable{

    @Id
    @Column(name="id")
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy="wordCategoryFrequencyPK.category", targetEntity=WordCategoryFrequency.class,
            fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<WordCategoryFrequency> wordFrequencies = new ArrayList<WordCategoryFrequency>();

    public Category() {
        super();
    }

    public Category(int id) {
        this.id = id;
    }

    //getter-setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<WordCategoryFrequency> getWordFrequencies() {
        return wordFrequencies;
    }

    public void setWordFrequencies(List<WordCategoryFrequency> wordFrequencies) {
        this.wordFrequencies = wordFrequencies;
    }
}
