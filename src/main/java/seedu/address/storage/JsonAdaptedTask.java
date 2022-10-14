package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityEnum;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskName;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    private final String taskName;
    private final String description;
    private final String priority;
    private final JsonAdaptedTaskCategory taskCategory;
    private final String deadline;
    private final JsonAdaptedPerson person;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
                           @JsonProperty("description") String description,
                           @JsonProperty("priority") String priority,
                           @JsonProperty("taskCategory") JsonAdaptedTaskCategory taskCategory,
                           @JsonProperty("deadline") String deadline,
                           @JsonProperty("person") JsonAdaptedPerson person,
                           @JsonProperty("status") String status) {
        this.taskName = taskName;
        this.description = description;
        this.priority = priority;
        this.taskCategory = taskCategory;
        this.deadline = deadline;
        this.person = person;
        this.status = status;
    }

    /**
     * Converts a given {@code task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getName().getTaskName();
        description = source.getDescription().getTaskDescription();
        priority = source.getPriority().toString();
        taskCategory = new JsonAdaptedTaskCategory(source.getCategory());
        deadline = source.getDeadline().toString();
        person = new JsonAdaptedPerson(source.getPerson());
        status = Task.convertIsDoneFromBooleanToString(source.isDone());
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidTaskName(taskName)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(taskName);

        if (taskCategory == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskCategory.class.getSimpleName()));
        }
        if (!TaskCategory.isValidTaskCategory(taskCategory.getLevel(), taskCategory.toString())) {
            throw new IllegalValueException(TaskCategory.MESSAGE_CONSTRAINTS);
        }
        final TaskCategory modelTaskCategory = taskCategory.toModelType();

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidTaskDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!PriorityEnum.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(PriorityEnum.getFromString(priority).get());

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDeadline.class.getSimpleName()));
        }
        final TaskDeadline modelTaskDeadline = new TaskDeadline(LocalDate.parse(deadline));

        if (person == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Person.class.getSimpleName()));
        }
        final Person modelPerson = person.toModelType();

        if (person == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Boolean.class.getSimpleName()));
        }
        final boolean modelStatus = Task.convertIsDoneFromStringToBoolean(status);

        return new Task(modelName, modelDescription, modelPriority, modelTaskCategory,
                modelTaskDeadline, modelPerson, modelStatus);
    }

}
