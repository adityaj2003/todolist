package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.TDLModel;
import model.Task;
import model.ToDoList;

/**
 * @author Priyansh Nayak
 * @author Utkarsh Upadhyay
 * @author Aditya Jadhav
 * This class is the controller for the MVC architecture. It establishes contact
 * between the view and model, exchanging change of information both ways. It also
 * performs all the conversions related to the deadline from String to Date objects.
 * It also handles the saving and loading of data for the entire project.
 */
public class TDLController {
	
	/**
	 * This is an instance of the model passed by the view.
	 */
	private TDLModel model;
	
	/**
	 * Constructor for the controller class
	 * @param model Is the instance passed by the view
	 */
	public TDLController(TDLModel model) {
		this.model = model;
	}
	
	/**
	 * Attempts to add a new to-do list for the user.
	 * @param name Name of the List
	 * @return true if added, else false
	 */
	public boolean addList(String name) {
		return this.model.newList(name);
	}
	
	/**
	 * Adds a new task to the list of choice.
	 * @param listName The List that task needs to be added to
	 * @param taskName Name of the task.
	 * @param description Description of task, if provided
	 * @param time Deadline for the task, if provided
	 * @param timeCreated A Double that indicates the second at which a task is created
	 * @param priority Priority given to the task
	 */
	public void addTask(String listName, String taskName, String description, String time, Double timeCreated, String priority) {
		Date deadline = getDateObject(time);
		model.addTask(listName, taskName, description, deadline, timeCreated, time, priority);
	}
	
	/**
	 * Deletes a task from the list of choice.
	 * @param listName The List that task needs to be removed from
	 * @param timeCreated Double of when task was created
	 */
	public void deleteTask(String listName, Double timeCreated) {
		this.model.removeTask(listName, timeCreated);
	}
	
	/**
	 * Checks off a completed task
	 * @param listName The List that stores the task
	 * @param timeCreated Double of when task was created
	 */
	public void checkOffTask(String listName, Double timeCreated) {
		this.model.setTaskCompleted(listName, timeCreated);
	}
	
	/**
	 * Removes a list from multiple to-do lists
	 * @param name The list to remove
	 */
	public void deleteList(String name) {
		this.model.removeList(name);
	}
	
	/**
	 * Returns details of a to-do list to view
	 * @param name List whose details are required
	 * @return ToDoList object of the list
	 */
	public ToDoList getList(String name) {
		return this.model.getList(name);
	}
	
	/**
	 * Renames a list
	 * @param oldName Old name of the list
	 * @param newName New name of the list
	 */
	public void editList(String oldName, String newName) {
		model.editList(oldName, newName);
	}
	
	/**
	 * Edits details of a task
	 * @param listName List that houses the task
	 * @param timeCreated Time the task was created
	 * @param taskName Name of the task
	 * @param taskDescription New Description of the task
	 * @param time New Deadline of the task
	 * @param priority New Priority of the task
	 */
	public void editTask(String listName, Double timeCreated, String taskName, String taskDescription, String time,String priority) {
		Date deadline = getDateObject(time);
		model.editTask(listName, timeCreated, taskName, taskDescription, deadline,priority);
	}
	
	/**
	 * Returns details of all lists
	 * @return HashMap of all lists
	 */
	public HashMap<String, ToDoList> getAllLists() {
		return this.model.getAllLists();
	}
	
	/**
	 * Returns details of all tasks in a list
	 * @param listName List to access
	 * @return LinkedHashMap of all tasks
	 */
	public LinkedHashMap<Double, Task> getAllTasks(String listName) {
		return getList(listName).getAllTasks();
	}
	
	/**
	 * Returns details of a chosen task
	 * @param listName List that houses the Task
	 * @param timeCreated Time the Task was created
	 * @return Details of task as Task object
	 */
	public Task getTask(String listName, Double timeCreated) {
		// maybe just returns information of a certain task from getList()
		return this.model.getTask(listName, timeCreated);
	}
	
	/**
	 * Converts a date string to Date object
	 * @param time String version of the deadline
	 * @return Date version of the deadline
	 */
	@SuppressWarnings("deprecation")
	public Date getDateObject(String time) {
		if(time.equals("MM/DD/YYYY HR:MN"))
			return new Date(0,0,0,0,0);
		
		int month = Integer.valueOf(time.substring(0, 2)) - 1;
		int date = Integer.valueOf(time.substring(3, 5));
		int year = Integer.valueOf(time.substring(6, 10));
		int hrs = Integer.valueOf(time.substring(11, 13));
		int min = Integer.valueOf(time.substring(14, 16));
		
		return new Date(year, month, date, hrs, min);
	}
	
	/**
	 * Saves the data of the project to listdata.txt
	 * @return true if all data was saved, false if there were some empty lists
	 */
	@SuppressWarnings("deprecation")
	public boolean saveData() {
		File file = new File("listdata.txt");
		BufferedWriter writer = null;
		boolean completeDataSaved = true;
		try {
            writer = new BufferedWriter(new FileWriter(file));
            if(this.getAllLists().isEmpty())
            	return completeDataSaved;
  
            for (String listName: this.getAllLists().keySet()) {
            	if(this.getAllTasks(listName).isEmpty()) {
            		completeDataSaved = false;
            		continue;
            	}
                writer.write(listName + "==");
                for(Double timeCreated: this.getAllTasks(listName).keySet()) {
                	String taskContent = "";
                	taskContent += timeCreated.toString() + "~~";
                	taskContent += this.getTask(listName, timeCreated).getName() + "~~";
                	if(this.getTask(listName, timeCreated).getDescription() == null)
                		taskContent += "empty~~";
                	else
                		taskContent += this.getTask(listName, timeCreated).getDescription() + "~~";
                	Date temp = this.getTask(listName, timeCreated).getDeadline();
                	if(this.getTask(listName, timeCreated).getDateString().equals("MM/DD/YYYY HR:MN")) {
                		taskContent += "MM/DD/YYYY HR:MN~~";
                	}
                	else {
                		int month = temp.getMonth();
                    	if(month < 10)
                    		taskContent += "0";
                    	taskContent += month + "/";
                    	int date = temp.getDate();
                    	if(date < 10)
                    		taskContent += "0";
                    	taskContent += date + "/";
                    	taskContent += temp.getYear() + " ";
                    	int hours = temp.getHours();
                    	if(hours < 10)
                    		taskContent += "0";
                    	taskContent += hours + ":";
                    	int minutes = temp.getMinutes();
                    	if(minutes < 10)
                    		taskContent += "0";
                    	taskContent += minutes + "~~";
                	}
                	taskContent += this.getTask(listName, timeCreated).getPriority() + "~~";
                	taskContent += this.getTask(listName, timeCreated).isComplete() + "``";
                	writer.write(taskContent);
                }
                writer.newLine();
            }
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
  
            try {
                writer.close();
            }
            catch (Exception e) {
            }
        }
		return completeDataSaved;
	}
	
	/**
	 * Loads the information from listdata.txt into the current model
	 * @return true if data was successfully loaded, false if file was empty.
	 */
	public boolean loadData() {
		BufferedReader reader = null;
		boolean dataLoaded = true;
        try {
            File file = new File("listdata.txt");
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
            	String[] currList = line.split("==");
            	String listName = currList[0];
            	this.addList(listName);
            	
            	String[] allTasks = currList[1].split("``");
            	for(String currTask: allTasks) {
            		String[] taskInfo = currTask.split("~~");
            		Double timeCreated = Double.valueOf(taskInfo[0]);
            		String taskName = taskInfo[1];
            		String taskDescription = taskInfo[2];
            		String taskDeadline = taskInfo[3];
            		String taskPriority = taskInfo[4];
            		Boolean taskStatus = Boolean.valueOf(taskInfo[5]);
            		
            		if(taskDescription.equals("empty~~"))
            			taskDescription = null;
            		
            		this.addTask(listName, taskName, taskDescription, taskDeadline, timeCreated, taskPriority);
            		if(taskStatus == true)
            			this.getTask(listName, timeCreated).setComplete();
            		
            		lineNumber++;
            	}
            }
            if(lineNumber == 0)
    			dataLoaded = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (Exception e) {
                };
            }
        }
		return dataLoaded;
	}
	
}
