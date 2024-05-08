package org.debooklog.debooklogserver.bookshelf.service.port

import org.debooklog.debooklogserver.member.domain.MemberCreatedEvent

interface BookshelfListener {
    fun handle(event: MemberCreatedEvent)
}
