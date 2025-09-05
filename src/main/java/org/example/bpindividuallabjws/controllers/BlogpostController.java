package org.example.bpindividuallabjws.controllers;

import org.example.bpindividuallabjws.entities.Blogpost;
import org.example.bpindividuallabjws.services.BlogpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
public class BlogpostController {

    private final BlogpostService blogpostService;

    @Value("${jwt.auth.converter.resource-id.name}")
    private String securityResourceId;

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
    public ResponseEntity<Blogpost> createNewBlogpost(@RequestBody Blogpost blogpost, @AuthenticationPrincipal Jwt jwt){
        blogpost.setCreator(jwt.getClaimAsString("preferred_username"));
        return new ResponseEntity<>(blogpostService.createBlogpost(blogpost), HttpStatus.CREATED);
    }

    //Update Blogpost           (Requires USER, USER must match owner of blogpost)
    @PutMapping("/updatepost")
    @ResponseBody
    public ResponseEntity<Blogpost> updateBlogpost(@RequestBody Blogpost blogpost, @AuthenticationPrincipal Jwt jwt){
        String username = jwt.getClaimAsString("preferred_username");
        return new ResponseEntity<>(blogpostService.updateBlogpost(blogpost, username), HttpStatus.ACCEPTED);
    }

    //Delete Blogpost           (Requires USER -> USER must match owner of blogpost, or must be ADMIN)
    @DeleteMapping("/deletepost/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteBlogpost(@PathVariable("id")Long id, @AuthenticationPrincipal Jwt jwt){
        String username = jwt.getClaimAsString("preferred_username");
        boolean admin = verifyAdmin(jwt);
        blogpostService.deleteBlogpostByID(id, username, admin);
        return new ResponseEntity<>(("Blog post w/ ID : " + id + " | Status: Deleted"), HttpStatus.OK);
    }

    //ADMIN ENDPOINTS

    //Get Number of Blogposts   (Requires ADMIN role)
    @GetMapping("/count")
    @ResponseBody
    public ResponseEntity<String> getBlogpostCount(){
        Long numOfBlogposts = blogpostService.getNumberOfBlogposts();
        return new ResponseEntity<>(("Total number of blog posts : "+ numOfBlogposts), HttpStatus.OK);
    }


    private boolean verifyAdmin(Jwt jwt){
        boolean isAdmin = false;
        Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");
        if (resourceAccess.containsKey(securityResourceId)){
            Map<String, Object> resourceData = (Map<String, Object>) resourceAccess.get(securityResourceId);
            if (resourceData.containsKey("roles")){
                ArrayList<String> roles = (ArrayList<String>) resourceData.get("roles");
                if (roles.contains("admin")){
                    isAdmin = true;
                }
            }
        }
        return isAdmin;
    }

}
