package org.example.bpindividuallabjws.services;

import org.example.bpindividuallabjws.entities.Blogpost;
import org.example.bpindividuallabjws.repositories.BlogpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                return null;
        }

        @Override
        public Blogpost createBlogpost(Blogpost blogpost) {
                return null;
        }

        @Override
        public Blogpost updateBlogpost(Blogpost blogpost) {
                return null;
        }

        @Override
        public void deleteBlogpostByID(Long id) {

        }

        @Override
        public Long getNumberOfBlogposts() {
                return 0L;
        }
}
