package com.petclinic;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.inject.Singleton;

import java.text.SimpleDateFormat;

@OpenAPIDefinition(
  info = @Info(
    title = "PetClinic",
    version = "0.1"
  )
)
public class Application {

  public static void main(String[] args) {
    Micronaut.run(Application.class, args);
  }

  @Singleton
  static class ObjectMapperBeanEventListener implements BeanCreatedEventListener<ObjectMapper> {

    @Override
    public ObjectMapper onCreated(BeanCreatedEvent<ObjectMapper> event) {
      ObjectMapper mapper = event.getBean();
      mapper.registerModule(new JavaTimeModule());
      mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
      mapper.setSerializationInclusion(Include.ALWAYS);
      return mapper;
    }
  }
}