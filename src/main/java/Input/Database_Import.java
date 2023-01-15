package Input;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Data.Heure;
import Data.Jour;

public class Database_Import {
    private Connection dbConnection;

    public Database_Import(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void storeData(Jour[] data) throws SQLException, ClassNotFoundException {
        String insertSQL = "INSERT INTO datas (date_gen, day_data, hours1_data, hours2_data, hours3_data, hours4_data, hours5_data, hours6_data, hours7_data, hours8_data, hours9_data, hours10_data, hours11_data, hours12_data, hours13_data, hours14_data, hours15_data, hours16_data, hours17_data, hours18_data, hours19_data, hours20_data, hours21_data, hours22_data, hours23_data, hours24_data, message_data) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertSQL);
        for (Jour jour : data) {
            preparedStatement.setString(1, jour.getGenerationFichier());
            preparedStatement.setDate(2, new java.sql.Date(jour.getDate().getTime()));
            //To take care of the hours array, we need to do the following
            Heure[] Arr24h = jour.getArr_24h();
            int i = 0;
            for (Heure heure : Arr24h) {
                try{
                    preparedStatement.setInt(i + 3, heure.getHvalue());
                } catch (NullPointerException e) {
                    preparedStatement.setInt(i + 3, 0);
                }
                i++;
            }

            preparedStatement.setString(27, jour.getMessage());

            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
    }
}