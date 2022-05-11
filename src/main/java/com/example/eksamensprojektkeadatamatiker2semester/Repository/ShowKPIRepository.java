package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;

import java.sql.Connection;

public class ShowKPIRepository {
    Connection connection;

    public ShowKPIRepository(){
        connection = ConnectionManager.getConnection();
    }
}
