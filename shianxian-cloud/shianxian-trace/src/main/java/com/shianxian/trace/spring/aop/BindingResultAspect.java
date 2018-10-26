package com.shianxian.trace.spring.aop;

import com.shianxian.common.utils.ResultUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;


/**
 * AOP切面
 */
@Aspect
@Component
public class BindingResultAspect {


    /**
     * 完成统一校验pojo参数
     * @param pjp
     * @param bindingResult
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.shianxian.*.controller.*.*(..)) && args(.., bindingResult)")
    public ResponseEntity<Object> doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        Object retVal = null;
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                retVal = allError.getDefaultMessage();
                break;
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg(retVal));
        } else {
            retVal = pjp.proceed();
            return ResponseEntity.ok(retVal);
        }
    }
}