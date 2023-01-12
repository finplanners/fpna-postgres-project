package com.msciq.storage.exception;

public class RoleNameStartsWithDefaultException extends RuntimeException {
    public RoleNameStartsWithDefaultException(String fieldName, String fieldValue) {
        super(String.format("Custom %s `%s` should not have prefix Default", fieldName, fieldValue));
    }
}
