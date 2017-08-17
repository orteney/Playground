package com.example.testapplication.modules.test

import com.example.testapplication.di.AppComponent
import com.example.testapplication.di.scopes.LifetimeScope
import dagger.Component
import dagger.Module
import dagger.Provides

@LifetimeScope
@Component(modules = arrayOf(TestModule::class), dependencies = arrayOf(AppComponent::class))
interface TestComponent {
    fun inject(testActivity: TestActivity)
}

@Module(includes = arrayOf(TestModule.Declarations::class))
class TestModule(val testView: ITestView, val testRouter: ITestRouter) {

    @Provides
    fun provideTestView(): ITestView {
        return testView
    }

    @Provides
    fun provideTestRouter(): ITestRouter {
        return testRouter
    }

    @Module
    interface Declarations {

//        @Binds
//        @LifetimeScope
//        fun bindTestPresenter(testPresenter: TestPresenter): TestPresenter
    }
}
