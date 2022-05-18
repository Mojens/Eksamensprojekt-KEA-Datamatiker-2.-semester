package com.example.eksamensprojektkeadatamatiker2semester.Model;

public class Dashboard {
  private Car car;
  private Lease lease;

  public Dashboard(Car car, Lease lease) {
    this.car = car;
    this.lease = lease;
  }

  public Dashboard() {
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Lease getLease() {
    return lease;
  }

  public void setLease(Lease lease) {
    this.lease = lease;
  }
}
