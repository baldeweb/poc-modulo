package com.example.shared_common.data.model.mock

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoadMock(
    val jsonFileName: String
)