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
    
    private final static Logger logger = Logger.getLogger(Zendesk.class.getName());

    static String observeCustomer = System.getenv("OBSERVE_CUSTOMER");
    static String observeToken = System.getenv("OBSERVE_TOKEN");
    static String observeCollectionHost = System.getenv("OBSERVE_COLLECTION_HOST");
    
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
        String observeCustomer = System.getenv("OBSERVE_CUSTOMER");
        String observeToken = System.getenv("OBSERVE_TOKEN");
        String observeCollectorHost = System.getenv("OBSERVE_COLLECTOR_HOST");
        String observeURL = "https://" + observeCustomer  + ":" + observeToken + "@" + observeCollectorHost + "/v1/http/zendesk";
        
        logger.fine("Using sync HTTP call to: " + observeURL);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(observeURL);
        target.register(TracingClientFilter.create(tracing));
        target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(ticket, MediaType.APPLICATION_JSON));
    }
}
