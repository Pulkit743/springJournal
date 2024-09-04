package com.example.demo.Service;

import com.example.demo.Entity.UserEntity;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(UserEntity.builder().userName("Pulkit12").password("1234").build()),
                Arguments.of(UserEntity.builder().userName("Mohan").password("1234").build())
        );
    }
}
