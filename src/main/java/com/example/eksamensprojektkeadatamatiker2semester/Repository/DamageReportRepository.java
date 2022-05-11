package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.DamageReport;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DamageReportRepository {
    Connection connection;
    public DamageReportRepository(){
        connection = ConnectionManager.getConnection();
    }
/*
    public DamageReport addDamageReport(DamageReport damageReport){

        final String SQL_ADD_QUERY = "INSERT INTO DamageReport(DamageReportID,)";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);

        } catch (SQLException e) {
            System.out.println("Could not add a Damage Report");
        }

    }

 */
}
