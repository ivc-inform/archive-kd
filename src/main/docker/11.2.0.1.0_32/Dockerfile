# LICENSE CDDL 1.0 + GPL 2.0
#
# DOCKERFILE FOR ORACLE DB 11g
# --------------------------

#FROM oraclelinux:6.6
FROM ivcinform/centos-i386:centos6

ENV DEBIAN_FRONTEND "noninteractive"

########################################
# Install ORACLE 11g Enterprise Edition
########################################
ADD installation_files/p13390677_112040_LINUX_1of7.zip /tmp/install/p13390677_112040_LINUX_1of7.zip
ADD installation_files/p13390677_112040_LINUX_2of7.zip /tmp/install/p13390677_112040_LINUX_2of7.zip
ADD installation_files/oraInst.loc /etc/oraInst.loc
ADD installation_files/oracle.sh /usr/local/bin/oracle.sh
RUN chmod -R 775 /usr/local/bin/oracle.sh
#
RUN cd /tmp/install && unzip p13390677_112040_LINUX_1of7.zip && unzip p13390677_112040_LINUX_2of7.zip && rm *.zip
#
RUN echo "oracle:oracle" | chpasswd
#
ENV ORACLE_SID ORCL
ENV ORACLE_BASE /u01/app/oracle
ENV ORACLE_HOME $ORACLE_BASE/product/11.2/db_1
ENV LD_LIBRARY_PATH $ORACLE_HOME/lib/
ENV ORACLE_INVENTORY $ORACLE_BASE/oraInventory
ENV PATH $ORACLE_HOME/bin:$PATH
#
RUN mkdir -p $ORACLE_BASE && chown -R oracle:oinstall $ORACLE_BASE && chmod -R 775 $ORACLE_BASE
RUN mkdir -p $ORACLE_INVENTORY && chown -R oracle:oinstall $ORACLE_INVENTORY && chmod -R 775 $ORACLE_INVENTORY

ADD sysctl.conf /etc/sysctl.conf
RUN echo "oracle soft stack 10240" >> /etc/security/limits.conf

# To avoid error: sudo: sorry, you must have a tty to run sudo
RUN sed -i -e "s/Defaults    requiretty.*/ #Defaults    requiretty/g" /etc/sudoers
#
USER oracle
#
RUN cd /tmp/install/database/

RUN /tmp/install/database/runInstaller -silent -ignoreSysPrereqs -ignorePrereq -force \
    oracle.install.option=INSTALL_DB_SWONLY \
    UNIX_GROUP_NAME=oinstall \
    INVENTORY_LOCATION=$ORACLE_INVENTORY \
    ORACLE_HOME=$ORACLE_HOME \
    ORACLE_HOME_NAME="OraDb11g_Home1" \
    ORACLE_BASE=$ORACLE_BASE \
    oracle.install.db.InstallEdition=SE \
    oracle.install.db.isCustomInstall=false \
    oracle.install.db.DBA_GROUP=dba \
    oracle.install.db.OPER_GROUP=dba \
    DECLINE_SECURITY_UPDATES=true \
    -waitforcompletion
#
USER root
RUN rm -rf /tmp/install

# Run the root.sh script to execute the final steps after the installation
RUN $ORACLE_HOME/root.sh

EXPOSE 1521 1158

########################################
# Create a new database
########################################
# Copy all init scripts & files and create the ORCL instance

ADD database_files/oracle-.bashrc /home/oracle/.bashrc
ADD database_files/initORCL.ora $ORACLE_HOME/dbs/initORCL.ora
ADD database_files/createdb.sql $ORACLE_HOME/config/scripts/createdb.sql
ADD database_files/create.sh /tmp/create.sh
#
RUN mkdir -p $ORACLE_BASE/oradata && \
  chown -R oracle:oinstall $ORACLE_BASE/oradata && \
  chmod -R 775 $ORACLE_BASE/oradata && \
  mkdir -p $ORACLE_BASE/fast_recovery_area && \
  chown -R oracle:oinstall $ORACLE_BASE/fast_recovery_area && \
  chmod -R 775 $ORACLE_BASE/fast_recovery_area
#
RUN  /tmp/create.sh && rm /tmp/create.sh && echo "ORCL:$ORACLE_HOME:Y" >> /etc/oratab
#
VOLUME [ \
  "$ORACLE_BASE/admin/docker/adump", \
  "$ORACLE_BASE/diag", \
  "$ORACLE_BASE/oradata/docker", \
  "$ORACLE_HOME/cfgtoollogs", \
  "$ORACLE_HOME/log", \
  "$ORACLE_HOME/rdbms/log" \
]

########################################
# Start Oracle command
########################################
CMD oracle.sh
