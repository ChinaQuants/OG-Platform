/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.livedata.cogda.msg;

import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

/**
 * 
 */
public class ConnectionRequestBuilder implements FudgeBuilder<ConnectionRequestMessage> {
  
  public static MutableFudgeMsg buildMessageStatic(FudgeSerializer serializer, ConnectionRequestMessage request) {
    MutableFudgeMsg msg = serializer.newMessage();
    
    msg.add("userName", request.getUserName());
    
    msg.add("capabilities", request.getCapabilities());
    
    return msg;
  }

  @Override
  public MutableFudgeMsg buildMessage(FudgeSerializer serializer, ConnectionRequestMessage object) {
    return buildMessageStatic(serializer, object);
  }

  public static ConnectionRequestMessage buildObjectStatic(FudgeDeserializer deserializer, FudgeMsg message) {
    ConnectionRequestMessage request = new ConnectionRequestMessage();
    request.setUserName(message.getString("userName"));
    request.applyCapabilities(message.getMessage("capabilities"));
    return request;
  }

  @Override
  public ConnectionRequestMessage buildObject(FudgeDeserializer deserializer, FudgeMsg message) {
    return buildObjectStatic(deserializer, message);
  }

}
