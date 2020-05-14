package com.agh.introwertycznelosie.data;

import java.util.Comparator;

public class SortByYearAndSemesterDesc implements Comparator<Recruitment> {
    public int compare(Recruitment earlier, Recruitment later) {
        return (int)(Math.signum((later.getYear() + 0.5 * later.getSemester().ordinal()) -
                (earlier.getYear() + 0.5 * earlier.getSemester().ordinal())));
    }
}