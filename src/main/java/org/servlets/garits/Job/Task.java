package org.servlets.garits.Job;

import java.sql.Timestamp;
import org.servlets.garits.StockControl.SparePart;
import java.util.ArrayList;

public class Task {

    public Task(int taskID, String description, ArrayList<SparePart> partsUsed) {
        this.taskID = taskID;
        this.description = description;
        this.partsUsed = partsUsed;
    }

    private int taskID;
    private String description;
    private ArrayList<SparePart> partsUsed = new ArrayList<SparePart>();
    private Timestamp estimatedTime;
    private Timestamp taskDuration;

    public int getTaskID() {
        return this.taskID;
    }

    public Timestamp getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Timestamp estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Timestamp getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(Timestamp taskDuration) {
        this.taskDuration = taskDuration;
    }
    
    

    /**
     *
     * @param taskID
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<SparePart> getPartsUsed() {
        return this.partsUsed;
    }

    /**
     *
     * @param partsUsed
     */
    public void setPartsUsed(ArrayList<SparePart> partsUsed) {
        this.partsUsed = partsUsed;
    }

    public Task() {
    }
}
