package testschedules;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Lecture {

    @PlanningId
    private int lectureId;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange", nullable = true)
    private Integer startTimeslot;

    public Lecture() {
    }

    public Lecture(int lectureId) {
        this.lectureId = lectureId;
    }

    public boolean isScheduled() {
        return startTimeslot != null;
    }
}
