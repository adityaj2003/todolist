package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Observable;


/**
 * @author Kevin Kankia
 * Acts as the main model of the Project. Uses objects of types Task and
 * ToDoList to store all of its data. Is the main point of contact for the
 * Controller. Also notifies the View of changes made in the data so View can
 * update itself.
 */
public class TDLModel extends Observable {

	/*
	 * To store the multiple ToDoLists while also giving the Controller the ability
	 * to point out which ToDoList is to be altered without having to store the
	 * ToDoLists.
	 */
	private HashMap<String, ToDoList> lists;

	/**
	 * Constructor for the Model.
	 */
	public TDLModel() {
		lists = new HashMap<>();
	}

	/**
	 * Returns all the ToDoLists that are currently stored in the Model.
	 * 
	 * @return HashMap of all lists and their names
	 */
	public HashMap<String, ToDoList> getAllLists() {
		return this.lists;
	}

	/**
	 * Returns the ToDoList that has the name passed.
	 * 
	 * @param listName name of the ToDoList to be returned
	 * @return ToDoList
	 */
	public ToDoList getList(String listName) {
		updateView();
		return lists.get(listName);
	}

	/**
	 * Creates a new ToDoList with the passed name
	 * 
	 * @param listName name of the ToDoList to be created
	 * @return true if newList gets created
	 */
	public boolean newList(String listName) {
		if (lists.containsKey(listName))
			return false;
		lists.put(listName, new ToDoList(listName));
		updateView();
		return true;
	}

	/**
	 * Deletes the list with the passed name.
	 * 
	 * @param listName
	 */
	public void removeList(String listName) {
		lists.remove(listName);
	}

	/**
	 * Updates the name of a ToDoList.
	 * 
	 * @param oldName current name of the ToDoList to be updated.
	 * @param newName new name of that ToDoList
	 */
	public void editList(String oldName, String newName) {
		ToDoList todoList = lists.remove(oldName);
		todoList.setName(newName);
		lists.put(newName, todoList);
		updateView();
	}

	/**
	 * Returns the Task created at timeCreated in the List with the name passed.
	 * Time of creation for each task is unique
	 * 
	 * @param listName    name of the list the task is in
	 * @param timeCreated time of creation of task wanted
	 * @return Task in listname created at timeCreated
	 */
	public Task getTask(String listName, Double timeCreated) {
		return lists.get(listName).getTask(timeCreated);
	}

	/**
	 * Updates the state of the Task created at timeCreated in the ToDoList with the
	 * name passed based on the parameters provided.
	 * 
	 * @param listName        Name of the ToDoList the Task is in
	 * @param timeCreated     Time of creation of the Task
	 * @param taskName        new Name for the Task
	 * @param taskDescription new Description for the Task
	 * @param taskDeadline    new Deadline for the Task
	 */
	public void editTask(String listName, Double timeCreated, String taskName, String taskDescription,
			Date taskDeadline, String taskPriority) {
		lists.get(listName).getTask(timeCreated).setName(taskName);
		lists.get(listName).getTask(timeCreated).setDescription(taskDescription);
		lists.get(listName).getTask(timeCreated).setPriority(taskPriority);
		lists.get(listName).getTask(timeCreated).setDeadline(taskDeadline);
		updateView();
	}

	/**
	 * Adds a new Task with the passed properties in the ToDoList with the name
	 * passed.
	 * 
	 * @param listName        name of the ToDoList in which the Task is to be added
	 * @param taskName        name for the new Task
	 * @param taskDescription Description for the new Task
	 * @param taskDeadline    Deadline for the new Task
	 * @param timeCreated     Time at which the new Task is created
	 * @param strdate         String representation of the date
	 * @param priority        Priority of the Task
	 */
	public void addTask(String listName, String taskName, String taskDescription, Date taskDeadline, Double timeCreated,
			String strdate, String priority) {
		lists.get(listName).addTask(taskName, taskDescription, taskDeadline, timeCreated, strdate, priority);
		updateView();
	}

	/**
	 * Deletes the Task created at timeCreated in the ToDoList with the name passed.
	 * 
	 * @param listName    Name of the ToDoList the Task is present in
	 * @param timeCreated Time of creation of the Task
	 */
	public void removeTask(String listName, Double timeCreated) {
		lists.get(listName).removeTask(timeCreated);
		updateView();
	}

	/**
	 * Marks the Task in listName ToDoList created at timeCreated as completed
	 * 
	 * @param listName    name of the ToDoList the Task is present in
	 * @param timeCreated time of creation of the ToDoList
	 */
	public void setTaskCompleted(String listName, Double timeCreated) {
		lists.get(listName).setTaskCompleted(timeCreated);
		updateView();
	}

	/**
	 * Notifies the view to update itself when the Model has changes.
	 */
	private void updateView() {
		setChanged();
		notifyObservers();
	}

}
