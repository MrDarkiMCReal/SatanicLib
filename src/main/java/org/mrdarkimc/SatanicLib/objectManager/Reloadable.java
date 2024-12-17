package org.mrdarkimc.SatanicLib.objectManager;

import org.mrdarkimc.SatanicLib.Debugger;

import java.util.ArrayList;
import java.util.List;

public class Reloadable {
    private static final List<org.mrdarkimc.SatanicLib.objectManager.interfaces.Reloadable> list = new ArrayList<>();
    private static void register(org.mrdarkimc.SatanicLib.objectManager.interfaces.Reloadable r){
        if (list.contains(r))
            return;
        list.add(r);
    }

    static void reloadAll(){
        //ReloadableClasses.forEach(Reloadable::reload);
        //Debugger.chat("Reloaded cache for class: " +e.toString(),2);
        list.forEach(org.mrdarkimc.SatanicLib.objectManager.interfaces.Reloadable::reload);
    }
}
