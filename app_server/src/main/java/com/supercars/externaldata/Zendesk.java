/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.externaldata;

import brave.jaxrs2.TracingClientFilter;
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
        switch ((new Random()).nextInt(4)) {
            case 0:
                ticket.setDescription("I can't log in");
                break;
            case 1:
                ticket.setDescription("The page fails to load when I add a filter");
                break;
            case 2:
                ticket.setDescription("I don't get results for serching by item");
                break;
            case 3:
                ticket.setDescription("I get a error on the home page");
                break;
            default:
                // This shouldn't ever really happen
                ticket.setDescription("The site crashed for me just now");
        }
        ticket.setTicketID((new Random()).nextInt(40000));
        ticket.setAccount(username.substring(username.indexOf('@')+1, username.lastIndexOf('.')));
        switch ((new Random()).nextInt(4)) {
            case 0:
                ticket.setPriority("HIGH");
                break;
            case 1:
                ticket.setPriority("MEDIUM");
                break;
            case 2:
                ticket.setPriority("MEDIUM");
                break;
            case 3:
                ticket.setPriority("LOW");
                break;
            default:
                // This also should never happen
                ticket.setPriority("NONE");
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
        String proxyEndpoint = System.getenv("PROXY_ENDPOINT");
        String observeURL = proxyEndpoint + "/v1/observations/zendesk";
        
        logger.fine("Using sync HTTP call");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(observeURL);
        target.register(TracingClientFilter.create(tracing));
        target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(ticket, MediaType.APPLICATION_JSON));
    }
}
