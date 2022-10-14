package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskCategoryType;

/**
 * Jackson-friendly version of {@link TaskCategory}.
 */
public class JsonAdaptedTaskCategory {
    private final int level;

    private final String taskCategoryType;

    /**
     * Constructs a {@code JsonAdaptedTaskCategory} with the given {@code level, taskCategoryType}.
     */
    @JsonCreator
    public JsonAdaptedTaskCategory(int level, String taskCategoryType) {
        this.level = level;
        this.taskCategoryType = taskCategoryType;
    }

    /**
     * Converts a given {@code TaskCategory} into this class for Jackson use.
     */
    public JsonAdaptedTaskCategory(TaskCategory source) {
        level = source.getLevel();
        taskCategoryType = source.getTaskCategoryType().name();
    }

    @JsonValue
    public int getLevel() {
        return level;
    }

    @JsonValue
    public String getTaskCategoryType() {
        return taskCategoryType;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code TaskCategory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public TaskCategory toModelType() throws IllegalValueException {
        if (!TaskCategory.isValidTaskCategory(level, taskCategoryType)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new TaskCategory(level,
                TaskCategoryType.getFromString(taskCategoryType).orElse(TaskCategoryType.OTHERS));
    }

}

