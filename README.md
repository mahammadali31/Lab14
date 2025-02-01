lab14

implementation 'org.springframework.boot:spring-boot-starter'
implementation group: 'org.liquibase', name: 'liquibase-core', version: '4.28.0'
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.mysql:mysql-connector-j'

    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0'
    annotationProcessor 'org.projectlombok:lombok'
    implementation 'org.mapstruct:mapstruct:1.5.2.Final'
    implementation 'org.mapstruct:mapstruct-jdk8:1.5.2.Final'
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.2.Final'
    implementation group: 'org.springdoc', name: 'springdoc-openapi-starter-webmvc-ui', version: '2.0.3'
    implementation group: 'io.swagger', name: 'swagger-annotations', version: '2.0.0-rc2'


    implementation 'org.springframework.boot:spring-boot-starter-security'
    testImplementation 'org.springframework.security:spring-security-test'
    implementation 'org.springframework.security:spring-security-crypto'

    implementation 'org.springframework.boot:spring-boot-starter-security:3.1.1'


    implementation group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.5'
    implementation group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.5'
    implementation group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.5'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.mockito:mockito-core'
