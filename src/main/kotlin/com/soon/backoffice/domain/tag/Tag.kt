package com.soon.backoffice.domain.tag

import jakarta.persistence.*

@Entity
@Table(name = "tag")
class Tag (
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    @Column(name = "tag_no")
    val no: Int = 0,

    @Column(name = "title")
    val title: String,

    @Column(name = "description")
    val description: String,
)