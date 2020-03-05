/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.tracing;

import com.supercars.rest.CarService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tombatchelor
 */
public class ZipkinMetricReporter implements zipkin2.reporter.ReporterMetrics, Runnable {

    private final static Logger logger = Logger.getLogger(CarService.class.getName());
    
    private long messages = 0;
    private long messagesDropped = 0;
    private long spans = 0;
    private long spansDropped = 0;
    private long spanBytes = 0;
    private long messageBytes = 0;
    private int queuedSpans = 0;
    private int queuedBytes = 0;

    @Override
    public void incrementMessages() {
        messages++;
    }

    @Override
    public void incrementMessagesDropped(Throwable arg0) {
        messagesDropped++;
    }

    @Override
    public void incrementSpans(int arg0) {
        spans += arg0;
    }

    @Override
    public void incrementSpanBytes(int arg0) {
        spanBytes += arg0;
    }

    @Override
    public void incrementMessageBytes(int arg0) {
        messageBytes += arg0;
    }

    @Override
    public void incrementSpansDropped(int arg0) {
        spansDropped++;
    }

    @Override
    public void updateQueuedSpans(int arg0) {
        queuedSpans = arg0;
    }

    @Override
    public void updateQueuedBytes(int arg0) {
        queuedBytes = arg0;
    }
    
    public void run() {
        while (true) {
            logger.log(Level.INFO, "Metrics messages={0} messagesDropped={1} spans={2} spansDropped={3} spanBytes={4} messageBytes={5} queuedSpans={6} queuedBytes={7}",
                    new Object[]{messages, messagesDropped, spans, spansDropped, spanBytes, messageBytes, queuedSpans, queuedBytes});
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
