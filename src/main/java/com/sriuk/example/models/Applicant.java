package com.sriuk.example.models;

import java.util.List;

import lombok.Data;

@Data
public class Applicant {
private String referenceNumber;
private List<ApplicantDetails> details;

}
