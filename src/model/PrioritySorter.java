package model;

import java.util.Comparator;

/**
 * A Comparator class that helps compare two Task objects by Deadline.
 */
public class PrioritySorter implements Comparator<Task> {

	@Override
	public int compare(Task task1, Task task2) {
		String p1 = task1.getPriority();
		String p2 = task2.getPriority();
		
		return getPriorityInteger(p1).compareTo(getPriorityInteger(p2));
	}
	
	private Integer getPriorityInteger(String priority) {
		if (priority.toUpperCase().equals("HIGH"))
			return 1;
		if (priority.toUpperCase().equals("LOW"))
			return 3;
		return 2;
	}
}
