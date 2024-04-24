package com.sdc.webdev.dao;

import com.sdc.webdev.model.PhoneNumber;
import java.util.List;

public interface PhoneNumberDAO {
    void addPhoneNumber(PhoneNumber phoneNumber);
    List<PhoneNumber> getAllPhoneNumbers();
    void updatePhoneNumber(PhoneNumber phoneNumber);
    void deletePhoneNumber(String phoneNumber);
}
