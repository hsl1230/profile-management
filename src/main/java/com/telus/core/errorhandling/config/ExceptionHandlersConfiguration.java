package com.telus.core.errorhandling.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.telus.core.errorhandling.errorhandler.ExceptionHandler;
import com.telus.core.errorhandling.errorhandler.ExceptionProcessor;

@Import({ DefaultControllerAdvice.class, DefaultExceptionHandlingFilter.class })
public class ExceptionHandlersConfiguration {
	@Bean("exceptionHandlers")
	public Map<Class<? extends Throwable>, ExceptionHandler> exceptionHandlers(ListableBeanFactory beanFactory) {
		Map<Class<? extends Throwable>, ExceptionHandler> handlers = new HashMap<>();
		Collection<ExceptionHandler> exceptionHandlers = beanFactory.getBeansOfType(ExceptionHandler.class).values();
		for (ExceptionHandler exceptionHandler : exceptionHandlers) {
			handlers.put(exceptionHandler.exceptionClass(), exceptionHandler);
		}
		return handlers;
	}

//	private List<ExceptionHandler> findErrorHandlers(String scanPackage) throws ClassNotFoundException,
//			NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//		ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
//		ArrayList<ExceptionHandler> errorHandlers = new ArrayList<>();
//		for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
//			Class<ExceptionHandler> clazz = (Class<ExceptionHandler>) Class.forName(beanDef.getBeanClassName());
//			errorHandlers.add(clazz.getConstructor().newInstance());
//		}
//		return errorHandlers;
//	}
//
//	private ClassPathScanningCandidateComponentProvider createComponentScanner() {
//		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
//		provider.addIncludeFilter(new AnnotationTypeFilter(ErrorHandler.class));
//		return provider;
//	}
//
	@Bean
	public ExceptionProcessor exceptionProcessor(
			@Qualifier("exceptionHandlers") Map<Class<? extends Throwable>, ExceptionHandler> exceptionHandlers) {
		return new ExceptionProcessor(exceptionHandlers);
	}
}
