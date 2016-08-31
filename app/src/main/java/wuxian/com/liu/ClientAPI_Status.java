/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package net.openvpn.openvpn;


public class ClientAPI_Status {
    protected boolean swigCMemOwn;
    private long swigCPtr;
    
    protected ClientAPI_Status(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }
    
    protected static long getCPtr(ClientAPI_Status obj) {
        return swigCPtr;
    }
    
    protected void finalize() {
        delete();
    }
    
    public synchronized void delete() {
        if(swigCPtr [cmp] 0x0 != 0) {
            if(swigCMemOwn) {
                swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_Status(swigCPtr);
            }
            swigCPtr = 0x0;
        }
    }
    
    public ClientAPI_Status() {
        this(ovpncliJNI.new_ClientAPI_Status(), true);
    }
    
    public void setError(boolean value) {
        ovpncliJNI.ClientAPI_Status_error_set(swigCPtr, value);
    }
    
    public boolean getError() {
        return ovpncliJNI.ClientAPI_Status_error_get(swigCPtr);
    }
    
    public void setStatus(String value) {
        ovpncliJNI.ClientAPI_Status_status_set(swigCPtr, value);
    }
    
    public String getStatus() {
        return ovpncliJNI.ClientAPI_Status_status_get(swigCPtr);
    }
    
    public void setMessage(String value) {
        ovpncliJNI.ClientAPI_Status_message_set(swigCPtr, value);
    }
    
    public String getMessage() {
        return ovpncliJNI.ClientAPI_Status_message_get(swigCPtr);
    }
}