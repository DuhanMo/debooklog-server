package org.debooklog.debooklogserver.member.controller.port

import org.debooklog.debooklogserver.member.domain.MemberCreateCommand

interface MemberService {
    fun create(command: MemberCreateCommand)
}
