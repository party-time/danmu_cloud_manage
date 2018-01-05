<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="30 seconds">
    <property name="APP_NAME" value="${projectName}"/>
    <property name="INFO_TYPE" value="info"/>
    <property name="ERROR_TYPE" value="error"/>
    <property name="LOG_PATH" value="/home/workspace/log/java"/>
    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d [%thread] %-5level %40logger{40} - %msg%n</pattern>
        </encoder>
    </appender>
    <appender name="INFO" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
		<file>${r'${LOG_PATH}'}/${r'${APP_NAME}'}/${r'${APP_NAME}'}-${r'${INFO_TYPE}'}.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<FileNamePattern>${r'${LOG_PATH}'}/${r'${APP_NAME}'}/%d{yyyy-MM-dd}/${r'${INFO_TYPE}'}/${r'${APP_NAME}'}-${r'${INFO_TYPE}'}-%d{yyyy-MM-dd_HH}-%i.log</FileNamePattern>
			<TimeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <MaxFileSize>50MB</MaxFileSize>
            </TimeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <layout class="ch.qos.logback.classic.PatternLayout">
            <Pattern>%d [%thread] %-5level %40logger{40} - %msg%n</Pattern>
        </layout>
    </appender>

    <appender name="ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 当前Log文件名 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>DENY</onMatch>
            <onMismatch>ACCEPT</onMismatch>
        </filter>
		<file>${r'${LOG_PATH}'}/${r'${APP_NAME}'}/${r'${APP_NAME}'}-${r'${ERROR_TYPE}'}.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${r'${LOG_PATH}'}/${r'${APP_NAME}'}/%d{yyyy-MM-dd}/${r'${INFO_TYPE}'}/${r'${APP_NAME}'}-${r'${INFO_TYPE}'}-%d{yyyy-MM-dd_HH}-%i.log</FileNamePattern>
            <TimeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <MaxFileSize>50MB</MaxFileSize>
            </TimeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <layout class="ch.qos.logback.classic.PatternLayout">
            <Pattern>%d [%thread] %-5level %40logger{40} - %msg%n</Pattern>
        </layout>
    </appender>
    <root level="info">
        <appender-ref ref="Console" />
        <appender-ref ref="INFO" />
        <appender-ref ref="ERROR" />
    </root>
</configuration>