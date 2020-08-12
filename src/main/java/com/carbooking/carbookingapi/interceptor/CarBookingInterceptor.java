package com.carbooking.carbookingapi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CarBookingInterceptor implements HandlerInterceptor {

  private final String SECRET = "carbooking";

  @Override
  public boolean preHandle(
    HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    System.out.println("Pre handle method is calling.");

    // Skip CORS pre-flight
    if (request.getMethod().equals("OPTIONS")) {
      return true;
    }

    if (request.getHeader("Authorization") == null) {
      return false;
    } else {
      // Header: Authorization: Bearer <jwt>
      String token = request.getHeader("Authorization").split(" ")[1];
      try {
        DecodedJWT jwt = JWT.decode(token);
        String id = jwt.getClaim("id").asString();
        String name = jwt.getClaim("name").asString();
        String role = jwt.getClaim("role").asString();

        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
          .withClaim("id", id)
          .withClaim("name", name)
          .withClaim("role", role)
          .build();
        verifier.verify(token);
        return true;
      } catch (JWTDecodeException e) {
        return false;
      } catch (JWTVerificationException e) {
        return false;
      }
    }
  }

  @Override
  public void postHandle(
    HttpServletRequest request, HttpServletResponse response, Object handler,
    ModelAndView modelAndView)
    throws Exception {
    System.out.println("Post handle method is calling.");
  }

  @Override
  public void afterCompletion(
    HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
    throws Exception {
    System.out.println("Request and Response is completed.");
  }
}