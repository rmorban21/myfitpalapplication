package com.decadev.entities;

public enum GymAccess {
    HOME_GYM_NO_WEIGHTS("Home gym with no weights: Ideal for those focusing on bodyweight exercises, yoga, pilates, or " +
            "any fitness routine that doesn't require weights. Workouts are designed to be done in the comfort of your " +
            "home with minimal equipemnt, emphasizing flexibility, balance and endurance."),
    HOME_GYM_WITH_WEIGHTS("Home gym with weights: Suitable for individuals who prefer the convenience of working out at " +
            "home and have access to basic equipment or weights."),
    FULL_GYM_ACCESS("Full gym access: Designed for those with access to a commmercial or fully equipped gym. Workouts " +
            "include a wide range of equipment and facilities, including cardio machines, free weights, resistance " +
            "machines, and specialty fitness classes. This is perfect for users seeking a comprehensive fitness " +
            "experience and variety in their workouts.");
    private final String gymAccess;
    GymAccess (String gymAccess) {
        this.gymAccess=gymAccess;
    }
    @Override
    public String toString(){
        return this.gymAccess;
    }
}
