package com.carbooking.carbookingapi.controller;

import com.carbooking.carbookingapi.model.rest.request.CarRequest;
import com.carbooking.carbookingapi.util.ObjectValidatorUtils;

import org.springframework.beans.factory.annotation.Autowired;

public class CarControllerValidator {

  @Autowired
  private ObjectValidatorUtils validator;

  protected void postCarValidation(CarRequest request) throws Exception {
    if (!validator.validateMandatory(request.getBrandName()) &&
        !validator.validateMandatory(request.getModelName()) &&
        !validator.validateMandatory(String.valueOf(request.getPrice())) &&
        !validator.validateMandatory(String.valueOf(request.getReserved()))) {
      throw new Exception("Required field(s) is/are missing.");
    }
  }

  protected void putCarValidation(CarRequest request) throws Exception {
    if (!validator.validateMandatory(request.getBrandName()) &&
        !validator.validateMandatory(request.getModelName()) &&
        !validator.validateMandatory(String.valueOf(request.getPrice())) &&
        !validator.validateMandatory(String.valueOf(request.getReserved()))) {
      throw new Exception("Required field(s) is/are missing.");
    }
  }
}