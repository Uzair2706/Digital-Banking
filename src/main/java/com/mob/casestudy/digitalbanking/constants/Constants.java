package com.mob.casestudy.digitalbanking.constants;

public final class Constants {

    private Constants(){}
    public static final String CAPTION_NOT_VALID = "CSI-CREATE-FIE-002";
    public static final String CAPTION_SIZE_NOT_VALID_DESCRIPTION = "Size must be minimum of 3 characters";
    public static final String CAPTION_NOT_NULL_DESCRIPTION = "Caption should not be null";
    public static final String CAPTION_NOT_EMPTY_DESCRIPTION = "Caption should not be empty";
    public static final String FIELD_NOT_FOUND_DESCRIPTION = "Mandatory fields should be validated";
    public static final String FIELD_NOT_FOUND_CODE = "CSI-CREATE-FIE-001";
    public static final String SECURITY_QUESTION_NOT_FOUND_CODE = "CSQ-GET-FIE-001";
    public static final String SECURITY_IMAGE_NOT_FOUND_CODE = "CSI-CREATE-FIE-003";
    public static final String USER_NOT_VALID = "CSQ-GET-FIE-002";
    public static final String CUSTOMER_NOT_VALID = "CSI-GET-FIE-005";
    public static final String CUSTOMER_WITH_INVALID_CODE = "OTP-INI-FIE-001";
    public static final String TEMPLATE_ID_NOT_VALID = "OTP-INI-FIE-002";
    public static final String TEMPLATE_ID_NOT_VALID_DESCRIPTION = "The template id is not valid";
    public static final String DEFAULT_OTP = "Your OTP is ";
    public static final String REG_OTP = "OTP for the Registration is ";
    public static final String LOGIN_OTP = "OTP for the Login is ";

    public static final String CUS_MAND_VALID_CODE = "CUS-CREATE-FIE-001";
    public static final String PHONE_NO_INVALID_CODE = "CUS-CREATE-FIE-002";
    public static final String EMAIL_INVALID_CODE = "CUS-CREATE-FIE-003";
    public static final String PREF_LANG_CODE = "CUS-CREATE-FIE-004";
    public static final String USERNAME_INVALID_CODE = "CUS-CREATE-FIE-005";
    public static final String NON_UNIQUE_USERNAME_CODE = "CUS-CREATE-FIE-006";

    public static final String CUS_MAND_VALID_DESCRIPTION = "Mandatory fields are not validated";
    public static final String PHONE_NO_INVALID_DESCRIPTION = "Phone number should be of 10 digits";
    public static final String EMAIL_INVALID_DESCRIPTION = "Email is not in the proper format";
    public static final String PREF_LANG_DESCRIPTION = "Preferred Language is not in proper format,Try using EN,FR,DE";
    public static final String USERNAME_INVALID_DESCRIPTION = "Invalid, Should use the stated format for username";
    public static final String NON_UNIQUE_USERNAME_DESCRIPTION = "This username is already in use, chose a unique one";

    public static final String UPD_MAND_CODE = "CUS-UPDATE-FIE-001";
    public static final String UPD_MAND_DESCRIPTION = "All the Mandatory Fields should be validated";
    public static final String UPD_PHN_CODE = "CUS-UPDATE-FIE-002";
    public static final String UPD_PHN_DESCRIPTION = "The number to be updated is not in the proper format";
    public static final String UPD_EML_CODE = "CUS-UPDATE-FIE-003";
    public static final String UPD_EML_DESCRIPTION = "Updating an Email requires a proper format ";
    public static final String UPD_PREF_CODE = "CUS-UPDATE-FIE-004";
    public static final String UPD_PREF_DESCRIPTION = "Preferred Language is not in proper format,Try using EN,FR,DE";
    public static final String UPD_CUS_CODE = "CUS-UPDATE-NFD-001";

}
