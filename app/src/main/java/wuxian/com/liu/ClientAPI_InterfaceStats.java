/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package wuxian.com.liu;


public class ClientAPI_InterfaceStats {
    protected boolean swigCMemOwn;
    private long swigCPtr;
    
    protected ClientAPI_InterfaceStats(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }
    
    protected static long getCPtr(ClientAPI_InterfaceStats obj) {
        return swigCPtr;
    }
    
    protected void finalize() {
        delete();
    }
    
    public synchronized void delete() {
        if(swigCPtr [cmp] 0x0 != 0) {
            if(swigCMemOwn) {
                swigCMemOwn = false;
                ovpncliJNI.delete_ClientAPI_InterfaceStats(swigCPtr);
            }
            swigCPtr = 0x0;
        }
    }
    
    public void setBytesIn(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_bytesIn_set(swigCPtr, value);
    }
    
    public long getBytesIn() {
        return ovpncliJNI.ClientAPI_InterfaceStats_bytesIn_get(swigCPtr);
    }
    
    public void setPacketsIn(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_packetsIn_set(swigCPtr, value);
    }
    
    public long getPacketsIn() {
        return ovpncliJNI.ClientAPI_InterfaceStats_packetsIn_get(swigCPtr);
    }
    
    public void setErrorsIn(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_errorsIn_set(swigCPtr, value);
    }
    
    public long getErrorsIn() {
        return ovpncliJNI.ClientAPI_InterfaceStats_errorsIn_get(swigCPtr);
    }
    
    public void setBytesOut(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_bytesOut_set(swigCPtr, value);
    }
    
    public long getBytesOut() {
        return ovpncliJNI.ClientAPI_InterfaceStats_bytesOut_get(swigCPtr);
    }
    
    public void setPacketsOut(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_packetsOut_set(swigCPtr, value);
    }
    
    public long getPacketsOut() {
        return ovpncliJNI.ClientAPI_InterfaceStats_packetsOut_get(swigCPtr);
    }
    
    public void setErrorsOut(long value) {
        ovpncliJNI.ClientAPI_InterfaceStats_errorsOut_set(swigCPtr, value);
    }
    
    public long getErrorsOut() {
        return ovpncliJNI.ClientAPI_InterfaceStats_errorsOut_get(swigCPtr);
    }
    
    public ClientAPI_InterfaceStats() {
        this(ovpncliJNI.new_ClientAPI_InterfaceStats(), true);
    }
}
