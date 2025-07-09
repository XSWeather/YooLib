package dev.yooproject.yoolib.async;

import java.util.function.*;

public interface AsyncFutureBuilder<T> {
    AsyncFutureBuilder<T> onSuccess(Consumer<T> consumer);
    AsyncFutureBuilder<T> onFailure(Consumer<Throwable> consumer);
    AsyncFutureBuilder<T> onCancel(Runnable runnable);
    AsyncFuture<T> build();
}
