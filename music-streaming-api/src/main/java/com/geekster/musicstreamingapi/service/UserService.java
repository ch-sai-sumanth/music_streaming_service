package com.geekster.musicstreamingapi.service;

import com.geekster.musicstreamingapi.dto.SignInInput;
import com.geekster.musicstreamingapi.dto.SignInOutput;
import com.geekster.musicstreamingapi.dto.SignUpInput;
import com.geekster.musicstreamingapi.dto.SignUpOutput;
import com.geekster.musicstreamingapi.model.AuthenticationToken;
import com.geekster.musicstreamingapi.model.User;
import com.geekster.musicstreamingapi.repository.IAuthTokenRepository;
import com.geekster.musicstreamingapi.repository.IUserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {


    @Autowired
    IUserRepository userRepository;

    @Autowired
    AuthTokenService tokenService;

    @Autowired
    IAuthTokenRepository tokenRepository;
    public SignUpOutput signup(SignUpInput singupdto) {

        User user=userRepository.findFirstByUserEmail(singupdto.getEmail());

        if(user!=null)
        {
            throw new IllegalStateException("User already exist!.. Try sign in instead");
        }

        //encrypting user password
        String encryptedPassword=null;

        try
        {
            encryptedPassword=encryptPassword(singupdto.getPassword());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        singupdto.setPassword(encryptedPassword);

        //creating user from signUpInput
        User newUser=new User(singupdto.getId(),singupdto.getFirstName(),singupdto.getLastName(),singupdto.getPhoneNumber(),
                singupdto.getEmail(),singupdto.getPassword());

        userRepository.save(newUser);

        return new SignUpOutput("User registered","music player account created succesfully");
    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {

        MessageDigest md5=MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signin(SignInInput signInDto) {

        User user=userRepository.findFirstByUserEmail(signInDto.getUserEmail());
        if(user==null)
        {
            throw new IllegalStateException("user not found!.. sign up instead");
        }

        String encryptedPassword=null;
        try
        {
            encryptedPassword = encryptPassword(signInDto.getUserPassword());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        boolean isPasswordValid=encryptedPassword.equals(user.getUserPassword());

        if(!isPasswordValid)    //if password does not matches
        {
            throw new IllegalStateException("user invalid!!.. sign up instead");
        }

        //Token generation and savind in to repo
        AuthenticationToken token=new AuthenticationToken(user);
        tokenService.saveToken(token);

        //output response
        return new SignInOutput("Authentication succesfull!!",token.getToken());

    }
    public void updateUser(User user, String token) {
        User originalUser =tokenRepository.findFirstByToken(token).getUser();

        //passed data if not null then setting the parameters
        if(user.getUserFirstName()!=null)
            originalUser.setUserFirstName(user.getUserFirstName());
        if(user.getUserLastName()!=null)
            originalUser.setUserLastName(user.getUserLastName());
        if(user.getUserPassword()!=null)
        {
            String encryptedPassword=null;

            try
            {
                encryptedPassword=encryptPassword(user.getUserPassword());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            originalUser.setUserPassword(encryptedPassword);
        }

        if(user.getPhoneNumber()!=null)
        {
            Pattern p = Pattern.compile("\\d{2}-\\d{10}");
            Matcher m = p.matcher(user.getPhoneNumber());
            if( (m.find() && m.group().equals(user.getPhoneNumber()))){
                originalUser.setPhoneNumber(user.getPhoneNumber());

            }else{
                throw new IllegalStateException("Enter correct details");
            }
        }

        if(user.getUserEmail()!=null)
        {
            Pattern p = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}");

            Matcher m = p.matcher(user.getUserEmail());
            if( (m.find() && m.group().equals(user.getUserEmail()))){
                originalUser.setUserEmail(user.getUserEmail());

            }else{
                throw new IllegalStateException("Enter correct details");
            }
        }
       //-------------------------------------------------------------------------------

       //finally all the details are set to original user , now saving in to repo
        userRepository.save(originalUser);

    }
}
