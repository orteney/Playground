package com.example.testapplication.modules.test

import com.example.testapplication.common.viper.router.BaseAppRouter
import com.example.testapplication.common.viper.router.DefaultRouter
import javax.inject.Inject

/**
 * Created by a.kojemyakin on 27.03.2017.
 */

class TestRouter @Inject constructor(router: BaseAppRouter) : DefaultRouter(router), ITestRouter {

}
