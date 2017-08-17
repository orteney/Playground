package com.example.testapplication.common.viper.router


open class DefaultRouter(router: BaseAppRouter) : IBaseRouter {

    var router: BaseAppRouter
        internal set

    init {
        this.router = router
    }

    override fun back() {
        router.exit()
    }

//    override fun showLogin() {
//        router.replaceScreen(AuthTransitions.LOGIN)
//    }
}
