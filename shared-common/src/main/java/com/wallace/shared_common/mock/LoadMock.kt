package com.wallace.shared_common.mock

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoadMock(
    val jsonFileName: String
)