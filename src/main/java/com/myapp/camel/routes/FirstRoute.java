package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FirstRoute extends RouteBuilder {
    public void configure() throws Exception {
        from("file:data/input?noop=true&recursive=true&initialDelay=5000&delay=2000&include=.*\\.txt$")
                .log("File ${header:CamelFileName} contents :\n${bodyAs(String)}")
                .to("file:data/output?noop=true");
    }
}
