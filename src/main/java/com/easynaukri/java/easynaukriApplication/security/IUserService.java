package com.easynaukri.java.easynaukriApplication.security;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.easynaukri.java.easynaukriApplication.dto.UserDto;
import com.easynaukri.java.easynaukriApplication.error.UserAlreadyExistException;
import com.easynaukri.java.easynaukriApplication.model.NewLocationToken;
import com.easynaukri.java.easynaukriApplication.model.PasswordResetToken;
import com.easynaukri.java.easynaukriApplication.model.RecruiterProfile;
import com.easynaukri.java.easynaukriApplication.model.User;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;
import com.easynaukri.java.easynaukriApplication.model.VerificationToken;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto,boolean isRecuiter) throws UserAlreadyExistException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    Optional<User> getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(User user) throws UnsupportedEncodingException;

    User updateUser2FA(boolean use2FA);
    
    public List<User> getUserByRoleName(String roleName)throws Exception;
    
    public List<RecruiterProfile> getUserByRoleParams(Map<String, Object> searchCriteria)throws Exception;
    
    public List<UserProfile> getUserProfilesByRoleParams(Map<String, Object> searchCriteria)throws Exception;

//    List<String> getUsersFromSessionRegistry();

//    NewLocationToken isNewLoginLocation(String username, String ip);

    String isValidNewLocationToken(String token);
    
    public User getRecruiter(String recruiterProfileId);

//    void addUserLocation(User user, String ip);
}
