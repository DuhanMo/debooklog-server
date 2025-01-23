package org.debooklog.core.bookshelf.port

import org.debooklog.core.member.service.MemberCreatedEvent

interface BookshelfListener {
    fun handle(event: MemberCreatedEvent)
}
