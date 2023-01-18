package com.msciq.storage.exception;

import java.util.List;

public class BadRequestException extends ServicesException {

    public BadRequestException() {
        this(1001, BadRequestException.class.getSimpleName());
    }

    public BadRequestException(final Integer code) {
        this(code, BadRequestException.class.getSimpleName());
    }

    public BadRequestException(final Integer code, final String... params) {
        super(code, params);
    }

    public BadRequestException(final Integer code, final String message) {
        super(code, message);
    }

    public BadRequestException(final Integer code, final List<String> params) {
        super(code, params);
    }
}
