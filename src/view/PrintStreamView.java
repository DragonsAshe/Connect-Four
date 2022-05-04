package view;

import java.io.IOException;
import java.io.PrintStream;

public abstract class PrintStreamView {
    /**
     * Print a (visual) representation to given stream
     *
     */
    public abstract void print(PrintStream ps) throws IOException;
}
