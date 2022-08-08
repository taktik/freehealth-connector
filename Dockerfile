################
# Dockerfile
################
# --------------------------
# Stage 1
# npm install from full image
# --------------------------
FROM amazoncorretto:8-alpine-jre

RUN apk add --no-cache aws-cli

COPY build build
COPY --chmod=0755 startFhc.sh ./startFhc.sh

WORKDIR .

ARG PORT=8090
ENV PORT $PORT
ENV FHC_STAGE $FHC_STAGE

# Port to expose
EXPOSE $PORT

RUN aws --version

# Start app
ENTRYPOINT ["sh","/startFhc.sh"]


