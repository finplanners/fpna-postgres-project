package com.msciq.storage.common;

/**
 * <p>
 * Success code to fetch message from property. Property
 * file(application.property) present in resource folder.
 * </p>
 *
 * @author Vigneshkumar created on Jun 30, 2022
 *
 */
public enum SuccessCode {
	FREQUENCY_SAVE(2000), GET_FREQUENCY(2001),

	// Glucose Log
	GLUCOSE_LOG_SAVE(7001), GET_GLUCOSE_LOG_LIST(7002), GLUCOSE_LOG_DELETE(7003),

	// Screening Log
	SCREENING_LOG_SAVE(9001), GET_SCREENING_LOG(9002), SCREENING_LOG_DELETE(9003),

	// Patient
	PATIENT_SAVE(3001), GET_PATIENT(3002), PATIENT_DELETE(3003), PATIENT_VISIT_SAVE(3005),

	// Patient Tracker
	PATIENT_TRACKER_SAVE(4001), GET_PATIENT_TRACKER(4002), MENTAL_HEALTH_SAVE(4005), MENTAL_HEALTH_DETAILS(4010),

	PATIENT_TRACKER_DELETE(4004),

	BP_LOG_SAVE(8001), GET_BP_LOG_LIST(8002), GET_SYMPTOMS(5001), SYMPTOM_SAVE(5002),

	GET_MY_PATIENTS_LIST(4003), SEARCH_PATIENTS(4003), PATIENT_BASIC_DETAILS(4004), PATIENT_UPDATE(4005),

	PRESCRIPTION_SAVE(29001), PRESCRIPTION_GET(29002), PRESCRIPTION_HISTORY_GET(29003), PRESCRIPTION_DELETE(29004),
	FILL_PRESCRIPTION_GET(29005), FILL_PRESCRIPTION_UPDATE(29006), REFILL_PRESCRIPTION_GET(29007),
	TREATMENTPLAN_SAVE(4008), GET_TREATMENTPLAN(4009), TREATMENTPLAN_UPDATE(4007),

	ASSESSMENT_SAVE(1001), MEDICAL_REVIEW_SAVE(1002), GET_MEDICAL_REVIEW_SUMMARY(1003),
	GET_MEDICAL_REVIEW_LIST(1004), GET_MEDICAL_REVIEW_COUNT(1005),
	GET_MEDICAL_REVIEW_STATIC_DATA(1006), GET_STATIC_DATA(1007),

	PATIENT_LABTEST_SAVE(10001), REVIEW_PATIENT_LABTEST(10002), PATIENT_LABTEST_DELETE(10003),

	PATIENT_LABTEST_RESULT_SAVE(11001), GOT_PATIENT_LABTEST_RESULTS(11002), PATIENT_PREGNANCY_SAVE(12001),
	GET_PATIENT_PREGNANCY(12002), UPDATE_PATIENT_PREGNANCY(12003), GET_PATIENT_lABTEST_LIST(10004),

	// Patient Nutrition Lifestyle
	PATIENT_NUTRITION_LIFESTYLE_SAVE(2001), PATIENT_NUTRITION_LIFESTYLE_UPDATE(2002),
	PATIENT_NUTRITION_LIFESTYLE_LIST(2003), PATIENT_NUTRITION_LIFESTYLE_DELETE(2004),
	PATIENT_NUTRITION_LIFESTYLE_UPDATE_VIEW(2005), ASSESSMENT_BPLOG_SAVE(1011), ASSESSMENT_GLUCOSELOG_SAVE(1012),
	// Device Details
	DEVICE_DETAILS_SAVE(2006), CONFIRM_DIAGNOSIS_UPDATE(3003), PATIENT_NUTRITION_LIFESTYLE_UPDATE_ERROR(3004),
	
	COUNTRY_SAVE(19001),
	COUNTRY_UPDATE(19002),
	GET_COUNTRY_SUCCESS(19003),
	GET_COUNTRIES_SUCCESS(19004),
	
	CURRENCY_SAVE(19005),
	CURRENCY_UPDATE(19006),
	GET_CURRENCY_SUCCESS(19007),
	GET_CURRENCIES_SUCCESS(19008),
	
	DEPARTMENT_SAVE(19020),
	DEPARTMENT_UPDATE(19021),
	GET_DEPARTMENT_SUCCESS(19022),
	GET_DEPARTMENTS_SUCCESS(19023),

	
	FISCAL_DEPT_SAVE(19030),
	FISCAL_DEPT_UPDATE(19031),
	GET_FISCAL_DEPT_SUCCESS(19032),
	GET_FISCAL_DEPTS_SUCCESS(19033),
	
	
	GC_SAVE(19040),
	GC_UPDATE(19041),
	GET_GC_SUCCESS(19042),
	GET_GC_LIST_SUCCESS(19043),
	
	COMPANY_SAVE(19050),
	COMPANY_UPDATE(19051),
	GET_COMPANY_SUCCESS(19052),
	GET_COMPANY_LIST_SUCCESS(19053),
	
	BU_SAVE(19060),
	BU_UPDATE(19061),
	GET_BU_SUCCESS(19062),
	GET_BU_LIST_SUCCESS(19063),

	LOCATION_SAVE(19064),
	LOCATION_UPDATE(19065),
	GET_LOCATION_SUCCESS(19066),
	GET_LOCATIONS_SUCCESS(19067),

	USER_SAVE(19068),
	USER_UPDATE(19069),
	GET_USER_SUCCESS(19070),
	GET_USER_LIST_SUCCESS(19071),
	GET_BUDGET_CATEGORIES_SUCCESS(19080),
	SUCCESS(19077),

	FORECAST_SUBMIT_SUCCESS(19090);


	private int key;

	SuccessCode(int key) {
		this.key = key;
	}

	public int getKey() {
		return this.key;
	}
}
