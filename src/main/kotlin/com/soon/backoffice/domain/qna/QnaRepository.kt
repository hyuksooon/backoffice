package com.soon.backoffice.domain.qna

import org.springframework.data.jpa.repository.JpaRepository

interface QnaRepository :JpaRepository<Qna,Int> {
}