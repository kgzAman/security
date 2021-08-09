package com.example.test.exeption;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(code = HttpStatus.NOT_FOUND)
@Data
public class NotFoundException extends RuntimeException {

}
