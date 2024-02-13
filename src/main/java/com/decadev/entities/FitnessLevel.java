package com.decadev.entities;

public enum FitnessLevel {
    BEGINNER("Beginner: Ideal for those just starting their fitness journey or returning after a long break. This " +
            "level focuses on building foundational strength, endurance and flexibility. Workouts are designed to " +
            "introduce basic movements and gradually increase in intensity."),
    INTERMEDIATE("Intermediate: Suitable for individuals with a solid fitness base looking to challenge themselves " +
            "further. This level introduces more complex exercises and increases the intensity and volume of workouts, " +
            "aiming for improved strength, endurance, and skill development."),
    ADVANCED("Advanced: Designed for those with a strong fitness background seeking to push their limits. Workouts at " +
            "this level include high-intensity training, complex movements, and longer duration challenges. Focuses " +
            "on enhancing peak performance, strength, and agility."),
    EXPERT("Expert: Reserved for highly experienced athletes and fitness enthusiasts. This level demands the highest " +
            "intensity and skill, incorporating elite training methodologies and challenges that require superior " +
            "strength, endurance and discipline. Emphasizes specialized goals and peak athletic performance.");
    private final String level;
    FitnessLevel(String level) {
        this.level = level;
    }
    @Override
    public String toString(){
        return this.level;
    }
}
