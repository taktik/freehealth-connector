# Base: OpenJDK 8 JRE under Alpine (AdoptOpenJDK build)
FROM adoptopenjdk/openjdk8:alpine-jre

# Environment variables
ENV FHC_PORT 8090
ENV FHC_HOME /opt/freehealth-connector
ENV FHC_JAR freehealth-connector.jar
ENV EHEALTH_RESOURCES_DIR /opt/ehealth

# Working directory
RUN mkdir -p $FHC_HOME
WORKDIR $FHC_HOME

# Dependencies
RUN apk add --no-cache bash curl && \
    rm -rf /var/cache/apk/* && \
    addgroup -S -g 777 fhc && \
    adduser -D -S -u 777 -h $FHC_HOME -s /sbin/nologin -G fhc fhc

# Copy jar file to working directory
COPY build/libs/*.jar $FHC_HOME/$FHC_JAR

# Create eHealth resource directory
RUN mkdir -p $EHEALTH_RESOURCES_DIR && \
    chown fhc:fhc $EHEALTH_RESOURCES_DIR

# Port to expose
EXPOSE $FHC_PORT

# User
USER fhc

# Entry point
ENTRYPOINT java $JAVA_OPTS \
           -server \
           -XX:+UnlockExperimentalVMOptions \
           -XX:+UseCGroupMemoryLimitForHeap \
           -Djava.security.egd=file:/dev/./urandom \
           -Dfile.encoding=UTF-8 \
           -DKEYSTORE_DIR=$EHEALTH_RESOURCES_DIR/ \
           -jar $FHC_HOME/$FHC_JAR
