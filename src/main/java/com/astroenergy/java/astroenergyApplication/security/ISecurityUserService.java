package com.astroenergy.java.astroenergyApplication.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
