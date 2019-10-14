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
    
    @Path("/quote")
    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    public LoanQuote getLoanQuote(LoanQuoteRequest quoteRequest) {
        LoanQuote loanQuote = LoanQuotes.getQuote(quoteRequest);
        
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.loan.rate", loanQuote.getRate());
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.loan.payment", loanQuote.getPayment());
        TracingHelper.tag(TracingHelper.CARS_APP_NAME, "supercars.loan.payment", loanQuote.getTerm());
        
        return loanQuote;
    }
}
