package com.jobster.BLL;

import com.jobster.BLL.types.JobsterErrorType;

public class JobsterException extends Exception {

    private static final long serialVersionUID = 1L;
    private int _errorNumber;

    public JobsterException() {

    }

    public JobsterException(String message) {

        super(message);
    }

    public JobsterException(JobsterErrorType error) {
        super(error.getMessage());
        this._errorNumber = error.getCode();
    }

    public JobsterException(String message, int ErrorNumber) {
        super(message);
        this._errorNumber = ErrorNumber;

    }

    public int getErrorNumber() {
        return _errorNumber;
    }

    public void setErrorNumber(int value) {
        _errorNumber = value;
    }
}
