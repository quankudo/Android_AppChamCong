package com.example.appchamcong.domain;

import android.widget.Button;
import android.widget.TextView;

public class JoinTeam {
    String valueName, valueNumber, valuePosition;

    public JoinTeam(String valueName, String valueNumber, String valuePosition) {
        this.valueName = valueName;
        this.valueNumber = valueNumber;
        this.valuePosition = valuePosition;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(String valueNumber) {
        this.valueNumber = valueNumber;
    }

    public String getValuePosition() {
        return valuePosition;
    }

    public void setValuePosition(String valuePosition) {
        this.valuePosition = valuePosition;
    }
}
