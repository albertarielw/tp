package seedu.address.model.task;


import java.util.Arrays;
import java.util.Optional;

/**
 * Represents the category of a task.
 */
public class TaskCategory {
    public static final String MESSAGE_CONSTRAINTS =
            "Category must be one of the following: " + TaskCategoryType.getValidTaskCategories()
            + " and level must be non-negative integer";
    private final int level;
    private final TaskCategoryType taskCategoryType;

    /**
     * Constructor for TaskCategory
     */
    public TaskCategory(int level, TaskCategoryType taskCategoryType) {
        this.level = level;
        this.taskCategoryType = taskCategoryType;
    }

    /**
     * Returns the category of a task.
     *
     * @return The task category.
     */
    public TaskCategoryType getTaskCategoryType() {
        return this.taskCategoryType;
    }

    /**
     * Returns the level of a task.
     *
     * @return The task level.
     */
    public int getLevel() {
        return this.level;
    }


    /**
     * Returns hashcode of the current object
     *
     * @return Hashcode of the object.
     */
    @Override
    public int hashCode() {
        return taskCategoryType.hashCode();
    }


    /**
     * Returns the string representation of the task category.
     *
     * @return Task priority.
     */
    @Override
    public String toString() {
        return taskCategoryType.name();
    }

    /**
     * Returns true if task category level is valid.
     *
     * @param test int to test.
     * @return Whether the int is a valid task category level.
     */
    public static boolean isValidLevel(int test) {
        return test >= 0;
    }

    /**
     * Returns true if taskCategoryName is valid.
     *
     * @param taskCategoryName String to test.
     * @return Whether the string is a valid taskCategoryName.
     */
    public static boolean isValidTaskCategoryName(String taskCategoryName) {
        return getFromString(taskCategoryName).isPresent();
    }

    /**
     * Returns true if it is a valid task category is valid.
     *
     * @param taskCategoryName String to test.
     * @param level int to test
     * @return Whether the task category is a valid.
     */
    public static boolean isValidTaskCategory(int level, String taskCategoryName) {
        return isValidLevel(level) && isValidTaskCategoryName(taskCategoryName);
    }

    /**
     * Looks up a {@code enum TaskCategoryType} from a given string.
     *
     * @param taskCategoryName string to test.
     * @return Empty if {@code taskCategoryName} is not a valid TaskCategoryType,
     * else the corresponding {@code PriorityEnum}.
     */
    public static Optional<TaskCategoryType> getFromString(String taskCategoryName) {
        return Arrays.stream(TaskCategoryType.values())
                .filter(taskCategoryType -> taskCategoryType.name().equals(taskCategoryName))
                .findFirst();
    }

    /**
     * Compares another object with the Category object.
     *
     * @param other The other object to be compared to.
     * @return If the two objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskCategory // instanceof handles nulls
                && ((TaskCategory) other).level == this.level
                && ((TaskCategory) other).taskCategoryType.equals(this.taskCategoryType));
    }

}
