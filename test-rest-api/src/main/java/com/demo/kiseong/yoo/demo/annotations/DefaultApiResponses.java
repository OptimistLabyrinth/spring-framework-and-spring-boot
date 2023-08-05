package com.demo.kiseong.yoo.demo.annotations;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
    @ApiResponse(
        responseCode = "400",
        description = "Bad Request",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DefaultApiResponses.ErrorFormat.class))
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DefaultApiResponses.ErrorFormat.class))
    ),
})
public @interface DefaultApiResponses {

    @RequiredArgsConstructor
    class ErrorFormat {
        @JsonProperty(required = true)
        private final String message;
    }

}
