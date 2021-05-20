package com.example.bugtracker;

import java.util.Date;

public class BugModel {

    private int bugID;
    private String bugDesc;
    private String steps2rep; //Steps to reproduce the bug
    private int priority; //3-Low to 1-High
    private String reporter;
    private String created;// Date in string format
    private String processor;
    private String lastUpdated;// Date in String format
    private int status; //0-Reported, 1-Resolved
    private String soln;

    public BugModel(int bugID, String bugDesc, String steps2rep, int priority, String reporter, String created, String processor, String lastUpdated, int status, String soln) {
        this.bugID = bugID;
        this.bugDesc = bugDesc;
        this.steps2rep = steps2rep;
        this.priority = priority;
        this.reporter = reporter;
        this.created = created;
        this.processor = processor;
        this.lastUpdated = lastUpdated;
        this.status = status;
        this.soln = soln;
    }

    @Override
    public String toString() {
        String stat;
        if(status==0)
        {
            stat = "Reported";
        }
        else{
            stat = "Resolved";
        }
        return "Bug: "+bugDesc+"\nReporter: "+reporter+"\nCreated on: "+created+"\nStatus: "+stat;
//        return "BugModel{" +
//                "bugID=" + bugID +
//                ", bugDesc='" + bugDesc + '\'' +
//                ", steps2rep='" + steps2rep + '\'' +
//                ", priority=" + priority +
//                ", reporter='" + reporter + '\'' +
//                ", created='" + created + '\'' +
//                ", processor='" + processor + '\'' +
//                ", lastUpdated='" + lastUpdated + '\'' +
//                ", status=" + status +
//                ", soln='" + soln + '\'' +
//                '}';
    }

    public int getBugID() {
        return bugID;
    }

    public void setBugID(int bugID) {
        this.bugID = bugID;
    }

    public String getBugDesc() {
        return bugDesc;
    }

    public void setBugDesc(String bugDesc) {
        this.bugDesc = bugDesc;
    }

    public String getSteps2rep() {
        return steps2rep;
    }

    public void setSteps2rep(String steps2rep) {
        this.steps2rep = steps2rep;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSoln() {
        return soln;
    }

    public void setSoln(String soln) {
        this.soln = soln;
    }
}
