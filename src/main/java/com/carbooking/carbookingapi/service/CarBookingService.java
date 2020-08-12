package com.carbooking.carbookingapi.service;

import com.carbooking.carbookingapi.model.rest.response.CarResponse;

public interface CarBookingService {
  
  public CarResponse bookCar(int id) throws Exception;

}
