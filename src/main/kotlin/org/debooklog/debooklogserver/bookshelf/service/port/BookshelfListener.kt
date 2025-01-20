package org.debooklog.debooklogserver.bookshelf.service.port

import org.debooklog.debooklogserver.core.member.service.MemberCreatedEvent

interface BookshelfListener {
    fun handle(event: MemberCreatedEvent)
}
