package io.github.jackson.jq.parser;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class ParserResourceTest {

    @Inject
    ObjectMapper objectMapper;

    @Test
    public void testParseEndpoint() throws JsonProcessingException {
        final ParserResource.Document doc = new ParserResource.Document();
        // join this array
        doc.setDocument(objectMapper.readTree("[\"1\", 2, 3, 4, 5, \"6\"]"));
        doc.setExpression("join(\"-\")");

        given().when()
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(doc))
                .post("/parser")
                .then()
                .statusCode(200)
                .body(is("[\"1-2-3-4-5-6\"]"));
    }
}
