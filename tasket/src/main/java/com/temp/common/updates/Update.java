package com.temp.common.updates;

import com.temp.common.Message;

public abstract class Update<T> implements Message {
    private T update;

    public Update(T update) {
        this.update = update;
    }

    public T getUpdate() {
        return update;
    }
}
