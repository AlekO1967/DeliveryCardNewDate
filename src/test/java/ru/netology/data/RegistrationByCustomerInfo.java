package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationByCustomerInfo {
    private String city;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public RegistrationByCustomerInfo(String city) {
    }
}
