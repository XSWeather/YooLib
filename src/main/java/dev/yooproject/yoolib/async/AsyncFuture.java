package dev.yooproject.yoolib.async;

import java.util.concurrent.*;
import java.util.function.*;
import java.util.*;

public interface AsyncFuture<T> {

    boolean isDone();
    boolean isCancelled();
    boolean cancel(boolean mayInterruptIfRunning);
    T get() throws InterruptedException, ExecutionException;
    T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

    <U> AsyncFuture<U> thenApply(Function<? super T, ? extends U> fn);
    <U> AsyncFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn);
    <U> AsyncFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor);

    AsyncFuture<Void> thenAccept(Consumer<? super T> action);
    AsyncFuture<Void> thenAcceptAsync(Consumer<? super T> action);
    AsyncFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor);

    AsyncFuture<Void> thenRun(Runnable action);
    AsyncFuture<Void> thenRunAsync(Runnable action);
    AsyncFuture<Void> thenRunAsync(Runnable action, Executor executor);

    <U, V> AsyncFuture<V> thenCombine(AsyncFuture<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn);
    <U, V> AsyncFuture<V> thenCombineAsync(AsyncFuture<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn);
    <U, V> AsyncFuture<V> thenCombineAsync(AsyncFuture<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor);

    AsyncFuture<T> exceptionally(Function<Throwable, ? extends T> fn);

    AsyncFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
    AsyncFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action);
    AsyncFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor);

    AsyncFuture<T> withTimeout(long timeout, TimeUnit unit);
    AsyncFuture<T> onTimeout(Supplier<? extends T> valueSupplier, long timeout, TimeUnit unit);

    static <U> AsyncFuture<List<U>> allOf(Collection<AsyncFuture<U>> futures) { throw new UnsupportedOperationException(); }
    static <U> AsyncFuture<U> anyOf(Collection<AsyncFuture<U>> futures) { throw new UnsupportedOperationException(); }

    static <U> AsyncFuture<U> completed(U value) { throw new UnsupportedOperationException(); }
    static <U> AsyncFuture<U> failed(Throwable ex) { throw new UnsupportedOperationException(); }
    static <U> AsyncFuture<U> supplyAsync(Supplier<U> supplier) { throw new UnsupportedOperationException(); }
    static <U> AsyncFuture<U> supplyAsync(Supplier<U> supplier, Executor executor) { throw new UnsupportedOperationException(); }
    static AsyncFuture<Void> runAsync(Runnable runnable) { throw new UnsupportedOperationException(); }
    static AsyncFuture<Void> runAsync(Runnable runnable, Executor executor) { throw new UnsupportedOperationException(); }
}
