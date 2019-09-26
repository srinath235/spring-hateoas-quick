package com.sriuk.example.models;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ApplicantDeserializer extends StdDeserializer<ApplicantDetails>{
	
	public ApplicantDeserializer() {
		this(null);
	}

	protected ApplicantDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ApplicantDetails deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode applicantNode = jp.getCodec().readTree(jp);
		ApplicantDetails applicantDetails = null;
		if(applicantNode.get("companyName")==null) {
			applicantDetails = new Individual();
			((Individual)applicantDetails).setFirstName(applicantNode.get("firstName").textValue());
			((Individual)applicantDetails).setLastName(applicantNode.get("lastName").textValue());
			((Individual)applicantDetails).setMiddleName(applicantNode.get("middleName").textValue());
		} else {
			applicantDetails = new Business();
			((Business)applicantDetails).setCompanyName(applicantNode.get("companyName").textValue());
		}
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(applicantNode.get("requirements").toString());
		applicantDetails.setRequirement(mapper.readValue(applicantNode.get("requirements").toString().getBytes(), new TypeReference<List<Requirement>>(){}));
		return applicantDetails;
	}

}
