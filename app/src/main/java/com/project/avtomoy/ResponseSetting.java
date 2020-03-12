package com.project.avtomoy;

public class ResponseSetting {
    private SettingsClass settings;

    public ResponseSetting(SettingsClass settings) {
        this.settings = settings;
    }

    public SettingsClass getSettings() {
        return settings;
    }

    public void setSettings(SettingsClass settings) {
        this.settings = settings;
    }
}
