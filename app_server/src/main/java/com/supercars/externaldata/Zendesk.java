/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.externaldata;

import brave.jaxrs2.TracingClientFilter;
import com.supercars.InsuranceQuote;
import com.supercars.ZendeskTicket;
import java.util.Random;
import com.supercars.ZendeskComment;
import static com.supercars.externaldata.FuelPrices.tracing;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tom_n
 */
public class Zendesk {
    
    private final static Logger logger = Logger.getLogger(FuelPrices.class.getName());
    
    public static void sendZendeskTicket(String username, String manufacturer) {
        // Create the ticket
        ZendeskTicket ticket = new ZendeskTicket();
        ticket.setTicketCreator(username);
        ticket.setDescription("When I try to look at a " + manufacturer + " the site crashes");
        ticket.setTicketID((new Random()).nextInt(40000));
        if (username.contains("uber")) {
            ticket.setPriority("HIGH");
            ticket.setAccount("Uber");
        } else if (username.contains("snowflake")) {
            ticket.setPriority("MEDIUM");
            ticket.setAccount("Snowflake");
        } else if (username.contains("observeinc")) {
            ticket.setPriority("LOW");
            ticket.setAccount("Observe");
        } else if (username.contains("calm")) {
            ticket.setPriority("MEDIUM");
            ticket.setAccount("Calm");
        } else if (username.contains("slack")) {
              ticket.setPriority("MEDIUM");
              ticket.setAccount("Slack");
        } else if (username.contains("docusign")) {
              ticket.setPriority("MEDIUM");
              ticket.setAccount("Docusign");
        } else if (username.contains("box")) {
              ticket.setPriority("MEDIUM");
              ticket.setAccount("Box");
        } else if (username.contains("crunchbase")) {
              ticket.setPriority("LOW");
              ticket.setAccount("Crunchbase");
        }
        
        ZendeskComment comment1 = new ZendeskComment();
        comment1.setAuthor("support@observeinc.com");
        comment1.setComment("We're looking into your issue");
        comment1.setCustomerVisible(true);
        
        ZendeskComment comment2 = new ZendeskComment();
        comment2.setAuthor("joe@observeinc.com");
        comment2.setComment("I think ops needs to look at this one, I can't understand what's happening");
        comment2.setCustomerVisible(false);
        
        ZendeskComment[] comments = new ZendeskComment[2];
        comments[0] = comment1;
        comments[1] = comment2;
        ticket.setComments(comments);
        
        // Send to ticket to Observe
        String observeURL = "https://collect.observe-staging.com/v1/observations/zendesk";
        String observeCustomerID = System.getenv("CUSTOMER_ID");
        String observeToken = System.getenv("TOKEN");
        String bearer = "Bearer " + observeCustomerID + " " + observeToken;
        
        logger.fine("Using sync HTTP call");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(observeURL);
        target.register(TracingClientFilter.create(tracing));
        target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", bearer)
                .post(Entity.entity(ticket, MediaType.APPLICATION_JSON));
    }
}
