FROM ibmcom/db2:11.5.7.0

#Contains vulnerable log4j library for features not needed for SSL
RUN rm -rf /opt/ibm/db2/V11.5/federation
RUN rm -rf /opt/ibm/db2/V11.5/db2tss

# Copy custom startup script that will run when server starts
COPY custom/ /var/custom/
RUN chmod 777 /var/custom/*

# Copy custom enablement script that will run when image is built
COPY setup/ /var/setup/
RUN chmod 777 /var/setup/* && /var/setup/enable-ssl.sh

# Make sure kdb and stash are readable
RUN chmod 755 /certs/*

# Force static environment variables
ENV LICENSE=accept
ENV DB2INSTANCE=db2inst1
ENV DB2INST1_PASSWORD=password
ENV DBNAME=testdb
ENV AUTOCONFIG=false
ENV ARCHIVE_LOGS=false

# Expose new port
EXPOSE 50001

# Currently tagged in DockerHub as: kyleaure/db2-ssl:3.0
