package com.santimattius.basic.skeleton.di

import org.koin.core.qualifier.named
import org.koin.dsl.module


val dslModule = module {
    single(named("base_url")) { "https://www.example.com" }
}