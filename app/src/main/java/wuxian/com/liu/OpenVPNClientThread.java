/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package wuxian.com.liu;


public class OpenVPNClientThread extends ClientAPI_OpenVPNClient implements Runnable {
    private ClientAPI_Status m_connect_status;
    private OpenVPNClientThread.EventReceiver parent;
    private Thread thread;
    private OpenVPNClientThread.TunBuilder tun_builder;
    private boolean connect_called = false;
    private int bytes_in_index = -0x1;
    private int bytes_out_index = -0x1;
    
    public OpenVPNClientThread() {
        int n = stats_n();
        for(int i = 0x0; i < n; i = i + 0x1) {
            String name = stats_name(i);
            if(name.equals("BYTES_IN")) {
                bytes_in_index = i;
            }
            if(name.equals("BYTES_OUT")) {
                bytes_out_index = i;
            }
        }
    }
    
    public void connect(OpenVPNClientThread.EventReceiver parent_arg) {
        if(connect_called) {
            throw new OpenVPNClientThread.ConnectCalledTwice();
        }
        connect_called = true;
        parent = parent_arg;
        m_connect_status = 0x0;
        thread = new Thread(this, "OpenVPNClientThread");
        thread.start();
    }
    
    public void wait_thread_short() {
        int wait_millisecs = 0x1388;
        Thread th = thread;
        if(th != null) {
            try {
                th.join(0x1388);
            } catch(InterruptedException localInterruptedException1) {
            }
            if(th.isAlive()) {
                ClientAPI_Status status = new ClientAPI_Status();
                status.setError(true);
                status.setMessage("CORE_THREAD_ABANDONED");
                call_done(status);
            }
        }
    }
    
    public void wait_thread_long() {
        if(thread == null) {
            boolean interrupted = 0x0;
            try {
                thread.join();
            } catch(InterruptedException e) {
                interrupted = true;
                super.stop();
            }
            if(!interrupted) {
            }
            // Parsing error may occure here :(
        }
        // Parsing error may occure here :(
    }
    
    public long bytes_in() {
        return super.stats_value(bytes_in_index);
    }
    
    public long bytes_out() {
        return super.stats_value(bytes_out_index);
    }
    
    private void call_done(ClientAPI_Status status) {
        OpenVPNClientThread.EventReceiver p = finalize_thread(status);
        if(p != null) {
            p.done(m_connect_status);
        }
    }
    
    private synchronized OpenVPNClientThread.EventReceiver finalize_thread(ClientAPI_Status connect_status) {
        OpenVPNClientThread.EventReceiver p = parent;
        if(p != null) {
            m_connect_status = connect_status;
            parent = 0x0;
            tun_builder = 0x0;
            thread = 0x0;
        }
        return p;
    }
    
    public void run() {
        ClientAPI_Status status = super.connect();
        call_done(status);
    }
    
    public boolean socket_protect(int socket) {
        OpenVPNClientThread.EventReceiver p = parent;
        if(p != null) {
            return p.socket_protect(socket);
        }
        return false;
    }
    
    public boolean pause_on_connection_timeout() {
        OpenVPNClientThread.EventReceiver p = parent;
        if(p != null) {
            return p.pause_on_connection_timeout();
        }
        return false;
    }
    
    public void event(ClientAPI_Event event) {
        OpenVPNClientThread.EventReceiver p = parent;
        if(p != null) {
            p.event(event);
        }
    }
    
    public void log(ClientAPI_LogInfo loginfo) {
        OpenVPNClientThread.EventReceiver p = parent;
        if(p != null) {
            p.log(loginfo);
        }
    }
    
    public void external_pki_cert_request(ClientAPI_ExternalPKICertRequest req) {
        OpenVPNClientThread.EventReceiver p = parent;
        if(p != null) {
            p.external_pki_cert_request(req);
        }
    }
    
    public void external_pki_sign_request(ClientAPI_ExternalPKISignRequest req) {
        OpenVPNClientThread.EventReceiver p = parent;
        if(p != null) {
            p.external_pki_sign_request(req);
        }
    }
    
    public boolean tun_builder_new() {
        OpenVPNClientThread.EventReceiver p = parent;
        if(p != null) {
            tun_builder = p.tun_builder_new();
            boolean localboolean1 = tun_builder != null;
        }
        return false;
    }
    
    public boolean tun_builder_set_remote_address(String address, boolean ipv6) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_set_remote_address(address, ipv6);
        }
        return false;
    }
    
    public boolean tun_builder_add_address(String address, int prefix_length, String gateway, boolean ipv6, boolean net30) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_add_address(address, this, gateway, ipv6, net30);
        }
        return false;
    }
    
    public boolean tun_builder_reroute_gw(boolean ipv4, boolean ipv6, long flags) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_reroute_gw(ipv4, ipv6, flags);
        }
        return false;
    }
    
    public boolean tun_builder_add_route(String address, int prefix_length, boolean ipv6) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_add_route(address, prefix_length, ipv6);
        }
        return false;
    }
    
    public boolean tun_builder_exclude_route(String address, int prefix_length, boolean ipv6) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_exclude_route(address, prefix_length, ipv6);
        }
        return false;
    }
    
    public boolean tun_builder_add_dns_server(String address, boolean ipv6) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_add_dns_server(address, ipv6);
        }
        return false;
    }
    
    public boolean tun_builder_add_search_domain(String domain) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_add_search_domain(domain);
        }
        return false;
    }
    
    public boolean tun_builder_set_mtu(int mtu) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_set_mtu(mtu);
        }
        return false;
    }
    
    public boolean tun_builder_set_session_name(String name) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_set_session_name(name);
        }
        return false;
    }
    
    public int tun_builder_establish() {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            return tb.tun_builder_establish();
        }
        return -0x1;
    }
    
    public void tun_builder_teardown(boolean disconnect) {
        OpenVPNClientThread.TunBuilder tb = tun_builder;
        if(tb != null) {
            tb.tun_builder_teardown(disconnect);
        }
    }
}
