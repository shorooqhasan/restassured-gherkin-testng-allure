package com.template.project.api.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.template.project.api.model.SampleModel;
import com.template.project.api.builder.SampleBuilder;

import static com.template.project.api.builder.SampleBuilder.addCustomer;
import static com.template.project.api.builder.SampleBuilder.updateCustomerDetails;

public class SamplePayload {

  public static String buildCustomerCreationPayload(String firstname, String lastname)
      throws JsonProcessingException {
    SampleModel sampleModel = addCustomer(firstname, lastname);
    return SampleBuilder.getJsonPayload(sampleModel);
  }

  public static String buildUpdateCustomerDetailsPayload(String firstname, String lastname)
      throws JsonProcessingException {
    SampleModel sampleModel = updateCustomerDetails(firstname, lastname);
    return SampleBuilder.getJsonPayload(sampleModel);
  }
}
