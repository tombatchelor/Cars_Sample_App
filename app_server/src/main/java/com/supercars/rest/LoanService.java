/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.rest;

import com.supercars.LoanQuote;
import com.supercars.LoanQuoteRequest;
import com.supercars.externaldata.LoanQuotes;
import com.supercars.tracing.TracingHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tombatchelor
 */
@Path("/loan")
public class LoanService {
    
    private final static Logger logger = Logger.getLogger(LoanService.class.getName());
    
    @Path("/quote")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public LoanQuote getLoanQuote(LoanQuoteRequest loanQuoteRequest) {
        logger.log(Level.FINE, "POST Loan quote request {0}", loanQuoteRequest.toString());
        LoanQuote loanQuote = LoanQuotes.getQuote(loanQuoteRequest);
        
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.loan.rate", loanQuote.getRate());
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.loan.payment", loanQuote.getPayment());
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.loan.term", loanQuote.getTerm());
        
        logger.log(Level.FINE, "Returning loan quote {0}", loanQuote.toString());
        return loanQuote;
    }
}
