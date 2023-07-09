package com.nat.nat.rules;

import java.util.HashMap;
import java.util.Map;


public class Degree {
    private static final Map<Grade, String> degreeMap = new HashMap<>();

    static {
        degreeMap.put(Grade.B4, "bachelor");
        degreeMap.put(Grade.M1, "master");
        degreeMap.put(Grade.M2, "master");
        degreeMap.put(Grade.D3, "doctor");
        degreeMap.put(Grade.D5, "doctor");
    }

    public static String getDegree(Grade grade) {
        return degreeMap.get(grade);
    }
}
