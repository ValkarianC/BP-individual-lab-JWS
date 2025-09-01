package org.example.bpindividuallabjws.services;

import org.example.bpindividuallabjws.entities.Blogpost;
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
                return retrievedBlogpost.get();
        }

        @Override
        public Blogpost createBlogpost(Blogpost blogpost) {
                return blogpostRepository.save(blogpost);
        }

        @Override
        public Blogpost updateBlogpost(Blogpost blogpost) {
                return blogpostRepository.save(blogpost);
        }

        @Override
        public void deleteBlogpostByID(Long id) {
                blogpostRepository.deleteById(id);
        }

        @Override
        public Long getNumberOfBlogposts() {
                return blogpostRepository.count();
        }
}
