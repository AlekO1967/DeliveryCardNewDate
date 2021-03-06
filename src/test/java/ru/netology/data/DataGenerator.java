package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String forwardDate(int plusDays) {
        LocalDate today = LocalDate.now();
        LocalDate newDate = today.plusDays(plusDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(newDate);
    }


    public static class Registration {
        private Registration() {
        }

        public static RegistrationByCustomerInfo generateByInfo(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationByCustomerInfo(
                    faker.address().city(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.phoneNumber().cellPhone());
        }

        public static String generateInvaldCity(String locale) {
            Faker faker = new Faker(new Locale("en"));
            return new String (
                    faker.address().city());
        }
    }
}