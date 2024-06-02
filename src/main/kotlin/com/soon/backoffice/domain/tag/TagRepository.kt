package com.soon.backoffice.domain.tag

import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Int> {
    fun findAllByNoIsIn(tagNos: List<Int>):List<Tag>
}