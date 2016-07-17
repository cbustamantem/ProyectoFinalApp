package edu.galileo.android.proyectofinalapp.lib;

import edu.galileo.android.proyectofinalapp.lib.base.EventBus;

/**
 * Created by ykro.
 */
public class GreenRobotEventBus implements EventBus {
    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotEventBus(){
        eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    public void register(Object subscriber){
        eventBus.register(subscriber);
    }

    public void unregister(Object subscriber){
        eventBus.unregister(subscriber);
    }

    public void post(Object event){
        eventBus.post(event);
    }
}
