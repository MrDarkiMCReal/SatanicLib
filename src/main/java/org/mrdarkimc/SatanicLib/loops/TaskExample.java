package org.mrdarkimc.SatanicLib.loops;


import org.mrdarkimc.SatanicLib.taskManager.interfaces.TimedTask;

public class TaskExample extends TimedTask {
    public TaskExample(long delayPluginBoot, long taskActiveTimeInTicks, long timeBetweenActivation) {
        super(delayPluginBoot, taskActiveTimeInTicks, timeBetweenActivation);
    }


    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }
}
