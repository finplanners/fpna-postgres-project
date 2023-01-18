package com.msciq.storage.common.msciq;

import lombok.Data;

@Data
public class CommonRequestDTO {

	private Long id;
	private Long patientTrackId;
	private Long patientVisitId;
	private Long tenantId;

}
