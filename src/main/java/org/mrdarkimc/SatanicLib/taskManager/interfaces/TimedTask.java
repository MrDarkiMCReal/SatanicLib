package org.mrdarkimc.SatanicLib.taskManager.interfaces;


import org.mrdarkimc.SatanicLib.taskManager.BaseTask;

public abstract class TimedTask implements BaseTask {
    public TimedTask(long delayPluginBoot, long taskActiveTimeInTicks, long timeBetweenActivation) {
        this.delayPluginBoot = delayPluginBoot;
        this.taskActiveTimeInTicks = taskActiveTimeInTicks;
        this.timeBetweenActivation = timeBetweenActivation;
    }
    private final long delayPluginBoot;
    private final long taskActiveTimeInTicks;
    private final long timeBetweenActivation;

    public long getDelayPluginBoot() {
        return delayPluginBoot;
    }

    public long getTaskActiveTimeInTicks() {
        return taskActiveTimeInTicks;
    }

    public long getTimeBetweenActivation() {
        return timeBetweenActivation;
    }

}
