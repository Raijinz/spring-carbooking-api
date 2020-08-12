package com.carbooking.carbookingapi.service;

import com.carbooking.carbookingapi.model.rest.request.LoginRequest;
import com.carbooking.carbookingapi.model.rest.response.LoginResponse;

public interface LoginService {
  
  public LoginResponse login(LoginRequest request) throws Exception;

}
