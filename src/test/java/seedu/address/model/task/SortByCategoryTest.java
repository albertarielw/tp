package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class SortByCategoryTest {
    private static final TaskCategory testCat = new TaskCategory(TaskCategoryType.BACKEND);
    private static final TaskCategory testCat2 = new TaskCategory(TaskCategoryType.FRONTEND);
    private static final Description testDisc = new Description("Test");
    private static final Priority testPriority = new Priority(PriorityEnum.MEDIUM);
    private static final TaskDeadline testDeadline = new TaskDeadline(LocalDate.now());
    private static final Person testPerson = new Person(new Name("test"), new Phone("99999999"),
            new Email("test@gmail.com"), new Address("test"), new HashSet(), new ArrayList<>());
    public static final Task ALICE = new Task(new TaskName("A"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    public static final Task BENSON = new Task(new TaskName("B"),
            testDisc, testPriority, testCat2, testDeadline, testPerson, false);
    public static final Task CARL = new Task(new TaskName("C"),
            testDisc, testPriority, testCat, testDeadline, testPerson, false);
    private SortByCategory test = new SortByCategory();

    @Test
    public void sortCategoryTest() {
        assertEquals(-1, test.compare(ALICE, BENSON));
    }

    @Test
    public void sortTasksTest2() {
        assertEquals(0, test.compare(ALICE, CARL));
    }

}
