package com.kpkdev.android.dev.networking.events;

/**
 * Created by krasimir.karamazov on 7/7/2015.
 */
public class DataLoadedEvent<T> {
    private T data;

    public DataLoadedEvent(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
