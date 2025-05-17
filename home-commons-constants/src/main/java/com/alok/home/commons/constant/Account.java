package com.alok.home.commons.constant;

public enum Account {
    ODION("Odion"),
    SAVING("Saving"),
    SBI_MAX_GAIN("SBI Max Gain"),
    INTEREST("Interest"),
    MISC("Misc"),
    ADARSH("Adarsh"),
    INTEREST_ADARSH("Interest_Adarsh"),
    OPENING_BALANCE("Opening Balance"),
    MISC_ADARSH("Misc_Adarsh"),
    BOB_ADVANTAGE("BoB Advantage"),
    JYOTHI("Jyothi"),
    INTEREST_JYOTHI("Interest_Jyothi"),
    MISC_JYOTHI("Misc_Jyothi"),
    LIC_HFL("LIC-HFL"),
    HDFC_HFL("HDFC-HFL");

    private String name;

    Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Account valueOfOrDefault(String myValue) {
        for (Account type : Account.class.getEnumConstants()) {
            if (type.toString().equals(myValue)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Account type not found: " + myValue);
    }
}