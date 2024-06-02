package com.soon.backoffice.domain.tag

import jakarta.persistence.*

@Entity
@Table(name = "qna_tag")
class QnaTag (
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    @Column(name = "qna_tag_no")
    val no: Int = 0,

    @Column(name = "qna_no")
    val qnaNo: Int,

    @Column(name = "tag_no")
    val tagNo: Int,
){
    companion object {
        fun toQnaTag(qnaNo:Int,tagNo: Int)=QnaTag(
            qnaNo=qnaNo,
            tagNo = tagNo
        )
    }
}