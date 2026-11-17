package com.edupulse.app.ui.base;

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
public class UiEvent<T> {
    private final T content;
    private boolean hasBeenHandled = false;

    public UiEvent(T content) {
        this.content = content;
    }

    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    public T peekContent() {
        return content;
    }
}
