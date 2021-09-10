package io.github.jackson.jq.parser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import net.thisptr.jackson.jq.BuiltinFunctionLoader;
import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.Versions;
import net.thisptr.jackson.jq.exception.JsonQueryException;
import net.thisptr.jackson.jq.quarkus.JacksonJqQuarkusScope;

@Path("/parser")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParserResource {

    @Inject
    JacksonJqQuarkusScope rootScope;

    public ParserResource() {

    }

    @POST
    public List<JsonNode> parse(Document document) throws JsonQueryException {
        final JsonQuery query = JsonQuery.compile(document.expression, Versions.JQ_1_6);
        List<JsonNode> out = new ArrayList<>();
        query.apply(this.rootScope, document.document, out::add);
        return out;
    }

    public static class Document {

        private String expression;
        private JsonNode document;

        public JsonNode getDocument() {
            return document;
        }

        public void setDocument(JsonNode document) {
            this.document = document;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }
    }
}
