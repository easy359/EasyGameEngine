package com.easy359.engine.events.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventObject;
import java.util.List;

public class EventHandler {

    public static boolean invoke(EventObject evt, Object obj) {
        for (Method mth : obj.getClass().getDeclaredMethods()) {
            if (mth.isAnnotationPresent(EventReceiver.class)) {
                if (mth.getParameterCount() == 1) {
                    if (mth.getParameters()[0].getType().equals(evt.getClass())) {
                        try {
                            mth.invoke(obj, evt);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean invoke(EventObject evt, List<Object> objs) {
        boolean noProblems = true;
        for (Object obj : objs) {
            if (!invoke(evt, obj))
                noProblems = false;
        }
        return noProblems;
    }
}
