package io.github.jackson.jq.parser;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

import io.quarkus.arc.DefaultBean;
import io.quarkus.runtime.StartupEvent;
import net.thisptr.jackson.jq.BuiltinFunctionLoader;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.Versions;

@ApplicationScoped
public class JacksonJqInitializer {

    private Scope scope;

    public void initialize(@Observes StartupEvent event) {
        if (scope == null) {
            this.scope = Scope.newEmptyScope();
            BuiltinFunctionLoader.getInstance().loadFunctions(Versions.JQ_1_6, this.scope);
        }
    }

    @Produces
    @DefaultBean
    public Scope createJqScope() {
        return scope;
    }
}
