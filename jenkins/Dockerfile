FROM jenkins/jenkins:lts

USER root

ADD --chown=jenkins:jenkins jenkins/jobs /var/jenkins_home/jobs/
ADD --chown=jenkins:jenkins jenkins/plugins /var/jenkins_home/plugins/
ADD --chown=jenkins:jenkins jenkins/users /var/jenkins_home/users/
ADD --chown=jenkins:jenkins jenkins/secrets /var/jenkins_home/secrets/
COPY --chown=jenkins:jenkins jenkins/hudson.tasks.Shell.xml /var/jenkins_home/hudson.tasks.Shell.xml
COPY --chown=jenkins:jenkins payloadCreate.json /var/jenkins_home/payloadCreate.json
RUN chmod 700 /var/jenkins_home/secrets

RUN mkdir /root/.ssh
RUN echo 'github.com ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAq2A7hRGmdnm9tUDbO9IDSwBK6TbQa+PXYPCPy6rbTrTtw7PHkccKrpp0yVhp5HdEIcKr6pLlVDBfOLX9QUsyCOV0wzfjIJNlGEYsdlLJizHhbn2mUjvSAHQqZETYP81eFzLQNnPHt4EVVUh7VfDESU84KezmD5QlWpXLmvU31/yMf+Se8xhHTvKSCZIFImWwoG6mbUoWf9nzpIoaSjB+weqqUUmpaaasXVal72J+UX2B+2RPW3RcT0eOzQgqlJL3RKrTJvdsjE3JEAvGq3lGHSZXy28G3skua2SmVi/w4yCE6gbODqnTWlg7+wC604ydGXA8VJiS5ap43JXiUFFAaQ==' >> /root/.ssh/known_hosts

RUN apt-get -y update

ARG MAVEN_VERSION=3.6.3
ARG USER_HOME_DIR="/root"
ARG SHA=c35a1803a6e70a126e80b2b3ae33eed961f83ed74d18fcd16909b2d44d7dada3203f1ffe726c17ef8dcca2dcaa9fca676987befeadc9b9f759967a8cb77181c0
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

RUN apt-get -yqq update
RUN apt-get -yqq install jq
RUN apt-get -yqq install redis-tools
RUN apt-get -yqq install vim

RUN curl -sSL https://get.docker.com/ | sh
RUN usermod -g docker jenkins
VOLUME /var/run/docker.sock
#ENTRYPOINT groupmod -g $(stat -c “%g” /var/run/docker.sock) docker && usermod -u $(stat -c “%u” /var/jenkins_home) jenkins && gosu jenkins /bin/tini – /usr/local/bin/jenkins.sh

COPY --from=lachlanevenson/k8s-kubectl:v1.15.5 /usr/local/bin/kubectl /usr/local/bin/kubectl
