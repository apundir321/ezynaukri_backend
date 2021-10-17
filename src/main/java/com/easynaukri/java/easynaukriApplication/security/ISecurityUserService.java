package com.easynaukri.java.easynaukriApplication.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
