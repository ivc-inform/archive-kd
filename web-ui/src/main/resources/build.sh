docker build --force-rm --no-cache --pull -f web-ui/target/docker/Dockerfile -t archive-kd:1.0.0.0 .
docker run -it -d -p8080:8080 --name archive-kd1000 archive-kd:1.0.0.0 /bin/bash
 docker cp web-ui/target/webapp/ jetty946_v20170531:/var/lib/jetty/webapps/archive-kd
docker exec -it archive-kd1000 bash
