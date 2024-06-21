/*
 * Copyright (C) 2024 The Android Open Source Project
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

package com.android.settings.notification.modes;

import static android.app.NotificationManager.INTERRUPTION_FILTER_ALL;
import static android.provider.Settings.EXTRA_AUTOMATIC_ZEN_RULE_ID;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.preference.Preference;

import com.android.settings.core.SubSettingLauncher;
import com.android.settingslib.notification.modes.ZenMode;
import com.android.settingslib.notification.modes.ZenModesBackend;

/**
 * Preference with a link and summary about what calls and messages can break through the mode
 */
class ZenModePeopleLinkPreferenceController extends AbstractZenModePreferenceController {

    private final ZenModeSummaryHelper mSummaryHelper;

    public ZenModePeopleLinkPreferenceController(Context context, String key,
            ZenModesBackend backend, ZenHelperBackend helperBackend) {
        super(context, key, backend);
        mSummaryHelper = new ZenModeSummaryHelper(mContext, helperBackend);
    }

    @Override
    public boolean isAvailable(ZenMode zenMode) {
        return zenMode.getRule().getInterruptionFilter() != INTERRUPTION_FILTER_ALL;
    }

    @Override
    public void updateState(Preference preference, @NonNull ZenMode zenMode) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_AUTOMATIC_ZEN_RULE_ID, zenMode.getId());
        // TODO(b/332937635): Update metrics category
        preference.setIntent(new SubSettingLauncher(mContext)
                .setDestination(ZenModePeopleFragment.class.getName())
                .setSourceMetricsCategory(0)
                .setArguments(bundle)
                .toIntent());
        preference.setSummary(mSummaryHelper.getPeopleSummary(zenMode));
    }
}
