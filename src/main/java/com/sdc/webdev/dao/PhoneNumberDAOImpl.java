package com.sdc.webdev.dao;

import com.sdc.webdev.model.PhoneNumber;
import com.sdc.webdev.util.DatabaseConfig;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumberDAOImpl implements PhoneNumberDAO {
    private DataSource dataSource = DatabaseConfig.getDataSource();

    @Override
    public void addPhoneNumber(PhoneNumber phoneNumber) {
        String sql = "INSERT INTO phone_book (phone_number, surname) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phoneNumber.getPhoneNumber());
            pstmt.setString(2, phoneNumber.getSurname());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding phone number", e);
        }
    }

    @Override
    public List<PhoneNumber> getAllPhoneNumbers() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        String sql = "SELECT * FROM phone_book";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                phoneNumbers.add(new PhoneNumber(rs.getString("phone_number"), rs.getString("surname")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving phone numbers", e);
        }
        return phoneNumbers;
    }

    @Override
    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        String sql = "UPDATE phone_book SET surname = ? WHERE phone_number = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phoneNumber.getSurname());
            pstmt.setString(2, phoneNumber.getPhoneNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating phone number", e);
        }
    }

    @Override
    public void deletePhoneNumber(String phoneNumber) {
        String sql = "DELETE FROM phone_book WHERE phone_number = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phoneNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting phone number", e);
        }
    }
}
