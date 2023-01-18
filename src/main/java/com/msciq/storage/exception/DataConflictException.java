package com.msciq.storage.exception;

import java.util.List;

public class DataConflictException extends ServicesException {
    public DataConflictException() {
        this(1001, DataConflictException.class.getSimpleName());
    }

    public DataConflictException(final Integer code) {
        this(code, DataConflictException.class.getSimpleName());
    }

    public DataConflictException(final Integer code, final String... params) {
        super(code, params);
    }

    public DataConflictException(final Integer code, final String message) {
        super(code, message);
    }

    public DataConflictException(final Integer code, final List<String> params) {
        super(code, params);
    }

}
