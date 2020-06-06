package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.response.UserDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private MongoOperations mongoOperations;

    private static final Logger LOGGER = LoggerFactory.getLogger( UserService.class );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = new Query(Criteria.where("email_id").is(username).and("is_verified").is(true));
        List<com.java.taskManager.model.UserDetails> user = mongoOperations.find(query, com.java.taskManager.model.UserDetails.class, "user_details");

        if (CollectionUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
        return new User(user.get(0).getEmailId(), user.get(0).getPassword(), authorities);
    }

    public UserDetailResponse getUserDetails(String emailId) throws Exception {
        UserDetailResponse user = new UserDetailResponse();
        try{
            LOGGER.info("fetching user details for - "+emailId);
            Query query = new Query(Criteria.where("email_id").is(emailId));
            List<com.java.taskManager.model.UserDetails> userDetails = mongoOperations.find(query, com.java.taskManager.model.UserDetails.class,"user_details");
            if(userDetails.size()==1) {
                user.setFirstName(userDetails.get(0).getFirstName());
                user.setLastName(userDetails.get(0).getLastName());
                user.setStatus(Constants.SUCCESS);
            } else if (CollectionUtils.isEmpty(userDetails)) {
                throw new UsernameNotFoundException("User not found");
            }
        }
             catch(Exception exception){
                LOGGER.error("Error in fetching user "+exception);
                throw new Exception(exception.getMessage());
            }
        return user;
        }
    }
