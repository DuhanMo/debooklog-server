package org.debooklog.debooklogserver.member.controller.dto

import org.debooklog.debooklogserver.member.domain.MemberCreateCommand

data class MemberCreateRequest(
    val name: String,
    val email: String,
    val password: String,
) {
    fun toCommand(): MemberCreateCommand {
        return MemberCreateCommand(name, email, password)
    }
}
