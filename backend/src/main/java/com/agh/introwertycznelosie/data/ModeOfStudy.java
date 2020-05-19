package com.agh.introwertycznelosie.data;

public enum ModeOfStudy {

    fullTime, partTime;

    public static ModeOfStudy fromString(String mode)
    {
        switch (mode)
        {
            case "fullTime":
                return fullTime;
            case "partTime":
                return partTime;
            default:
                throw new IllegalArgumentException();
        }
    }

}