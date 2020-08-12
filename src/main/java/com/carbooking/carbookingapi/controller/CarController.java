package com.carbooking.carbookingapi.controller;

import java.util.List;

import com.carbooking.carbookingapi.model.rest.request.CarRequest;
import com.carbooking.carbookingapi.model.rest.response.CarResponse;
import com.carbooking.carbookingapi.service.CarBookingService;
import com.carbooking.carbookingapi.service.CarManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/cars")
public class CarController extends CarControllerValidator {

  @Autowired
  private CarManagementService carManagementService;

  @Autowired
  private CarBookingService carBookingService;

  @GetMapping(path = "")
  public List<CarResponse> getAllCars() throws Exception {
    List<CarResponse> response = carManagementService.retrieveCar();
    return response;
  }

  @GetMapping(path = "/{id}")
  public Object getCar(@PathVariable int id) throws Exception {
    CarResponse response = carManagementService.retrieveCar(id);
    return response;
  }

  @PostMapping(path = "")
  public Object postCar(@RequestBody CarRequest request) throws Exception {
    postCarValidation(request);
    CarResponse response = carManagementService.createCar(request);
    return response;
  }

  @PutMapping(path = "/{id}")
  public Object putCar(@PathVariable int id, @RequestBody CarRequest request) throws Exception {
    putCarValidation(request);
    CarResponse response = carManagementService.updateCar(id, request);
    return response;
  }

  @PutMapping(path = "/{id}/book")
  public Object bookCar(@PathVariable int id) throws Exception {
    CarResponse response = carBookingService.bookCar(id);
    return response;
  }

  @DeleteMapping(path = "/{id}")
  public Object deleteCar(@PathVariable int id) throws Exception {
    CarResponse response = carManagementService.removeCar(id);
    return response;
  }
}
