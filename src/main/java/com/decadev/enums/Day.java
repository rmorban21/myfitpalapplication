package com.decadev.enums;

//TODO: For Day, the comments suggest different sets of days for different fitness goals.
// Ensure that the application logic correctly handles these variations when generating workout plans.
public enum Day {
    BACK_AND_SHOULDERS, // specific to BUILDMUSCLE GOAL
    LEGS, //specific to BUILDMUSCLE and STRENGTH GOALS
    CHEST, // specific to BUILDMUSCLE GOAL
    ARMS_CORE, // specific to BUILDMUSCLE GOAL
    PUSH, //specific to STRENGTH  GOAL
    PULL, //specific to STRENGTH GOAL
    UPPER_BODY, //specific to WEIGHTLOSS GOAL
    LOWER_BODY; //specific to WEIGHTLOSS GOAL

}