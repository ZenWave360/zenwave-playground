/** Inbound DTOs. */
@file:JvmName("PackageInfo")
package io.zenwave360.examples.kotlin.service.dtos

@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class Keep {
    // keeps this package even when it's empty,
    // allowing wildcard import "io.zenwave360.examples.kotlin.core.inbound.dtos.*;"
}
