package com.sriuk.example.models;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonDeserialize(using = ApplicantDeserializer.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDetails {
private List<Requirement> requirements;
}
