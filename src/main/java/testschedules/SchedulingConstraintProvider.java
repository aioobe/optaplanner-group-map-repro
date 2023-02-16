package testschedules;

import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintCollectors;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.DefaultConstraintJustification;

import java.util.List;

import static org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore.ONE_HARD;

public class SchedulingConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory cf) {
        return new Constraint[] {
                unscheduledLectureConstraint(cf),  // <--- Comment out either....
                groupByConstraint(cf)              // <--- ...of these and there's no crash.
        };
    }

    private Constraint unscheduledLectureConstraint(ConstraintFactory cf) {
        return cf.forEachIncludingNullVars(Lecture.class)
                .filter(l1 -> !l1.isScheduled())
                .penalize(ONE_HARD)
                .justifyWith((l, s) -> DefaultConstraintJustification.of(s, l))
                .asConstraint("unscheduled-lecture");
    }

    private Constraint groupByConstraint(ConstraintFactory cf) {
        return cf.forEachIncludingNullVars(Lecture.class)
                .filter(Lecture::isScheduled)
                .flattenLast(l -> List.of(LectureAndWeek.of(l, 1)))
                .groupBy(LectureAndWeek::getWeek, ConstraintCollectors.toSet(LectureAndWeek::getLecture))
                .groupBy((week, lectures) -> lectures, ConstraintCollectors.toSet((week, ls) -> 0))  // <-------- Comment out this line, and there's no crash.
                .penalize(ONE_HARD)
                .justifyWith((o1, o2, s) -> DefaultConstraintJustification.of(s, o1))
                .asConstraint("group-by-constraint");
    }
}
