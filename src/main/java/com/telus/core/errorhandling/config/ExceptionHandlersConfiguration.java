package com.telus.core.errorhandling.config;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telus.core.errorhandling.errorhandler.ErrorHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Import;

import com.telus.core.errorhandling.errorhandler.ExceptionHandler;
import com.telus.core.errorhandling.errorhandler.ExceptionProcessor;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@Import({DefaultControllerAdvice.class, DefaultExceptionHandlingFilter.class})
public class ExceptionHandlersConfiguration {
	@Bean("exceptionHandlers")
	public Map<Class<? extends Throwable>, ExceptionHandler> exceptionHandlers() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		Map<Class<? extends Throwable>, ExceptionHandler> handlers = new HashMap<>();
		List<? extends ExceptionHandler> loader = findErrorHandlers("com.telus");
		for (ExceptionHandler exceptionHandler : loader) {
			exceptionHandler.exceptionClasses().stream().forEach(clazz -> handlers.put(clazz, exceptionHandler));
		}
		return handlers;
	}

	private List<ExceptionHandler> findErrorHandlers(String scanPackage) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
		ArrayList<ExceptionHandler> errorHandlers = new ArrayList<>();
		for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
			Class<ExceptionHandler> clazz = (Class<ExceptionHandler>) Class.forName(beanDef.getBeanClassName());
			errorHandlers.add(clazz.getConstructor().newInstance());
		}
		return errorHandlers;
	}

	private ClassPathScanningCandidateComponentProvider createComponentScanner() {
		ClassPathScanningCandidateComponentProvider provider
				= new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(ErrorHandler.class));
		return provider;
	}

	@Bean
	public ExceptionProcessor exceptionProcessor(
		@Qualifier("exceptionHandlers") Map<Class<? extends Throwable>, ExceptionHandler> exceptionHandlers) {
		return new ExceptionProcessor(exceptionHandlers);
	}
}
