package com.hulkhiretech.payments.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hulkhiretech.payments.pojo.TestRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final ObjectMapper objectMapper;

    @GetMapping
    public String myMethod(@RequestParam int val1,
                        @RequestParam int val2){
        log.info("Inside myMethod of TestController with val1:{} and val2:{}", val1, val2);
        return " Hello from Testcontroller of myMethod " + "\n val1: " + val1 + " val2: " + val2;
    }

    @GetMapping("/m2")
    public String myMethod2(){
        log.info("Inside myMethod2 of TestController");
        return ("Hello from TestController MyMethod2() !");
    }

    @PostMapping("/post/hello/{pv1}/{pv2}/789")
    public String myMethodPost(@RequestParam int val1,
                               @RequestParam int val2,
                               @PathVariable String pv1,
                               @PathVariable String pv2,
                               @RequestHeader("my-header") String myHeader,
                               @RequestBody TestRequest testRequest){
        log.info("Inside myMethod of Post Mapping TestController with val1:{}, val2:{}, PathVariable1, PathVariable2, my-header:{}, requestBody:{}"
                , val1, val2, pv1, pv2, myHeader, testRequest);

        //TestRequest testRequest = convertjsonreqtoObject(requestBody);
        log.info("Converted TestRequest object: {}", testRequest);

        int sum = testRequest.getNum1() + testRequest.getNum2();
        log.info("Sum of num1 and num2 from TestRequest: {}", sum);
        return ("Hello from TestController PostMyMethod() !" +
                "\n val1: " + val1 +
                " val2: " + val2 +
                "\n pv1: " + pv1 +
                "\n pv2: " + pv2 +
                "\n my-header: " + myHeader +
                "\n testRequest: " + testRequest +
                "\n sum of num1 and num2: " + sum);

    }

    /**
     * Create a method convertjsonreqtoObject() which accepts a JSON request body and converts it to a Java object using @RequestBody annotation. The method should log the received object and return a response indicating successful conversion.
     * input is jsonReQuestString and output is TestRequest object
     * use jackson library to convert json string to java object
     * @return
     */
    private TestRequest convertjsonreqtoObject(@RequestBody String jsonRequestString){
        log.info("Inside convertjsonreqtoObject of TestController with jsonRequestString: {}", jsonRequestString);
        // Use Jackson ObjectMapper to convert JSON string to TestRequest object

        TestRequest testRequest = null;
        try {
            testRequest = objectMapper.readValue(jsonRequestString, TestRequest.class);
            log.info("Successfully converted JSON to TestRequest object: {}", testRequest);
        } catch (JsonProcessingException e) {
            log.error("Error converting JSON to TestRequest object", e);
        }
        return testRequest;
    }


    @PostMapping("/m2")
    public String myMethodPost2(){
        log.info("Inside myMethod2 of Post Mapping TestController");
        return ("Hello from TestController PostMyMethod2() !");
    }

}
