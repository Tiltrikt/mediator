package dev.tiltrikt.mediator.pattern;

import dev.tiltrikt.mediator.repository.ContextRepository;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ProxyConfiguration {

  @Bean
  @SneakyThrows
  @NotNull ProxyFactoryBean proxyFactoryBean() {
    Reflections reflections = new Reflections("dev.tiltrikt.mediator");
    Set<Class<? extends ContextRepository>> classSet = reflections.getSubTypesOf(ContextRepository.class);
    ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
    proxyFactoryBean.setProxyInterfaces(classSet.toArray(new Class[0]));
    proxyFactoryBean.setInterceptorNames("myMethodInterceptor");
    return proxyFactoryBean;
  }
}
