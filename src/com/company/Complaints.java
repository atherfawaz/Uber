package com.company;

import java.util.ArrayList;
import java.util.List;

public class Complaints
{

    public List<Complaint> cList = new ArrayList<Complaint>();


    public void addComplaint(String complaintDescription, Driver driver, Passenger passenger)
    {
        Complaint c = new Complaint(complaintDescription, driver, passenger);
        cList.add(c);
    }

    public void viewComplaints()
    {
        for (int i = 0; i < cList.size(); i++)
        {
            System.out.println("Sr. No. " + i+1);
            cList.get(i).viewComplaint();
        }
    }
}
