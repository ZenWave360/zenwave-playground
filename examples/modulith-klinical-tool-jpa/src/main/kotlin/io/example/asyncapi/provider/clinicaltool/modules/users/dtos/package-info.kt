/** Inbound DTOs. */
@file:JvmName("PackageInfo")

package io.example.asyncapi.provider.clinicaltool.modules.users.dtos

@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class Keep {
    // keeps this package even when it's empty,
    // allowing wildcard import "io.zenwave360.example.clinicaltool.core.inbound.dtos.*;"
}
