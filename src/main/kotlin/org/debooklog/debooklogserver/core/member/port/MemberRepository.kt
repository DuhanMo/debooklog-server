package org.debooklog.debooklogserver.core.member.port

import org.debooklog.debooklogserver.core.member.model.Member

interface MemberRepository {
    fun save(member: Member): Member

    fun getById(id: Long): Member

    fun findAll(): List<Member>

    fun findByEmail(email: String): Member?
}
