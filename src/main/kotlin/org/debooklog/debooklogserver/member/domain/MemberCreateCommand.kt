package org.debooklog.debooklogserver.member.domain

data class MemberCreateCommand(
    val name: String,
    val email: String,
    val password: String,
)
