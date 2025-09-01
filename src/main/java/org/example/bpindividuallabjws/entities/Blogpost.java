package org.example.bpindividuallabjws.entities;

import jakarta.persistence.*;

@Entity
public class Blogpost {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(length = 40, nullable = false)
    private String creator;

    @Column(length = 255, nullable = false)
    private String blogpost;

    public Blogpost() {
    }

    public Blogpost(Long id, String creator, String blogpost) {
        this.id = id;
        this.creator = creator;
        this.blogpost = blogpost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getBlogpost() {
        return blogpost;
    }

    public void setBlogpost(String blogpost) {
        this.blogpost = blogpost;
    }
}
