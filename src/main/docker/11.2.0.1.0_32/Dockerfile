# LICENSE CDDL 1.0 + GPL 2.0
#
# DOCKERFILE FOR ORACLE DB 11g
# --------------------------

#FROM oraclelinux:6.6
FROM toopher/centos-i386:centos6

ENV DEBIAN_FRONTEND "noninteractive"
########################################
# Install OS needed packages
########################################
RUN yum -y update
RUN yum -y install wget \
                   sed \
                   unzip \
                   sudo \
                   mc \
                   glibc.i686 \
                   libaio nano \
                   compat-libcap1-1.10-1.i686 \
                   compat-libstdc++-33-3.2.3-69.el6.i686

RUN wget http://public-yum.oracle.com/public-yum-ol6.repo
RUN cd /etc/yum.repos.d

# changing the field enabled=0 to enabled=1 to reflect repositories that correspond to the machine's operating system release.
RUN sed "s|enabled=0|enabled=1|g" public-yum-ol6.repo
#
#RUN yum -y install oracle-rdbms-server-11gR2-preinstall
#
RUN yum clean all

########
