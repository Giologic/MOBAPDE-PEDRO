package com.giologic.pedrosystem10.dao;


import com.giologic.pedrosystem10.model.MySQLSSHConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by MarcDominic on 3/8/2016.
 */
public class StudentDao {
    public static Collection<String> getStudentNames(){
        Connection connection = MySQLSSHConnector.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM pedrodb.Student");
            resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                list.add(resultSet.getString(2));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
