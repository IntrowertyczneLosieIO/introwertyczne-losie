package com.agh.introwertycznelosie.data;

public enum ExamType
{
    oral, written, atComputer;

    public static ExamType fromString(String mode)
    {
        switch (mode)
        {
            case "oral":
                return oral;
            case "written":
                return written;
            case "atComputer":
                return atComputer;
            default:
                throw new IllegalArgumentException();
        }
    }

}
