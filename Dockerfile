FROM maven:3.6.1-jdk-8-alpine

RUN apt-get update && apt-get install -y libltdl7 && rm -rf /var/lib/apt/lists/*
