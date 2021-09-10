package io.github.jackson.jq.parser;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import io.quarkus.arc.DefaultBean;
import net.thisptr.jackson.jq.BuiltinFunctionLoader;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.Versions;

@Dependent
public class JacksonJqProvider {

    @Produces
    @DefaultBean
    public Scope createJqScope() {
        final Scope scope = Scope.newEmptyScope();
        BuiltinFunctionLoader.getInstance().loadFunctions(Versions.JQ_1_6, scope);
        return scope;
    }
}
