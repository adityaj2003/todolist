package model;

import java.util.Date;

/**
 * @author Kevin Kania
 * @author Priyansh Nayak
 * 
 * This class handles the details of a Task in the to do list
 */
public class Task {
	/**
	 *  Name of the Task
	 */
	private String name;
	/**
	 *  Description of the Task
	 */
	private String description;
	/**
	 *  Deadline for the Task
	 */
	private Date deadline;
	/**
	 *  Priority of the Task
	 */
	private String priority;
	/**
	 *  Whether the Task has been completed
	 */
	private boolean isComplete;
	/**
	 *  Time of creation of the Task
	 */
	public Double timeCreated;
	/**
	 *  String representation of the Date
	 */
	private String strdate;

	/**
	 * The constructor creates a Task object with the properties passed to it.
	 * 
	 * @param description Description of the Task
	 * @param deadline    Deadline for the Task
	 * @param priority    Priority of the Task
	 * @param name        Name of the Task
	 * @param strdate     String representation of the Date
	 * @param timeCreated Time of creation of the Task
	 */
	public Task(String description, Date deadline, String priority, String name, String strdate, Double timeCreated) {
		this.description = description;
		this.deadline = deadline;
		this.isComplete = false;
		this.name = name;
		this.strdate = strdate;
		this.priority = priority;
		this.timeCreated = timeCreated;
	}

	/**
	 * Returns the name of the Task
	 * 
	 * @return Name of the Task
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Marks the Task as completed.
	 */
	public void setComplete() {
		this.isComplete = true;
	}

	/**
	 * Marks the Task as incomplete.
	 */
	public void setIncomplete() {
		this.isComplete = false;
	}

	/**
	 * Returns whether the Task has been completed or not.
	 * 
	 * @return
	 */
	public boolean isComplete() {
		return this.isComplete;
	}

	/**
	 * Returns the Description of the Task.
	 * 
	 * @return Description of the Task
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Updates the Description of the Task.
	 * 
	 * @param description New Description for the Task
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the Deadline of the Task.
	 * 
	 * @return Deadline of the Task
	 */
	public Date getDeadline() {
		return this.deadline;
	}

	/**
	 * Updates the Deadline of the Task.
	 * 
	 * @param deadline of the task
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * Returns the priority of the Task.
	 * 
	 * @return priority of the Task
	 */
	public String getPriority() {
		return this.priority;
	}
	
	/**
	 * Updates the priority of the task
	 * 
	 * @param newPriority priority of the Task
	 */
	public void setPriority(String newPriority) {
		this.priority = newPriority;
	}

	/**
	 * Updates the Name of the Task
	 * 
	 * @param newName new Name for the Task
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns the String representation of the Date stored.
	 * 
	 * @return strdate of the Task
	 */
	public String getDateString() {
		return this.strdate;
	}

}