package com.loopr.wallet.wallet.entity;

import android.support.annotation.Nullable;


public class ErrorEnvelope {
	public final int code;
	@Nullable
	public final String message;
	@Nullable
	private final Throwable throwable;

	public interface ErrorCode {

		int UNKNOWN = 1;
		int CANT_GET_STORE_PASSWORD = 2;
	}

	public ErrorEnvelope(@Nullable String message) {
		this(ErrorCode.UNKNOWN, message);
	}

	public ErrorEnvelope(int code, @Nullable String message) {
		this(code, message, null);
	}

	public ErrorEnvelope(int code, @Nullable String message, @Nullable Throwable throwable) {
		this.code = code;
		this.message = message;
		this.throwable = throwable;
	}
}
