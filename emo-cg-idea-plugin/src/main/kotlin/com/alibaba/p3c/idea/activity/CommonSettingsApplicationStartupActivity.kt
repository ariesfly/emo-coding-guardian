/*
 * Copyright 1999-2017 Alibaba Group.
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
package com.alibaba.p3c.idea.activity

import com.alibaba.p3c.idea.extentsions.config.P3cConfig
import com.alibaba.p3c.idea.i18n.P3cBundle
import com.alibaba.p3c.idea.util.HighlightInfoTypes
import com.alibaba.p3c.idea.util.HighlightSeverities
import com.alibaba.p3c.pmd.I18nResources
import com.alibaba.smartfox.idea.common.activity.AliBaseApplicationStartupActivity
import com.coding.guardian.plugin.config.SkinConfig
import com.intellij.codeInsight.daemon.impl.SeverityRegistrar
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.project.Project

/**
 *
 *
 * @author caikang
 * @date 2017/06/19
 */
class CommonSettingsApplicationStartupActivity : AliBaseApplicationStartupActivity {
    companion object {
        const val analyticsGroupId = "com.alibaba.p3c.analytics.action_group"
        const val analyticsGroupText = "$analyticsGroupId.text"
        private val p3cConfig = P3cConfig()
    }

    override fun runActivity(project: Project) {
        SeverityRegistrar.registerStandard(HighlightInfoTypes.BLOCKER, HighlightSeverities.BLOCKER)
        SeverityRegistrar.registerStandard(HighlightInfoTypes.CRITICAL, HighlightSeverities.CRITICAL)
        SeverityRegistrar.registerStandard(HighlightInfoTypes.MAJOR, HighlightSeverities.MAJOR)

        I18nResources.changeLanguage(p3cConfig.locale)
        val analyticsGroup = ActionManager.getInstance().getAction(analyticsGroupId)
        analyticsGroup.templatePresentation.text = SkinConfig.NAME + P3cBundle.getMessage(analyticsGroupText)
    }
}
