package com.github.privacystreams.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author toby
 * @date 2/28/17
 * @time 10:53 AM
 */
public class UserUIActionAccessibilityEventItem extends BaseAccessibilityEventItem {
    public static final String SOURCE_NODE = "source_node";

    public static boolean isAUserUIActionAccessibilityEventType (AccessibilityEvent event){
        int eventType = event.getEventType();
        return (eventType == AccessibilityEvent.TYPE_VIEW_CLICKED
                || eventType == AccessibilityEvent.TYPE_VIEW_LONG_CLICKED
                || eventType == AccessibilityEvent.TYPE_VIEW_FOCUSED
                || eventType == AccessibilityEvent.TYPE_VIEW_SELECTED);
    }

    public UserUIActionAccessibilityEventItem(){
        super();
    }

    public UserUIActionAccessibilityEventItem(AccessibilityEvent event, AccessibilityNodeInfo rootNode, Date timeStamp){
        super(event, rootNode, timeStamp);
        this.setFieldValue(SOURCE_NODE, event.getSource());
    }

    //TODO: FOR TESTING PURPOSE ONLY
    @Override
    public String toString(){
        String eventType = "";
        int eventTypeInt = getValueByField(EVENT_TYPE);
        switch (eventTypeInt){
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventType = "TYPE_VIEW_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                eventType = "TYPE_VIEW_LONG_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                eventType = "TYPE_VIEW_FOCUSED";
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                eventType = "TYPE_VIEW_SELECTED";
                break;
        }


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
        String timeStamp = format.format(getValueByField(TIMESTAMP));

        String packageName = getValueByField(PACKAGE_NAME).toString();

        String listSize = String.valueOf(((List<AccessibilityNodeInfo>)getValueByField(UI_NODE_LIST)).size());

        AccessibilityNodeInfo sourceNode = getValueByField(SOURCE_NODE);

        return timeStamp + " " + eventType + " " + packageName + " " + "NODE_COUNT: " + listSize + " {" + sourceNode + "}";

    }

}
