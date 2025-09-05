package org.example.bpindividuallabjws.services;

import org.example.bpindividuallabjws.entities.Blogpost;

import java.util.List;

public interface BlogpostServiceInterface {
    //USER
    List<Blogpost> getAllBlogposts();
    Blogpost getSpecificBlogpost(Long id);
    Blogpost createBlogpost(Blogpost blogpost);
    Blogpost updateBlogpost(Blogpost blogpost, String username);
    void deleteBlogpostByID(Long id, String username, boolean admin);

    //ADMIN
    Long getNumberOfBlogposts(); //TODO: check what is needed for "count" endpoint
}
