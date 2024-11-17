package com.example.Spring4.aspect;

import com.example.Spring4.entity.News;
import com.example.Spring4.entity.User;
import com.example.Spring4.exception.UnauthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthorizationAspect {
    @Around("@annotation(com.example.newsapi.aspect.AuthorizedForNews)")
    public Object checkAuthorization(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        News news = (News) joinPoint.getArgs()[0];

        if (!currentUser.getId().equals(news.getAuthor().getId())) {
            throw new UnauthorizedException("You are not authorized to perform this action.");
        }

        return joinPoint.proceed();
    }
}