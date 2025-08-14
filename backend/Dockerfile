FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# 빌드된 jar 파일을 컨테이너에 backend.jar로 복사
COPY build/libs/backend-0.0.1-SNAPSHOT.jar backend.jar

# 복사한 backend.jar를 실행하도록 CMD 수정
CMD ["java", "-jar", "backend.jar"]
