package com.loopr.wallet.wallet.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.loopr.wallet.wallet.entity.ErrorEnvelope;
import com.loopr.wallet.wallet.entity.ServiceException;

import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

	protected final MutableLiveData<ErrorEnvelope> error = new MutableLiveData<>();
	protected final MutableLiveData<Boolean> progress = new MutableLiveData<>();
	protected Disposable disposable;

	@Override
	protected void onCleared() {
		cancel();
	}

	protected void cancel() {
		if (disposable != null && !disposable.isDisposed()) {
			disposable.dispose();
		}
	}

	public LiveData<ErrorEnvelope> error() {
		return error;
	}

	public LiveData<Boolean> progress() {
		return progress;
	}

	protected void onError(Throwable throwable) {
		if (throwable instanceof ServiceException) {
			error.postValue(((ServiceException) throwable).error);
		} else {
			error.postValue(new ErrorEnvelope(ErrorEnvelope.ErrorCode.UNKNOWN, null, throwable));
		}
	}
}
