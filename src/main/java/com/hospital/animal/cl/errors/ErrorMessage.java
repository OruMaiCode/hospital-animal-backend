package com.hospital.animal.cl.errors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@Builder
public class ErrorMessage {
    private String code;
    private List<Map<String,String>> messages;
    public static final String INTERRUPT_MESSAGE="Interrupted!";
    public String formatMessage(BindingResult result){
        List<Map<String,String>> errors= result.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return error;
                }).toList();
        ErrorMessage errorMessage =ErrorMessage.builder().code("01").messages(errors).build();
      return errorMessageToJsonString(errorMessage);
    }

    private String errorMessageToJsonString(ErrorMessage errorMessage){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try{
            jsonString=mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }
}
