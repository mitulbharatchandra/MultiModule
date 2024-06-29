package com.app.multimodule.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val multiModuleDispatchers: MultiModuleDispatchers)

enum class MultiModuleDispatchers {
    Default,
    IO
}