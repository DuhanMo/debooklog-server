package org.debooklog.debooklogserver.member.service.port

import org.debooklog.debooklogserver.member.domain.Member

interface MemberRepository {
    fun save(member: Member): Member

    fun getById(id: Long): Member

    fun findAll(): List<Member>
}
