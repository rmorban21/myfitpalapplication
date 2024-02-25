package com.decadev.enums;

public enum BodyPart {
    CORE,  //relevant to all days regardless of goal, handled different for ARMS_CORE day for BUILD_MUSCLE
    LEGS, //relevant to LOWER_BODY day for WEIGHT_LOSS and LEG day for strength and for BUILD_MUSCLE
    BACK, //relevant to UPPER_BODY day for WEIGHT_LOSS, PULL day for strength and back_shoulders day for BUILD_MUSCLE
    CHEST,  //relevant to UPPER_BODY day for WEIGHT_LOSS loss, push day for strength and chest day for BUILD_MUSCLE
    SHOULDERS,  //relevant to UPPER_BODY day for WEIGHT_LOSS, push day for strength and back_shoulders day for BUILD_MUSCLE
    ARMS, //relevant to ARMS_CORE day for BUILD_MUSCLE
    CARDIO; //relevant to all days for WEIGHT_LOSS
}
