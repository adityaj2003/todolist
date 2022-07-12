package model;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author Kevin Kankia
 * Represents Todolist that contains task objects in it.
 */
public class ToDoList {

	/**
	 *  Name of the ToDoList
	 */
	private String name;
	/**
	 *  Number of Tasks in the ToDoList
	 */
	private int count;
	/**
	 *  Number of Tasks completed in the ToDoList
	 */
	private int completed;

	/*
	 * HashMap pairing the time of creation of a Task to the Task created. Uses the
	 * fact that two Tasks can not be created at the same time to save the Tasks in
	 * a way such that the Controller can refer to the time of creation to refer to
	 * a specific Task without having to store the Task in it.
	 * 
	 */
	private LinkedHashMap<Double, Task> tasks;

	/**
	 * The constructor creates a new ToDoList with the name passed to it.
	 * Initializes the HashMap of Tasks in it.
	 * 
	 * @param name name of the ToDoList to be created
	 */
	public ToDoList(String name) {
		this.name = name;
		this.count = 0;
		this.completed = 0;
		this.tasks = new LinkedHashMap<>();
	}

	/**
	 * Returns the name of the ToDoList
	 * 
	 * @return name the name of the ToDoList
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the HashMap of all the Tasks inside this list.
	 * 
	 * @return HashMap time of creation -> Task
	 */
	public LinkedHashMap<Double, Task> getAllTasks() {
		return tasks;
	}

	/**
	 * Setter to change the name of the ToDoList.
	 * 
	 * @param name new name for the ToDoList
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the percentage of Tasks completed in the ToDoList.
	 * 
	 * @return int percentage of Tasks completed in this ToDoList
	 */
	public int getPercentCompleted() {
		if (this.count == 0)
			return 0;
		return (this.completed / this.count) * 100;
	}

	/**
	 * Adds a new Task with the passed properties in this ToDoList.
	 * 
	 * @param taskName        name for the new Task
	 * @param taskDescription Description for the new Task
	 * @param taskDeadline    Deadline for the new Task
	 * @param timeCreated     Time at which the new Task is created
	 * @param strdate         String representation of the date
	 * @param priority        Priority of the Task
	 */
	public void addTask(String taskName, String taskDescription, Date taskDeadline, Double timeCreated, String strdate,
			String priority) {
		tasks.put(timeCreated, new Task(taskDescription, taskDeadline, priority, taskName, strdate, timeCreated));
		count++;
	}

	/**
	 * Returns the Task created at timeCreated in this ToDoList Time of creation for
	 * each task is unique
	 * 
	 * @param timeCreated time of creation of task wanted
	 * @return Task created at timeCreated
	 */
	public Task getTask(Double timeCreated) {
		return tasks.get(timeCreated);
	}

	/**
	 * Deletes the Task created at timeCreated in this ToDoList.
	 * 
	 * @param timeCreated Time of creation of the Task
	 */
	public void removeTask(Double timeCreated) {
		tasks.remove(timeCreated);
		count--;
	}

	/**
	 * Marks the Task created at timeCreated as completed.
	 * 
	 * @param timeCreated time of creation of the ToDoList
	 */
	public void setTaskCompleted(Double timeCreated) {
		tasks.get(timeCreated).setComplete();
		completed++;
	}

	/**
	 * Marks the Task created at timeCreated as incomplete.
	 * 
	 * @param timeCreated time of creation of the ToDoList
	 */
	public void setTaskIncomplete(Double timeCreated) {
		tasks.get(timeCreated).setIncomplete();
		completed--;
	}
}
