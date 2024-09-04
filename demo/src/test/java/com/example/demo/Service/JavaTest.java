package com.example.demo.Service;

import com.example.demo.Entity.UserEntity;
import com.example.demo.repository.userrepository;
import com.example.demo.service.userservice;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.convert.ValueConverter;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JavaTest {

        @Autowired
        private userrepository UserRepo;
        @Autowired
        private userservice UserService;

//        @Disabled
        @ParameterizedTest
//        @ValueSource(strings = {"ram","shyam","Vishnu"})
        @ArgumentsSource(UserArgumentsProvider.class)

        void contextLoads(UserEntity user) {
          assertTrue(UserService.savenewuser(user));
//            assertNotNull(UserRepo.findByuserName(name),"For "+name+" this fail");
        }

        @Disabled
        @ParameterizedTest
        @CsvSource({"1,1,2","2,2,4","5,6,13"})
        public void test(int a,int b,int c)
        {
            assertEquals(c,a+b);
        }

    }


