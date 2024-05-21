package org.debooklog.debooklogserver.member.controller.port

import org.debooklog.debooklogserver.member.domain.Member

interface MemberService {
    fun findAll(): List<Member>

    fun withdrawal(memberId: Long): Member
}
