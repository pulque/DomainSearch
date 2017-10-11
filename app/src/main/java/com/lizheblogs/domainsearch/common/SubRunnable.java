package com.lizheblogs.domainsearch.common;

/**
 * Created by LiZhe on 2017-10-11.
 * com.lizheblogs.domainsearch.common
 */

public class SubRunnable implements Runnable {

    public boolean isStopRun = false;

    public void stopThread(boolean isStopRun) {
        this.isStopRun = isStopRun;
    }

    @Override
    public void run() {

    }
}
