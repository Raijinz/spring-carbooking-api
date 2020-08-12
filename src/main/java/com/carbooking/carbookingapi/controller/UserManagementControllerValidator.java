package com.carbooking.carbookingapi.controller;

import com.carbooking.carbookingapi.constant.ExceptionConstant;
import com.carbooking.carbookingapi.model.rest.request.LoginRequest;
import com.carbooking.carbookingapi.model.rest.request.UserRequest;
import com.carbooking.carbookingapi.util.ObjectValidatorUtils;

import org.springframework.beans.factory.annotation.Autowired;

public class UserManagementControllerValidator {

  @Autowired
  private ObjectValidatorUtils validator;

  protected void postUserValidation(UserRequest request) throws Exception {
    if (!validator.validateMandatory(request.getName()) &&
        !validator.validateMandatory(request.getPassword()) &&
        !validator.validateMandatory(request.getRole())) {
      throw new Exception(ExceptionConstant.REQUIRED);
    }
  }

  protected void loginUserValidation(LoginRequest request) throws Exception {
    if (!validator.validateMandatory(request.getUsername()) &&
        !validator.validateMandatory(request.getPassword())) {
      throw new Exception(ExceptionConstant.REQUIRED);
    }
  }

  protected void putUserValidation(UserRequest request) throws Exception {
    if (!validator.validateMandatory(request.getName()) &&
        !validator.validateMandatory(request.getRole())) {
      throw new Exception(ExceptionConstant.REQUIRED);
    }
  }
}
