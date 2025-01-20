package org.debooklog.debooklogserver.core.bookshelf.port

import org.debooklog.debooklogserver.core.member.service.MemberCreatedEvent

interface BookshelfListener {
    fun handle(event: MemberCreatedEvent)
}
