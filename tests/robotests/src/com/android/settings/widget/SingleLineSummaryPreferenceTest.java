/*
 * Copyright (C) 2017 The Android Open Source Project
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
 * limitations under the License
 */
package com.android.settings.widget;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.support.v7.preference.PreferenceViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.settings.SettingsRobolectricTestRunner;
import com.android.settings.TestConfig;
import com.android.settings.widget.SingleLineSummaryPreference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(SettingsRobolectricTestRunner.class)
@Config(manifest = TestConfig.MANIFEST_PATH, sdk = TestConfig.SDK_VERSION)
public class SingleLineSummaryPreferenceTest {

    @Mock private TextView mSummary;

    private Context mContext;
    private PreferenceViewHolder mHolder;
    private SingleLineSummaryPreference mPreference;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mContext = RuntimeEnvironment.application;
        mPreference = new SingleLineSummaryPreference(mContext, null);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(mPreference.getLayoutResource(),
            new LinearLayout(mContext), false);
        mHolder = spy(PreferenceViewHolder.createInstanceForTests(view));
        when(mHolder.findViewById(android.R.id.summary)).thenReturn(mSummary);
    }

    @Test
    public void onBindViewHolder_shouldSetSingleLine() {
        mPreference.onBindViewHolder(mHolder);
        final TextView summary = (TextView) mHolder.findViewById(android.R.id.summary);

        verify(mSummary).setSingleLine();
    }
}
