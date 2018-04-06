package com.sms.util.fetcher;

import java.util.concurrent.TimeUnit;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class ClientConnectionMonitor extends Thread {

    private final PoolingHttpClientConnectionManager connManager;
    private volatile boolean shutdown;

    public ClientConnectionMonitor(
            PoolingHttpClientConnectionManager connManager) {
        super("Connection Manager");
        this.connManager = connManager;
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // Close expired connections
                    connManager.closeExpiredConnections();
                    // Optionally, close connections that have been idle longer
                    // than 30 sec
                    connManager.closeIdleConnections(30, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException ignored) {
            // terminate
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
