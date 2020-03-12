/*
 * Copyright (C) 2019-2020 The Evolution X Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.legion.settings.preference;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settingslib.core.AbstractPreferenceController;

import com.legion.settings.preference.SystemSettingSeekBarPreference;

import java.util.ArrayList;
import java.util.List;

public class QsBlurAlphaPreferenceController extends AbstractPreferenceController implements
        Preference.OnPreferenceChangeListener {

    private static final String QS_BLUR_ALPHA = "qs_blur_alpha";

    private SystemSettingSeekBarPreference mQsBlurAlpha;

    public QsBlurAlphaPreferenceController(Context context) {
        super(context);
    }

    @Override
    public String getPreferenceKey() {
        return QS_BLUR_ALPHA;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        mQsBlurAlpha = (SystemSettingSeekBarPreference) screen.findPreference(QS_BLUR_ALPHA);
        int qsBlurAlpha = Settings.System.getInt(mContext.getContentResolver(),
                Settings.System.QS_BLUR_ALPHA, 100);
        mQsBlurAlpha.setValue(qsBlurAlpha);
        mQsBlurAlpha.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mQsBlurAlpha) {
            int value = (Integer) newValue;
            Settings.System.putInt(mContext.getContentResolver(),
                    Settings.System.QS_BLUR_ALPHA, value);
            return true;
        }
        return false;
    }
}
