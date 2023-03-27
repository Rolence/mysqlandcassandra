package io.ara.remittance.security.config;


import feign.Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

@Configuration
@Slf4j
public class BeanConfig {
  @Bean
  public RestTemplate rest() {
    return new RestTemplate();
  }

  @Bean
  public Logger logger() {
    return LoggerFactory.getLogger("ServiceConstant.LOGGER_NAME");
  }
//  @Bean
//  public HandlerExceptionResolver sentryExceptionResolver() {
//    return new SentryExceptionResolver();
//  }
//
//  @Bean
//  public ServletContextInitializer sentryServletContextInitializer() {
//    return new SentryServletContextInitializer();
//  }

  @Bean
  public Client feignClient() {
    return new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
  }

  private SSLSocketFactory getSSLSocketFactory() {
    try {
      TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;

      SSLContext sslContext =
          SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
      return sslContext.getSocketFactory();
    } catch (Exception exception) {
      log.error(exception.getLocalizedMessage(), exception);
    }
    return null;
  }
}
