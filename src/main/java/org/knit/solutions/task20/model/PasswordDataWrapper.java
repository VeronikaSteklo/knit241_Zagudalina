package org.knit.solutions.task20.model;

import java.util.Map;

public class PasswordDataWrapper {
    private Map<String, LoginPasswordDTO> masterPassword;

    public PasswordDataWrapper() {}

    public PasswordDataWrapper(Map<String, LoginPasswordDTO> masterPassword) {
        this.masterPassword = masterPassword;
    }

    public Map<String, LoginPasswordDTO> getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(Map<String, LoginPasswordDTO> masterPassword) {
        this.masterPassword = masterPassword;
    }
}
