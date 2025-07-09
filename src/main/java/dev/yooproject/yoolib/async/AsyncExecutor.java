package dev.yooproject.yoolib.async;

import java.util.concurrent.Executor;

public interface AsyncExecutor extends Executor {
    void shutdown();
    boolean isShutdown();
    boolean isTerminated();
    void awaitTermination(long timeout, java.util.concurrent.TimeUnit unit) throws InterruptedException;
}
