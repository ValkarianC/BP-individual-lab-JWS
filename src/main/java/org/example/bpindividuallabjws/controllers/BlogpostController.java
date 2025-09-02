package org.example.bpindividuallabjws.controllers;

import org.example.bpindividuallabjws.entities.Blogpost;
import org.example.bpindividuallabjws.exceptions.InvalidUserException;
import org.example.bpindividuallabjws.services.BlogpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class BlogpostController {

    private final BlogpostService blogpostService;

    @Autowired
    public BlogpostController(BlogpostService blogpostService){
        this.blogpostService = blogpostService;
    }

    //USER ENDPOINTS

    //Get All Blogposts         (Requires Authentication)
    @GetMapping("/posts")
    @ResponseBody
    public ResponseEntity<Object> getAllBlogposts(){
        List<Blogpost> blogposts = blogpostService.getAllBlogposts();
        if (blogposts.isEmpty()){
            return new ResponseEntity<>("No blog posts in database", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(blogpostService.getAllBlogposts(), HttpStatus.FOUND);
        }
    }

    //Get Specific Blogpost     (Requires Authentication)
    @GetMapping("/post/{id}")
    @ResponseBody
    public ResponseEntity<Blogpost> getSpecificBlogpost(@PathVariable("id")Long id){
        return new ResponseEntity<>(blogpostService.getSpecificBlogpost(id), HttpStatus.FOUND);
    }

    //Create New Blogpost       (Requires USER role)
    @PostMapping("/newpost")
    @ResponseBody
    public ResponseEntity<Blogpost> createNewBlogpost(@RequestBody Blogpost blogpost){
        return new ResponseEntity<>(blogpostService.createBlogpost(blogpost), HttpStatus.CREATED);
    }

    //Update Blogpost           (Requires USER, USER must match owner of blogpost)
    @PutMapping("/updatepost")
    @ResponseBody
    public ResponseEntity<Blogpost> updateBlogpost(@RequestBody Blogpost blogpost){
        if (blogpost.getCreator().equals("TEST")) {  //TODO: Replace TEST with logged in username
            return new ResponseEntity<>(blogpostService.updateBlogpost(blogpost), HttpStatus.ACCEPTED);
        } else {
            throw new InvalidUserException("TEST", "EDIT/UPDATE", "Blogpost");
        }
    }

    //Delete Blogpost           (Requires USER -> USER must match owner of blogpost, or must be ADMIN)
    @DeleteMapping("/deletepost/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteBlogpost(@PathVariable("id")Long id){

        if (blogpostService.getSpecificBlogpost(id).getCreator().equals("TEST")) {  //TODO: Replace TEST with logged in username
            blogpostService.deleteBlogpostByID(id);
            return new ResponseEntity<>(("Blog post w/ ID : " + id + " | Status: Deleted"), HttpStatus.OK);
        } else {
            throw new InvalidUserException("TEST", "DELETE", "Blogpost");
        }

    }

    //ADMIN ENDPOINTS

    //Get Number of Blogposts   (Requires ADMIN role)
    @GetMapping("/count")
    @ResponseBody
    public ResponseEntity<String> getBlogpostCount(){
        Long numOfBlogposts = blogpostService.getNumberOfBlogposts();
        return new ResponseEntity<>(("Total number of blog posts : "+ numOfBlogposts), HttpStatus.OK);
    }
}
