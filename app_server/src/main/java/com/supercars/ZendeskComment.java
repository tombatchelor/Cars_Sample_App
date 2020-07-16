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
public class ZendeskComment {

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the customerVisible
     */
    public boolean isCustomerVisible() {
        return customerVisible;
    }

    /**
     * @param customerVisible the customerVisible to set
     */
    public void setCustomerVisible(boolean customerVisible) {
        this.customerVisible = customerVisible;
    }
    private String author;
    private String comment;
    private boolean customerVisible;
}
