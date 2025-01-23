package org.debooklog.core.member.service

data class MemberCreateCommand(
    val name: String,
    val email: String,
    val password: String,
)
