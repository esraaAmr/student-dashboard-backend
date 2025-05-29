package com.example.rest.entity;

import com.example.rest.config.StudentProperties;

public final class AddressFormatter {

    private AddressFormatter() {
    }

    public static String formatAddress(StudentProperties.Address address) {
        return "[Continent: " + address.getContinent()
                + ", Country: " + address.getCountry()
                + ", Government: " + address.getGovernment()
                + ", City: " + address.getCity()
                + ", Street: " + address.getStreet()
                + ", Building Number: " + address.getBuildingNumber()
                + ", Floor: " + address.getFloor()
                + ", Apartment Number: " + address.getApartmentNumber()
                + "]";
    }
}

