package model;

import java.util.Comparator;

/**
 * A Comparator class that helps compare two Task objects by Deadline.
 */
public class DeadlineSorter implements Comparator<Task> {

	@Override
	public int compare(Task task1, Task task2) {
		return task1.getDeadline().compareTo(task2.getDeadline());
	}

}
