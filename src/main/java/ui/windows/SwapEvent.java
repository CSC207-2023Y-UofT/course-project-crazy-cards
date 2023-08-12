package ui.windows;

import enums.WindowName;

public class SwapEvent {
    private WindowName previous;
    private WindowName next;

    public SwapEvent(WindowName previous, WindowName next) {
        this.previous = previous;
        this.next = next;
    }

    public WindowName getPrevious() {
        return previous;
    }

    public WindowName getNext() {
        return next;
    }
}
