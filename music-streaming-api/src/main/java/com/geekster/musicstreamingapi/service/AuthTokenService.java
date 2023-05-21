package com.geekster.musicstreamingapi.service;

import com.geekster.musicstreamingapi.model.AuthenticationToken;
import com.geekster.musicstreamingapi.repository.IAuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    @Autowired
    IAuthTokenRepository tokenRepository;
    public void saveToken(AuthenticationToken token) {
        tokenRepository.save(token);
    }

    public boolean authenticate(String email, String token) {

        if(token==null && email ==null)
            return false;

        AuthenticationToken authToken=tokenRepository.findFirstByToken(token);

        if(authToken==null)
            return false;

        //extracting email from the token
        String expectedEmail=authToken.getUser().getUserEmail();

        return expectedEmail.equals(email);
    }
}
