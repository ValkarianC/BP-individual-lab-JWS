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
                if (blogpost.getContent() == null){
                        throw new EmptyObjectException("Blogpost", "content");
                } else {
                        if (blogpost.getCreator().isBlank() || blogpost.getContent().isEmpty()){
                                blogpostRepository.save(blogpost);
                                throw new BlankFieldException("blogpost", "content", "ID", blogpost.getId());
                        }
                        return blogpostRepository.save(blogpost);
                }
        }

        @Override
        public Blogpost updateBlogpost(Blogpost blogpost) {
                Optional<Blogpost> blogpostToUpdate = blogpostRepository.findById(blogpost.getId());
                if (blogpostToUpdate.isPresent()){
                        return blogpostRepository.save(blogpost);
                } else {
                        throw new ResourceNotFoundException("Blogpost", "ID", blogpost.getId());
                }
        }

        @Override
        public void deleteBlogpostByID(Long id) {
                Optional<Blogpost> blogpostToDelete = blogpostRepository.findById(id);
                if (blogpostToDelete.isPresent()){
                        blogpostRepository.deleteById(id);
                } else {
                        throw new ResourceNotFoundException("Blogpost", "ID", id);
                }
        }

        @Override
        public Long getNumberOfBlogposts() {
                return blogpostRepository.count();
        }
}
