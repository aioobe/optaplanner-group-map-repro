package testschedules;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Schedule schedule = new Schedule(List.of(new Lecture(0), new Lecture(1)), List.of(0, 1));
        SolverFactory<Object> solverFactory = SolverFactory.createFromXmlResource("scheduling-config.xml");
        Solver<Object> solver = solverFactory.buildSolver();
        solver.solve(schedule);
    }
}
