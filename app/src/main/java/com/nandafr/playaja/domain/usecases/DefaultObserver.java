package com.nandafr.playaja.domain.usecases;

import androidx.annotation.NonNull;

import io.reactivex.observers.DisposableObserver;

public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
