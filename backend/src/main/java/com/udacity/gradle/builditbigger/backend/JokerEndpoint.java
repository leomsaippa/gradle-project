package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.lsaippa.java_joker.JavaJoker;


/** An endpoint class we are exposing */
@Api(
        name = "jokerApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class JokerEndpoint {

    JavaJoker javaJoker = new JavaJoker();

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(
            name = "tellJoke",
            httpMethod = ApiMethod.HttpMethod.GET)
    public MyBean tellJoke() {
        MyBean response = new MyBean();
        response.setData(javaJoker.getJoker());

        return response;
    }

}
