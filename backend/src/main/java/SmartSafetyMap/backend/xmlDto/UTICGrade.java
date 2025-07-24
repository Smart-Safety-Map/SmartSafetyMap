package SmartSafetyMap.backend.xmlDto;

import java.util.HashMap;
import java.util.Map;

public enum UTICGrade {

    정체("A0503", "정체"),
    서행("A0502" , "서행"),
    원할("A0501" , "원할"),
    A("A0401", "A등급"),   // 대규모(심각한) 지정체유발 및 위험이 매우 큰 상황
    B("A0402", "B등급"),   // 도로소통에 상당한 영향을 줄 수 있는 상황
    C("A0403", "C등급");   // 일반시민 제보(미확인), TBN한국교통방송(좌표 없음)

    private final String code;
    private final String description;

    private static final Map<String, UTICGrade> BY_CODE = new HashMap<>();

    static {
        for (UTICGrade grade : values()) {
            BY_CODE.put(grade.code, grade);
        }
    }

    UTICGrade(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() { return code; }
    public String getDescription() { return description; }

    // Map에서 바로 조회 (for문 없이 O(1))
    public static String getDescriptionByCode(String code) {
        UTICGrade grade = BY_CODE.get(code);
        return grade == null ? null : grade.getDescription();
    }

    public static UTICGrade fromCode(String code) {
        return BY_CODE.get(code);
    }


}
