/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars;

/**
 *
 * @author tom_n
 */
public class ZendeskTicket {

    /**
     * @return the ticketID
     */
    public int getTicketID() {
        return ticketID;
    }

    /**
     * @param ticketID the ticketID to set
     */
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    /**
     * @return the ticketCreator
     */
    public String getTicketCreator() {
        return ticketCreator;
    }

    /**
     * @param ticketCreator the ticketCreator to set
     */
    public void setTicketCreator(String ticketCreator) {
        this.ticketCreator = ticketCreator;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the comments
     */
    public ZendeskComment[] getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(ZendeskComment[] comments) {
        this.comments = comments;
    }
    private int ticketID;
    private String ticketCreator;
    private String account;
    private String priority;
    private String description;
    private ZendeskComment [] comments;
}
