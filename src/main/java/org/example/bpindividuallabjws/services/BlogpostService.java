package org.example.bpindividuallabjws.services;

import org.example.bpindividuallabjws.entities.Blogpost;
import org.example.bpindividuallabjws.exceptions.BlankFieldException;
import org.example.bpindividuallabjws.exceptions.EmptyObjectException;
import org.example.bpindividuallabjws.exceptions.InvalidUserException;
import org.example.bpindividuallabjws.exceptions.ResourceNotFoundException;
import org.example.bpindividuallabjws.repositories.BlogpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogpostService implements BlogpostServiceInterface{

        private final BlogpostRepository blogpostRepository;

        @Autowired
        public BlogpostService(BlogpostRepository blogpostRepository){
                this.blogpostRepository = blogpostRepository;
        }

        @Override
        public List<Blogpost> getAllBlogposts() {
                return blogpostRepository.findAll();
        }

        @Override
        public Blogpost getSpecificBlogpost(Long id) {
                Optional<Blogpost> retrievedBlogpost = blogpostRepository.findById(id);
                if (retrievedBlogpost.isPresent()){
                        return retrievedBlogpost.get();
                } else {
                        throw new ResourceNotFoundException("Blogpost", "ID", id);
                }
        }

        @Override
        public Blogpost createBlogpost(Blogpost blogpost) {
                Blogpost blogpostToSave;
                if (blogpost.getContent() == null){
                        throw new EmptyObjectException("Blogpost", "content");
                } else {
                        if (blogpost.getContent().isBlank() || blogpost.getContent().isEmpty()){
                                blogpostToSave = blogpostRepository.save(blogpost);
                                System.out.println("Empty blogpost with ID: "+ blogpostToSave.getId() + ", created by "+ blogpostToSave.getCreator());
                                throw new BlankFieldException("blogpost", "content", "ID", blogpost.getId());
                        }
                        blogpostToSave = blogpostRepository.save(blogpost);
                        System.out.println("Blogpost with ID: "+ blogpostToSave.getId() + ", created by "+ blogpostToSave.getCreator());
                        return blogpostToSave;
                }
        }

        @Override
        public Blogpost updateBlogpost(Blogpost blogpost, String username) {
                Optional<Blogpost> blogpostToUpdate = blogpostRepository.findById(blogpost.getId());


                if (blogpostToUpdate.isEmpty()){
                        throw new ResourceNotFoundException("Blogpost", "ID", blogpost.getId());
                } else if (!blogpostToUpdate.get().getCreator().equals(username)) {
                        throw new InvalidUserException(username, "EDIT/UPDATE", "Blogpost");
                } else {
                        blogpostToUpdate.get().setContent(blogpost.getContent());
                        System.out.println("Blogpost with ID: "+ blogpostToUpdate.get().getId() + ", updated by "+ username);
                        return blogpostRepository.save(blogpostToUpdate.get());
                }
        }

        @Override
        public void deleteBlogpostByID(Long id, String username, boolean admin) {
                Optional<Blogpost> blogpostToDelete = blogpostRepository.findById(id);
                if (blogpostToDelete.isEmpty()){
                        throw new ResourceNotFoundException("Blogpost", "ID", id);
                } else if (blogpostToDelete.get().getCreator().equals(username) || admin){
                        blogpostRepository.deleteById(id);
                        System.out.println("Blogpost with ID: "+ blogpostToDelete.get().getId() + ", deleted by "+ (admin? "ADMIN["+username: "OWNER["+username)+"]");
                } else {
                        throw new InvalidUserException(username, "DELETE", "Blogpost by" + blogpostToDelete.get().getCreator());
                }
        }

        @Override
        public Long getNumberOfBlogposts() {
                return blogpostRepository.count();
        }
}
