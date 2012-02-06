/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.rest;

import java.util.concurrent.ExecutorService;

import javax.jms.JMSException;
import javax.time.Instant;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.fudgemsg.FudgeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.transport.jaxrs.FudgeRest;

/**
 * Base class for a RESTful resource which uses a REST+JMS pattern to publish streaming results.
 * <p>
 * Provides heartbeat listening and control of the JMS stream.
 */
public abstract class AbstractRestfulJmsResultPublisher {

  private static final Logger s_logger = LoggerFactory.getLogger(AbstractRestfulJmsResultPublisher.class);
  
  //CSOFF: just constants
  public static final String PATH_HEARTBEAT = "heartbeat";
  public static final String PATH_START_JMS_RESULT_STREAM = "startJmsResultStream";
  public static final String PATH_STOP_JMS_RESULT_STREAM = "stopJmsResultStream";
  public static final String DESTINATION_FIELD = "destination";
  //CSON: just constants
  
  private final AbstractJmsResultPublisher _resultPublisher;
  private final ExecutorService _executor;
  private volatile Instant _lastAccessed = Instant.now();
  
  protected AbstractRestfulJmsResultPublisher(AbstractJmsResultPublisher resultPublisher, ExecutorService executor) {
    _resultPublisher = resultPublisher;
    _executor = executor;
  }
  
  //-------------------------------------------------------------------------
  /**
   * Tests whether the underlying resource has been terminated.
   * 
   * @return true if the underlying resource has been terminated, false otherwise
   */
  protected abstract boolean isTerminated();
  
  /**
   * Called to indicate that the publisher's consumer has failed to provide heartbeats, and the resource is no longer
   * required. Releases the underlying resource, stopping publication of results. 
   */
  protected abstract void expire();
  
  //-------------------------------------------------------------------------
  protected void startPublishingResults(String destination) throws Exception {
    getResultPublisher().startPublishingResults(destination);
  }
  
  protected void stopPublishingResults() throws JMSException {
    getResultPublisher().stopPublishingResults();
  }
  
  //-------------------------------------------------------------------------
  @POST
  @Path(PATH_HEARTBEAT)
  public Response heartbeat() {
    updateLastAccessed();
    return Response.ok().build();
  }
  
  public Instant getLastAccessed() {
    return _lastAccessed;
  }
  
  protected void updateLastAccessed() {
    _lastAccessed = Instant.now();
  }
  
  //-------------------------------------------------------------------------  
  @POST
  @Path(PATH_START_JMS_RESULT_STREAM)
  @Consumes(FudgeRest.MEDIA)
  public Response startResultStream(final FudgeMsg msg) {
    updateLastAccessed();
    final String destination = msg.getString(DESTINATION_FIELD);
    _executor.execute(new Runnable() {

      @Override
      public void run() {
        try {
          startPublishingResults(destination);
        } catch (Exception e) {
          s_logger.error("Error starting result publisher", e);
        }
      }
      
    });
    return Response.ok(destination).build();
  }
  
  @POST
  @Path(PATH_STOP_JMS_RESULT_STREAM)
  public Response stopResultStream() {
    updateLastAccessed();
    _executor.execute(new Runnable() {

      @Override
      public void run() {
        try {
          stopPublishingResults();
        } catch (Exception e) {
          s_logger.error("Error stopping result publisher", e);
        }
      }
      
    });
    return Response.ok().build();
  }
  
  //-------------------------------------------------------------------------
  private AbstractJmsResultPublisher getResultPublisher() {
    return _resultPublisher;
  }
  
}
