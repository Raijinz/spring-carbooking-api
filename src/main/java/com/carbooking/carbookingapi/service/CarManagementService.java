package com.carbooking.carbookingapi.service;

import java.util.List;

import com.carbooking.carbookingapi.model.rest.request.CarRequest;
import com.carbooking.carbookingapi.model.rest.response.CarResponse;

public interface CarManagementService {
  
  public List<CarResponse> retrieveCar() throws Exception;

  public CarResponse retrieveCar(int id) throws Exception;

  public CarResponse createCar(CarRequest request) throws Exception;

  public CarResponse updateCar(int id, CarRequest request) throws Exception;

  public CarResponse removeCar(int id) throws Exception;

}
