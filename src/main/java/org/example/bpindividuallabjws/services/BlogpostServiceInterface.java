package org.example.bpindividuallabjws.services;

import org.example.bpindividuallabjws.entities.Blogpost;

import java.util.List;

public interface BlogpostServiceInterface {
    //USER
    List<Blogpost> getAllBlogposts();
    Blogpost getSpecificBlogpost(Long id);
    Blogpost createBlogpost(Blogpost blogpost);
    Blogpost updateBlogpost(Blogpost blogpost);
    void deleteBlogpostByID(Long id);

    //ADMIN
    Long getNumberOfBlogposts(); //TODO: check what is needed for "count" endpoint
}
