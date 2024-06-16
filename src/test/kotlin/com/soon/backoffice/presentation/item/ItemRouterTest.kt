package com.soon.backoffice.presentation.item

import org.junit.jupiter.api.extension.ExtendWith
import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters.Companion.builder
import com.ninjasquad.springmockk.MockkBean
import com.soon.backoffice.application.item.ItemQueryService
import com.soon.backoffice.application.item.model.ItemResponse
import com.soon.backoffice.presentation.handler.item.ItemHandler
import com.soon.backoffice.presentation.router.ItemRouter
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(RestDocumentationExtension::class, SpringExtension::class, MockKExtension::class)
@ContextConfiguration(classes = [ItemRouter::class, ItemHandler::class])
@AutoConfigureRestDocs
@AutoConfigureWebFlux
@WebFluxTest
class ItemRouterTest(private val context: ApplicationContext) {

    private lateinit var webTestClient: WebTestClient


    @MockkBean
    private lateinit var itemQueryService: ItemQueryService


    @BeforeEach
    fun setUp(restDocumentation: RestDocumentationContextProvider) {
        webTestClient=WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .filter(WebTestClientRestDocumentation.documentationConfiguration(restDocumentation))
                .build()


    }
    @Test
    fun `아이템 단건 조회 api`() {
        val itemNo = 1

        val response= ItemResponse(
                itemNo=1,
                serviceNo = 1,
                title = "title",
                description = "description",
                thumbnail="thumbnail",
        )

        coEvery { itemQueryService.getItem(any()) } returns response
        webTestClient.get()
                .uri("/common/backoffice/item?itemNo={itemNo}", itemNo)
                .exchange()
                .expectStatus().isOk
                .expectBody().consumeWith(
                        document(
                                "test",
                                resource(
                                        builder()
                                                .tag("item")
                                                .description("아이템 단건 조회")
                                                .queryParameters(
                                                        parameterWithName("itemNo").description("아이템 시퀀스")
                                                )
                                                .responseFields(
                                                        fieldWithPath("itemNo").type(JsonFieldType.NUMBER).description("아이템 시퀀스").optional(),
                                                        fieldWithPath("serviceNo").type(JsonFieldType.NUMBER).description("서비스 시퀀스"),
                                                        fieldWithPath("title").type(JsonFieldType.STRING).description("아이템 이름"),
                                                        fieldWithPath("description").type(JsonFieldType.STRING).description("아이템 설명"),
                                                        fieldWithPath("thumbnail").type(JsonFieldType.STRING).description("아이템 썸네"),
                                                ).build()
                                )
                        )
                )
    }
}