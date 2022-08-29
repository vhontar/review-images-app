package com.vhontar.libs.connectionmonitor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

class PingMonitor {
    public boolean isNetworkConnected() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) { e.printStackTrace(); }
        return false;
    }
}
