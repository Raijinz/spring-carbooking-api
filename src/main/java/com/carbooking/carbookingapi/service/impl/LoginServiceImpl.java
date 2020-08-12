package com.carbooking.carbookingapi.service.impl;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.carbooking.carbookingapi.dao.UserDao;
import com.carbooking.carbookingapi.domain.User;
import com.carbooking.carbookingapi.model.rest.request.LoginRequest;
import com.carbooking.carbookingapi.model.rest.response.LoginResponse;
import com.carbooking.carbookingapi.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
  
  private final String SECRET = "carbooking";
  private final long EXP_TIME = 60 * 60 * 1000;

  @Autowired
  private UserDao userDao;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public LoginResponse login(LoginRequest request) throws Exception {
    LoginResponse response = new LoginResponse();
    bCryptPasswordEncoder = new BCryptPasswordEncoder();
    try {
      User user = userDao.findByName(request.getUsername());
      if (bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
        response.setSuccess(true);
        response.setRole(user.getRole());
        response.setJwtToken(signJwtToken(user));
      } else {
        response.setSuccess(false);
        response.setJwtToken("");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  private String signJwtToken(User user) {
    String token = "";
    try {
      Date now = new Date();
      long exp = now.getTime() + EXP_TIME;

      Algorithm algorithm = Algorithm.HMAC256(SECRET);
      token = JWT.create()
        .withClaim("id", user.getId())
        .withClaim("name", user.getName())
        .withClaim("role", user.getRole())
        .withExpiresAt(new Date(exp))
        .sign(algorithm);
    } catch (JWTCreationException e) {
      e.printStackTrace();
    }
    return token;
  }

}