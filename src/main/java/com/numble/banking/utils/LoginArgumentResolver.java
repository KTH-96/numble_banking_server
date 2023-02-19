package com.numble.banking.utils;

import static com.numble.banking.common.Constant.LOGIN_MEMBER;
import static com.numble.banking.exception.ErrorCode.NOT_LOGIN_STATUS;

import com.numble.banking.exception.NotSessionException;
import com.numble.banking.member.dto.LoginMember;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
		boolean hasLoginMemberType = LoginMember.class.isAssignableFrom(parameter.getParameterType());
		return hasLoginMemberType && hasLoginAnnotation;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = Optional.ofNullable(request.getSession(false))
			.orElseThrow(() -> new NotSessionException(NOT_LOGIN_STATUS));

		return session.getAttribute(LOGIN_MEMBER);
	}
}
