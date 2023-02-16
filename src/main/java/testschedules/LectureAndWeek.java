package testschedules;

public class LectureAndWeek {

    private Lecture lecture;
    private int week;

    public LectureAndWeek(Lecture lecture, int week) {
        this.lecture = lecture;
        this.week = week;
    }

    static LectureAndWeek of(Lecture lecture, int week) {
        return new LectureAndWeek(lecture, week);
    }

    public Lecture getLecture() {
        return lecture;
    }

    public int getWeek() {
        return week;
    }

}
