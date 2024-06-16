package com.soon.backoffice.domain.qna

import com.soon.backoffice.application.qna.model.QnaCreateCommand
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "qna")
class Qna (
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    @Column(name = "qna_no")
    val no: Int = 0,

    @Column(name = "member_no")
    val memberNo:Int,

    @Column(name = "service_no")
    val serviceNo:Int,

    @Column(name = "title")
    val title: String,

    @Column(name = "body")
    val body: String,

    @Column(name = "password")
    val password: String?,
){
    @Column(name = "status")
    val status: String="wating"

    @Column(name = "created_at")
    val createdAt:LocalDateTime=LocalDateTime.now()

    companion object {
        fun toQna(command: QnaCreateCommand) = Qna(
            memberNo = command.memberNo,
            serviceNo = command.serviceNo,
            title = command.title,
            body = command.body,
            password = command.password
        )
    }
}