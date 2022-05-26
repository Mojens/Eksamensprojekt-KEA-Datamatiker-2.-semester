package com.example.eksamensprojektkeadatamatiker2semester.Model;

/* Lavet Af Simon */
public class CarsLeases {

  private int id;
  private int carID;
  private int leaseID;

  public CarsLeases(int id, int carID, int leaseID) {
    this.id = id;
    this.carID = carID;
    this.leaseID = leaseID;
  }

  public CarsLeases(int carID, int leaseID) {
    this.carID = carID;
    this.leaseID = leaseID;
  }

  public CarsLeases() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCarID() {
    return carID;
  }

  public void setCarID(int carID) {
    this.carID = carID;
  }

  public int getLeaseID() {
    return leaseID;
  }

  public void setLeaseID(int leaseID) {
    this.leaseID = leaseID;
  }
}
