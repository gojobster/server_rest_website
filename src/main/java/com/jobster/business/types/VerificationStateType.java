package com.jobster.BLL.types;

public enum VerificationStateType {

    NONE(0),
    PENDING(1),
    READY(2);

    private final int code;

    VerificationStateType(int code) {
        this.code = code;
    }

    public int toInt() {
        return code;
    }
}
