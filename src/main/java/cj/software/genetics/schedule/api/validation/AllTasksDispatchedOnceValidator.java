package cj.software.genetics.schedule.api.validation;

import cj.software.genetics.schedule.api.entity.Solution;
import cj.software.genetics.schedule.api.entity.SolutionPriority;
import cj.software.genetics.schedule.api.entity.Task;
import cj.software.genetics.schedule.api.entity.Worker;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

public class AllTasksDispatchedOnceValidator implements ConstraintValidator<AllTasksDispatchedOnce, Solution> {

    private record Coordinate(int workerIndex, int priorityValue, int slot) {

    }

    @Override
    public boolean isValid(Solution solution, ConstraintValidatorContext context) {
        boolean result = true;
        Map<Task, Coordinate> alreadyFound = new HashMap<>();
        List<Worker> workers = solution.getWorkers();
        int workerIndex = 0;
        for (Worker worker : workers) {
            SortedSet<SolutionPriority> priorities = worker.getPriorities();
            for (SolutionPriority priority : priorities) {
                int prioValue = priority.getValue();
                SortedMap<Integer, Task> tasks = priority.getTasks();
                for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                    Task task = entry.getValue();
                    Coordinate taskPosition = new Coordinate(workerIndex, prioValue, entry.getKey());
                    if (alreadyFound.containsKey(task)) {
                        result = false;
                        Coordinate otherPosition = alreadyFound.get(task);
                        invalidate(solution, task, taskPosition, otherPosition, context);
                    } else {
                        alreadyFound.put(task, taskPosition);
                    }
                }
            }
            workerIndex++;
        }
        return result;
    }

    private void invalidate(
            Solution solution,
            Task task,
            Coordinate taskPosition,
            Coordinate otherPosition,
            ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        String defaultMessage = context.getDefaultConstraintMessageTemplate();
        String formatted = String.format(defaultMessage,
                solution,
                task,
                taskPosition.workerIndex,
                taskPosition.priorityValue,
                taskPosition.slot,
                otherPosition.workerIndex,
                otherPosition.priorityValue,
                otherPosition.slot);
        context.buildConstraintViolationWithTemplate(formatted)
                .addPropertyNode(String.format("worker[%d]", taskPosition.workerIndex))
                .addPropertyNode(String.format("priority[%d]", taskPosition.priorityValue))
                .addConstraintViolation();
    }
}
